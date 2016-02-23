package br.com.matta.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/helloWorld")
public class HelloController {

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

        // Passagem de parâmetro para a view.
		model.addAttribute("message", "Hello world!");

        // Nome da view que sera chamada.
		return "hello";
	}
}