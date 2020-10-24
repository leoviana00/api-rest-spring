package com.pulse.osworks.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Definindo a classe como um rest controller, assim o spring consegue identificar as classes controladoras, 
// assim ele pode configurar ela para que os GetMappings possam funcionar
@RestController
public class StatusController {
	
	//@GetMapping, anotação do springframework
	@GetMapping("/api/actuator/health")
	// Método que irá receber as requisições externas
	public String listar() {
		return "{status:ok}";
		
	}
}