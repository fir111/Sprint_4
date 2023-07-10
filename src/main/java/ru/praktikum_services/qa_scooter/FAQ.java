package ru.praktikum_services.qa_scooter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class FAQ {
    private final WebDriver driver;
    private WebElement header;
    private final By faqHeader = By.xpath(".//*[@id='root']//div[text()='Вопросы о важном']");
    private final By notHiddenAnswer =
            By.xpath(".//*[@id='root']//div[@class='accordion__panel' and not(@hidden)]/p");
    public FAQ(WebDriver driver) {
        this.driver = driver;
    }
    public void setFAQHeader(){
        this.header = driver.findElement(faqHeader);
    }

    public void scrollToHeader(){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView();", header);
    }
    public String getAnswerByQuestion(String question){
        String xpath = String.format(".//*[@id='root']//div[@class='accordion__button' and text()='%s']", question);
        WebElement element = driver.findElement(By.xpath(xpath));
        element.click();
        String hiddenXPath =
                String.format(".//*[@id='root']//div[@class='accordion__button' and text()='%s']/ancestor::div[@class='accordion__item']/*[@class='accordion__panel']/p", question);
        WebElement hiddenElement = driver.findElement(By.xpath(hiddenXPath));
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(hiddenElement));
        return driver.findElement(notHiddenAnswer).getText();
    }
}
