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
import farias.paulino.kauan.SistemaAgendamento.repository.IClienteRepository;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ClienteController {

	@Autowired
	private IClienteRepository cRep;

	@RequestMapping(name = "cliente", value = "/cliente", method = RequestMethod.GET)
	public ModelAndView ClienteGet(ModelMap model) {
		return new ModelAndView("cliente");
	}

	@RequestMapping(name = "cliente", value = "/cliente", method = RequestMethod.POST)
	public ModelAndView ClientePost(@RequestParam Map<String, String> param, ModelMap model, HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		// Entrada
		String cmd = param.get("botao");
		String id = param.get("id");
		String email = param.get("email");
		String senha = param.get("senha");
		String nivelAcesso = param.get("nivelAcesso");
		String nome = param.get("nome");
		String cpf = param.get("cpf");
		String telefone = param.get("telefone");
		String logradouro = param.get("logradouro");
		String numero = param.get("numero");
		String endereco = logradouro + ", " + numero;
		
		// Retorno
		String mensagemSucesso = "";
		String mensagemErro = "";
		Cliente cliente = new Cliente();

		try {
			if (cmd.equals("Cadastre-se")) {
				cliente = new Cliente(0, email, senha, "0", cpf, nome, telefone, endereco);
				cRep.save(cliente);
				mensagemSucesso = "Cliente cadastrado com sucesso!";
			}
			if (cmd.equals("Atualizar")) {
				Cliente clienteAntigo = buscar(Integer.parseInt(id));
				
				cliente.setEmail(clienteAntigo.getEmail());
				cliente.setCpf(clienteAntigo.getCpf());
				cliente.setNivelAcesso("0");
				cliente.setId(Integer.parseInt(id));
				
				cliente.setNome(nome);
				cliente.setEndereco(logradouro);
				cliente.setSenha(senha);
				cliente.setTelefone(telefone);

				cRep.save(cliente);
				mensagemSucesso = "Cliente atualizado com sucesso com sucesso!";
			}
		} catch (Exception e) {
			mensagemErro = trataErros(e.getMessage());
			model.addAttribute("erro", mensagemErro);
		}
		model.addAttribute("mensagemSucesso", mensagemSucesso);
		model.addAttribute("mensagemErro", mensagemErro);
		model.addAttribute("cliente", cliente);
		return new ModelAndView("loginCadastroCliente");
	}

	private Cliente buscar(int id) {
		return cRep.findById(id).orElse(new Cliente());
	}

	private List<Cliente> listar() {
		return cRep.findAll();
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
