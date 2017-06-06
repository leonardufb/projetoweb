package br.unipe.pos.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.unipe.pos.web.dao.PerfilDAO;
import br.unipe.pos.web.dao.UsuarioDAO;

@Controller
public class PerfilController {

		
	// id nome email senha
	@Autowired
	private PerfilDAO repositorio;
}
