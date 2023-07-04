package ru.praktikum_services.qa_scooter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Order {
    
    private final WebDriver driver;
    private final By upperOrderButton =
            By.xpath("//*[@id='root']/div/div/div[1]/div[2]/button[text()='Заказать']");
    private final By lowerOrderButton =
            By.xpath("//*[@id='root']/div/div/div[4]/div[2]/div[5]/button[text()='Заказать']");
    private final By orderFormHeader = By.xpath("//*[@id='root']/div/div[2]/div[1]");
    public Order(WebDriver driver){
        this.driver = driver;
    }
    public void clickUpperOrderButton(){
        driver.findElement(upperOrderButton).click();
    }
    public void clickLowerOrderButton() { driver.findElement(lowerOrderButton).click(); }
    public void waitOrderFormIsOpen(){
        WebElement header = driver.findElement(orderFormHeader);
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(header));
    }
}
