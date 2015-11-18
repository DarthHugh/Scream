package br.ifpb.monteiro.scream.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.ifpb.monteiro.scream.entities.DefinicaoDePronto;
import br.ifpb.monteiro.scream.entities.ItemProductBacklog;
import br.ifpb.monteiro.scream.entities.Produto;
import br.ifpb.monteiro.scream.entities.Projeto;
import br.ifpb.monteiro.scream.entities.Sprint;
import br.ifpb.monteiro.scream.entities.enums.DefinicaoDeProntoEnum;
import br.ifpb.monteiro.scream.services.DefinicaoDeProntoService;
import br.ifpb.monteiro.scream.services.ItemProductBacklogService;
import br.ifpb.monteiro.scream.services.ProdutoService;
import br.ifpb.monteiro.scream.services.ProjetoService;
import br.ifpb.monteiro.scream.services.SprintService;
import br.ifpb.monteiro.scream.util.jsf.ItemPBModel;
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

	@Inject
	private SprintService sprintService;
	
	@Inject
	private ItemProductBacklogService iBacklogService;

	@Inject
	private ProdutoService produtoService;
	
	private Projeto projeto;
	private Projeto projetoSelecionado;
	private Projeto projetoEscolhido;

	private List<Projeto> listProjeto;
	private List<ItemProductBacklog> itemProductBacklogs;
	private List<ItemProductBacklog> selectedItemProductBacklogs;
	private List<ItemProductBacklog> ItensProjeto;
	private List<Sprint> listSprint;
	
	private ItemPBModel ipbModel;
	
	private List<Produto> listProduto;
	private Produto produto;

	private DefinicaoDePronto definicaoPronto;
	private Sprint sprint;
	private Sprint sprintSelected;
	


	FacesContext contexto = FacesContext.getCurrentInstance();

	@PostConstruct
	public void Init(){
		
		setProjeto(new Projeto());
		setProjetoEscolhido(new Projeto());
		setProjetoSelecionado(manterProjeto());
		
		setListProjeto(projetoService.findAll());
		
		setListProduto(produtoService.findAll());
		
		
		

		if(definicaoPronto==null){
			setDefinicaoPronto(new DefinicaoDePronto());
		}
		if(sprint==null){
			sprint = new Sprint();
		}

		//		definicaoPronto = manterDefinicao();
		findAllItemPB();
		//		definicaoPronto = findDefinicaoPronto(DefinicaoDeProntoEnum.PRODUCTBACKLOG, projetoSelecionado.getId());

        ipbModel = new ItemPBModel(getItemProductBacklogs());
		
	}

	//Métodos de Projeto

	public void create() {
		registrarData();
		projeto.setIsCompleted(false);
		System.out.println(projeto.getNome());
//		projeto.setProduto(produto);
		projetoService.create(projeto);
		JsfUtil.addSuccessMessage("Projeto adicionado com sucesso!");
		redirect();
	}
	
	public void syso(){
		System.out.println("Alguma coisa!!!");
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
	
	public void updateProjeto(Produto produto){
		System.out.println(produto.getDescricao());
		proj.setProduto(produto);
		projetoService.update(proj);
		addMessage("Produto com sucesso");
	}

	public void remove(Projeto projetoSelec){
		projetoService.remove(projetoSelec);
		redirect();
	}
	
	public List<Produto> getListProduto(){
		return listProduto;
	}
	
	public void setListProduto(List<Produto> listProduto) {
		this.listProduto = listProduto;
	}
	
	public List<ItemProductBacklog> listaItemPB(){
		return itemProductBacklogService.findByProjeto(proj.getId());
	}

	//Métodos de Definição de Pronto 

	public void updateDefinicao(){

		DefinicaoDePronto dP = findDPProductBacklog(); 

		dP.setDescricao(definicaoPronto.getDescricao());

		if(validarDefinicao(dP))
			dPS.update(dP);

	}

	public void prepararDefinicao(){

		projetoEscolhido = projetoService.find(projetoEscolhido.getId());
		proj = projetoEscolhido;
		definicaoPronto = findDPProductBacklog();
		getListSprint();
		setItensProjeto(listaItemPB());

	}
	
	public void updateItens(){
		System.out.println(selectedItemProductBacklogs.size());
		for (int i = 0; i < selectedItemProductBacklogs.size(); i++) {
			selectedItemProductBacklogs.get(i).setProjeto(proj);
			iBacklogService.update(selectedItemProductBacklogs.get(i));
		}
	}

	public Boolean validarDefinicao(DefinicaoDePronto dp){

		if(dp.getDescricao() == null || dp.getDescricao().equals(""))
			return false;

		return true;
	}
	
	public String thumbMessage(){
		if(proj==null)
			return "Projeto";
		else
			return "Projeto "+proj.getNome();
	}
	
	public String listMessage(){
		if(proj.getProduto()==null)
			return "Adicione um produto";
		else
			return "Produto: "+proj.getProduto().getNome();
	}


	//Métodos de Sprint

	public void createSprint(){

		sprintService.create(sprint);
		sprint.setProjeto(proj);
		sprintService.edit(sprint);
		setSprint(new Sprint());
	}
	
	public List<Sprint> getListSprint(){
		
		List<Sprint> sprints = sprintService.findByProject(proj);
		setListSprint(sprints);
		return sprints;
	}
	
	public void setListSprint(List<Sprint> listSprint) {
		this.listSprint = listSprint;
	}

	//Métodos de Classe

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



	public Projeto manterProjeto() {
		Projeto aux = (Projeto) contexto.getExternalContext().getSessionMap().put("projeto", projetoSelecionado);
		if(aux==null)
			return new Projeto();
		else 
			return aux;
	}

	public List<ItemProductBacklog> findAllItemPB(){
		itemProductBacklogs = itemProductBacklogService.findItemPBAll();
		return itemProductBacklogs;
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	private static Projeto proj;

	public DefinicaoDePronto findDPProductBacklog(){

		DefinicaoDePronto dP = null;

		dP = dPS.findByProject(DefinicaoDeProntoEnum.PRODUCTBACKLOG, proj);

		return dP;

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

	public List<ItemProductBacklog> getItemProductBacklogs() {
		return itemProductBacklogs;
	}

	public void setItemProductBacklogs(List<ItemProductBacklog> itemProductBacklogs) {
		this.itemProductBacklogs = itemProductBacklogs;
	}

	public Projeto getProjetoSelecionado() {
		return projetoSelecionado;
	}
	
	public void setProjetoSelecionado(Projeto projetoSelecionado) {
		this.projetoSelecionado = projetoSelecionado;
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

	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}

	public Sprint getSprintSelected() {
		return sprintSelected;
	}

	public void setSprintSelected(Sprint sprintSelected) {
		this.sprintSelected = sprintSelected;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<ItemProductBacklog> getSelectedItemProductBacklogs() {
		return selectedItemProductBacklogs;
	}

	public void setSelectedItemProductBacklogs(
			List<ItemProductBacklog> selectedItemProductBacklogs) {
		this.selectedItemProductBacklogs = selectedItemProductBacklogs;
	}

	public ItemPBModel getIpbModel() {
		return ipbModel;
	}

	public void setIpbModel(ItemPBModel ipbModel) {
		this.ipbModel = ipbModel;
	}

	public List<ItemProductBacklog> getItensProjeto() {
		return ItensProjeto;
	}

	public void setItensProjeto(List<ItemProductBacklog> itensProjeto) {
		ItensProjeto = itensProjeto;
	}
	
	
	
	

}
