package br.com.yacatecuhtli.core;

import java.time.Instant;
import java.util.Date;

public class SystemTime {

    public static final SystemTime INSTANCE = new SystemTime();

    public Date now() {
        return Date.from(Instant.now());
    }

}
