package br.ifpb.monteiro.scream.controllers;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.ifpb.monteiro.scream.entities.ItemProductBacklog;
import br.ifpb.monteiro.scream.entities.Produto;
import br.ifpb.monteiro.scream.services.ItemProductBacklogService;
import br.ifpb.monteiro.scream.services.ProdutoService;
import br.ifpb.monteiro.scream.util.jsf.JsfUtil;

/**
 *
 * @author Mauricio
 */
@Named(value = "itemProductBacklogController")
@RequestScoped
public class ItemProductBacklogController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ItemProductBacklogService itemProductBacklogService;

	@Inject
	private ProdutoService produtoService;

	private List<Produto> listProduto;


	private Produto produtoSelecionado;

	private ItemProductBacklog itemProductBacklog;

	private ItemProductBacklog selectItemProductBacklog;

	private List<ItemProductBacklog> listItensProduto;

	FacesContext contexto = FacesContext.getCurrentInstance();

	@PostConstruct
	public void Init(){
		//setSelectItemProductBacklog();

		produtoSelecionado = produtoService.find(buscaIdURL());
		itemProductBacklog = new ItemProductBacklog();

		if(contexto.getExternalContext().getSessionMap().get("item")==null){
			setSelectItemProductBacklog(new ItemProductBacklog());
		}else{
			setSelectItemProductBacklog((ItemProductBacklog) contexto.getExternalContext().getSessionMap().get("item"));
		} 
		listItensProduto= itemProductBacklogService.findItensProduto(buscaIdURL());
	}

	public void create(){
		gerarData();

		itemProductBacklogService.create(itemProductBacklog);
		produtoSelecionado.getListItensProduct().add(itemProductBacklog);
		itemProductBacklog.setProduto(produtoSelecionado);
		this.itemProductBacklogService.update(itemProductBacklog);
		redirect();
	}

	/**
	 * M�todo remove o ItemProductBacklog selecionado do Banco de dados
	 * @param itemProductBacklog
	 */
	public void delete(ItemProductBacklog itemProductBacklog) {
		itemProductBacklogService.remove(itemProductBacklog);
		redirect();
	}



	public void update(){
		if(selectItemProductBacklog.getId() == null){
			System.out.println("Deu Merge");
		}else{			
			itemProductBacklogService.update(selectItemProductBacklog);			
		}
		redirect();
	}

	private void gerarData() {
		Calendar calendar = GregorianCalendar.getInstance();
		SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.format(calendar.getTime());
		calendar = dateFormat.getCalendar();
		itemProductBacklog.setDataInicio(calendar.getTime());
	}

	private Long buscaIdURL(){

		String idAux = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		if(idAux != null)
			idProduto = Long.parseLong(idAux);

		return idProduto;
	}

	public void redirect(){
		try {//Redirect para atualiza��o das informa��es
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect("/Scream/itensProduto/index.xhtml?id="+idProduto);
		} catch (IOException e) {
			JsfUtil.addErrorMessage("Aconteceu algo inesperado ao apagar este produto");
		}
	}
	
//	public void atualizarId(){
//		if(contexto.getExternalContext().getSessionMap().get("item")==null){
//			setSelectItemProductBacklog(new ItemProductBacklog());
//		}else{
//			setSelectItemProductBacklog((ItemProductBacklog) contexto.getExternalContext().getSessionMap().get("item"));
//			idItemPB = getSelectItemProductBacklog().getId();
//		}
//		
//		
//		System.out.println(idItemPB);
//	}

	public void manterItem() {
		contexto.getExternalContext().getSessionMap().put("item", selectItemProductBacklog);
	}

	//Pesquisas no Banco

	public int count() {
		return itemProductBacklogService.count();
	}

	public ItemProductBacklog find(Long id) {
		return (ItemProductBacklog) itemProductBacklogService.find(id);
	}


	public List<ItemProductBacklog> findRange(int[] range) {
		return itemProductBacklogService.findRange(range);
	}

	public List<ItemProductBacklog> findAll(){
		List<ItemProductBacklog> itemProductBacklogs = itemProductBacklogService.findAll();
		return itemProductBacklogs;
	}

	public ItemProductBacklog getSelectItemProductBacklog() {
		return selectItemProductBacklog;
	}

	public void setSelectItemProductBacklog(ItemProductBacklog selectItemProductBacklog) {
		this.selectItemProductBacklog = selectItemProductBacklog;
	}

	public ItemProductBacklog getItemProductBacklog() {
		return itemProductBacklog;
	}

	public void setItemProductBacklog(ItemProductBacklog itemProductBacklog) {
		this.itemProductBacklog = itemProductBacklog;
	}

	public Produto getProduto() {
		return produtoSelecionado;
	}

	public void setProduto(Produto produto) {
		this.produtoSelecionado = produto;
	}

	public List<Produto> getListProduto() {
		return listProduto;
	}

	public void setListProduto(List<Produto> listProduto) {
		this.listProduto = listProduto;
	}

	
	public static Long idProduto;
	//	
	//	public static Long idItemPB;


	public List<ItemProductBacklog> getListItensProduto() {
		return listItensProduto;
	}

	public void setListItensProduto(List<ItemProductBacklog> listItensProduto) {
		this.listItensProduto = listItensProduto;
	}

}