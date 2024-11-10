package farias.paulino.kauan.SistemaAgendamento.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import farias.paulino.kauan.SistemaAgendamento.model.Agendamento;

public interface IAgendamentoRepository extends JpaRepository<Agendamento, Integer>{
	@Query(value="Select * from Agendamento where cliente_id = :id", nativeQuery=true)
	List<Agendamento> listarAgendamentoFicha(@Param("id") int id);
	
	@Query(value="Select * from Agendamento where data = :data and cliente_id = :cliente", nativeQuery=true)
	List<Agendamento> listarAgendamentoCliente(@Param("data") LocalDate data, @Param("cliente") int id);
}
