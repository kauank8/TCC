package farias.paulino.kauan.SistemaAgendamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import farias.paulino.kauan.SistemaAgendamento.model.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Integer>{
	@Query(value="Select u.*, c.* from Usuario u, Cliente c \r\n"
			+ "where u.id = c.id \r\n"
			+ "and u.email = :email and u.senha = :senha", nativeQuery=true)
		Cliente login_cliente(@Param("email") String email, @Param("senha") String senha);
	
	@Query(value="Select u.* , c.* from Usuario u, Cliente c\r\n"
			+ "where u.id = c.id\r\n"
			+ "and c.cpf = :cpf", nativeQuery=true)
		Cliente buscarPorCpf(@Param("cpf") String cpf);
	
	@Query(value = "SELECT u.*, c.* from Usuario u, Cliente c \r\n"
			+ "WHERE u.id = c.id and c.nome LIKE %:nome%", nativeQuery = true)
    List<Cliente> buscarPorNome(@Param("nome") String nome);
}
