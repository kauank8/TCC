package farias.paulino.kauan.SistemaAgendamento;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import farias.paulino.kauan.SistemaAgendamento.model.Funcionario;
import farias.paulino.kauan.SistemaAgendamento.repository.IFuncionarioRepository;

@SpringBootTest
class FuncionarioTests {

	@Autowired
	private IFuncionarioRepository funcionarioRepository;

	@Test
	public void testarSalvarFuncionario() {
		// Criando um funcionario com o construtor que herda de Usuario
		Funcionario funcionario = new Funcionario(0, "joao@example.com", "senha123", "1", "82444698037", "João Silva",
				"11988888888", "Gerente", "linkedin.com/in/joaosilva");

		// Salvando o funcionario no banco de dados
		Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);

		// Verifica se o funcionario foi salvo com sucesso (id não deve ser nulo)
		assertThat(funcionarioSalvo.getId()).isNotNull();
		assertThat(funcionarioSalvo.getCpf()).isEqualTo("54411002060");
		assertThat(funcionarioSalvo.getNome()).isEqualTo("João Silva");
		assertThat(funcionarioSalvo.getEmail()).isEqualTo("joao@example.com");
	}

}
