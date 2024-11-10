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
public class ConsultarFichaController {
	
	@Autowired
	private IFichaRepository fRep;
	@Autowired
	private IClienteRepository cRep;
	@Autowired
	private IAgendamentoRepository aRep;

	@RequestMapping(name = "consultarFicha", value = "/consultarFicha", method = RequestMethod.GET)
	public ModelAndView ConsultarFichaGet(ModelMap model, HttpSession session) {
		String mensagemErro = "";
		Ficha ficha = new Ficha();
		List<Agendamento> agendamentos = new ArrayList<>();
		
		//Verificar estado da sessao
		Funcionario funcionario = (Funcionario)session.getAttribute("sessaoFuncionario");
		if(funcionario == null) {
			mensagemErro = "Você não tem acesso a essa pagina";
			session.setAttribute("sessaoFuncionario", funcionario);
			model.addAttribute("mensagemErro", mensagemErro);
			return new ModelAndView("loginCadastroCliente");
		}
		Cliente cliente = (Cliente) session.getAttribute("clienteFicha");
		if(cliente == null ) {
			mensagemErro = "Você precisa selecionar um cliente para consultar a ficha";
			model.addAttribute("mensagemErro", mensagemErro);
			return new ModelAndView("consultarCliente");
		}
		
		try {
			agendamentos = listarAgendametos(cliente);
			ficha.setCliente(cliente);
			
			model.addAttribute("ficha", ficha);
			model.addAttribute("cliente", cliente);
			
		} catch (Exception e) {
			mensagemErro = e.getMessage();
		}
		
		model.addAttribute("agendamentos", agendamentos);
		model.addAttribute("mensagemErro", mensagemErro);
		return new ModelAndView("consultarFicha");
	}
	
	

	@RequestMapping(name = "consultarFicha", value = "/consultarFicha", method = RequestMethod.POST)
	public ModelAndView ConsultarFichaPost(ModelMap model, @RequestParam Map<String, String> param,
			HttpSession session, HttpServletRequest request) {
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		//Entrada
		String cmd = param.get("botao");
		String cpfPesquisa = param.get("cpf-pesquisa");
		String filtro = param.get("filtro");
		
		//Saida
		String mensagemErro="";
		String mensagemSucesso="";
		Ficha ficha = new Ficha();
		
		try {
			if(cmd.equals("Pesquisar")) {
				Cliente cliente = cRep.buscarPorCpf(cpfPesquisa);
				if(cliente !=null) {
					ficha.setCliente(cliente);
					model.addAttribute("ficha", ficha);
				}else {
					mensagemErro="Cpf inexistente ou não cadastrado";
				}
			}
		} catch (Exception e) {
			mensagemErro=e.getMessage();
		}
		model.addAttribute("mensagemErro", mensagemErro);
		return new ModelAndView("consultarFicha");
	}
	
	private List<Agendamento> listarAgendametos(Cliente cliente) {
		return aRep.listarAgendamentoFicha(cliente.getId());
	}
}
