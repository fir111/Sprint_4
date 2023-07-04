package ru.praktikum_services.qa_scooter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class OrderModal {
    private final WebDriver driver;
    private final By modal = By.xpath(".//div[@id='root']//div[contains(@class, 'Order_Modal_')]");


    public OrderModal(WebDriver driver) {
        this.driver = driver;
    }
    public WebElement getModal(){
        return driver.findElement(modal);
    }
}
