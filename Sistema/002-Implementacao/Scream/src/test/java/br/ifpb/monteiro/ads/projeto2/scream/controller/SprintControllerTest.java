package br.ifpb.monteiro.ads.projeto2.scream.controller;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.ifpb.monteiro.scream.dao.SprintDAO;
import br.ifpb.monteiro.scream.entities.Sprint;
import br.ifpb.monteiro.scream.services.SprintService;
import br.ifpb.monteiro.scream.util.jpa.EntityManagerProducer;


/**
 *
 * @author Marcus Patriota
 */

@RunWith(MockitoJUnitRunner.class)
public class SprintControllerTest {
	
	private static EntityManager em;
	
	@Mock
    private SprintService sprintService;
    
    @Mock
    private SprintDAO sprintDAO;
    
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        EntityManagerProducer emp = new EntityManagerProducer("ScreamTest");
        em = emp.create();
        
    }
    
    @Test
    public void testCreate() {
        Sprint sprint = new Sprint();
        sprint.setDescricao("Nova Descrição");
        sprint.setDuracao(15);
        Mockito.when(sprintService.create(sprint)).thenReturn(true);
        
        assertEquals(true, sprintService.create(sprint));
    }
    
    
    
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
    
}
