package com.GrupoMasterExpress.GrupoMasterExpress.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.GrupoMasterExpress.GrupoMasterExpress.models.Prospect;
import com.GrupoMasterExpress.GrupoMasterExpress.models.Funcionario;

public interface ProspectRepository extends CrudRepository<Prospect, Long> {

	Iterable<Prospect> findByFuncionario(Funcionario funcionario);

	Prospect findByCNPJ(String cnpj);
	
	Prospect findById(long id);
	List<Prospect> findByNome(String nome);

	
	@Query(value = "select u from Prospect u where u.nome like %?1%")
	List<Prospect> findByNomesProspect(String nome);

}
