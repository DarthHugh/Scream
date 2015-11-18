package br.ifpb.monteiro.ads.projeto2.scream.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.BeforeClass;
import org.junit.Test;

import br.ifpb.monteiro.scream.dao.ProjetoDAO;
import br.ifpb.monteiro.scream.dao.SprintDAO;
import br.ifpb.monteiro.scream.entities.Projeto;
import br.ifpb.monteiro.scream.entities.Sprint;
import br.ifpb.monteiro.scream.util.jpa.EntityManagerProducer;

/**
 * @author Marcus Patriota
 *
 */
public class SprintDAOTest {

	private static ProjetoDAO projetoDAO;
	private static SprintDAO sprintDao;
	private static EntityManager em;
	private static Projeto projeto;


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		sprintDao= new SprintDAO();
		projetoDAO=new ProjetoDAO();
		EntityManagerProducer emp = new EntityManagerProducer("ScreamTest");
		em = emp.create();  

		projetoDAO.setEntityManager(em);
		sprintDao.setEntityManager(em); 

		//criando projeto
		projeto= new Projeto();
		projeto.setNome("Teste");
		projeto.setIsCompleted(false);

	}

	@Test
	public void createTest(){

		Sprint result=criarSprint("Sprint Teste", projeto);
		assertEquals(result.getDescricao(), "Sprint Teste");
	}

	@Test
	public void testFindAll() {
		List<Sprint> listResult=findAll();
		List<Sprint> listFind= sprintDao.findAll();

		assertEquals(listResult, listFind);
	}

	@Test
	public void delteTest(){
		Sprint sprintResult= criarSprint("Outro test", projeto);
		
		sprintDao.getEntityManager().getTransaction().begin();
		sprintDao.delete(sprintResult);
		sprintDao.getEntityManager().getTransaction().commit();
		
		List<Sprint> sprint = sprintDao.query("select sp from Sprint sp "
				+ "where sp.descricao= ?1",sprintResult.getDescricao());

		assertEquals(0, sprint.size());
	}

	@Test
	public void updateTest(){
		String descricao="Sprint Nova Edite"; 
		criarSprint(descricao, projeto);

		List<Sprint> sprintEdite= sprintDao.query("select sp from Sprint sp "
				+ "where sp.descricao= ?1 ",descricao);
		Sprint sprintArray=sprintEdite.get(0);
		descricao= "Sprint Edite";  
		sprintArray.setDescricao(descricao);

		sprintDao.getEntityManager().getTransaction().begin();
		sprintDao.update(sprintEdite.get(0));
		sprintDao.getEntityManager().getTransaction().commit();

		List<Sprint> validar= sprintDao.query("select sp from Sprint sp "
				+ "where sp.descricao= ?1", descricao);

		assertEquals("Sprint Edite", validar.get(0).getDescricao());

	}
	@Test
	public void findById(){
		Sprint sprint=criarSprint("Sprint Nova Edite", projeto);
		sprintDao.getEntityManager().getTransaction().begin();
		Sprint result=sprintDao.findById(sprint.getId());
		sprintDao.getEntityManager().getTransaction().commit();
		
		assertEquals(sprint, result);
	}


	public Sprint criarSprint(String descricao, Projeto projeto){
		Sprint sprint= new Sprint();
		sprint.setDescricao(descricao);
		sprint.setProjeto(projeto);
		sprintDao.getEntityManager().getTransaction().begin();
		sprintDao.create(sprint);
		sprintDao.getEntityManager().getTransaction().commit();
		return sprint;
	}

	public List<Sprint> findAll(){
		return sprintDao.query("select sp from Sprint sp");
	}	

}
