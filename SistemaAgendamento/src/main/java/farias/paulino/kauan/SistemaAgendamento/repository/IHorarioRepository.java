package farias.paulino.kauan.SistemaAgendamento.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import farias.paulino.kauan.SistemaAgendamento.model.Horario;

public interface IHorarioRepository extends JpaRepository<Horario, Integer>{
	@Query(value="SELECT h.*\r\n"
			+ "FROM Horario h\r\n"
			+ "LEFT JOIN Agendamento a ON CAST(h.hora as varchar(5)) = a.horario AND a.data = :data AND a.funcionario_id = :funcionarioId\r\n"
			+ "LEFT JOIN HorarioCancelado hc ON h.id = hc.horario_id AND hc.data = :data AND hc.funcionario_id = :funcionarioId\r\n"
			+ "WHERE a.id IS NULL AND hc.id IS NULL", nativeQuery=true)
			List<Horario> listaHorariosDisponiveis(@Param("data") LocalDate data, @Param("funcionarioId") int funcionarioid);
}
