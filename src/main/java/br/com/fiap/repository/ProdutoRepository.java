package br.com.fiap.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.model.ProdutoModel;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
	
	@Query("SELECT p from ProdutoModel p WHERE p.preco > ?1 AND  p.categoria.nomeCategoria = ?2")
	List<ProdutoModel> findExpensiveProductsByCategory(BigDecimal preco, String nomeCategoria);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE tb_produto SET nome = ?1, sku = ?2 WHERE id = ?3", nativeQuery = true)
	void updateProductNameAndSku(String nome, String sku, long id);
	
//	@Transactional
//	@Modifying
//	@Query("DELETE ProdutoModel WHERE id = ?1")
//	void deleteById(long id);
}
