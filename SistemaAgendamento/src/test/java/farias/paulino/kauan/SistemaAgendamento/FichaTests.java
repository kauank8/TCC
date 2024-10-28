package farias.paulino.kauan.SistemaAgendamento;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import farias.paulino.kauan.SistemaAgendamento.model.Agendamento;
import farias.paulino.kauan.SistemaAgendamento.model.Cliente;
import farias.paulino.kauan.SistemaAgendamento.model.Ficha;
import farias.paulino.kauan.SistemaAgendamento.model.Funcionario;
import farias.paulino.kauan.SistemaAgendamento.model.Servico;
import farias.paulino.kauan.SistemaAgendamento.repository.IAgendamentoRepository;
import farias.paulino.kauan.SistemaAgendamento.repository.IClienteRepository;
import farias.paulino.kauan.SistemaAgendamento.repository.IFichaRepository;
import farias.paulino.kauan.SistemaAgendamento.repository.IFuncionarioRepository;
import farias.paulino.kauan.SistemaAgendamento.repository.IServicoRepository;

@SpringBootTest
class FichaTests {
	 @Autowired
	    private IFichaRepository fichaRepository;

	    @Autowired
	    private IClienteRepository clienteRepository;
	    
	    @Autowired
	    private IAgendamentoRepository agendamentoRepository;
	    
	    @Autowired
	    private IFuncionarioRepository funcionarioRepository;

	    @Autowired
	    private IServicoRepository servicoRepository;

	    @Test
	    public void testarSalvarFicha() {
//	        // Criando um cliente para associar à ficha
//	        Cliente cliente = new Cliente(0, "joana@example.com", "senha123", "C", "98765432100", "Joana Silva", "11998887777", "Rua A, 123");
//	        Cliente clienteSalvo = clienteRepository.save(cliente);
//
//	        // Criando um funcionário
//	        Funcionario funcionario = new Funcionario(0, "pedro@example.com", "senha123", "F", "12345678901", "Pedro Oliveira", "11997776666", "Cabeleireiro", "linkedin.com/in/pedro");
//	        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);
//	        
//	        // Criando um serviço
//	        Servico servico = new Servico(0, "Corte de Cabelo", 60, 100.0, new ArrayList<>());
//	        Servico servicoSalvo = servicoRepository.save(servico);
//
//	        // Criando agendamentos para associar à ficha
//	        Agendamento agendamento1 = new Agendamento(0, LocalDate.now(), 100.0, "10:00", null, clienteSalvo, funcionarioSalvo, List.of(servicoSalvo));
//	        Agendamento agendamento2 = new Agendamento(0, LocalDate.now().plusDays(1), 150.0, "11:00", null, clienteSalvo, funcionarioSalvo, List.of(servicoSalvo));
//	        
//	        // Criando uma lista de agendamentos
//	        List<Agendamento> agendamentos = new ArrayList<>();
//	        agendamentos.add(agendamento1);
//	        agendamentos.add(agendamento2);
//	        
//	        // Criando a ficha
//	        Ficha ficha = new Ficha(0, clienteSalvo, "Observações iniciais", agendamentos);
//
//	        // Associando a ficha aos agendamentos
//	        agendamento1.setFicha(ficha);
//	        agendamento2.setFicha(ficha);
//
//	        // Salvando a ficha no banco de dados
//	        Ficha fichaSalva = fichaRepository.save(ficha);
//
//	        // Verificando se a ficha foi salva corretamente
//	        assertThat(fichaSalva.getId()).isNotNull();
//	        assertThat(fichaSalva.getCliente().getId()).isEqualTo(clienteSalvo.getId());
//	        assertThat(fichaSalva.getAgendamentos()).hasSize(2);
//	        assertThat(fichaSalva.getAgendamentos().get(0).getHorario()).isEqualTo("10:00");
//	        assertThat(fichaSalva.getAgendamentos().get(1).getHorario()).isEqualTo("11:00");
	    }
}
