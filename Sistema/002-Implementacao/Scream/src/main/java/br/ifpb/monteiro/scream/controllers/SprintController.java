package br.ifpb.monteiro.scream.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.ifpb.monteiro.scream.entities.Projeto;
import br.ifpb.monteiro.scream.entities.Sprint;
import br.ifpb.monteiro.scream.services.SprintService;

/**
 * 
 * @author Markus Patriota
 *
 */
@Named
@RequestScoped
public class SprintController {

	@Inject
	private SprintService sprintService;
	
//	@Inject
//	private ProjetoController projetoController;

	private Sprint sprint;
	private Long projetoId;
	private Projeto projeto;
	
	FacesContext contexto = FacesContext.getCurrentInstance();

	private List<Sprint> listSprint;

	@PostConstruct
	public void Init() {

		setSprint(new Sprint());		

	}

	public void detele(){
		sprintService.remove(sprint);
	}
	public void create(){
//		Projeto projeto = projetoController.getProjetoEscolhido();
//		sprint.setProjeto(projeto);
		sprintService.create(sprint);
		setSprint(new Sprint());
		addMessage("Sprint Criada com Sucesso");
	}

	public void update(){
		sprintService.edit(sprint);
		addMessage("Sprint Editada com Sucesso");
	}

	public List<Sprint> findAll(){
		return sprintService.findAll();
	}

	public Projeto manterProjeto() {
		Projeto aux = (Projeto) contexto.getExternalContext().getSessionMap().put("projeto", projeto);
		if(aux==null)
			return new Projeto();
		else 
			return aux;
	}


	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	/**
	 * 
	 * Get's and Set's
	 */
	public SprintService getSprintService() {
		return sprintService;
	}

	public void setSprintService(SprintService sprintService) {
		this.sprintService = sprintService;
	}

	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}

	public List<Sprint> getListSprint() {
		return listSprint;
	}

	public void setListSprint(List<Sprint> listSprint) {
		this.listSprint = listSprint;
	}

	public Long getProjetoId() {
		return projetoId;
	}

	public void setProjetoId(Long projetoId) {
		this.projetoId = projetoId;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}


}
