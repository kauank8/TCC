package farias.paulino.kauan.SistemaAgendamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import farias.paulino.kauan.SistemaAgendamento.model.Cliente;
import farias.paulino.kauan.SistemaAgendamento.model.Funcionario;

public interface IFuncionarioRepository extends JpaRepository<Funcionario, Integer> {
	@Query(value="Select u.*, f.* from Usuario u, Funcionario f \r\n"
			+ "where u.id = f.id \r\n"
			+ "and u.email = :email and u.senha = :senha", nativeQuery=true)
		Funcionario login_funcionario(@Param("email") String email, @Param("senha") String senha);
}
