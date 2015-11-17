package br.ifpb.monteiro.ads.projeto2.scream.controller;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.ifpb.monteiro.scream.dao.AtividadeDAO;
import br.ifpb.monteiro.scream.entities.Atividade;
import br.ifpb.monteiro.scream.entities.ItemProductBacklog;
import br.ifpb.monteiro.scream.entities.enums.StatusKanbanEnum;
import br.ifpb.monteiro.scream.services.AtividadeService;
import br.ifpb.monteiro.scream.util.jpa.EntityManagerProducer;

/**
 * @author Markus Patriota
 *
 */
public class AtividadeControllerTest {

	private static EntityManager em;

	@Mock
	private AtividadeService atividadeService;

	@Mock
	private AtividadeDAO atividadeDAO;
	
	
	@BeforeClass
    public static void setUpBeforeClass() throws Exception {
        EntityManagerProducer emp = new EntityManagerProducer("ScreamTest");
        em = emp.create();
        
    }
	
	
	@Test
    public void testCreate() {
		ItemProductBacklog itemProductBacklog= new ItemProductBacklog();
		itemProductBacklog.setDescricao("Novo");
		itemProductBacklog.setComplexidade(5);
        Atividade atividade= new Atividade();
        atividade.setDescricao("Nova Descrição");
        atividade.setTipoAtividade("Atividade Teste");
        atividade.setStatusKanbanEnum(StatusKanbanEnum.TODO);
        atividade.setValorGasto(5.0);
        atividade.setItemProductBacklog(itemProductBacklog);
        
        
//        Mockito.when(atividadeService.create(atividade)).thenReturn(true);
//        
//        assertEquals(true, atividadeService.create(atividade));
        
    }
	
	
	 @AfterClass
	    public static void tearDownAfterClass() throws Exception {
	 }	
	
}
