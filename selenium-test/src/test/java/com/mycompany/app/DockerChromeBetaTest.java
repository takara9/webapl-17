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


public class DockerChromeBetaTest {

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
    public void it_rest_users() {
        driver.get("http://webapl-17.test.k8s4.labo.local/list.html");
        assertThat(driver.getTitle()).contains("Hello AngularJS");

        Thread.sleep(Duration.ofSeconds(2).toMillis());
        Path recordingPath = wdm.getDockerRecordingPath();
        assertThat(recordingPath).exists();
    }

    @Test
    public void it_rest_input_user() {
        driver.get("http://webapl-17.test.k8s4.labo.local/input.html");
        assertThat(driver.getTitle()).contains("Hello AngularJS");
    }

    @Test
    public void shouldAnswerWithTrue()
    {
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








