package br.com.fiap.business;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.fiap.exception.ResponseBusinessException;
import br.com.fiap.model.CategoriaModel;
import br.com.fiap.model.ProdutoModel;
import br.com.fiap.repository.CategoriaRepository;

@Component
public class ProdutoBusiness {
	
	@Value("${rest.exception.business.contains-teste}")
	private String containsTeste;
	
	@Autowired
	public CategoriaRepository categoriaRepository;
	
	public ProdutoModel applyBusiness(ProdutoModel produto) throws ResponseBusinessException {
		
		String sku = changeSkuToUpperCase(produto.getSku());
		produto.setSku(sku);

		BigDecimal preco = addValueToPreco(produto.getPreco(), produto.getCategoria());
		produto.setPreco(preco);
		
		verifyNomeProduto(produto.getNome());
		
		return produto;
	}
	
	private String changeSkuToUpperCase(String sku) {
		return sku.toUpperCase();
	}
	
	private BigDecimal addValueToPreco(BigDecimal preco, CategoriaModel categoria) {
		
		CategoriaModel categoriaModel = categoriaRepository.findById(categoria.getIdCategoria()).get();
		
		if ("Smartphone".equals(categoriaModel.getNomeCategoria())) {
			preco = preco.add(new BigDecimal(10));
		} else if ("Notebook".equals(categoriaModel.getNomeCategoria())) {
			preco = preco.add(new BigDecimal(20));
		}
		
		return preco;
	}
	
	private void verifyNomeProduto(String nome) throws ResponseBusinessException {
		
		String nomeProduto = nome.toUpperCase();
		
		if (nomeProduto.contains("TESTE")) {
			throw new ResponseBusinessException(containsTeste);
		}
	}

}
