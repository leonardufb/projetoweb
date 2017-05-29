
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

import br.unipe.pos.web.dao.ContatoDAO;
import br.unipe.pos.web.model.ContatoModel;

/**
 * @author Rodrigo C. Fujioka
 * @date 28 de abr de 2017
 * @time 17:09:28
 *
 */

@Controller
@RequestMapping("/contato")
public class ContatoController {	
	
	
	// id nome email senha
	@Autowired
	private ContatoDAO repositorio;

	@RequestMapping("/index")
	@ResponseBody
	public String index() {
		return "index";
	}
	
	@RequestMapping(path="/form", method=RequestMethod.GET)	
	public String form(Model model) {
		model.addAttribute("contato", new ContatoModel());
		return "/contato/contatoform";
	}

	@RequestMapping(path="/consultar",method=RequestMethod.GET)
	@ResponseBody
	public String consultar(
			@PathVariable(name="id") int id){		
		
		ContatoModel contato = repositorio.findOne(id);
		if(contato!=null) 
			return contato.toString();
		
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
	public String incluir(ContatoModel contato){
		repositorio.save(contato);
		return "Sucesso";
	}
	
	@RequestMapping(path={"/listar","/"} , method=RequestMethod.GET)
	public List<ContatoModel> listar(){
		List<ContatoModel> lista = 
				repositorio.findAll();
		return lista;
	}	
		

	public ContatoDAO getRepositorio() {
		return repositorio;
	}

	public void setRepositorio(ContatoDAO repositorio) {
		this.repositorio = repositorio;
	}



}
