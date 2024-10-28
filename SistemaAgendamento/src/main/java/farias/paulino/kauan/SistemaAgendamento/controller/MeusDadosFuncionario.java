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

import farias.paulino.kauan.SistemaAgendamento.model.Cliente;
import farias.paulino.kauan.SistemaAgendamento.model.Funcionario;
import farias.paulino.kauan.SistemaAgendamento.repository.IFuncionarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MeusDadosFuncionario {
	
	@Autowired
	IFuncionarioRepository fRep;
	@RequestMapping(name = "funcionarioDados", value = "/meusDadosFuncionario", method = RequestMethod.GET)
	public ModelAndView meusDadosFuncionarioGet(ModelMap model, HttpSession session) {
		String mensagemErro = "";

		// Verificar estado da sessao
		Funcionario funcionario = (Funcionario) session.getAttribute("sessaoFuncionario");
		if (funcionario == null) {
			mensagemErro = "Você não tem acesso a essa pagina";
			session.setAttribute("sessaoFuncionario", funcionario);
			model.addAttribute("mensagemErro", mensagemErro);
			return new ModelAndView("loginCadastroCliente");
		}

		try {
			model.addAttribute("funcionario", funcionario);
		} catch (Exception e) {
			mensagemErro = e.getMessage();
		}
		model.addAttribute("mensagemErro", mensagemErro);
		return new ModelAndView("meusDadosFuncionario");

	}

	@RequestMapping(name = "funcionarioDados", value = "/meusDadosFuncionario", method = RequestMethod.POST)
	public ModelAndView meusDadosFuncionarioPost(@RequestParam Map<String, String> param, ModelMap model,
			HttpServletRequest request, HttpSession session) {
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
		String perfil = param.get("perfil");
		String redeSocial = param.get("redeSocial");

		// Retorno
		String mensagemSucesso = "";
		String mensagemErro = "";
		Funcionario funcionario = new Funcionario();
		
		try {
			if (cmd.equals("Atualizar")) {
				Funcionario funcionarioAntigo = (Funcionario)session.getAttribute("sessaoFuncionario");
				
				funcionario.setCpf(funcionarioAntigo.getCpf());
				funcionario.setNivelAcesso(funcionarioAntigo.getNivelAcesso());
				funcionario.setId(funcionarioAntigo.getId());
				
				funcionario.setNome(nome);
				funcionario.setSenha(senha);
				funcionario.setTelefone(telefone);
				funcionario.setEmail(email);
				funcionario.setPerfil(perfil);
				funcionario.setRedeSocial(redeSocial);
				
				fRep.save(funcionario);
				session.setAttribute("sessaoFuncionario", funcionario);
				mensagemSucesso = "Dados atualizado com sucesso!";
			}
		} catch (Exception e) {
			mensagemErro = trataErros(e.getMessage());
			funcionario = fRep.findById(funcionario.getId()).orElseThrow();
			session.setAttribute("sessaoFuncionario", funcionario);
		}
		
		model.addAttribute("mensagemSucesso", mensagemSucesso);
		model.addAttribute("mensagemErro", mensagemErro);
		model.addAttribute("funcionario", funcionario);
		return new ModelAndView("meusDadosFuncionario");
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
