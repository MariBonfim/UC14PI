package com.GrupoMasterExpress.GrupoMasterExpress.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.GrupoMasterExpress.GrupoMasterExpress.models.Servico;

public interface ServicoRepository extends CrudRepository<Servico, Long> {
	Servico findByCodigo(long codigo);

	List<Servico> findByNome(String nome);

	
	@Query(value = "select u from Servico u where u.nome like %?1%")
	List<Servico> findByNomesServico(String servico);
}
