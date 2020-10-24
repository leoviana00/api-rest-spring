package com.pulse.osworks.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pulse.osworks.domain.model.Cliente;


   /* 
   A classe vai herdar uma interface do spring data jpa. "extendes ..."
   Tipando com o tipo da Entidade: que é Cliente e o tipo do identificador que no caso é o ID: que é Long.
   Definindo a interface como um componente do spring .
   Componente do spring ele é gerenciado, é instanciado pelo proprio framework do proprio spring.
   Pode ser utilizado em outras classes facilmente atraves das injecoes de dependencias.
   @Repository => annotation do spring que defini a classe como componente do spring.
   */

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

		List<Cliente> findByNome(String nome);
		List<Cliente> findByNomeContaining(String nome);
		Cliente findByEmail(String Email);
}
