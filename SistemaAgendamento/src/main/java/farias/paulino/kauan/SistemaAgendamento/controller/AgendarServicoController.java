package farias.paulino.kauan.SistemaAgendamento.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import farias.paulino.kauan.SistemaAgendamento.model.Funcionario;
import farias.paulino.kauan.SistemaAgendamento.model.Horario;
import farias.paulino.kauan.SistemaAgendamento.model.Servico;
import farias.paulino.kauan.SistemaAgendamento.repository.IAgendamentoRepository;
import farias.paulino.kauan.SistemaAgendamento.repository.IFuncionarioRepository;
import farias.paulino.kauan.SistemaAgendamento.repository.IHorarioRepository;
import farias.paulino.kauan.SistemaAgendamento.repository.IServicoRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class AgendarServicoController {
	@Autowired
	IFuncionarioRepository fRep;
	
	@Autowired
	IServicoRepository sRep;
	
	@Autowired
	IHorarioRepository hRep;
	
	@Autowired
	IAgendamentoRepository aRep;
	
	@RequestMapping(name = "agendarServico", value = "/agendarServico", method = RequestMethod.GET)
	public ModelAndView agendarServicoGet(ModelMap model, HttpSession session, @RequestParam Map<String, String> param
			// @RequestParam(value = "servicos", required = false) List<Servico> servicos) {
			) {
		// Saida
		String mensagemErro = "";
		List<Funcionario> funcionarios = new ArrayList<>();
		List<Servico> servicos = new ArrayList<>();
		Double precoTotal = 0.0;
		
		// Verificar estado da sessao
		Cliente cliente = (Cliente) session.getAttribute("sessaoCliente");
		if (cliente == null) {
			mensagemErro = "Você não tem acesso a essa pagina";
			session.setAttribute("sessaoCliente", cliente);
			model.addAttribute("mensagemErro", mensagemErro);
			return new ModelAndView("loginCadastroCliente");
		}

		try {
			funcionarios = listarFuncionarios();
			if(servicos.isEmpty()) {
				servicos = (List<Servico>) session.getAttribute("servicosSelecionados");
				servicos = montaServicoSelecionado(servicos);
			}
			precoTotal = calculaPrecoTotal(servicos);
		} catch (Exception e) {
			mensagemErro = e.getMessage();
		}
		model.addAttribute("cliente", cliente);
		model.addAttribute("funcionarios", funcionarios);
		model.addAttribute("servicos", servicos);
		model.addAttribute("data", LocalDate.now());
		model.addAttribute("mensagemErro", mensagemErro);
		model.addAttribute("precoTotal", precoTotal);
		return new ModelAndView("agendarServico");
	}

	
	@RequestMapping(name = "agendarServico", value = "/agendarServico", method = RequestMethod.POST)
	public ModelAndView agendarServicoPost(ModelMap model, HttpSession session, @RequestParam Map<String, String> param,
			RedirectAttributes redirectAttributes
			//@RequestParam(value = "servicos", required = false) List<Integer> //servicosSelecionados
			) {
		
		// Entrada
		String funcionarioId = param.get("funcionario");
		List<Servico> servicos = new ArrayList<>();
		Double precoTotal = 0.0;
		String data = param.get("data");
		String cmd = param.get("botao");
		String horario = param.get("horarioSelecionado");
		
		// Saida
		List<Funcionario> funcionarios = new ArrayList<>();
		String mensagemErro = "";
		String mensagemSucesso = "";
		Funcionario funcionario = new Funcionario();
		List<Horario> horarios = new ArrayList<>();
		Agendamento agendamento = new Agendamento();

		try {
			servicos = (List<Servico>) session.getAttribute("servicosSelecionados");
			servicos = montaServicoSelecionado(servicos);
			precoTotal = calculaPrecoTotal(servicos);
			
			if (funcionarioId != null && !funcionarioId.isEmpty() && !funcionarioId.isBlank()) {
				funcionarios = listarFuncionarios();
				funcionario = buscarFuncionario(funcionarioId);
				if (data != null && !data.isEmpty() && !data.isBlank()) {
					horarios = listarHorarios(data, funcionarioId);
					horarios = listarHorariosServicos(horarios, servicos);
				}
			}
			if(data.isEmpty()) {
				data = LocalDate.now().toString();
			}
			

			if(cmd != null && cmd.equals("Confirmar")) {
				agendamento.setCliente((Cliente) session.getAttribute("sessaoCliente"));
				agendamento.setData(LocalDate.parse(data));
				agendamento.setFuncionario(funcionario);
				
				//hora inicio
				Horario h = new Horario();
				h = hRep.findById(Integer.parseInt(horario)).orElseThrow();
				agendamento.setHoraInicio(h.getHora());
				
				//horaFim
				 int duracaoTotal = 0;
				    for (Servico servico : servicos) {
				        duracaoTotal += servico.getDuracao();
				    }
				agendamento.setHoraFim(agendamento.getHoraInicio().plusMinutes(duracaoTotal-1));
				
				agendamento.setId(0);
				agendamento.setServicos(servicos);
				agendamento.setValorTotal(precoTotal);
				
				aRep.save(agendamento);
				mensagemSucesso="Agendamento realizado com sucesso<br> Funcionario: " + funcionario.getNome() 
				+ "<br>Data: "  + data + "<br>Horario: " + agendamento.getHoraInicio() +" horas" + "<br>Obrigado Pela Preferência!";
				
				session.removeAttribute("servicosSelecionados");
				
//				mensagemSucesso = "Teste";
//				model.addAttribute("mensagemSucesso", mensagemSucesso);
//				return new ModelAndView("redirect:/consultarServicoCliente");
				redirectAttributes.addFlashAttribute("mensagemSucesso", mensagemSucesso);
				return new ModelAndView("redirect:/consultarServicoCliente");
			}
			
		} catch (Exception e) {
			mensagemErro = "Erro" + e.getMessage();
		}

		model.addAttribute("servicos", servicos);
		model.addAttribute("data", data);
		model.addAttribute("mensagemErro", mensagemErro);
		model.addAttribute("mensagemSucesso", mensagemSucesso);
		model.addAttribute("funcionarios", funcionarios);
		model.addAttribute("funcionario", funcionario);
		model.addAttribute("precoTotal", precoTotal);
		model.addAttribute("horarios", horarios);
		return new ModelAndView("agendarServico");
	}


	private List<Horario> listarHorariosServicos(List<Horario> horarios, List<Servico> servicos) {
//		List<Horario> horariosDisponiveis = new ArrayList<>();
	/*    
	    int duracaoTotal = 0;
	    for (Servico servico : servicos) {
	        duracaoTotal += servico.getDuracao();
	    }
	  
	    int bloco = duracaoTotal/60;
	    int sizeHorarios = horarios.size();
	    int contRemocao = 0;
	    int y=0;
	    for(int x=0; x<sizeHorarios;x++) {
	    	int z=1;
	    	Horario horarioBase = horarios.get(x);
	    	for(y = x+1; z < bloco;z++) {
	    		if(y==sizeHorarios) {
	    			if(bloco>1) {
	    				horarios.remove(y-1);
	    			}
	    			break;
	    		}
	    		Horario proximoHorario = horarios.get(y);
	    		if(horarioBase.getHora().plusMinutes(60) == proximoHorario.getHora()) {
	    			horarioBase = proximoHorario;
	    			y+=1;
	    		}else {
	    			horarios.remove(x);
	    			contRemocao+=1;
	    			break;
	    		}
	    	}
	    	x-=contRemocao;
	    	if(x<0) {
	    		x=-1;
	    	}
	    	contRemocao=0;
	    	y=0;
	    	sizeHorarios = horarios.size();
	    }*/
		
		  // Calcula a duração total dos serviços em minutos
	    int duracaoTotal = servicos.stream().mapToInt(Servico::getDuracao).sum();
	    
	    // Calcula quantos blocos de 60 minutos são necessários
	    int blocosNecessarios = duracaoTotal / 60;
	    
	    List<Horario> horariosDisponiveis = new ArrayList<>();
	    
	    // Itera sobre a lista de horários disponíveis
	    for (int i = 0; i < horarios.size(); i++) {
	        Horario horarioBase = horarios.get(i);
	        boolean blocoEncontrado = true;

	        // Verifica se existem horários disponíveis para o número de blocos necessários
	        for (int j = 1; j < blocosNecessarios; j++) {
	            // Verifica se ainda estamos dentro do tamanho da lista de horários
	            if (i + j >= horarios.size()) {
	                blocoEncontrado = false; // Não há mais horários disponíveis
	                break;
	            }
	            Horario proximoHorario = horarios.get(i + j);
	            // Verifica se o próximo horário está disponível e se é sequencial
	            if (!horarioBase.getHora().plusMinutes(60).equals(proximoHorario.getHora())) {
	                blocoEncontrado = false; // Não é sequencial
	                break;
	            }
	            // Avança para o próximo horário
	            horarioBase = proximoHorario;
	        }

	        // Se um bloco completo foi encontrado, adicione o horário base à lista de horários disponíveis
	        if (blocoEncontrado) {
	            horariosDisponiveis.add(horarios.get(i));
	        }
	    }
	 // Ordena a lista de horários disponíveis do menor para o maior
//	    Collections.sort(horariosDisponiveis, (h1, h2) -> h1.getHora().compareTo(h2.getHora()));
//	    return horariosDisponiveis;
	    // Retorna a lista ordenada de horários disponíveis
        return horariosDisponiveis.stream()
                                  .sorted((h1, h2) -> h1.getHora().compareTo(h2.getHora()))
                                  .collect(Collectors.toList());
	}


	private Funcionario buscarFuncionario(String funcionarioId) {
		return fRep.findById(Integer.parseInt(funcionarioId)).orElseThrow();
	}

	private List<Funcionario> listarFuncionarios() {
		return fRep.findAll();
	}

	private Servico buscarServico(int id) {
		return sRep.findById(id).orElse(new Servico());
	}

	private List<Servico> montaServicoSelecionado(List<Servico> servicosSelecionados) {
		List<Servico> servicos = new ArrayList<>();
		for(Servico s : servicosSelecionados) {
			Servico servico = sRep.findById(s.getId()).orElseThrow();
			servicos.add(servico);
		}
		return servicos;
	}
	

	private Double calculaPrecoTotal(List<Servico> servicos) {
		Double precoTotal = 0.0;
		for(Servico s : servicos) {
			precoTotal+=s.getPreco();
		}
		return precoTotal;
	}
	
	private List<Horario> listarHorarios(String data, String funcionarioId) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dataFormatada = LocalDate.parse(data);
		return hRep.listaHorariosDisponiveis_2(dataFormatada, Integer.parseInt(funcionarioId)) ;
	}

}
