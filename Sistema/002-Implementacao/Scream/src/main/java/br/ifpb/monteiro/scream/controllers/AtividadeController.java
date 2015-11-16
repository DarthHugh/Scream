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
import br.ifpb.monteiro.scream.entities.enums.StatusKanbanEnum;
import br.ifpb.monteiro.scream.services.AtividadeService;
import br.ifpb.monteiro.scream.util.jsf.JsfUtil;

/**
 * 
 * @author Markus Patriota
 *
 */
@Named
@RequestScoped
public class AtividadeController {

	@Inject
	private AtividadeService atividadeSevice;

	private Atividade atividade;

	private List<Atividade> listatividade;	

	private DashboardModel model;

	@PostConstruct
	public void Init(){

		setAtividade(new Atividade());
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
		atividadeSevice.edit(atividade);
		redirect();
	}

	public List<Atividade> findAll(){
		return atividadeSevice.findAll();
	}

	public Atividade findById(Long id){
		return atividadeSevice.find(id);
	}

	public void redirect(){
		try {//Redirect para atualiza��o das informações
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect("/Scream/atividades/index.xhtml");
		} catch (IOException e) {
			JsfUtil.addErrorMessage("Aconteceu algo inesperado. Tente recarregar a página.");
		}
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

	public List<Atividade> getListatividade() {
		return listatividade;
	}

	public void setListatividade(List<Atividade> listatividade) {
		this.listatividade = listatividade;
	}

	public DashboardModel getModel() {
		return model;
	}

}
