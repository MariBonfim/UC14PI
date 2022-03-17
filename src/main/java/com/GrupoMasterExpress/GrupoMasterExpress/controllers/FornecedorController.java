package com.GrupoMasterExpress.GrupoMasterExpress.controllers;

import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.GrupoMasterExpress.GrupoMasterExpress.models.Fornecedor;
import com.GrupoMasterExpress.GrupoMasterExpress.repository.FornecedorRepository;

@Controller
public class FornecedorController {

	
	  @Autowired private FornecedorRepository er;
	  
	  
	  
		@RequestMapping("/inserirFornecedor")
		public String form() {
			return "fornecedor/inserirFornecedor";
		}
		
		@RequestMapping(value="/inserirFornecedor", method=RequestMethod.POST)
		public String form(@Valid Fornecedor fornecedor, BindingResult result, RedirectAttributes attributes) {
			
			if (result.hasErrors()) {
				attributes.addFlashAttribute("mensagem", "Verifique os campos...");
				return "redirect:/inserirFornecedor";
			}

			er.save(fornecedor);
			attributes.addFlashAttribute("mensagem", "Fornecedor cadastrado com sucesso!");
			return "redirect:/inserirFornecedor";
		}

	  

		@RequestMapping("/fornecedor")
		public ModelAndView listaFornecedor() {
			ModelAndView mv = new ModelAndView("fornecedor/listar-fornecedores");
			Iterable<Fornecedor> fornecedor = er.findAll();
			mv.addObject("fornecedor", fornecedor);
			return mv;
		}
		
		@RequestMapping("/deletarFornecedor")
	public String deletarFornecedor(long id) {
		Fornecedor fornecedor = mv.findById(id);
		mv.delete(fornecedor);
		return "redirect:/fornecedor";
		}

		@RequestMapping("/atualizar-fornecedor")
		public ModelAndView atualizarFornecedor(long empid) {
			Fornecedor emp = new Fornecedor();
			emp = er.findById(id);
			ModelAndView mv = new ModelAndView("fornecedor/atualizar-fornecedor");
			mv.addObject("emp", emp);
			return mv;
		}
		
		@RequestMapping(value="/atualizar-fornecedor", method=RequestMethod.POST)
		public String updateFornecedor(@Valid Fornecedor emp, BindingResult result, RedirectAttributes attributes) {
			er.save(emp);
			attributes.addFlashAttribute("mensagem", "Fornecedor alterado com sucesso!");
			return "redirect:/atualizar-fornecedor?empid=" + emp.getId();
		}

			 

}
