package br.ifpb.monteiro.ads.projeto2.scream.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.ifpb.monteiro.scream.dao.DefinicaoDeProntoDAO;
import br.ifpb.monteiro.scream.entities.DefinicaoDePronto;
import br.ifpb.monteiro.scream.entities.Projeto;
import br.ifpb.monteiro.scream.entities.enums.DefinicaoDeProntoEnum;
import br.ifpb.monteiro.scream.services.DefinicaoDeProntoService;
import br.ifpb.monteiro.scream.services.ProjetoService;
import br.ifpb.monteiro.scream.services.SecurityService;
import br.ifpb.monteiro.scream.util.jpa.EntityManagerProducer;

@RunWith(MockitoJUnitRunner.class)
public class DefinicaoProntoTest {

	private static DefinicaoDePronto definicaoDePronto;

	private static SecurityService securityService;

	@Mock 
	private DefinicaoDeProntoDAO definicaoDeProntoDAO;

	@Mock
	private static DefinicaoDeProntoService definicaoDeProntoService;

	@Mock
	private static ProjetoService projetoService;

	private static EntityManager entityManager;

	@BeforeClass
	public static void setUpBeforeClass(){
		EntityManagerProducer emp = new EntityManagerProducer("ScreamTest");
		entityManager = emp.create();
		definicaoDePronto= new DefinicaoDePronto();
		definicaoDeProntoService= Mockito.mock(DefinicaoDeProntoService.class);
		projetoService= Mockito.mock(ProjetoService.class);
		

	}

	@Test 
	public void testUpdate(){
		Projeto projeto= new Projeto();
		projeto.setNome("Teste 1");
		Mockito.when(projetoService.create(projeto)).thenReturn(true);//Uma definição de pronto só pode ser criado junto com projeto. 
		List<DefinicaoDePronto> prontos= new ArrayList<DefinicaoDePronto>();
		prontos.add(definicaoDePronto);
		Mockito.when(definicaoDeProntoService.findByProjeto(projeto)).thenReturn(prontos);//não importo o que esta implementado

		prontos.get(0).setDescricao("Nova Descrição");
		Mockito.when(definicaoDeProntoService.update(prontos.get(0))).thenReturn(true);


	}

	@AfterClass
	public static void tearDownClass() {

	}

	@After
	public void tearDown() {
	}

	@Before
	public void setUp() {

	}


}
