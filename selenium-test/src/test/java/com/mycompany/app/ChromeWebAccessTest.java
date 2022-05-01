package com.mycompany.app;

import java.time.Duration;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import static io.github.bonigarcia.wdm.WebDriverManager.isDockerAvailable;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;


public class ChromeWebAccessTest {

    WebDriver driver;

    WebDriverManager wdm = WebDriverManager.chromedriver().browserInDocker()
        .enableRecording();
        //.browserVersion("beta");

    @BeforeEach
    public void setupTest() {
        //assumeThat(isDockerAvailable()).isTrue();
        driver = wdm.create();
    }

    @AfterEach
    public void teardown() {
        wdm.quit();
    }

    /* テスト本体 */
    @Test
    public void it_トップページ表示() throws Exception {
        driver.get("http://webapl-17.test.k8s4.labo.local/");
        assertThat(driver.getTitle()).contains("ビジターブック");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
	
        driver.findElement(By.xpath("//a[text()='ビジターのリスト表示']")).click(); 
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        Path recordingPath = wdm.getDockerRecordingPath();
        assertThat(recordingPath).exists();
    }
    
    @Test
    public void it_ユーザーのリスト表示() throws Exception {
        driver.get("http://webapl-17.test.k8s4.labo.local/list.html");
        assertThat(driver.getTitle()).contains("Hello AngularJS");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
	
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        Path recordingPath = wdm.getDockerRecordingPath();
        assertThat(recordingPath).exists();
    }

    @Test
    public void it_ユーザー登録画面() throws Exception {
        driver.get("http://webapl-17.test.k8s4.labo.local/input.html");
        assertThat(driver.getTitle()).contains("Hello AngularJS");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
	
        Thread.sleep(Duration.ofSeconds(2).toMillis());
        Path recordingPath = wdm.getDockerRecordingPath();
        assertThat(recordingPath).exists();
    }

    @Test
    public void shouldAnswerWithTrue() throws Exception {
        driver.get("https://google.com");
        Assertions.assertEquals("Google", driver.getTitle());
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement searchBox = driver.findElement(By.name("q"));
        WebElement searchButton = driver.findElement(By.name("btnK"));
        searchBox.sendKeys("Selenium");
        searchButton.click();

        searchBox = driver.findElement(By.name("q"));
        Assertions.assertEquals("Selenium", searchBox.getAttribute("value"));
    }
}
