package ru.praktikum_services.qa_scooter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CookieButton {
    private final WebDriver driver;
    private final By cookieButton = By.xpath("//*[@id='rcc-confirm-button']");

    public CookieButton(WebDriver driver) {
        this.driver = driver;
    }
    public void clickCookieButton(){
        driver.findElement(cookieButton).click();
    }
}
