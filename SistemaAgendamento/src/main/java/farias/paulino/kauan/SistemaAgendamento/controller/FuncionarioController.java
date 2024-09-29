package farias.paulino.kauan.SistemaAgendamento.controller;

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

@Controller
public class FuncionarioController {

	@Autowired
	private IFuncionarioRepository fRep;

	@RequestMapping(name = "funcionario", value = "/funcionario", method = RequestMethod.GET)
	public ModelAndView FuncionarioGet(ModelMap model) {
		return new ModelAndView("funcionario");
	}

	@RequestMapping(name = "funcionario", value = "/funcionario", method = RequestMethod.POST)
	public ModelAndView FuncionarioPost(@RequestParam Map<String, String> param, ModelMap model) {
		// Entrada
		String cmd = param.get("cmd");
		String id = param.get("id");
		String email = param.get("email");
		String senha = param.get("senha");
		String nivelAcesso = param.get("nivelAcesso");
		String nome = param.get("nome");
		String cpf = param.get("cpf");
		String telefone = param.get("telefone");
		String perfil = param.get("perfil");
		String redeSocial = param.get("redeSocial");

		// Retorno
		String saida = "";
		String erro = "";
		Funcionario funcionario = new Funcionario();

		try {
			if (cmd.equals("Cadastrar")) {
				funcionario = new Funcionario(0, email, senha, "0", cpf, nome, telefone, perfil, redeSocial);
				fRep.save(funcionario);
				saida = "Funcionario cadastrado com sucesso!";
			}
			if (cmd.equals("Atualizar")) {
				Funcionario funcinarioAntigo = buscar(Integer.parseInt(id));
				funcionario.setId(Integer.parseInt(id));
				funcionario.setSenha(senha);
				funcionario.setNome(nome);
				funcionario.setTelefone(telefone);
				funcionario.setPerfil(perfil);
				funcionario.setRedeSocial(redeSocial);
				funcionario.setId(Integer.parseInt(id));
				funcionario.setCpf(funcinarioAntigo.getCpf());
				funcionario.setEmail(funcinarioAntigo.getEmail());
				funcionario.setNivelAcesso("1");
				fRep.save(funcionario);
				saida = "Funcionario atualizado com sucesso com sucesso!";
				// Tratar as validações de cpf 
			}
		} catch (Exception e) {
			erro = trataErros(e.getMessage());
			model.addAttribute("erro", erro);
		}
		model.addAttribute("mensagem", saida);
		model.addAttribute("erro", erro);
		model.addAttribute("funcionario", funcionario);
		return new ModelAndView("index");
	}
	
	private Funcionario buscar(int id) {
		return fRep.findById(id).orElse(new Funcionario());
	}
	private List<Funcionario> listar(){
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
