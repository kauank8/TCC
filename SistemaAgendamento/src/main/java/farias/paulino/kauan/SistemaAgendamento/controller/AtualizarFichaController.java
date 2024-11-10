package farias.paulino.kauan.SistemaAgendamento.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import farias.paulino.kauan.SistemaAgendamento.model.Agendamento;
import farias.paulino.kauan.SistemaAgendamento.model.Cliente;
import farias.paulino.kauan.SistemaAgendamento.model.Ficha;
import farias.paulino.kauan.SistemaAgendamento.model.Funcionario;
import farias.paulino.kauan.SistemaAgendamento.repository.IAgendamentoRepository;
import farias.paulino.kauan.SistemaAgendamento.repository.IClienteRepository;
import farias.paulino.kauan.SistemaAgendamento.repository.IFichaRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class AtualizarFichaController {

	@Autowired
	private IFichaRepository fRep;
	@Autowired
	private IClienteRepository cRep;
	@Autowired
	private IAgendamentoRepository aRep;

	@RequestMapping(name = "atualizarFicha", value = "/atualizarFicha", method = RequestMethod.GET)
	public ModelAndView atualizarFichaGet(ModelMap model, HttpSession session) {

		String mensagemErro = "";
		Ficha ficha = new Ficha();
		Agendamento agendamento = new Agendamento();

		// Verificar estado da sessao
		Funcionario funcionario = (Funcionario) session.getAttribute("sessaoFuncionario");
		if (funcionario == null) {
			mensagemErro = "Você não tem acesso a essa pagina";
			session.setAttribute("sessaoFuncionario", funcionario);
			model.addAttribute("mensagemErro", mensagemErro);
			return new ModelAndView("loginCadastroCliente");
		}
		Cliente cliente = (Cliente) session.getAttribute("clienteFicha");
		if (cliente == null) {
			mensagemErro = "Você precisa selecionar um cliente para consultar a ficha";
			model.addAttribute("mensagemErro", mensagemErro);
			return new ModelAndView("consultarCliente");
		}

		try {
			ficha.setCliente(cliente);

			model.addAttribute("ficha", ficha);
			model.addAttribute("cliente", cliente);

		} catch (Exception e) {
			mensagemErro = e.getMessage();
		}

		model.addAttribute("mensagemErro", mensagemErro);
		return new ModelAndView("consultarFicha");
	}

	@RequestMapping(name = "atualizarFicha", value = "/atualizarFicha", method = RequestMethod.POST)
	public ModelAndView atualizarFichaPost(ModelMap model, HttpSession session, @RequestParam Map<String, String> param,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		// Entrada
		String cmd = param.get("botao");
		String fichaId = param.get("fichaId");
		String observacoes = param.get("observacoes");
		
		// Saida
		String mensagemErro = "";
		Ficha ficha = new Ficha();
		Agendamento agendamento = new Agendamento();
		String mensagemSucesso = "";
		
		try {
			if(cmd.equals("Atualizar")) {
				ficha = fRep.findById(Integer.parseInt(fichaId)).orElseThrow();
				ficha.setObservacoes(observacoes);
				fRep.save(ficha);
				mensagemSucesso="Ficha atualizada com o sucesso";
				redirectAttributes.addFlashAttribute("mensagemSucesso", mensagemSucesso);

				model.addAttribute("mensagemSucesso", mensagemSucesso);
				return new ModelAndView("redirect:consultarFicha");
			}
//			ficha.setCliente(cliente);

			model.addAttribute("ficha", ficha);
//			model.addAttribute("cliente", cliente);

		} catch (Exception e) {
			mensagemErro = e.getMessage();
		}

		model.addAttribute("mensagemErro", mensagemErro);
		model.addAttribute("ficha", ficha);
		model.addAttribute("mensagemSucesso", mensagemSucesso);
		return new ModelAndView("atualizarFicha");
	}

}
