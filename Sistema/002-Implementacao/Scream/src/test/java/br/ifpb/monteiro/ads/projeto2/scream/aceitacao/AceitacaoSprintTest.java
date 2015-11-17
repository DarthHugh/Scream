package br.ifpb.monteiro.ads.projeto2.scream.aceitacao;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * @author Markus Patriota
 *
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AceitacaoSprintTest {

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
	public void criarSprint(){
		driver.get("http://localhost:8080/Scream/app.xhtml");
		driver.findElement(By.xpath("//div[@id='j_idt17:j_idt18']/ul/li[3]/a/span")).click();
		driver.findElement(By.id("formProjeto:datatableProjeto:0:j_idt37")).click();

		driver.findElement(By.id("formProjetoSelecionado:j_idt45")).click();
		driver.findElement(By.id("formProjetoSelecionado:j_idt56")).clear();
		driver.findElement(By.id("formProjetoSelecionado:j_idt56")).sendKeys("Nova descrição");
		driver.findElement(By.id("formProjetoSelecionado:j_idt58")).clear();
		driver.findElement(By.id("formProjetoSelecionado:j_idt58")).sendKeys("15");
		driver.findElement(By.id("formProjetoSelecionado:j_idt59")).click();
	}


}
