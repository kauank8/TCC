package farias.paulino.kauan.SistemaAgendamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import farias.paulino.kauan.SistemaAgendamento.model.Agendamento;
import farias.paulino.kauan.SistemaAgendamento.model.Ficha;

public interface IFichaRepository extends JpaRepository<Ficha, Integer>{
	@Query(value="Select * from Ficha where cliente_id = :cliente and agendamento_id = :agendamento", nativeQuery=true)
	Ficha buscarFichaPorAgendamento(@Param("cliente") int idCliente, @Param("agendamento") int idAgendamento);
}
