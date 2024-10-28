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

import farias.paulino.kauan.SistemaAgendamento.model.Funcionario;
import farias.paulino.kauan.SistemaAgendamento.model.Horario;
import farias.paulino.kauan.SistemaAgendamento.model.HorarioCancelado;
import farias.paulino.kauan.SistemaAgendamento.repository.IFuncionarioRepository;
import farias.paulino.kauan.SistemaAgendamento.repository.IHorarioCanceladoRepository;
import farias.paulino.kauan.SistemaAgendamento.repository.IHorarioRepository;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CancelarHorarioController {
	@Autowired
	private IHorarioRepository hRep;

	@Autowired
	private IHorarioCanceladoRepository hcRep;
	
	@Autowired
	private IFuncionarioRepository fRep;

	@RequestMapping(name = "cancelarHorario", value = "/cancelarHorario", method = RequestMethod.GET)
	public ModelAndView cancelarHorarioGet(ModelMap model) {
		List<Horario> horarios = listarHorarios();
		model.addAttribute("horarios", horarios);
		
		return new ModelAndView("cancelarHorario");
	}

	@RequestMapping(name = "cancelarHorario", value = "/cancelarHorario", method = RequestMethod.POST)
	public ModelAndView cancelarHorarioPost(@RequestParam Map<String, String> param,
			@RequestParam(value = "horarios", required = false) List<Integer> horariosId, ModelMap model,
			HttpServletRequest request) {

		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		//Entrada
		String cmd = param.get("botao");
		String id = param.get("id");
		String data = param.get("data");
		//Saida
		String mensagemSucesso = "";
		String mensagemErro = "";
		List<Horario> horarios = new ArrayList<>();
		if(horariosId != null && !horariosId.isEmpty()) {
			horarios = montaListaHorario(horariosId);
		}
		
		try {
			if(cmd.equals("Confirmar")) {
				Funcionario funcionario = fRep.findById(1).orElseThrow();
				for(Horario h : horarios) {
					HorarioCancelado horarioCancelado = new HorarioCancelado(0,funcionario, h, LocalDate.parse(data), "Teste");
					hcRep.save(horarioCancelado);
				}
				mensagemSucesso = "Horarios cancelados com sucesso";		
			}
		} catch (Exception e) {
			mensagemErro = e.getMessage();
		}
		return new ModelAndView("cancelarHorario");
	}

	private List<Horario> montaListaHorario(List<Integer> horariosId) {
		List<Horario> horarios = new ArrayList<>();
		for(Integer h: horariosId) {
			Horario horario = new Horario();
			horario.setId(h);
			horarios.add(horario);
		}
		return horarios;
	}

	private List<Horario> listarHorarios() {
		return hRep.findAll();
	}

}
