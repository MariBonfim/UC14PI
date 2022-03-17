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

import com.GrupoMasterExpress.GrupoMasterExpress.models.Cliente;
import com.GrupoMasterExpress.GrupoMasterExpress.models.Servico;
import com.GrupoMasterExpress.GrupoMasterExpress.repository.ClienteRepository;
import com.GrupoMasterExpress.GrupoMasterExpress.repository.ServicoRepository;

@Controller
public class ServicoController {

	@Autowired
	private ServicoRepository vr;
	
	@Autowired
	private ClienteRepository cr;

	@RequestMapping("/inserirServico")
	public String form() {
		return "servico/inserir-servico";
	}

	@RequestMapping(value = "/inserirServico", method = RequestMethod.POST)
	public String form(@Valid Servico servico, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos...");
			return "redirect:/inserirServico";
		}

		vr.save(servico);
		attributes.addFlashAttribute("mensagem", "Servico cadastrado com sucesso!");
		return "redirect:/inserirServico";
	}

	
	@RequestMapping("/servicos")
	public ModelAndView listaServico() {
		ModelAndView mv = new ModelAndView("servico/listar-servicos");
		Iterable<Servico> servicos = vr.findAll();
		mv.addObject("servicos", servicos);
		return mv;
	}

	@RequestMapping("/servico/{codigo}")
	public ModelAndView detalhesServico(@PathVariable("codigo") long codigo) {
		Vaga vaga = vr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("servico/detalhes-servico");
		mv.addObject("servico", servico);

		Iterable<Cliente> cliente = cr.findByservico(cliente);
		mv.addObject("clientes", clientes);

		return mv;

	}


	@RequestMapping("/excluirServico")
	public String deletarServico(long codigo) {
		Servico servico = vr.findByCodigo(codigo);
		vr.delete(servico);
		return "redirect:/servico";
	}

	@RequestMapping(value = "/servico/{codigo}", method = RequestMethod.POST)
	public String detalhesServico(@PathVariable("codigo") long codigo, @Valid Cliente cliente,
			BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/vaga/{codigo}";
		}

		
		if (cr.findByCNPJ(cliente.getCNPJ()) != null) {
			attributes.addFlashAttribute("mensagem_erro", "CNPJ invalido");
			return "redirect:/servico/{codigo}";
		}

		Servico servico = vr.findByCodigo(codigo);
		cliente.setServico(servico);
		cr.save(cliente);
		attributes.addFlashAttribute("mensagem", "Cliente adionado com sucesso!");
		return "redirect:/servico/{codigo}";
	}

	
	@RequestMapping("/deletarCliente")
	public String deletarCliente(String CNPJ) {
		Cliente cliente = cr.findByCNPJ(cnpj);
		Servico servico = cliente.getServico();
		String codigo = "" + servico.getCodigo();

		cr.delete(cliente);

		return "redirect:/servico/" + codigo;

	}

	
	@RequestMapping("/atualizar-servico")
	public ModelAndView editarServico(long codigo) {
		Servico servico = vr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("servico/atualizar-servico");
		mv.addObject("servico", servico);
		return mv;
	}
	@RequestMapping(value = "/atualizar-servico", method = RequestMethod.POST)
	public String updateServico(@Valid Servico servico, BindingResult result, RedirectAttributes attributes) {
		vr.save(servico);
		attributes.addFlashAttribute("success", "Servico alterado com sucesso!");

		long codigoLong = servico.getCodigo();
		String codigo = "" + codigoLong;
		return "redirect:/servico/" + codigo;
	}

}
