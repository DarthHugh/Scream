package br.ifpb.monteiro.scream.controllers;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.ifpb.monteiro.scream.entities.Atividade;
import br.ifpb.monteiro.scream.services.AtividadeService;

/**
 * 
 * @author Markus Patriota
 *
 */
@Named
@RequestScoped
public class AtividadeController {

	private AtividadeService atividadeSevice;
	
	private Atividade atividade;
	
	private List<Atividade> listatividade;	
	
	public void create(){
		
	}
	
	public void delete(){
	
	}
	
	public void update(){
		
	}
	
	public List<Atividade> findAll(){
		return atividadeSevice.findAll();
	}

	/**
	 * 
	 * Get's and Set's
	 */
	public AtividadeService getAtividadeSevice() {
		return atividadeSevice;
	}

	public void setAtividadeSevice(AtividadeService atividadeSevice) {
		this.atividadeSevice = atividadeSevice;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public List<Atividade> getListatividade() {
		return listatividade;
	}

	public void setListatividade(List<Atividade> listatividade) {
		this.listatividade = listatividade;
	}

}
