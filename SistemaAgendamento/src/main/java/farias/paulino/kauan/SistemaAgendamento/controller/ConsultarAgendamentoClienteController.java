package farias.paulino.kauan.SistemaAgendamento.controller;

import java.time.LocalDate;
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
import farias.paulino.kauan.SistemaAgendamento.model.Cliente;
import farias.paulino.kauan.SistemaAgendamento.model.Funcionario;
import farias.paulino.kauan.SistemaAgendamento.model.Horario;
import farias.paulino.kauan.SistemaAgendamento.model.Servico;
import farias.paulino.kauan.SistemaAgendamento.repository.IAgendamentoRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class ConsultarAgendamentoClienteController {
	
	@Autowired
	private IAgendamentoRepository aRep;
	@RequestMapping(name = "consultarAgendamentoCliente", value = "/consultarAgendamentoCliente", method = RequestMethod.GET)
	public ModelAndView consultarAgendamentoClienteGet(ModelMap model, HttpSession session) {

		// Saida
		String mensagemErro = "";
		LocalDate data = LocalDate.now();
		List <Agendamento> agendamentos = new ArrayList<>();
		
		// Verificar estado da sessao
		Cliente cliente = (Cliente) session.getAttribute("sessaoCliente");
		if (cliente == null) {
			mensagemErro = "Você não tem acesso a essa pagina";
			session.setAttribute("sessaoCliente", cliente);
			model.addAttribute("mensagemErro", mensagemErro);
			return new ModelAndView("loginCadastroCliente");
		}

		try {
			agendamentos = listarAgendamentos(cliente, data);
		} catch (Exception e) {
			mensagemErro = e.getMessage();
		}
		model.addAttribute("cliente", cliente);
		model.addAttribute("agendamentos", agendamentos);
		model.addAttribute("data", data);
		model.addAttribute("mensagemErro", mensagemErro);
		return new ModelAndView("consultarAgendamentoCliente");
	}

	

	@RequestMapping(name = "consultarAgendamentoCliente", value = "/consultarAgendamentoCliente", method = RequestMethod.POST)
	public ModelAndView consultarAgendamentoClientePost(ModelMap model, HttpSession session, 
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
		
		try {
			if(cmd != null && cmd.equals("Pesquisar")) {
				Cliente cliente = (Cliente) session.getAttribute("sessaoCliente");
				agendamentos = listarAgendamentos(cliente, LocalDate.parse(data));
			}
			if(cancelar != null && !cancelar.isEmpty() && !cancelar.isBlank()) {
				agendamento = aRep.findById(Integer.parseInt(cancelar)).orElseThrow();
//				aRep.delete(agendamento);
				
				agendamento.setStatusAgendamento("Cancelado");
				aRep.save(agendamento);
				
				Cliente cliente = (Cliente) session.getAttribute("sessaoCliente");
				agendamentos = listarAgendamentos(cliente, LocalDate.parse(data));
				mensagemSucesso = "Agendamento Cancelado com sucesso";
			}
		} catch (Exception e) {
			mensagemErro = e.getMessage();
		}
		model.addAttribute("data", data);
		model.addAttribute("agendamentos", agendamentos);
		model.addAttribute("mensagemErro", mensagemErro);
		model.addAttribute("mensagemSucesso", mensagemSucesso);
		return new ModelAndView("consultarAgendamentoCliente");
	}
	
	private List<Agendamento> listarAgendamentos(Cliente cliente, LocalDate data) {
		return aRep.listarAgendamentoCliente(data, cliente.getId(), "Ativo");
	}
}
