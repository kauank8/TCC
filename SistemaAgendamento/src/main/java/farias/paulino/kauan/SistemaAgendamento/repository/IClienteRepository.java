package farias.paulino.kauan.SistemaAgendamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import farias.paulino.kauan.SistemaAgendamento.model.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Integer>{
	@Query(value="Select u.*, c.* from Usuario u, Cliente c \r\n"
			+ "where u.id = c.id \r\n"
			+ "and u.email = :email and u.senha = :senha", nativeQuery=true)
		Cliente login_cliente(@Param("email") String email, @Param("senha") String senha);
}
