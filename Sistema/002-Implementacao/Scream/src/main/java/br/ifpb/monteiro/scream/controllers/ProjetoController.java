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

import br.ifpb.monteiro.scream.entities.DefinicaoDePronto;
import br.ifpb.monteiro.scream.entities.ItemProductBacklog;
import br.ifpb.monteiro.scream.entities.Projeto;
import br.ifpb.monteiro.scream.entities.enums.DefinicaoDeProntoEnum;
import br.ifpb.monteiro.scream.services.DefinicaoDeProntoService;
import br.ifpb.monteiro.scream.services.ItemProductBacklogService;
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

	@Inject
	private ItemProductBacklogService itemProductBacklogService;

	@Inject
	private DefinicaoDeProntoService dPS;

	private Projeto projeto;

	private Projeto projetoSelecionado;

	private Projeto projetoEscolhido;

	private List<Projeto> listProjeto;
	private List<ItemProductBacklog> itemProductBacklogs;
	private DefinicaoDePronto definicaoPronto;


	FacesContext contexto = FacesContext.getCurrentInstance();

	@PostConstruct
	public void Init(){
		projeto = new Projeto();

		setProjetoEscolhido(new Projeto());

		projetoSelecionado= manterProjeto();
		listProjeto = projetoService.findAll();

		if(definicaoPronto==null){
			setDefinicaoPronto(new DefinicaoDePronto());
		}

		//		definicaoPronto = manterDefinicao();
		findAllItemPB();
		//		definicaoPronto = findDefinicaoPronto(DefinicaoDeProntoEnum.PRODUCTBACKLOG, projetoSelecionado.getId());
	}

	public void create() {

		registrarData();
		projetoService.create(projeto);
		JsfUtil.addSuccessMessage("Projeto adicionado com sucesso!");
		redirect();

	}

	public void update(){

		if (projetoSelecionado.getId() == null) {
			JsfUtil.addErrorMessage("Erro ao selecionar seu projeto, por favor tente mais tarde");
		} else {
			projetoService.update(projetoSelecionado);
			JsfUtil.addSuccessMessage("Projeto atualizado com sucesso");
			redirect();
		}
	}


	public void updateDefinicao(){

		DefinicaoDePronto dP = findDPProductBacklog(); 

		dP.setDescricao(definicaoPronto.getDescricao());

		if(validarDefinicao(dP))
			dPS.update(dP);

	}

	public void remove(Projeto projetoSelec){
		projetoService.remove(projetoSelec);
		redirect();

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
			JsfUtil.addErrorMessage("Aconteceu algo inesperado ao apagar este projeto");
		}
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

	public void prepararDefinicao(){

		projetoEscolhido = projetoService.find(projetoEscolhido.getId());
		definicaoPronto = findDPProductBacklog();

	}

	public List<ItemProductBacklog> findAllItemPB(){
		itemProductBacklogs = itemProductBacklogService.findItemPBAll();
		return itemProductBacklogs;
	}

	public DefinicaoDePronto findDPProductBacklog(){

		DefinicaoDePronto dP = dPS.findByProject(DefinicaoDeProntoEnum.PRODUCTBACKLOG, projetoEscolhido);

		return dP;

	}

	public Boolean validarDefinicao(DefinicaoDePronto dp){

		if(dp.getDescricao() == null || dp.getDescricao().equals(""))
			return false;

		return true;
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


public Projeto getProjetoEscolhido() {
	return projetoEscolhido;
}

public void setProjetoEscolhido(Projeto projetoEscolhido) {
	this.projetoEscolhido = projetoEscolhido;
}

public DefinicaoDePronto getDefinicaoPronto() {
	return definicaoPronto;
}

public void setDefinicaoPronto(DefinicaoDePronto definicaoPronto) {
	this.definicaoPronto = definicaoPronto;
}

}
