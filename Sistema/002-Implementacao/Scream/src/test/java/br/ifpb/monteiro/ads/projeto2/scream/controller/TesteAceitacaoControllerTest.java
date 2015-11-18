
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

import br.ifpb.monteiro.scream.dao.TesteAceitacaoDAO;
import br.ifpb.monteiro.scream.entities.Projeto;
import br.ifpb.monteiro.scream.entities.TesteAceitacao;
import br.ifpb.monteiro.scream.services.TesteAceitacaoService;
import br.ifpb.monteiro.scream.util.jpa.EntityManagerProducer;




/**
 *
 * @author Markus
 */
@RunWith(MockitoJUnitRunner.class)
public class TesteAceitacaoControllerTest {
    
    
    private Projeto projeto;
    
    private static EntityManager em;
    
    @Mock
    private TesteAceitacaoService aceitacaoservice;
    
    @Mock
    private TesteAceitacaoDAO aceitacaoDAO;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        EntityManagerProducer emp = new EntityManagerProducer("ScreamTest");
        em = emp.create();
    }
    
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
    
    @Test
    public void testCreate() {
        TesteAceitacao aceitacao = new TesteAceitacao();
        aceitacao.setDescricao("Destrição de teste");
        aceitacao.setEstadoTeste(true);      
        
        Mockito.when(aceitacaoservice.create(aceitacao)).thenReturn(true);
        
        assertEquals(true, aceitacaoservice.create(aceitacao));
    }
}
