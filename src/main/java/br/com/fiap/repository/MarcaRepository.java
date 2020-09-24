package br.com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.model.MarcaModel;

@Repository
public interface MarcaRepository extends JpaRepository<MarcaModel, Long>{

}
