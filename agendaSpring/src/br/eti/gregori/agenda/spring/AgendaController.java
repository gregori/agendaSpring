package br.eti.gregori.agenda.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import br.eti.gregori.jdbc.dao.ContatoDao;
import br.eti.gregori.jdbc.modelo.Contato;

@Controller
public class AgendaController {
	@RequestMapping("novoContato")
	public String form(Long id, Model model) {
		ContatoDao dao = new ContatoDao();
		
		model.addAttribute("contato", dao.getContato(id));
		
		return "adiciona-contato";
	}
	
	@RequestMapping("adicionaContato")
	public String adiciona(Contato contato, Model model) {
		ContatoDao dao = new ContatoDao();
		
		if (contato.getId() != 0) {
			dao.altera(contato);
			model.addAttribute("mensagem", "Contato editado com sucesso.");
		} else {
			dao.adiciona(contato);
			model.addAttribute("mensagem", "Contato adicionado com sucesso.");
		}
		
		return "redirect:listaContatos";
	}
	
	@RequestMapping("removeContato")
	public String remove(Contato contato, Model model) {
		ContatoDao dao = new ContatoDao();
		dao.remove(contato);
		
		model.addAttribute("mensagem", "Contato removido com sucesso.");
		
		return "redirect:listaContatos";
	}
	
	@RequestMapping("listaContatos")
	public String lista(Model model) {
		ContatoDao dao = new ContatoDao();
		
		model.addAttribute("contatos", dao.getLista());
		
		return "lista-contatos";
		
	}
}
