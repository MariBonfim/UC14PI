package com.GrupoMasterExpress.GrupoMasterExpress.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.GrupoMasterExpress.GrupoMasterExpress.models.Fornecedor;

public interface FornecedorRepository extends CrudRepository<Fornecedor, Long> {
	Fornecedor findByCNPJ(String cnpj);

	List<Fornecedor> findByNome(String nome);

	
	@Query(value = "select u from Fornecedor u where u.nome like %?1%")
	List<Fornecedor> findByNomesFornecedor(String nome);
}
