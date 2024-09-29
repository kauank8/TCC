package farias.paulino.kauan.SistemaAgendamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import farias.paulino.kauan.SistemaAgendamento.model.Agendamento;

public interface IAgendamentoRepository extends JpaRepository<Agendamento, Integer>{

}
