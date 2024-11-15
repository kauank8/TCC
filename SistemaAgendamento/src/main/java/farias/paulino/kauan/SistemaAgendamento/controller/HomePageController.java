package farias.paulino.kauan.SistemaAgendamento.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {

	@RequestMapping(name = "homePage", value = "/homePage", method = RequestMethod.GET)
	public ModelAndView homePageGet(ModelMap model) {

		return new ModelAndView("homePage");
	}
	
	@RequestMapping(name = "homePage", value = "/homePage", method = RequestMethod.POST)
	public ModelAndView homePagePost(ModelMap model) {
		
		return new ModelAndView("homePage");
	}
	
}
