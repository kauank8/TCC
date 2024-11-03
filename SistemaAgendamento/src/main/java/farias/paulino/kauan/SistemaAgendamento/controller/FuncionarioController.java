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

import farias.paulino.kauan.SistemaAgendamento.model.Funcionario;
import farias.paulino.kauan.SistemaAgendamento.repository.IFuncionarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class FuncionarioController {

	@Autowired
	private IFuncionarioRepository fRep;

	@RequestMapping(name = "funcionario", value = "/funcionario", method = RequestMethod.GET)
	public ModelAndView FuncionarioGet(ModelMap model, HttpSession session) {
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
			List<Funcionario> funcionarios = listar();
			model.addAttribute("funcionarios", funcionarios);
		} catch (Exception e) {
			mensagemErro = e.getMessage();
		}
		model.addAttribute("mensagemErro", mensagemErro);
		return new ModelAndView("cadastroFuncionarioProprietaria");
	}

	@RequestMapping(name = "funcionario", value = "/funcionario", method = RequestMethod.POST)
	public ModelAndView FuncionarioPost(@RequestParam Map<String, String> param, ModelMap model,
			HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		// Entrada
		String cmd = param.get("botao");
		String pagina = param.get("pagina");
		String id = param.get("id");
		String email = param.get("email");
		String senha = param.get("senha");
		String nivelAcesso = param.get("acesso");
		String nome = param.get("nome");
		String cpf = param.get("cpf");
		String telefone = param.get("telefone");
		String perfil = param.get("perfil");
		String redeSocial = param.get("redeSocial");

		// Retorno
		String mensagemSucesso = "";
		String mensagemErro = "";
		Funcionario funcionario = new Funcionario();

		try {
			if (cmd.equals("Cadastrar")) {
				perfil = "";
				redeSocial = "";

				funcionario = new Funcionario(0, email, senha, "1", cpf, nome, telefone, perfil, redeSocial);
				fRep.save(funcionario);
				funcionario = new Funcionario();

				mensagemSucesso = "Funcionario cadastrado com sucesso!";
			}

			if (cmd.equals("Editar")) {
				funcionario = buscar(Integer.parseInt(id));
			}

			if (cmd.equals("Atualizar")) {
				if (!id.equals("0") && !id.isEmpty()) {
					Funcionario funcionarioAntigo = buscar(Integer.parseInt(id));
					funcionario.setCpf(funcionarioAntigo.getCpf());
					funcionario.setEmail(funcionarioAntigo.getEmail());

					funcionario.setId(Integer.parseInt(id));
					funcionario.setSenha(senha);
					funcionario.setNome(nome);
					funcionario.setTelefone(telefone);

					if (pagina.equals("proprietaria")) {
						funcionario.setNivelAcesso(nivelAcesso);
						funcionario.setRedeSocial(funcionarioAntigo.getRedeSocial());
						funcionario.setPerfil(funcionarioAntigo.getPerfil());
					}
					if (pagina.equals("funcionario")) {
						funcionario.setRedeSocial(redeSocial);
						funcionario.setPerfil(perfil);
						funcionario.setNivelAcesso(funcionarioAntigo.getNivelAcesso());
					}

					fRep.save(funcionario);
					funcionario = new Funcionario();
					mensagemSucesso = "Funcionario atualizado com sucesso com sucesso!";
				} else {
					mensagemErro = "É possível atualizar apenas funcionarios previamente cadastrados, após selecionar a opção de editar!";
				}
			}
		} catch (Exception e) {
			mensagemErro = trataErros(e.getMessage());
		}
		List<Funcionario> funcionarios = listar();
		model.addAttribute("funcionarios", funcionarios);
		model.addAttribute("mensagemSucesso", mensagemSucesso);
		model.addAttribute("mensagemErro", mensagemErro);
		model.addAttribute("funcionario", funcionario);
		return new ModelAndView("cadastroFuncionarioProprietaria");
	}

	private Funcionario buscar(int id) {
		return fRep.findById(id).orElse(new Funcionario());
	}

	private List<Funcionario> listar() {
		return fRep.findAll();
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
