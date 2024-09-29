package farias.paulino.kauan.SistemaAgendamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import farias.paulino.kauan.SistemaAgendamento.model.Produto;

public interface IProdutoRepository extends JpaRepository<Produto, Integer>{

}
