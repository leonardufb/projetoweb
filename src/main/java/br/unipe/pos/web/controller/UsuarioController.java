
/**
 * Projeto das trilhas de especialização em desenvolvimento Web coordenada 
 * pelo professor Rodrigo Fujioka.
 * Disciplina: Web Frameworks.
 * Fontes disponíveis em https://github.com/rodrigofujioka/aulaposwebframeworks
 * 
 * Professor: Rodrigo da Cruz Fujioka
 * Ano: 2017
 * http://www.rodrigofujioka.com
 * http://www.fujideia.com.br
 * http://lattes.cnpq.br/0843668802633139
 * 
 * Contato: rcf4@cin.ufpe.br 
 */
package br.unipe.pos.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.unipe.pos.web.dao.UsuarioDAO;
import br.unipe.pos.web.model.UsuarioModel;

/**
 * @author Rodrigo C. Fujioka
 * @date 28 de abr de 2017
 * @time 17:09:28
 *
 */

@Controller
@RequestMapping("/usuario")
public class UsuarioController {	
		
	// id nome email senha
	@Autowired
	private UsuarioDAO repositorio;

	@RequestMapping("/index")
	@ResponseBody
	public String index() {
		return "index";
	}
	
	@RequestMapping(path="/form", method=RequestMethod.GET)	
	public String form(Model model) {
		model.addAttribute("usuario", new UsuarioModel());
		return "/usuario/form";
	}	
	
	
	@RequestMapping(path="/login")
	@ResponseBody
	public String login(UsuarioModel usuario){		
		
		/*UsuarioModel usuario = repositorio.findOne(id);
		if(usuario!=null) 
			return usuario.toString();*/
		
		return "Sem resultado";
	}

	@RequestMapping(path="/consultar",method=RequestMethod.GET)
	@ResponseBody
	public String consultar(
			@PathVariable(name="id") int id){		
		
		UsuarioModel usuario = repositorio.findOne(id);
		if(usuario!=null) 
			return usuario.toString();
		
		return "Sem resultado";
	}
	
	@RequestMapping(path="/remover/{id}",method=RequestMethod.GET)
	@ResponseBody
	public String remover(
			@PathVariable(name="id") int id){				
		repositorio.delete(id);		
		return "Sucesso";
	}
	
	@RequestMapping("/incluir")
	@ResponseBody
	public String incluir(UsuarioModel usuario){
		repositorio.save(usuario);
		return "Sucesso";
	}
	
	@RequestMapping(path={"/listar","/"})
	public String listar(Model model){
		List<UsuarioModel> usuarios = repositorio.findAll();
		model.addAttribute("usuarios", usuarios);
		return "/usuario/listar";
	}	
		

	public UsuarioDAO getRepositorio() {
		return repositorio;
	}

	public void setRepositorio(UsuarioDAO repositorio) {
		this.repositorio = repositorio;
	}
}
