package br.ifpb.monteiro.ads.projeto2.scream.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.BeforeClass;
import org.junit.Test;

import br.ifpb.monteiro.scream.dao.AtividadeDAO;
import br.ifpb.monteiro.scream.entities.Atividade;
import br.ifpb.monteiro.scream.entities.enums.StatusKanbanEnum;
import br.ifpb.monteiro.scream.util.jpa.EntityManagerProducer;

public class AtividadeDAOTest {

	private static AtividadeDAO atividadeDAO;

	private static EntityManager em;

	@BeforeClass
	public static void setUpBeforeClass(){
		
		atividadeDAO= new AtividadeDAO();
		EntityManagerProducer emp = new EntityManagerProducer("ScreamTest");
		em = emp.create();
		atividadeDAO.setEntityManager(em);
		
	}
	
	@Test
	public void createTest(){
		Atividade atividade=criaAtividade("Descrição Nova Atividade", "Atividade 1", 2.0, StatusKanbanEnum.TODO);
		
		List<Atividade> result=atividadeDAO.query("select atividade from Atividade atividade "
				+ "where atividade.descricao= ?1 ",atividade.getDescricao());
	
		assertEquals(atividade.getDescricao(), result.get(0).getDescricao());
	}
	
	@Test
	public void updateTest(){
		Atividade atividade=criaAtividade("Descrição Nova Atividade", "Atividade 1", 2.0, StatusKanbanEnum.TODO);
		List<Atividade> result=atividadeDAO.query("select atividade from Atividade atividade "
				+ "where atividade.descricao= ?1 ",atividade.getDescricao());
		result.get(0).setDescricao("Descrição Editada");
		result.get(0).setStatusKanbanEnum(StatusKanbanEnum.DONE);
		
		atividadeDAO.getEntityManager().getTransaction().begin();
		atividadeDAO.update(result.get(0));
		atividadeDAO.getEntityManager().getTransaction().commit();
		
		List<Atividade> result2=atividadeDAO.query("select atividade from Atividade atividade "
				+ "where atividade.descricao= ?1 ",result.get(0).getDescricao());
		
		assertEquals(result.get(0), result2.get(0));
		
		
	}
	
	@Test
	public void deleteTest(){
		Atividade atividade=criaAtividade("Descrição DELETE", "Atividade 4", 2.0, StatusKanbanEnum.DONE);
		atividadeDAO.getEntityManager().getTransaction().begin();
		atividadeDAO.delete(atividade);
		atividadeDAO.getEntityManager().getTransaction().commit();
		
		List<Atividade> result=atividadeDAO.query("select atividade from Atividade atividade "
				+ "where atividade.descricao= ?1 ",atividade.getDescricao());
		
		assertEquals(0, result.size());
		
	}
	
	@Test
	public void findById(){
		Atividade atividade=criaAtividade("Descrição FindID", "Atividade 5", 2.0, StatusKanbanEnum.DONE);
		
		atividadeDAO.getEntityManager().getTransaction().begin();
		Atividade find=atividadeDAO.findById(atividade.getId());
		atividadeDAO.getEntityManager().getTransaction().commit();
		
		assertEquals(atividade, find);
	}
	
	@Test
	public void findAll(){
		criaAtividade("Descrição Atividade 1", "Atividade 5", 2.0, StatusKanbanEnum.DONE);
		criaAtividade("Descrição Atividade 2", "Atividade 5", 2.0, StatusKanbanEnum.DONE);
		criaAtividade("Descrição Atividade 3", "Atividade 5", 2.0, StatusKanbanEnum.DONE);
		atividadeDAO.getEntityManager().getTransaction().begin();
		List<Atividade> atividades=atividadeDAO.findAll();
		atividadeDAO.getEntityManager().getTransaction().commit();
		
		assertEquals(3, atividades.size());
		
	}
	

	public Atividade criaAtividade(String descricao, String tipo,Double valorGasto, StatusKanbanEnum kanbanEnum){
		Atividade atividade= new Atividade();
		atividade.setDescricao(descricao);
		atividade.setTipoAtividade(tipo);
		atividade.setValorGasto(valorGasto);
		atividade.setStatusKanbanEnum(kanbanEnum);
		
		atividadeDAO.getEntityManager().getTransaction().begin();
		atividadeDAO.create(atividade);
		atividadeDAO.getEntityManager().getTransaction().commit();
		
		return atividade;
		
		
	}
	
}
