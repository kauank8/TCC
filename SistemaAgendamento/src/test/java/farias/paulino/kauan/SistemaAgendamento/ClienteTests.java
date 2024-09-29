package farias.paulino.kauan.SistemaAgendamento;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import farias.paulino.kauan.SistemaAgendamento.model.Cliente;
import farias.paulino.kauan.SistemaAgendamento.repository.IClienteRepository;

@SpringBootTest
class ClienteTests {
	@Autowired
	private IClienteRepository clienteRepository;

	@Test
	public void testarSalvarCliente() {
		Cliente cliente = new Cliente(0, "maria@example.com", "senha123", "C", "37708128080", "Kauan", "11999999999",
				"Rua B, 456");
		Cliente salvo = clienteRepository.save(cliente);
		assertThat(salvo.getId()).isNotNull();
		assertThat(salvo.getCpf()).isEqualTo("37708128080");
		assertThat(salvo.getEmail()).isEqualTo("maria@example.com");
	}
}
