package br.ifpb.monteiro.scream.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.ifpb.monteiro.scream.entities.Projeto;
import br.ifpb.monteiro.scream.services.ProjetoService;
import br.ifpb.monteiro.scream.util.jsf.JsfUtil;

/**
 *
 * @author Mauricio
 */
@Named
@RequestScoped
public class ProjetoController {

	@Inject
	private ProjetoService projetoService;

	private Projeto projeto;

	private Projeto projetoSelecionado;
	private List<Projeto> listProjeto;

	FacesContext contexto = FacesContext.getCurrentInstance();

	@PostConstruct
	public void Init(){
		projeto = new Projeto();
		projetoSelecionado= manterProjeto();
		listProjeto=projetoService.findAll();
	}

	public void create() {
		//		System.out.println(contaService);
		registrarData();
		projetoService.create(projeto);
		JsfUtil.addSuccessMessage("Projeto adicionado com sucesso!");
		redirect();

	}

	public void update(){
		if (projetoSelecionado.getId() == null) {
			JsfUtil.addErrorMessage("Erro ao selecionar seu produto, por favor tente mais tarde");
		} else {
			projetoService.update(projetoSelecionado);
			JsfUtil.addSuccessMessage("Produto atualizado com sucesso");
			redirect();
		}
	}

	public void remove(Projeto projetoSelec){
		projetoService.remove(projetoSelec);
	}

	private void registrarData() {
		Calendar calendar = GregorianCalendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.format(calendar.getTime());
		calendar = dateFormat.getCalendar();
		projeto.setDataInicio(calendar.getTime());
	}

	public void redirect(){
		try {//Redirect para atualização das informações
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect("/Scream/projeto/index.xhtml");
		} catch (IOException e) {
			JsfUtil.addErrorMessage("Aconteceu algo inesperado ao apagar este produto");
		}
	}

	public ProjetoService getProjetoService() {
		return projetoService;
	}

	public void setProjetoService(ProjetoService projetoService) {
		this.projetoService = projetoService;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public List<Projeto> getListProjeto() {
		return listProjeto;
	}

	public void setListProjeto(List<Projeto> listProjeto) {
		this.listProjeto = listProjeto;
	}

	public Projeto getProjetoSelecionado() {
		return projetoSelecionado;
	}

	public void setProjetoSelecionado(Projeto projetoSelecionado) {
		this.projetoSelecionado = projetoSelecionado;
	}
	public Projeto manterProjeto() {
		Projeto aux = (Projeto) contexto.getExternalContext().getSessionMap().put("projeto", projetoSelecionado);
		if(aux==null)
			return new Projeto();
		else 
			return aux;
	}
}
