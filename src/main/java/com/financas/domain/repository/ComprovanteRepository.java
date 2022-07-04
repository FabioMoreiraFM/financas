package com.financas.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.financas.domain.model.Comprovante;

@Repository
public interface ComprovanteRepository extends JpaRepository<Comprovante, Long>{

	@Query("select c from Comprovante c join c.parcelaDespesa p "
			+ "where p.id = :parcelaDespesaId")
	Optional<Comprovante> findComprovanteByParcelaDespesaId(Long parcelaDespesaId);
	
	void deleteByNome(String nome);
}
