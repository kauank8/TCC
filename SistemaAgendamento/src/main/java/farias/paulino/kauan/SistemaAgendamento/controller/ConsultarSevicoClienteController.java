package farias.paulino.kauan.SistemaAgendamento.controller;

import java.io.UnsupportedEncodingException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import farias.paulino.kauan.SistemaAgendamento.model.Cliente;
import farias.paulino.kauan.SistemaAgendamento.model.Servico;
import farias.paulino.kauan.SistemaAgendamento.repository.IServicoRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class ConsultarSevicoClienteController {
	@Autowired
	private IServicoRepository sRep;


	@RequestMapping(name = "consultarServicoCliente", value = "/consultarServicoCliente", method = RequestMethod.GET)
	public ModelAndView consultarServicoGet(ModelMap model, HttpSession session) {
		String mensagemErro = "";
		
		//Verificar estado da sessao
		Cliente cliente = (Cliente)session.getAttribute("sessaoCliente");
		if(cliente == null) {
			mensagemErro = "Você não tem acesso a essa pagina";
			session.setAttribute("sessaoCliente", cliente);
			model.addAttribute("mensagemErro", mensagemErro);
			return new ModelAndView("loginCadastroCliente");
		}
		try {
			List<Servico> servicos = listar();
			model.addAttribute("cliente", cliente);
			model.addAttribute("servicos", servicos);
		} catch (Exception e) {
			mensagemErro = e.getMessage();
		}
		model.addAttribute("mensagemErro", mensagemErro);
		return new ModelAndView("consultarServicoCliente");
	}
	
	@RequestMapping(name = "consultarServicoCliente", value = "/consultarServicoCliente", method = RequestMethod.POST)
	public ModelAndView consultarServicoPost(@RequestParam Map<String, String> param,
			@RequestParam(value = "servicosSelecionados", required = false) List<Integer> servicosId, ModelMap model,
			HttpServletRequest request, HttpSession session) {

		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		//Entrada
		String cmd = param.get("botao");
		String id = param.get("id");
		String filtro = param.get("filtro");

		//Saida
		String mensagemSucesso = "";
		String mensagemErro = "";
		Servico servico = new Servico();
		List<Servico> servicos = new ArrayList<>();
		
		try {
			if(cmd.equals("Pesquisar")) {
				if(!filtro.isEmpty() && !filtro.isBlank()) {
				servicos = sRep.buscarPorTitulo(filtro);
				if(servicos.isEmpty()) {
					mensagemErro="Não existe nenhum servico com este titulo";
					servicos = listar();
				}
				}else {
				servicos = listar();
				}
			}
			if(cmd.equals("Reservar")) {
				if(servicosId !=null) {
				servicos = montaListaServicosSelecionados(servicosId);
				session.setAttribute("servicosSelecionados", servicos);
		        return new ModelAndView("redirect:/agendarServico");
//				model.addAttribute("servicos",servicos);
//				return new ModelAndView("agendarServico");
				}
				else {
					mensagemErro="Para realizar um agendamento é necessario selecionar ao menos um servico";
					servicos = listar();
				}
			}
		} catch (Exception e) {
			mensagemErro = trataErros(e.getMessage());
		}
		
		model.addAttribute("mensagemErro", mensagemErro);
		model.addAttribute("mensagemSucesso", mensagemSucesso);
		model.addAttribute("servicos",servicos);
		return new ModelAndView("consultarServicoCliente");
		
	}
	
	private List<Servico> montaListaServicosSelecionados(List<Integer> servicosId) {
		List<Servico> servicos = new ArrayList<>();
		for (Integer s : servicosId) {
			Servico servico = buscar(s);
			servicos.add(servico);
		}
		return servicos;
	}

	private String trataErros(String message) {
		return null;
	}

	private Servico buscar(int id) {
		return sRep.findById(id).orElse(new Servico());
	}

	private List<Servico> listar() {
		return sRep.findAll();
	}

}
