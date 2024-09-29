package farias.paulino.kauan.SistemaAgendamento.controller;

import java.io.UnsupportedEncodingException;
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
import farias.paulino.kauan.SistemaAgendamento.repository.IProdutoRepository;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ProdutoController {

	@Autowired
	private IProdutoRepository pRep;

	@RequestMapping(name = "produto", value = "/produto", method = RequestMethod.GET)
	public ModelAndView ProdutoGet(ModelMap model) {
		String mensagemErro = "";
		try {
			List<Produto> produtos = listar();
			model.addAttribute("produtos", produtos);
		} catch (Exception e) {
			mensagemErro = e.getMessage();
		}
		model.addAttribute("mensagemErro", mensagemErro);
		return new ModelAndView("cadastroProdutoProprietaria");
	}

	@RequestMapping(name = "produto", value = "/produto", method = RequestMethod.POST)
	public ModelAndView ProdutoPost(@RequestParam Map<String, String> param, ModelMap model, HttpServletRequest request) {
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		// Entrada
		String cmd = param.get("botao");
		String nome = param.get("nome");
		String id = param.get("id");
		String status = param.get("status");

		// Saida
		String mensagemSucesso = "";
		String mensagemErro = "";
		Produto produto = new Produto();
		
		try {
			if(cmd.equals("Cadastrar")) {
				produto.setNome(nome);
				produto.setStatus(status);
				
				pRep.save(produto);
				produto = new Produto();
				model.addAttribute("produto", produto);
				
				mensagemSucesso = "Produto cadastrado com sucesso";
			}
			if(cmd.equals("Editar")) {
				produto = buscar(Integer.parseInt(id));
				model.addAttribute("produto", produto);
			}
			if(cmd.equals("Atualizar")) {
				if(!id.equals("0") && !id.isEmpty()) {
				produto.setNome(nome);
				produto.setStatus(status);
				produto.setId(Integer.parseInt(id));
				
				pRep.save(produto);
				mensagemSucesso = "Produto atualizado com sucesso";
				
				List<Produto> produtos = listar();
				model.addAttribute("produtos", produtos);
				
				produto = new Produto();
				model.addAttribute("produto", produto);
				}
				else {
					mensagemErro = "É possível atualizar apenas produtos previamente cadastrados, após selecionar a opção de editar!";
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		List<Produto> produtos = listar();
		model.addAttribute("produtos", produtos);
		model.addAttribute("mensagemErro", mensagemErro);
		model.addAttribute("mensagemSucesso", mensagemSucesso);
		return new ModelAndView("cadastroProdutoProprietaria");
	}
	
	private List<Produto> listar() {
		return pRep.findAll();
	}
	
	private Produto buscar(int id) {
		return pRep.findById(id).orElse(new Produto());
	}
	
}
