package farias.paulino.kauan.SistemaAgendamento.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import farias.paulino.kauan.SistemaAgendamento.model.Cliente;
import farias.paulino.kauan.SistemaAgendamento.repository.IClienteRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MeusDadosClienteController {
	@Autowired
	private IClienteRepository cRep;

	@RequestMapping(name = "clienteDados", value = "/meusDadosCliente", method = RequestMethod.GET)
	public ModelAndView meusDadosClienteGet(ModelMap model, HttpSession session) {
		String mensagemErro = "";
		
		//Verificar estado da sessao
		Cliente cliente = (Cliente)session.getAttribute("sessaoCliente");
		if(cliente == null) {
			mensagemErro = "Você não tem acesso a essa pagina";
			session.setAttribute("sessaoCliente", cliente);
			model.addAttribute("mensagemErro", mensagemErro);
			return new ModelAndView("loginCadastroCliente");
		}
		
		try {
			model.addAttribute("cliente", cliente);
		} catch (Exception e) {
			mensagemErro = e.getMessage();
		}
		model.addAttribute("mensagemErro", mensagemErro);
		return new ModelAndView("meusDadosCliente");
		
	}

	@RequestMapping(name = "clienteDados", value = "/meusDadosCliente", method = RequestMethod.POST)
	public ModelAndView meusDadosClientePost(@RequestParam Map<String, String> param, ModelMap model, HttpServletRequest request, HttpSession session) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		// Entrada
		String cmd = param.get("botao");
		String email = param.get("email");
		String senha = param.get("senha");
		String nome = param.get("nome");
		String telefone = param.get("telefone");
		String logradouro = param.get("logradouro");
		String numero = param.get("numero");
		String endereco = logradouro + ", " + numero;
		
		// Retorno
		String mensagemSucesso = "";
		String mensagemErro = "";
		Cliente cliente =  new Cliente();
		
		try {
			if (cmd.equals("Atualizar")) {
				Cliente clienteAntigo = (Cliente)session.getAttribute("sessaoCliente");
				
				cliente.setCpf(clienteAntigo.getCpf());
				cliente.setNivelAcesso(clienteAntigo.getNivelAcesso());
				cliente.setId(clienteAntigo.getId());
				
				cliente.setNome(nome);
				cliente.setEndereco(endereco);
				cliente.setSenha(senha);
				cliente.setTelefone(telefone);
				cliente.setEmail(email);
				
				cRep.save(cliente);
				session.setAttribute("sessaoCliente", cliente);
				mensagemSucesso = "Dados atualizado com sucesso!";
			}
		} catch (Exception e) {
			mensagemErro = trataErros(e.getMessage());
			cliente = cRep.findById(cliente.getId()).orElseThrow();
			session.setAttribute("sessaoCliente",cliente );
		}
		model.addAttribute("mensagemSucesso", mensagemSucesso);
		model.addAttribute("mensagemErro", mensagemErro);
		model.addAttribute("cliente", cliente);
		return new ModelAndView("meusDadosCliente");
	}
	
	private String trataErros(String message) {
		System.out.println(message);
		if (message.contains("cpf")) {
			if (message.contains("UNIQUE")) {
				return "Cpf já cadastrado";
			}
			return "Cpf invalido";
		} else if (message.contains("email")) {
			if (message.contains("UNIQUE")) {
				return "Email já cadastrado";
			} else {
				return "O email informado possui o formato invalido";
			}
		}
		return message;
	}
}
