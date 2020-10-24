// Classe controller, é responsável por receber as requisições externas, http e responder.

package com.pulse.osworks.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

/*
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.pulse.osworks.domain.model.Cliente;
import com.pulse.osworks.domain.repository.ClienteRepository;
import com.pulse.osworks.domain.service.CadastroClienteService;


//Definindo a classe como um rest controller, assim o spring consegue identificar as classes controladoras, 
//assim ele pode configurar ela para que os GetMappings possam funcionar
@RestController
@RequestMapping("/api")
public class ClienteController {
	
	/*EntityManager -> Interface do jakata persistence utilizada para fazer operações nas entidades como: buscar, deletar, inserir e atualizar.
	  Precisa ser Instanciado e injetado aqui na variável "manager".
	  A notação @PersistenceContext do jakata persistence proporciona que o EnitityManager seja instanciado e injetado no manager de forma automática.
	
	@PersistenceContext 
	private EntityManager manager;*/
	
	  /*
	  Instanciando um clienteRepository
	  O spring faz o instanciamento e injeta no "clienteRepository"
	  @Aotowired, anotação que sinaliza que quer o instanciamento da interface ClienteRepository e que ela seja injetada em clienteRepository.
	  */
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CadastroClienteService cadastroCliente;
	
	//@GetMapping, notação do springframework
	@GetMapping("/clientes")
	
	// Método que irá receber as requisições externas
	public List<Cliente> listar() {
		
		// Lista nomes contendo "Vic"
		//return clienteRepository.findByNomeContaining("Vic");
		
		// Lista nomes "Leonardo Viana"
		//return clienteRepository.findByNome("Leonardo Viana");
		
		// Lista todos os nomes
		return clienteRepository.findAll();// Método findAll, retorna uma lista de clientes
		
		   /* 
		   Utilizando JPQL ("from Cliente), é uma linguagem de consulta.
		   Utilizar o Cliente.class para tipar o objeto, quando eu der um getResultList, ele retornará a Lista do meu objeto tipado.
		   ---
		   return manager.createQuery("from Cliente", Cliente.class)
				.getResultList();
		   */
	}
	
	@GetMapping("/clientes/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId){
		Optional <Cliente> cliente = clienteRepository.findById(clienteId);
		
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
	// Enviando status 404
		return ResponseEntity.notFound().build();
	}
	
	//@RequestBody transforma o que vem no corpo do json em objeto cliente
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return cadastroCliente.salvar(cliente);
	}
	
	@PutMapping("/clientes/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, 
			@RequestBody Cliente cliente){
		
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId);
		cliente = cadastroCliente.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/clientes/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();	
	}
		//ok código 200
		//notFound codigo 404
		//noContent codigo 204
		cadastroCliente.excluir(clienteId);
		return ResponseEntity.noContent().build();
	}
}
