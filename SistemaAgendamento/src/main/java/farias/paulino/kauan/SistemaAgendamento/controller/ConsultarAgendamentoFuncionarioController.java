package farias.paulino.kauan.SistemaAgendamento.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import farias.paulino.kauan.SistemaAgendamento.model.Agendamento;
import farias.paulino.kauan.SistemaAgendamento.model.Funcionario;
import farias.paulino.kauan.SistemaAgendamento.repository.IAgendamentoRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class ConsultarAgendamentoFuncionarioController {
	@Autowired
	private IAgendamentoRepository aRep;

	@RequestMapping(name = "consultarAgendamentoFuncionario", value = "/consultarAgendamentoFuncionario", method = RequestMethod.GET)
	public ModelAndView consultarAgendamentoFuncionarioGet(ModelMap model, HttpSession session) {

		// Saida
		String mensagemErro = "";
		LocalDate data = LocalDate.now();
		List<Agendamento> agendamentosDoDia = new ArrayList<>();
		List <Agendamento> proximosAgendamentos = new ArrayList<>();
		int qtdAgendamentosDoDia=0;
		
		// Verificar estado da sessao
		Funcionario funcionario = (Funcionario) session.getAttribute("sessaoFuncionario");
		if (funcionario == null) {
			mensagemErro = "Você não tem acesso a essa pagina";
			session.setAttribute("sessaoFuncionario", funcionario);
			model.addAttribute("mensagemErro", mensagemErro);
			return new ModelAndView("loginCadastroCliente");
		}

		try {
			agendamentosDoDia = listarAgendamentos(funcionario,data);
			qtdAgendamentosDoDia = agendamentosDoDia.size();
			if(agendamentosDoDia.isEmpty()) {
				proximosAgendamentos = aRep.listarProximosAgendamentos(LocalDate.now(), funcionario.getId());
			}
		} catch (Exception e) {
			mensagemErro = e.getMessage();
		}
		model.addAttribute("mensagemErro", mensagemErro);
		model.addAttribute("qtdAgendamentosDoDia", qtdAgendamentosDoDia);
		model.addAttribute("agendamentos", agendamentosDoDia);
		model.addAttribute("funcionario", funcionario);
		model.addAttribute("data", data);
		model.addAttribute("proximosAgendamentos", proximosAgendamentos);
		return new ModelAndView("consultarAgendamentoFuncionario");
	}
	
	@RequestMapping(name = "consultarAgendamentoFuncionario", value = "/consultarAgendamentoFuncionario", method = RequestMethod.POST)
	public ModelAndView consultarAgendamentoFuncionarioPost(ModelMap model, HttpSession session, 
			@RequestParam Map<String, String> param) {
		
		// Entrada
		String cmd = param.get("botao");
		String data = param.get("data");
		String cancelar = param.get("Cancelar");
		
		// Saida
		String mensagemErro = "";
		String mensagemSucesso = "";
		Agendamento agendamento = new Agendamento();
		List <Agendamento> agendamentos = new ArrayList<>();
		List <Agendamento> proximosAgendamentos = new ArrayList<>();
		int qtdAgendamentosDoDia=0;
	

		try {
			Funcionario funcionario = (Funcionario) session.getAttribute("sessaoFuncionario");
			if(cmd != null && cmd.equals("Pesquisar")) {
				agendamentos = listarAgendamentos(funcionario, LocalDate.parse(data));
				qtdAgendamentosDoDia = agendamentos.size();
			}
			if(agendamentos.isEmpty()) {
				proximosAgendamentos = aRep.listarProximosAgendamentos(LocalDate.now(), funcionario.getId());
			}
		} catch (Exception e) {
			mensagemErro = e.getMessage();
		}
		model.addAttribute("data", data);
		model.addAttribute("agendamentos", agendamentos);
		model.addAttribute("mensagemErro", mensagemErro);
		model.addAttribute("mensagemSucesso", mensagemSucesso);
		model.addAttribute("qtdAgendamentosDoDia", qtdAgendamentosDoDia);
		model.addAttribute("proximosAgendamentos", proximosAgendamentos);
		return new ModelAndView("consultarAgendamentoFuncionario");
	}

	private List<Agendamento> listarAgendamentos(Funcionario funcionario, LocalDate data) {
		List<Agendamento> agendamentos = aRep.listarAgendamentoFuncionario(data, funcionario.getId());
		return agendamentos;
	}

	
}
