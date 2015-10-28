package br.ifpb.monteiro.ads.projeto2.scream.aceitacao;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AceitacaoProjeto {
	private static WebDriver driver;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		driver = new FirefoxDriver();
		driver.get("localhost:8080/Scream/");
		driver.findElement(By.id("formularioLogin:username")).clear();
		driver.findElement(By.id("formularioLogin:username")).sendKeys("UsuarioTeste");
		driver.findElement(By.id("formularioLogin:password")).clear();
		driver.findElement(By.id("formularioLogin:password")).sendKeys("minhasenha123");
		driver.findElement(By.id("formularioLogin:botaoEntrar")).click();
		driver.findElement(By.xpath("//div[@id='j_idt17:j_idt18']/ul/li[3]/a/span")).click();
	}

	@Test
	public void testCriarProjeto(){
		driver.findElement(By.id("formProjeto:datatableProjeto:novoProjeto")).click();
		driver.findElement(By.id("formProjeto:j_idt55")).clear();
		driver.findElement(By.id("formProjeto:j_idt55")).sendKeys("Novo");
		driver.findElement(By.id("formProjeto:dataInicio_input")).click();
		driver.findElement(By.id("formProjeto:dataInicio_input")).sendKeys("28-10-2015");
		driver.findElement(By.id("formProjeto:dataTermino_input")).click();
		driver.findElement(By.id("formProjeto:dataTermino_input")).sendKeys("30-10-2015");
		driver.findElement(By.id("formProjeto:j_idt59")).clear();
		driver.findElement(By.id("formProjeto:j_idt59")).sendKeys("12");
		driver.findElement(By.xpath("//div[@id='formProjeto:j_idt61']/div[2]/span")).click();
		driver.findElement(By.id("formProjeto:buttonSalvar")).click();

	}
	@Test
	public void testEditarProjeto(){
		driver.findElement(By.id("formProjeto:datatableProjeto:0:buttonEditar")).click();
		driver.findElement(By.id("formProjeto:j_idt45")).clear();
		driver.findElement(By.id("formProjeto:j_idt45")).sendKeys("Novo Teste");

		driver.findElement(By.id("formProjeto:dataInicioEdite_input")).click();
		driver.findElement(By.id("formProjeto:dataInicioEdite_input")).sendKeys("20-10-2015");
		driver.findElement(By.id("formProjeto:dataTerminoEdite_input")).click();
		driver.findElement(By.id("formProjeto:dataTerminoEdite_input")).sendKeys("30-10-2015");

		driver.findElement(By.xpath("//div[@id='formProjeto:editeProjetoDialog']/div")).click();
		driver.findElement(By.id("formProjeto:j_idt49")).clear();
		driver.findElement(By.id("formProjeto:j_idt49")).sendKeys("7");
		driver.findElement(By.xpath("//div[@id='formProjeto:j_idt51']/div[2]")).click();
		driver.findElement(By.id("formProjeto:buttonUpdate")).click();
	}
	@Test
	public void testDeletarProjeto(){
		driver.get("localhost:8080/Scream/projeto/index.xhtml");
		driver.findElement(By.id("formProjeto:datatableProjeto:0:buttonDelete")).click();
		driver.findElement(By.id("formProjeto:datatableProjeto:8:j_idt41")).click();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.findElement(By.id("j_idt14")).click();
	}

}
