package com.GrupoMasterExpress.GrupoMasterExpress.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.GrupoMasterExpress.GrupoMasterExpress.models.Cliente;
import com.GrupoMasterExpress.GrupoMasterExpress.models.Prospect;
import com.GrupoMasterExpress.GrupoMasterExpress.models.Fornecedor;
import com.GrupoMasterExpress.GrupoMasterExpress.models.Funcionario;
import com.GrupoMasterExpress.GrupoMasterExpress.models.Servico;

import com.GrupoMasterExpress.GrupoMasterExpress.repository.FuncionarioRepository;
import com.GrupoMasterExpress.GrupoMasterExpress.repository.ServicoRepository;
import com.GrupoMasterExpress.GrupoMasterExpress.repository.ClienteRepository;
import com.GrupoMasterExpress.GrupoMasterExpress.repository.FornecedorRepository;
import com.GrupoMasterExpress.GrupoMasterExpress.repository.ProspectRepository;

@Controller
public class BuscaController {
	
	@Autowired
	private FuncionarioRepository fr;
	
	@Autowired
	private ClienteRepository vr;
	
	@Autowired
	private ProspectRepository dr;
	
	@Autowired
	private ClienteRepository cr;
	
	@Autowired
	private FornecedorRepository er;
	
	
	@RequestMapping("/")
	public ModelAndView abrirIndex() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView buscarIndex(@RequestParam("buscar") String buscar, @RequestParam("nome") String nome) {
		
		ModelAndView mv = new ModelAndView("index");
		String mensagem = "Resultados da busca por " + buscar;
		
		if(nome.equals("nomefuncionario")) {
			mv.addObject("funcionarios", fr.findByNomes(buscar));
			
		}else if(nome.equals("nomeprospect")) {
			mv.addObject("prospects", dr.findByNomesProspect(buscar));
			
		}else if(nome.equals("nomecliente")) {
			mv.addObject("clientes", cr.findByNomesClientes(buscar));
			
		}else if(nome.equals("nomeservico")) {
			mv.addObject("servicos", vr.findByServico(buscar));
			
		}else if(nome.equals("nomefornecedor")) {
			mv.addObject("fornecedores", er.findByFornecedor(buscar));
			
		}else {
			mv.addObject("funcionarios", fr.findByNomes(buscar));
			mv.addObject("prospects", dr.findByNomesProspect(buscar));
			mv.addObject("clientes", cr.findByNomesClientes(buscar));
			mv.addObject("servicos", vr.findByServicos(buscar));
			mv.addObject("fornecedores", er.findByFornecedores(buscar));
		}
		S
		mv.addObject("mensagem", mensagem);
		
		return mv;
	}

}
