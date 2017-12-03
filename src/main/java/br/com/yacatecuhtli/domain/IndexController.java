package br.com.yacatecuhtli.domain;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping(value = {"/", "/error"})
    public String index() {
        return "Alive and kicking!";
    }

}
