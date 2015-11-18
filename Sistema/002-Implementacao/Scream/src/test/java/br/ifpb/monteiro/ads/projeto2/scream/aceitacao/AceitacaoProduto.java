/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpb.monteiro.ads.projeto2.scream.aceitacao;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author Mauricio
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AceitacaoProduto {


	private static WebDriver driver;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		driver = new FirefoxDriver();
		driver.get("localhost:8080/Scream/");
		driver.findElement(By.id("formularioLogin:username")).sendKeys("UsuarioTeste");
		driver.findElement(By.id("formularioLogin:senha")).sendKeys("minhasenha123");
		driver.findElement(By.id("formularioLogin:botaoEntrar")).click();
		driver.get("localhost:8080/Scream/produto/index.xhtml");
	}

	@Test
	public void testCriar() throws Exception {
		
	    driver.findElement(By.xpath("//div[@id='j_idt17:j_idt18']/ul/li[2]/a/span")).click();
	    driver.findElement(By.id("formProduto:datatableProdutos:j_idt34")).click();
	    driver.findElement(By.id("formProduto:novoNome")).clear();
	    driver.findElement(By.id("formProduto:novoNome")).sendKeys("Novo Produto");
	    driver.findElement(By.id("formProduto:j_idt54")).clear();
	    driver.findElement(By.id("formProduto:j_idt54")).sendKeys("Nova descrição");
	    driver.findElement(By.id("formProduto:novoProduto")).click();

		
	}

	@Test
	public void testEditar() throws Exception {

		driver.get("localhost:8080/Scream/produto/index.xhtml");
	    driver.findElement(By.id("formProduto:datatableProdutos:2:j_idt42")).click();
	    driver.findElement(By.id("formProduto:nome")).clear();
	    driver.findElement(By.id("formProduto:nome")).sendKeys("Novo Produto Editado");
	    driver.findElement(By.id("formProduto:j_idt51")).clear();
	    driver.findElement(By.id("formProduto:j_idt51")).sendKeys("Nova descrição Editada");
	    driver.findElement(By.id("formProduto:editarProduto")).click();
	}

	@Test
	public void testExcluirProduto(){

		driver.get("localhost:8080/Scream/produto/index.xhtml");
		driver.findElement(By.id("formProduto:datatableProdutos:2:j_idt43")).click();
	    driver.findElement(By.id("formProduto:datatableProdutos:4:j_idt46")).click();
	}
}
