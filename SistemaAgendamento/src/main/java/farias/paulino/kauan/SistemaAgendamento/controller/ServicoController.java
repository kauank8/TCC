package farias.paulino.kauan.SistemaAgendamento.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import farias.paulino.kauan.SistemaAgendamento.model.Produto;
import farias.paulino.kauan.SistemaAgendamento.model.Servico;
import farias.paulino.kauan.SistemaAgendamento.repository.IProdutoRepository;
import farias.paulino.kauan.SistemaAgendamento.repository.IServicoRepository;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ServicoController {

	@Autowired
	private IServicoRepository sRep;

	@Autowired
	IProdutoRepository pRep;

	@RequestMapping(name = "servico", value = "/servico", method = RequestMethod.GET)
	public ModelAndView ServicoGet(ModelMap model) {
		List<Servico> servicos = listar();
		List<Produto> produtos = listarProdutos();

		model.addAttribute("servicos", servicos);
		model.addAttribute("produtos", produtos);

		return new ModelAndView("cadastrarServicoProprietaria");
	}

	@RequestMapping(name = "servico", value = "/servico", method = RequestMethod.POST)
	public ModelAndView ServicoPost(@RequestParam Map<String, String> param,
			@RequestParam(value = "produtos", required = false) List<Integer> produtosId, ModelMap model,
			HttpServletRequest request) {

		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String cmd = param.get("botao");
		String id = param.get("id");
		Integer duracao = Integer.parseInt(param.get("duracao"));
		String titulo = param.get("titulo");
		String status = param.get("status");
		Double preco = Double.parseDouble(param.get("preco"));

		String mensagemSucesso = "";
		String mensagemErro = "";
		Servico servico = new Servico();
		List<Produto> produtos = new ArrayList<>();
		if(produtosId != null && !produtosId.isEmpty()) {
			produtos = montaListaProdutos(produtosId);
		}
		List<Servico> servicos = listar();
		
		try {
			if (cmd.equals("Cadastrar")) {
				servico = new Servico(0, titulo, duracao, preco, status, produtos);
				sRep.save(servico);
				servico = new Servico();
				mensagemSucesso = "Serviço cadastrado com sucesso!";
			}
			if(cmd.equals("Editar")) {
				servico = buscar(Integer.parseInt(id));
			}
			if(cmd.equals("Atualizar")) {
				 if (!id.equals("0") && !id.isEmpty()) {
					 servico.setId(Integer.parseInt(id));
					 servico.setTitulo(titulo);
					 servico.setDuracao(duracao);
					 servico.setPreco(preco);
					 servico.setStatus(status);
					 servico.setProdutos(produtos);
					 
					 sRep.save(servico);
					 servico = new Servico();
					 mensagemSucesso = "Serviço atualizado com sucesso!";
				 }else {
					 mensagemErro = "É possível atualizar apenas serviços previamente cadastrados após selecionar a opção de editar!";
				 }
			}
		} catch (Exception e) {
			mensagemErro = trataErros(e.getMessage());
		}
		produtos = listarProdutos();
		servicos = listar();
		
        model.addAttribute("servicos", servicos);
        model.addAttribute("produtos", produtos);
		model.addAttribute("mensagemSucesso", mensagemSucesso);
		model.addAttribute("mensagemErro", mensagemErro);
		model.addAttribute("servico", servico);
		return new ModelAndView("cadastrarServicoProprietaria");
	}

	private String trataErros(String message) {
			if (message.contains("UNIQUE")) {
				return "Ja existe um serviço com esse titulo";
			}
		return message;
	}

	private List<Produto> montaListaProdutos(List<Integer> produtosId) {
		List<Produto> produtos = new ArrayList<>();
		for (Integer p : produtosId) {
			Produto produto = new Produto();
			produto.setId(p);

			produtos.add(produto);
		}
		return produtos;
	}

	private Servico buscar(int id) {
		return sRep.findById(id).orElse(new Servico());
	}

	private List<Servico> listar() {
		return sRep.findAll();
	}

	private List<Produto> listarProdutos() {
		return pRep.findAll();
	}

}
