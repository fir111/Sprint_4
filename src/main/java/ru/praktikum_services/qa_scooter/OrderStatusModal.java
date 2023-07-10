package ru.praktikum_services.qa_scooter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderStatusModal {
    private final WebDriver driver;

    private final By orderStatusModal = By.xpath(".//*[@id='root']//div[contains(@class, 'Order_Modal_')]");
    private final By orderStatusModalText =
            By.xpath(".//*[@id='root']//div[contains(@class, 'Order_ModalHeader')]");

    public OrderStatusModal(WebDriver driver) {
        this.driver = driver;
    }

    public void waitStatusModalIsOpen(){
        WebElement modal = driver.findElement(orderStatusModal);
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(modal));
    }
    public String getOrderStatusModalText(){
        return driver.findElement(orderStatusModalText).getText();
    }

}
