package farias.paulino.kauan.SistemaAgendamento.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import farias.paulino.kauan.SistemaAgendamento.model.Horario;
import farias.paulino.kauan.SistemaAgendamento.model.HorarioCancelado;

public interface IHorarioCanceladoRepository extends JpaRepository<HorarioCancelado, Integer>{
	@Query("""
		    SELECT h
		    FROM Horario h
		    LEFT JOIN HorarioCancelado hc
		        ON h.id = hc.horario.id
		        AND hc.data = :data
		        AND hc.funcionario.id = :funcionarioId
		    WHERE hc.id IS NULL
		    """)
		List<Horario> listaHorariosDisponiveis(@Param("data") LocalDate data, @Param("funcionarioId") int funcionarioId);
}
