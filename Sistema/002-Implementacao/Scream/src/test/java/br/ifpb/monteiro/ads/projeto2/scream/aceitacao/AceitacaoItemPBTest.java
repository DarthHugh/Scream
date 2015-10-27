package br.ifpb.monteiro.ads.projeto2.scream.aceitacao;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AceitacaoItemPBTest {

	private static WebDriver driver;
	
	 @BeforeClass
	    public static void setUpBeforeClass() throws Exception{
	        driver = new FirefoxDriver();
	        driver.get("localhost:8080/Scream/");
	        driver.findElement(By.id("formularioLogin:username")).sendKeys("adm");
	        driver.findElement(By.id("formularioLogin:senha")).sendKeys("123");
	        driver.findElement(By.id("formularioLogin:botaoEntrar")).click();
//	        driver.get("localhost:8080/Scream/produto/index.xhtml");
	        driver.findElement(By.id("formProduto:verItens")).click();
	    }
	    
	 @Test
	    public void testCriarItem(){
	        
//	        driver.findElement(By.id("formItensDialog:novoItem")).click();
//	        driver.findElement(By.id("formItensDialog:")).sendKeys("Descrição do item testado.");
//	        driver.findElement(By.id("formItensDialog:")).sendKeys("2");
//	        driver.findElement(By.id("formItensDialog:")).sendKeys("5");
//	        driver.findElement(By.id("formItensDialog:")).sendKeys("4");
//	        driver.findElement(By.id("formItensDialog:")).sendKeys("6");
//	        driver.findElement(By.id("formItensDialog:aceitoPO")).click();
//	        driver.findElement(By.id("formItensDialog:buttonSalvar")).click();
	    }
	
	 @Test
	    public void testEditarItem(){
	        
//		 driver.get("localhost:8080/scream/itensProduto/index.xhtml");
//	        driver.findElement(By.xpath("//tbody[@id='formItensDialog:datatableItensProduto_data']/tr[3]/td[2]")).click();
//	        driver.findElement(By.id("formItensDialog:datatableItensProduto:j_idt40")).click();
//	        driver.switchTo().activeElement();
//	        driver.findElement(By.id("formItensDialog:")).clear();
//	        driver.findElement(By.id("formItensDialog:")).sendKeys("Descrição Editada");
//	        driver.findElement(By.id("formItensDialog:")).clear();
//	        driver.findElement(By.id("formItensDialog:")).sendKeys("8");
//	        driver.findElement(By.id("formItensDialog:")).clear();
//	        driver.findElement(By.id("formItensDialog:")).sendKeys("12");
//	        driver.findElement(By.id("formItensDialog:")).clear();
//	        driver.findElement(By.id("formItensDialog:")).sendKeys("5");
//	        driver.findElement(By.id("formItensDialog:")).clear();
//	        driver.findElement(By.id("formItensDialog:")).sendKeys("8");
//	        driver.findElement(By.id("formItensDialog:aceitoPO")).click();
//	        driver.findElement(By.id("formItensDialog:buttonEditar")).click();
	    }
	
	 @Test
	    public void testExcluirProduto(){
	        
//	        driver.get("localhost:8080/scream/itensProduto/index.xhtml");
//	        driver.findElement(By.xpath("//tbody[@id='formItensDialog:datatableItensProduto_data']/tr[4]/td[2]")).click();
//	        driver.findElement(By.id("formItensDialog:datatableItensProduto:j_idt41")).click();
//	        driver.findElement(By.id("formItensDialog:datatableItensProduto:j_idt44")).click();
	 }
}
