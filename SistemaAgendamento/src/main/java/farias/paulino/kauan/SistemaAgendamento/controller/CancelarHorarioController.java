package farias.paulino.kauan.SistemaAgendamento.controller;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import farias.paulino.kauan.SistemaAgendamento.model.Funcionario;
import farias.paulino.kauan.SistemaAgendamento.model.Horario;
import farias.paulino.kauan.SistemaAgendamento.model.HorarioCancelado;
import farias.paulino.kauan.SistemaAgendamento.repository.IFuncionarioRepository;
import farias.paulino.kauan.SistemaAgendamento.repository.IHorarioCanceladoRepository;
import farias.paulino.kauan.SistemaAgendamento.repository.IHorarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CancelarHorarioController {
	@Autowired
	private IHorarioRepository hRep;

	@Autowired
	private IHorarioCanceladoRepository hcRep;

	@Autowired
	private IFuncionarioRepository fRep;

	@RequestMapping(name = "cancelarHorario", value = "/cancelarHorario", method = RequestMethod.GET)
	public ModelAndView cancelarHorarioGet(ModelMap model, HttpSession session, @RequestParam Map<String, String> param) {
		String mensagemErro = (String) session.getAttribute("mensagemErro");
		session.removeAttribute("mensagemErro");
		// Verificar estado da sessao
		Funcionario funcionario = (Funcionario) session.getAttribute("sessaoFuncionario");
		if (funcionario == null) {
			mensagemErro = "Você não tem acesso a essa pagina";
			session.setAttribute("sessaoFuncionario", funcionario);
			model.addAttribute("mensagemErro", mensagemErro);
			return new ModelAndView("loginCadastroCliente");
		}

		try {
			List<Horario> horarios = listarHorarios(funcionario, LocalDate.now());
			
			model.addAttribute("horarios", horarios);
			model.addAttribute("mensagemErro", mensagemErro);
			model.addAttribute("data", LocalDate.now());
		} catch (Exception e) {
			mensagemErro = e.getMessage();
		}
		return new ModelAndView("cancelarHorario");
	}

	@RequestMapping(name = "cancelarHorario", value = "/cancelarHorario", method = RequestMethod.POST)
	public ModelAndView cancelarHorarioPost(@RequestParam Map<String, String> param,
			@RequestParam(value = "horarios", required = false) List<Integer> horariosId, ModelMap model,
			HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) {

		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		// Entrada
		String cmd = param.get("botao");
		String id = param.get("id");
		String data = param.get("data");
		
		// Saida
		String mensagemSucesso = "";
		String mensagemErro = "";
		List<Horario> horarios = new ArrayList<>();
		Funcionario funcionario = (Funcionario) session.getAttribute("sessaoFuncionario");
		
		if(data !=null && !data.isEmpty() && !data.isBlank()) {
			LocalDate dataEscolhida = LocalDate.parse(data);
			
			// Verifica se a data escolhida é maior que a data atual
			if (!dataEscolhida.isBefore(LocalDate.now())) {
			    // A data é válida, então prossegue com a listagem dos horários
			    horarios = listarHorarios(funcionario, dataEscolhida);
			} else {
			    // A data não é válida (é no passado ou é a data atual)
			    mensagemErro = "Por favor, selecione uma data futura para o cancelamento.";
			    
//			    redirectAttributes.addFlashAttribute("mensagemErro", mensagemErro);
//			    redirectAttributes.addFlashAttribute(mensagemErro);
//			    model.addAttribute("mensagemErro",mensagemErro);
//			    redirectAttributes.addAttribute("mensagemErro", mensagemErro);
			    session.setAttribute("mensagemErro", mensagemErro);
	            return new ModelAndView("redirect:/cancelarHorario"); // Retorna a view com a mensagem de erro
			}
		}
		
		if (horariosId != null && !horariosId.isEmpty()) {
			horarios = montaListaHorario(horariosId);
		}
		
		try {
			if (cmd.equals("Confirmar")) {
				for (Horario h : horarios) {
					HorarioCancelado horarioCancelado = new HorarioCancelado(0, funcionario, h, LocalDate.parse(data),
							"Teste");
					hcRep.save(horarioCancelado);
				}
				mensagemSucesso = "Horarios cancelados com sucesso";
				horarios = listarHorarios(funcionario, LocalDate.parse(data));
			}
		} catch (Exception e) {
			mensagemErro = e.getMessage();
		}
		model.addAttribute("mensagemErro", mensagemErro);
		model.addAttribute("mensagemSucesso", mensagemSucesso);
		model.addAttribute("data", data);
		model.addAttribute("horarios", horarios);
		return new ModelAndView("cancelarHorario");
	}

	private List<Horario> montaListaHorario(List<Integer> horariosId) {
		List<Horario> horarios = new ArrayList<>();
		for (Integer h : horariosId) {
			Horario horario = new Horario();
			horario.setId(h);
			horarios.add(horario);
		}
		return horarios;
	}

	private List<Horario> listarHorarios(Funcionario funcionario, LocalDate data) {
		return hcRep.listaHorariosDisponiveis(data, funcionario.getId());
	}

}
