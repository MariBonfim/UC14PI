package com.GrupoMasterExpress.GrupoMasterExpress.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.GrupoMasterExpress.GrupoMasterExpress.models.Servico;
import com.GrupoMasterExpress.GrupoMasterExpress.models.Cliente;
import com.GrupoMasterExpress.GrupoMasterExpress.repository.ServicoRepository;
import com.GrupoMasterExpress.GrupoMasterExpress.ClienteRepository;

@Controller
public class ClienteController {

	@Autowired
	private ClienteRepository vr;
	
	@Autowired
	private ServicoRepository cr;


	@RequestMapping("/inserirCliente")
	public String form() {
		return "cliente/inserir-cliente";
	}


	@RequestMapping(value = "/inserirCliente", method = RequestMethod.POST)
	public String form(@Valid Servico servico, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos...");
			return "redirect:/inserirCliente";
		}

		mv.save(servico);
		attributes.addFlashAttribute("mensagem", "Servico cadastrado com sucesso!");
		return "redirect:/inserirServico";
	}

	
	@RequestMapping("/servico")
	public ModelAndView listaServico() {
		ModelAndView mv = new ModelAndView("servico/lista-servico");
		Iterable<Servico> servico = mv.findAll();
		mv.addObject("servico", servico);
		return mv;
	}


	@RequestMapping("/servico/{codigo}")
	public ModelAndView detalhesServico(@PathVariable("codigo") long codigo) {
		Servico servico = vr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("servico/detalhes-servico");
		mv.addObject("servico", servico);

		Iterable = findbyServicoRepository =  (servico) = cr.findByServico(servico);
		mv.addObject("servicos", servicos);

		return mv;
	}

	@RequestMapping("/excluirServico")
	public String deletarServico(long codigo) {
		Servico servico = vr.findByCodigo(codigo);
		vr.delete(servico);
		return "redirect:/servico";
	}

	
	@RequestMapping(value = "/servico/{codigo}", method = RequestMethod.POST)
	public String detalhesServico(@PathVariable("codigo") long codigo, @Valid Servico servico,
			BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/servico/{codigo}";
		}

		
		if (cr.findByCNPJ(servico.getCNPJ()) != null) {
			attributes.addFlashAttribute("mensagem_erro", "CNPJ inválido");
			return "redirect:/servico/{codigo}";
		}

		Servico ServicoRepository = vr.findByCodigo(codigo);
		servico.setServico(servico);
		cr.save(servico);
		attributes.addFlashAttribute("mensagem", "Servico adionado com sucesso!");
		return "redirect:/servico/{codigo}";
	}

	
	@RequestMapping("/excluirServico")
	public String deletarServico(String cnpj) {
		Servico servico = cr.findByCNPJ(CNPJ);
		Cliente cliente = servico.getCliente();
		String codigo = "" + cliente.getCodigo();

		cr.delete(servico);

		return "redirect:/vaga/" + codigo;

	}

	
	@RequestMapping("/atualizar-cliente")
	public ModelAndView editarCliente(long codigo) {
		Cliente cliente = vr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("cliente/atualizar-cliente");
		mv.addObject("cliente", cliente);
		return mv;
	}

	
	@RequestMapping(value = "/atualizar-cliente", method = RequestMethod.POST)
	public String updateCliente(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {
		vr.save(cliente);
		attributes.addFlashAttribute("success", "Cliente alterado com sucesso!");

		long codigoLong = cliente.getCodigo();
		String codigo = "" + codigoLong;
		return "redirect:/cliente/" + codigo;
	}

}
