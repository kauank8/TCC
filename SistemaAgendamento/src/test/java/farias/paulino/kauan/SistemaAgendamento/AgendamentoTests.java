package farias.paulino.kauan.SistemaAgendamento;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import farias.paulino.kauan.SistemaAgendamento.model.Agendamento;
import farias.paulino.kauan.SistemaAgendamento.model.Cliente;
import farias.paulino.kauan.SistemaAgendamento.model.Funcionario;
import farias.paulino.kauan.SistemaAgendamento.model.Servico;
import farias.paulino.kauan.SistemaAgendamento.repository.IAgendamentoRepository;
import farias.paulino.kauan.SistemaAgendamento.repository.IClienteRepository;
import farias.paulino.kauan.SistemaAgendamento.repository.IFuncionarioRepository;
import farias.paulino.kauan.SistemaAgendamento.repository.IServicoRepository;

@SpringBootTest
class AgendamentoTests {
	@Autowired
	private IAgendamentoRepository agendamentoRepository;
	
	@Autowired
	private IClienteRepository clienteRepository;
	
	@Autowired
	private IFuncionarioRepository funcionarioRepository;
	
	@Autowired
	private IServicoRepository servicoRepository;
	

	
	public void testarSalvarAgendamento() {
		// Cria cliente e funcionário
		Cliente cliente = new Cliente(0, "cliente3@example.com", "senha123", "C", "37708128080", "Cliente Teste 3",
				"11988887779", "Rua de Teste 3");
		Cliente clienteSalvo = clienteRepository.save(cliente);

		Funcionario funcionario = new Funcionario(0, "funcionario@example.com", "senha123", "F", "54411002060",
				"Funcionario Teste", "11988887770", "Gerente", "linkdin.com");
		Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);

		// Cria agendamento
		Agendamento agendamento = new Agendamento(0, LocalDate.now(), 120.0, "11:00", null, clienteSalvo,
				funcionarioSalvo, new ArrayList<>());
		Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento);

		// Verifica se o agendamento foi salvo corretamente
		assertThat(agendamentoSalvo.getId()).isNotNull();
		assertThat(agendamentoSalvo.getCliente().getCpf()).isEqualTo("37708128080");
		assertThat(agendamentoSalvo.getFuncionario().getNome()).isEqualTo("Funcionario Teste");
		assertThat(agendamentoSalvo.getValorTotal()).isEqualTo(120.0);
	}
	
	 @Test
	    public void testarAssociarServicosAoAgendamento() {
//	        // Cria cliente e funcionário
//	        Cliente cliente = new Cliente(0, "cliente4@example.com", "senha123", "C", "37708128080", "Cliente Teste 4", "11988887780", "Rua de Teste 4");
//	        Cliente clienteSalvo = clienteRepository.save(cliente);
//
//	        Funcionario funcionario = new Funcionario(0, "funcionario2@example.com", "senha123", "F", "54411002060", "Funcionario Teste 2", "11988887771", "Gerente", "linkdin.com");
//	        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);
//
//	        // Cria alguns serviços
//	        Servico servico1 = new Servico(0, "Corte de cabelo", 30, 50.0, new ArrayList<>());
//	        Servico servico2 = new Servico(0, "Manicure", 45, 80.0, new ArrayList<>());
//	        servicoRepository.save(servico1);
//	        servicoRepository.save(servico2);
//
//	        // Cria agendamento
//	        Agendamento agendamento = new Agendamento(0, LocalDate.now(), 130.0, "15:00", null, clienteSalvo, funcionarioSalvo, new ArrayList<>());
//	        
//	        // Associa os serviços ao agendamento
//	        agendamento.getServicos().add(servico1);
//	        agendamento.getServicos().add(servico2);
//
//	        // Salva o agendamento
//	        Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento);
//
//	        // Verifica se os serviços foram associados corretamente
//	        assertThat(agendamentoSalvo.getServicos()).hasSize(2);
//	        assertThat(agendamentoSalvo.getServicos().get(0).getTitulo()).isEqualTo("Corte de cabelo");
//	        assertThat(agendamentoSalvo.getServicos().get(1).getTitulo()).isEqualTo("Manicure");
	    }

}
