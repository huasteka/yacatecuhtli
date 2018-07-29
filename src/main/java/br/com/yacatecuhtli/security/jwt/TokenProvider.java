package br.com.yacatecuhtli.security.jwt;

import br.com.yacatecuhtli.core.service.DateService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class TokenProvider {

    private final Logger log = LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "auth";

    @Value("${application.jwt.secret-key}")
    private String secretKey;

    @Value("${application.jwt.token-validity-in-seconds}")
    private long tokenValidityInSeconds;

    @Value("${application.jwt.token-validity-in-seconds-for-remember-me}")
    private long tokenValidityInSecondsForRememberMe;

    @Autowired
    private DateService dateService;

    public String createToken(Authentication authentication, Boolean rememberMe) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Date now = dateService.getNow();
        Date validity;
        if (rememberMe) {
            validity = dateService.addSecondsToDate(now, this.tokenValidityInSecondsForRememberMe);
        } else {
            validity = dateService.addSecondsToDate(now, this.tokenValidityInSeconds);
        }

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(SignatureAlgorithm.HS512, this.getSecretKey())
                .setExpiration(validity)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(this.getSecretKey())
                .parseClaimsJws(token)
                .getBody();

        Object authoritiesList = claims.get(AUTHORITIES_KEY);
        Collection<? extends GrantedAuthority> authorities = Optional.ofNullable(authoritiesList)
                .map(auth -> Arrays.asList(auth.toString().split(",")))
                .orElseGet(ArrayList::new)
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        User principal = new User(claims.get("user_id", String.class), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(this.getSecretKey()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.info("Invalid JWT signature: " + e.getMessage());
            return false;
        }
    }

    public byte[] getSecretKey() {
        return secretKey.getBytes();
    }
}
