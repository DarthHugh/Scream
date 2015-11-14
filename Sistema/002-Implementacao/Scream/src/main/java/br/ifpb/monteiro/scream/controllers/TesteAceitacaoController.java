/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpb.monteiro.scream.controllers;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import br.ifpb.monteiro.scream.entities.ItemProductBacklog;
import br.ifpb.monteiro.scream.entities.TesteAceitacao;
import br.ifpb.monteiro.scream.services.TesteAceitacaoService;
/**
 *
 * @author Mauricio
 */
@Named(value="testeAceitacaoController")
@RequestScoped
public class TesteAceitacaoController {

	@Inject
	private TesteAceitacaoService criterioAceitacaoService;

	private TesteAceitacao criterioAceitacao;

	private List<TesteAceitacao> listAceitacao;

	private ItemProductBacklog selectItemProductBacklog;

	FacesContext contexto = FacesContext.getCurrentInstance();

	@PostConstruct
	public void Init(){

		if(contexto.getExternalContext().getSessionMap().get("item")==null){
			setSelectItemProductBacklog(new ItemProductBacklog());
		}else{
			setSelectItemProductBacklog((ItemProductBacklog) contexto.getExternalContext().getSessionMap().get("item"));
		}
		atualizarLista();
		criterioAceitacao = new TesteAceitacao();        

	}

	public void create(){

		if (validarTA(criterioAceitacao)){
			criterioAceitacaoService.create(criterioAceitacao);
			criterioAceitacao.setItemProductBacklog(selectItemProductBacklog);
			criterioAceitacaoService.update(criterioAceitacao);
			atualizarLista();
			setCriterioAceitacao(new TesteAceitacao());
		}
	}

	public void remove(TesteAceitacao entity) {
		this.criterioAceitacaoService.remove(entity);
		atualizarLista();
	}

	public void update(TesteAceitacao entity){
		this.criterioAceitacaoService.update(entity);
	}

	public static Long idItemPB; 

	public void manterItem(){
		contexto.getExternalContext().getSessionMap().put("item", selectItemProductBacklog);
	}

	public Boolean validarTA(TesteAceitacao tA){
		if (tA.getDescricao()==null || tA.getDescricao().equals("")){
			return false;
		}else{
			return true;
		}
	}

	public void atualizarLista(){
		listAceitacao = criterioAceitacaoService.find(buscaIdURL());
	}

	public void onRowEdit(RowEditEvent event){
		FacesMessage msg = new FacesMessage("Teste de aceitação editado", ((TesteAceitacao) event.getObject()).getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		TesteAceitacao t = (TesteAceitacao) event.getObject();
		
		if (validarTA(t)){
			update(t);
		}
		atualizarLista();
	}

	public void onRowCancel(RowEditEvent event){
		FacesMessage msg = new FacesMessage("Teste de aceitação edição cancelada", ((TesteAceitacao) event.getObject()).getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	//Pesquisas no Banco

	private Long buscaIdURL(){

		String idAux = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idItem");
		if(idAux != null)
			idItemPB = Long.parseLong(idAux);

		return idItemPB;
	}

	public int count() {
		return criterioAceitacaoService.count();
	}

	public TesteAceitacao find(Long id) {
		return (TesteAceitacao) criterioAceitacaoService.find(id);
	}


	public List<TesteAceitacao> findRange(int[] range) {
		return criterioAceitacaoService.findRange(range);
	}


	public List<TesteAceitacao> findAll(){
		List<TesteAceitacao> criterioAceitacao = criterioAceitacaoService.findAll();
		return criterioAceitacao;
	}

	//Get's and Set's

	public TesteAceitacao getCriterioAceitacao() {
		return criterioAceitacao;
	}


	public ItemProductBacklog getSelectItemProductBacklog() {
		return selectItemProductBacklog;
	}


	public void setSelectItemProductBacklog(ItemProductBacklog selectItemProductBacklog) {
		this.selectItemProductBacklog = selectItemProductBacklog;
	}


	public void setCriterioAceitacao(TesteAceitacao criterioAceitacao) {
		this.criterioAceitacao = criterioAceitacao;
	}


	public List<TesteAceitacao> getListAceitacao() {
		return listAceitacao;
	}


	public void setListAceitacao(List<TesteAceitacao> listAceitacao) {
		this.listAceitacao = listAceitacao;
	}


	public TesteAceitacaoService getCriterioAceitacaoService() {
		return criterioAceitacaoService;
	}


	public void setCriterioAceitacaoService(
			TesteAceitacaoService criterioAceitacaoService) {
		this.criterioAceitacaoService = criterioAceitacaoService;
	}

}