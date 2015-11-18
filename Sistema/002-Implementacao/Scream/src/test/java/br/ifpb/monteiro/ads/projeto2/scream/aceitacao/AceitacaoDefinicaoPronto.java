package br.ifpb.monteiro.ads.projeto2.scream.aceitacao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AceitacaoDefinicaoPronto {
	
	private static WebDriver driver;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		driver = new FirefoxDriver();
		driver.get("localhost:8080/Scream/");
		driver.findElement(By.id("formularioLogin:username")).sendKeys("UsuarioTeste");
		driver.findElement(By.id("formularioLogin:senha")).sendKeys("minhasenha123");
		driver.findElement(By.id("formularioLogin:botaoEntrar")).click();
		driver.get("http://localhost:8080/Scream/projeto/index.xhtml");
  	    
	}

	@Test
	public void test() {
		
		System.out.println("aaaaaaaaaaaaaaaaaaaaa");
		
		driver.findElement(By.id("formProjeto:datatableProjeto:0:j_idt37")).click();
		
		driver.findElement(By.id("formProjetoSelecionado:definicaoPronto")).clear();
	    driver.findElement(By.id("formProjetoSelecionado:definicaoPronto")).sendKeys("Testando definição de Pronto");
	    driver.findElement(By.name("formProjetoSelecionado:j_idt43")).click();
		
	}

}
