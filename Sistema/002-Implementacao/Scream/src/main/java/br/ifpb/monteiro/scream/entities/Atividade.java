package br.ifpb.monteiro.scream.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.CascadeOnDelete;

import br.ifpb.monteiro.scream.entities.enums.StatusKanbanEnum;

/**
 * 
 * @author Markus Patriota
 *
 */
@Entity
@Table(name="atividade")
public class Atividade implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, name = "descricao")
	private String descricao;
	
	@Column(nullable = true, name = "horas")
	private String horas;
	
	
	@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="usuario_projeto_id")
	private UsuarioProjeto usuarioProjeto;

	@Column(nullable = false, name = "tipo_atividade")
	private String tipoAtividade;

	@Column(nullable = false, name = "valor_gasto")
	private double valorGasto;

	@Column(nullable = false, name = "status_kanban")
	private StatusKanbanEnum statusKanbanEnum;
	
	

	@ManyToOne(cascade=CascadeType.ALL)
	@CascadeOnDelete
	@JoinColumn(name="item_product_backlog_id")
	private ItemProductBacklog itemProductBacklog;

	//
	//private List<MembroTime> responsaveis;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTipoAtividade() {
		return tipoAtividade;
	}

	public void setTipoAtividade(String tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}

	public String getHoras() {
		return horas;
	}

	public void setHoras(String horas) {
		this.horas = horas;
	}

	public UsuarioProjeto getUsuarioProjeto() {
		return usuarioProjeto;
	}

	public void setUsuarioProjeto(UsuarioProjeto usuarioProjeto) {
		this.usuarioProjeto = usuarioProjeto;
	}

	public double getValorGasto() {
		return valorGasto;
	}

	public void setValorGasto(double valorGasto) {
		this.valorGasto = valorGasto;
	}

	public StatusKanbanEnum getStatusKanbanEnum() {
		return statusKanbanEnum;
	}

	public void setStatusKanbanEnum(StatusKanbanEnum statusKanbanEnum) {
		this.statusKanbanEnum = statusKanbanEnum;
	}

	public ItemProductBacklog getItemProductBacklog() {
		return itemProductBacklog;
	}

	public void setItemProductBacklog(ItemProductBacklog itemProductBacklog) {
		this.itemProductBacklog = itemProductBacklog;
	}
	
	
	
}
