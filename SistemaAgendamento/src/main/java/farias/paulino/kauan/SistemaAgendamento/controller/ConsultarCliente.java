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

import farias.paulino.kauan.SistemaAgendamento.model.Cliente;
import farias.paulino.kauan.SistemaAgendamento.model.Funcionario;
import farias.paulino.kauan.SistemaAgendamento.repository.IClienteRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ConsultarCliente {
	
	@Autowired
	private IClienteRepository cRep;
	
	@RequestMapping(name = "consultarCliente", value = "/consultarCliente", method = RequestMethod.GET)
	public ModelAndView ConsultarClienteGet(ModelMap model, HttpSession session) {
		
		String mensagemErro = "";
		
		//Verificar estado da sessao
		Funcionario funcionario = (Funcionario)session.getAttribute("sessaoFuncionario");
		if(funcionario == null) {
			mensagemErro = "Você não tem acesso a essa pagina";
			session.setAttribute("sessaoFuncionario", funcionario);
			model.addAttribute("mensagemErro", mensagemErro);
			return new ModelAndView("loginCadastroCliente");
		}
		
		try {
			List<Cliente> clientes = listar();
			model.addAttribute("clientes", clientes);
		} catch (Exception e) {
			mensagemErro = e.getMessage();
		}
		model.addAttribute("mensagemErro", mensagemErro);
		return new ModelAndView("consultarCliente");
	}
	
	

	@RequestMapping(name = "consultarCliente", value = "/consultarCliente", method = RequestMethod.POST)
	public ModelAndView ConsultarClientePost(@RequestParam Map<String, String> param, ModelMap model, HttpSession session,
			HttpServletRequest request) {
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		// Entrada
		String cmd = param.get("botao");
		String nome = param.get("nome");
		String idCliente = param.get("idCliente");
		//Retorno
		String mensagemSucesso = "";
		String mensagemErro = "";
		List<Cliente> clientes = new ArrayList<>();
		
		try {
			if(cmd.equals("Pesquisar")) {
				clientes = filtrarPorNome(nome);
				if(clientes.isEmpty()) {
					mensagemErro = "Cliente não encontrado";
					clientes = listar();
				}
			}
			if(cmd.equals("Ver Ficha")) {
				Cliente c = cRep.findById(Integer.parseInt(idCliente)).orElseThrow();
				session.setAttribute("clienteFicha", c);
				return new ModelAndView("redirect:/consultarFicha");
			}
//			clientes = listar();
			model.addAttribute("clientes", clientes);
		} catch (Exception e) {
			mensagemErro = e.getMessage();
		}
		model.addAttribute("mensagemErro", mensagemErro);
		return new ModelAndView("consultarCliente");
	}
	
	private List<Cliente> listar() {
		return cRep.findAll();
	}
	
	private List<Cliente> filtrarPorNome(String nome) {
		return cRep.buscarPorNome(nome);
	}
}
