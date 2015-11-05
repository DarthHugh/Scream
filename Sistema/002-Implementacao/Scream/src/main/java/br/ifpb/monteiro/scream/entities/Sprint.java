package br.ifpb.monteiro.scream.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.persistence.annotations.CascadeOnDelete;

/**
 *
 * @author Mauricio
 */
@Entity
@Table(name="sprint")
public class Sprint implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private List<Atividade> sprintBacklog;

	@Temporal(TemporalType.DATE)
	private Date dataInicio;

	@Temporal(TemporalType.DATE)
	private Date dataTermino;

	@Column(nullable = false, name = "descricao")
	private String descricao;

	@ManyToOne(cascade=CascadeType.ALL)
	@CascadeOnDelete
	@JoinColumn(name="projeto_id")
	private Projeto projeto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Atividade> getSprintBacklog() {
		return sprintBacklog;
	}

	public void setSprintBacklog(List<Atividade> sprintBacklog) {
		this.sprintBacklog = sprintBacklog;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}


	@Override
	public String toString() {
		return "Sprint [sprintBacklog=" + sprintBacklog + ", dataInicio=" + dataInicio + ", dataTermino=" + dataTermino
				+ ", descricao=" + descricao + ", projeto=" + projeto + "]";
	}

}
