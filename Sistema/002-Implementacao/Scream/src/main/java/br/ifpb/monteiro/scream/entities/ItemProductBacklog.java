package br.ifpb.monteiro.scream.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Mauricio
 */
@Entity
@Table(name = "item_product_backlog")
public class ItemProductBacklog  implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = true, length = 300, name = "descricao")
    private String descricao;
    
    @Column(nullable = true, length = 30, name = "aceitoPO")
    private boolean aceitoPO;
    
    @Column(nullable = false, length = 100, name = "data_inicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    
    @Column(nullable = true, length = 100, name = "data_termino")
    @Temporal(TemporalType.DATE)
    private Date dataTermino;
    
    @Column(nullable = true, length = 10, name = "peso")
    private int peso;
    
    @Column(nullable = false, length = 100, name = "prioridade")
    private int prioridade;
    
    @Column(nullable = false, length = 100, name = "complexidade")
    private int complexidade;
    
    @Column(nullable = false, length = 100, name = "valor_ganho")
    private Double valorGanho;
    
//    @ManyToOne
//    @JoinColumn(name="id_item_productbacklog", referencedColumnName = "id_item_productbacklog")
//    ItemProductBacklog itemProductBacklog;

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

    public boolean isAceitoPO() {
        return aceitoPO;
    }

    public void setAceitoPO(boolean aceitoPO) {
        this.aceitoPO = aceitoPO;
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

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public int getComplexidade() {
        return complexidade;
    }

    public void setComplexidade(int complexidade) {
        this.complexidade = complexidade;
    }

    public Double getValorGanho() {
        return valorGanho;
    }

    public void setValorGanho(Double valorGanho) {
        this.valorGanho = valorGanho;
    }  
    
}
