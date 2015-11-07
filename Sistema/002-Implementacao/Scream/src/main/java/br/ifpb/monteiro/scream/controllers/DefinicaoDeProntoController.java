/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpb.monteiro.scream.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.ifpb.monteiro.scream.entities.DefinicaoDePronto;
import br.ifpb.monteiro.scream.services.DefinicaoDeProntoService;

/**
 *
 * @author Mauricio
 */
@Named
@RequestScoped
public class DefinicaoDeProntoController {

	@Inject
	DefinicaoDeProntoService definicaoDeProntoService;

	DefinicaoDePronto definicaoDePronto;
	
	FacesContext contexto = FacesContext.getCurrentInstance();

	@PostConstruct
	public void Init(){
		this.definicaoDePronto = manterDefinicao();

	}
	
	public DefinicaoDePronto manterDefinicao() {
		DefinicaoDePronto aux = (DefinicaoDePronto) contexto.getExternalContext().getSessionMap().put("definicao", definicaoDePronto);
		if(aux==null)
			return new DefinicaoDePronto();
		else 
			return aux;
	}


	public void update(){
		definicaoDeProntoService.update(definicaoDePronto);
	}

	public List<DefinicaoDePronto> findAll(){
		List<DefinicaoDePronto> definicoes = definicaoDeProntoService.findAll();
		return definicoes;
	}

	public DefinicaoDeProntoService getDefinicaoDeProntoService() {
		return definicaoDeProntoService;
	}

	public void setDefinicaoDeProntoService(DefinicaoDeProntoService definicaoDeProntoService) {
		this.definicaoDeProntoService = definicaoDeProntoService;
	}

	public DefinicaoDePronto getDefinicaoDePronto() {
		return definicaoDePronto;
	}

	public void setDefinicaoDePronto(DefinicaoDePronto definicaoDePronto) {
		this.definicaoDePronto = definicaoDePronto;
	}




}
