package br.ifpb.monteiro.ads.projeto2.scream.aceitacao;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * @author Markus Patriota
 *
 */
public class AceitacaoAtividadeTest {

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
	public void createAtividade(){
		driver.get("http://localhost:8080/Scream/app.xhtml");
	    driver.findElement(By.xpath("//div[@id='j_idt17:j_idt18']/ul/li[3]/a/span")).click();
	    driver.findElement(By.id("formProjeto:datatableProjeto:0:j_idt37")).click();

	    driver.findElement(By.id("formProjetoSelecionado:j_idt43")).click();
	    driver.findElement(By.id("formProjetoSelecionado:datatableSprintDialog:0:j_idt51")).click();

	    driver.findElement(By.id("j_idt31:j_idt35")).click();
	    driver.findElement(By.id("j_idt31:j_idt38")).clear();
	    driver.findElement(By.id("j_idt31:j_idt38")).sendKeys("Atividade Nova");
	    driver.findElement(By.id("j_idt31:j_idt40")).clear();
	    driver.findElement(By.id("j_idt31:j_idt40")).sendKeys("12");
	    driver.findElement(By.id("j_idt31:j_idt42")).clear();
	    driver.findElement(By.id("j_idt31:j_idt42")).sendKeys("10.0");
	    new Select(driver.findElement(By.id("j_idt31:tipoAtividade"))).selectByVisibleText("Projeto");
	    driver.findElement(By.cssSelector("option[value=\"Projeto\"]")).click();
	    driver.findElement(By.id("j_idt31:buttonSave")).click();
	}
	
	@Test
	public void editeAtividade(){
		driver.findElement(By.id("j_idt31:datatableItensProduto:0:buttonEditar")).click();
		driver.findElement(By.id("j_idt31:j_idt51")).clear();
		driver.findElement(By.id("j_idt31:j_idt51")).sendKeys("Atividade Editada");
		driver.findElement(By.id("j_idt31:j_idt53")).clear();
		driver.findElement(By.id("j_idt31:j_idt53")).sendKeys("15");
		driver.findElement(By.id("j_idt31:j_idt55")).clear();
		driver.findElement(By.id("j_idt31:j_idt55")).sendKeys("10.0");
		new Select(driver.findElement(By.id("j_idt31:tipoAtividadeEdit"))).selectByVisibleText("Análise");
		driver.findElement(By.id("j_idt31:buttonEdit")).click();
	}
	
	
	
}
