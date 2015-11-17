package br.ifpb.monteiro.scream.controllers;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

import br.ifpb.monteiro.scream.entities.Atividade;
import br.ifpb.monteiro.scream.entities.Sprint;
import br.ifpb.monteiro.scream.entities.enums.StatusKanbanEnum;
import br.ifpb.monteiro.scream.services.AtividadeService;
import br.ifpb.monteiro.scream.services.SprintService;
import br.ifpb.monteiro.scream.util.jsf.JsfUtil;

/**
 * 
 * @author Mauricio
 *
 */
@Named
@RequestScoped
public class AtividadeController {

	@Inject
	private AtividadeService atividadeSevice;
	
	@Inject
	private SprintService sprintService;
	
	private Sprint sprintSelecionado;

	private Atividade atividade;
	
	private Atividade atividadeSelecionada;

	private List<Atividade> listatividade;	

	private DashboardModel model;
	
	FacesContext contexto = FacesContext.getCurrentInstance();

	@PostConstruct
	public void Init(){

		setSprintSelecionado(new Sprint());
		setAtividade(new Atividade());
		setAtividadeSelecionada(manterAtividade());
		prepararTela();
	}

	public void create(){
		atividade.setStatusKanbanEnum(StatusKanbanEnum.SNOOZE);
		atividadeSevice.create(atividade);
		setAtividade(new Atividade());
		redirect();
	}

	public void delete(){
		atividadeSevice.remove(atividade);
		redirect();
	}

	public void update(){
		System.out.println(atividadeSelecionada.getId());
		atividadeSevice.edit(atividadeSelecionada);
		redirect();
	}

	public List<Atividade> findAll(){
		return atividadeSevice.findAll();
	}

	public Atividade findById(Long id){
		return atividadeSevice.find(id);
	}

	public void redirect(){
		try {//Redirect para atualização das informações
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect("/Scream/atividades/index.xhtml");
		} catch (IOException e) {
			JsfUtil.addErrorMessage("Aconteceu algo inesperado. Tente recarregar a página.");
		}
	}
	
	public Atividade manterAtividade() {
		Atividade atv = (Atividade) contexto.getExternalContext().getSessionMap().put("atividade", atividadeSelecionada);
		
		if(atv==null)
			return new Atividade();
		else
			return atv;
	}
	
	public void prepararAtividade(){
		
		sprintSelecionado = sprintService.find(sprintSelecionado.getId());
		sprintSelecionada = sprintSelecionado; 
	}
	
	

	public void prepararTela(){
		model = new DefaultDashboardModel();
		DashboardColumn column1 = new DefaultDashboardColumn();
		DashboardColumn column2 = new DefaultDashboardColumn();
		DashboardColumn column3 = new DefaultDashboardColumn();
		DashboardColumn column4 = new DefaultDashboardColumn();

		column1.addWidget("sports");
		column1.addWidget("finance");

		column2.addWidget("lifestyle");
		column2.addWidget("weather");

		column3.addWidget("politics");

		model.addColumn(column1);
		model.addColumn(column2);
		model.addColumn(column3);
		model.addColumn(column4);
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
	
	public static Sprint sprintSelecionada; 

	public List<Atividade> getListatividade() {
		return listatividade;
	}

	public void setListatividade(List<Atividade> listatividade) {
		this.listatividade = listatividade;
	}

	public Atividade getAtividadeSelecionada() {
		return atividadeSelecionada;
	}

	public void setAtividadeSelecionada(Atividade atividadeSelecionada) {
		this.atividadeSelecionada = atividadeSelecionada;
	}

	public Sprint getSprintSelecionado() {
		return sprintSelecionado;
	}

	public void setSprintSelecionado(Sprint sprint) {
		this.sprintSelecionado = sprint;
	}

	public DashboardModel getModel() {
		return model;
	}

}
