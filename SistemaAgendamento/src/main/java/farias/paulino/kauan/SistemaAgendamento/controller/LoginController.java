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
import farias.paulino.kauan.SistemaAgendamento.model.Funcionario;
import farias.paulino.kauan.SistemaAgendamento.repository.IClienteRepository;
import farias.paulino.kauan.SistemaAgendamento.repository.IFuncionarioRepository;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

	@Autowired
	private IClienteRepository cRep;

	@Autowired
	private IFuncionarioRepository fRep;

	@RequestMapping(name = "login", value = "/login", method = RequestMethod.GET)
	public ModelAndView LoginGet(ModelMap model) {
		return new ModelAndView("loginCadastroCliente");
	}

	@RequestMapping(name = "login", value = "/login", method = RequestMethod.POST)
	public ModelAndView LoginPost(@RequestParam Map<String, String> param, ModelMap model, HttpServletRequest request) {

		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		// Entrada
		String cmd = param.get("botao");
		String email = param.get("email");
		String senha = param.get("senha");

		// Saida
		String mensagemSucesso = "";
		String mensagemErro = "";
		Cliente cliente = new Cliente();
		Funcionario funcionario = new Funcionario();

		try {
			if (cmd.equals("Entrar")) {
				// Verifico se é um cliente
				cliente = cRep.login_cliente(email, senha);
				if (cliente != null) {
					mensagemSucesso = "Montou cliente";
					model.addAttribute("mensagemSucesso", mensagemSucesso);
					return new ModelAndView("loginCadastroCliente");
				}

				// Verifico se é um funcionario
				funcionario = fRep.login_funcionario(email, senha);
				if (funcionario != null) {
					mensagemSucesso = "Montou funcionario";
					model.addAttribute("mensagemSucesso", mensagemSucesso);
					return new ModelAndView("loginCadastroCliente");
				}

				mensagemErro = "Email ou senha invalido";

			}
		} catch (Exception e) {
			mensagemErro = e.getMessage();
		}
		model.addAttribute("mensagemErro", mensagemErro);
		return new ModelAndView("loginCadastroCliente");
	}
}
