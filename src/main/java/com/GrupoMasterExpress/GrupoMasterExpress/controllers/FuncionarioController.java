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

import com.GrupoMasterExpress.GrupoMasterExpress.models.Prospect;
import com.GrupoMasterExpress.GrupoMasterExpress.models.Funcionario;
import com.GrupoMasterExpress.GrupoMasterExpress.repository.ProspectRepository;
import com.GrupoMasterExpress.GrupoMasterExpress.repository.FuncionarioRepository;

@Controller
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository fr;

	@Autowired
	private ProspectRepository dr;

	@RequestMapping("/inserirFuncionario")
	public String form() {
		return "funcionario/inserir-funcionario";
	}

	
	@RequestMapping(value = "/inserirFuncionario", method = RequestMethod.POST)
	public String form(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/inserirFuncionario";
		}

		fr.save(funcionario);
		attributes.addFlashAttribute("mensagem", "Funcionário cadastrado com sucesso!");
		return "redirect:/cadastrarFuncionario";
	}

	
	@RequestMapping("/funcionarios")
	public ModelAndView listaFuncionarios() {
		ModelAndView mv = new ModelAndView("funcionario/lista-funcionario");
		Iterable<Funcionario> funcionarios = fr.findAll();
		mv.addObject("funcionarios", funcionarios);
		return mv;
	}

	
	@RequestMapping("/detalhes-funcionario/{id}")
	public ModelAndView detalhesFuncionario(@PathVariable("id") long id) {
		Funcionario funcionario = fr.findById(id);
		ModelAndView mv = new ModelAndView("funcionario/detalhes-funcionario");
		mv.addObject("funcionarios", funcionario);

	
		Iterable<Prospect> prospect = dr.findByFuncionario(funcionario);
		mv.addObject("prospect", prospect);

		return mv;

	}

	
	@RequestMapping(value="/detalhes-funcionario/{id}", method = RequestMethod.POST)
	public String detalhesFuncionarioPost(@PathVariable("id") long id, Prospect prospect, BindingResult result,
			RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/detalhes-funcionario/{id}";
		}
		
		if(dr.findByCNPJ(prospect.getCNPJ()) != null) {
			attributes.addFlashAttribute("mensagem_erro", "CNPJ inválido");
			return "redirect:/detalhes-funcionario/{id}";
		}
		
		Funcionario funcionario = fr.findById(id);
		prospect.setFuncionario(funcionario);
		dr.save(prospect);
		attributes.addFlashAttribute("mensagem", "Prospect adicionado com sucesso");
		return "redirect:/detalhes-funcionario/{id}";
		
	}
	

	@RequestMapping("/deletarFuncionario")
	public String deletarFuncionario(long id) {
		Funcionario funcionario = fr.findById(id);
		fr.delete(funcionario);
		return "redirect:/funcionarios";
		
	}
	
	@RequestMapping("/atualizar-funcionario")
	public ModelAndView editarFuncionario(long id) {
		Funcionario funcionario = fr.findById(id);
		ModelAndView mv = new ModelAndView("funcionario/atualizar-funcionario");
		mv.addObject("funcionario", funcionario);
		return mv;
	}
	

	@RequestMapping(value = "/atualizar-funcionario", method = RequestMethod.POST)
	public String updateFuncionario(@Valid Funcionario funcionario,  BindingResult result, RedirectAttributes attributes){
		
		fr.save(funcionario);
		attributes.addFlashAttribute("success", "Funcionário alterado com sucesso!");
		
		long idLong = funcionario.getId();
		String id = "" + idLong;
		return "redirect:/detalhes-funcionario/" + id;
		
	}
	
	
	@RequestMapping("/excluirProspect")
	public String deletarProspect(String cnpj) {
		Prospect prospect = dr.findByCNPJ(cnpj);
		
		Funcionario funcionario = prospect.getFuncionario();
		String codigo = "" + funcionario.getId();
		
		dr.delete(prospect);
		return "redirect:/detalhes-funcionario/" + codigo;
	
	}
}
