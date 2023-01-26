package br.com.inteligir.cobranca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CobrancaController {

    @GetMapping("/")
    public String paginaInicio(){


        return "index";
    }


    
}
