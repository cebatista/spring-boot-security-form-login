package com.cebatista.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthController {

	@GetMapping("/entrar" )
    public String entrar() {
        return "entrar";
    }
	

	@GetMapping("/" )
    public String inicio() {
        return "inicio";
    }
	
	@GetMapping("/projetos" )
    public String projetos() {
        return "projetos";
    }
	
	@GetMapping("/relatorio-custos" )
    public String relatorioCustos() {
        return "relatorio-custos";
    }

	@GetMapping("/relatorio-equipe" )
    public String relatorioEquipe() {
        return "relatorio-equipe";
    }
}
