package farias.paulino.kauan.SistemaAgendamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import farias.paulino.kauan.SistemaAgendamento.model.Servico;

public interface IServicoRepository extends JpaRepository<Servico, Integer>{
	@Query(value = "SELECT * FROM Servico WHERE titulo LIKE %:titulo%", nativeQuery = true)
    List<Servico> buscarPorTitulo(@Param("titulo") String titulo);
}
