package com.GrupoMasterExpress.GrupoMasterExpress.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.GrupoMasterExpress.GrupoMasterExpress.models.Cliente;
import com.GrupoMasterExpress.GrupoMasterExpress.models.Servico;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

	Iterable<Cliente> findByServico(Servico servico);

	Cliente findByCNPJ(String cnpj);

	Cliente findById(long id);

		@Query(value = "select u from Cliente u where u.nomeCliente like %?1%")
	List<Cliente> findByNomesClientes(String nomeCliente);
}
