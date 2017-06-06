package br.unipe.pos.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.unipe.pos.web.dao.UsuarioDAO;

@Controller
@RequestMapping("/login")
public class LoginController {
	
		// id nome email senha
		@Autowired
		private UsuarioDAO repositorio;

		@RequestMapping("/index")		
		public String index() {
			return "/login/form";
		}
		
		@RequestMapping(path="/form")			
		public String form(Model model) {
			return "/login/form";
		}				

		public UsuarioDAO getRepositorio() {
			return repositorio;
		}

		public void setRepositorio(UsuarioDAO repositorio) {
			this.repositorio = repositorio;
		}
}
