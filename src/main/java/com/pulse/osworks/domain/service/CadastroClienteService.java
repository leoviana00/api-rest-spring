package com.pulse.osworks.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pulse.osworks.domain.exception.NegocioException;
import com.pulse.osworks.domain.model.Cliente;
import com.pulse.osworks.domain.repository.ClienteRepository;


// Tornando a classe um componente do spring
// Anotando a classe com @Service
/* 
 * Quando a classe é anotada ela se torna um componente do spring,
   o spring vai instanciar um objeto dessa classe, 
   e esse objeto vai se tornar disponível para ser injetado
   em qualquer componente do spring, como por exemplo o clienteController.
*/
@Service
public class CadastroClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente salvar(Cliente cliente) {
		Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
		
		if (clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException("Já existe um cliente cadastrado com esse email.");
		}
		return clienteRepository.save(cliente);
	}
	
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
}
