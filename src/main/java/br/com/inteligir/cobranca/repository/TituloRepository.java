package br.com.inteligir.cobranca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

import br.com.inteligir.cobranca.model.Titulo;

public interface TituloRepository extends JpaRepository<Titulo, Long>{
    
}
