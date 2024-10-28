package farias.paulino.kauan.SistemaAgendamento;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import farias.paulino.kauan.SistemaAgendamento.model.Produto;
import farias.paulino.kauan.SistemaAgendamento.model.Servico;
import farias.paulino.kauan.SistemaAgendamento.repository.IProdutoRepository;
import farias.paulino.kauan.SistemaAgendamento.repository.IServicoRepository;

@SpringBootTest
class ServicoTests {
	@Autowired
	private IServicoRepository servicoRepository;
	
	@Autowired
	private IProdutoRepository produtoRepository;

	@Test
	public void testarSalvarServico_Produto() {
//		// Criando produtos relacionados
//		Produto produto1 = new Produto(0, "Shampoo", "Disponível");
//		Produto produto2 = new Produto(0, "Condicionador", "Disponível");
//
//		produtoRepository.saveAll(Arrays.asList(produto1, produto2));
//
//		// Criando um serviço com produtos
//		Servico servico = new Servico(0, "Corte de Cabelo", 30, 50.00, Arrays.asList(produto1, produto2));
//
//		// Salvando o serviço
//		Servico salvo = servicoRepository.save(servico);
//
//		// Verificações
//		assertThat(salvo).isNotNull();
//		assertThat(salvo.getId()).isNotNull();
//		assertThat(salvo.getTitulo()).isEqualTo("Corte de Cabelo");
//		assertThat(salvo.getDuracao()).isEqualTo(30);
//		assertThat(salvo.getPreco()).isEqualTo(50.00);
//		assertThat(salvo.getProdutos()).contains(produto1, produto2);
	}
}
