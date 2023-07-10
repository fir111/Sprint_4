package ru.praktikum_services.qa_scooter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderForm {
    private final WebDriver driver;
    private final By orderFormHeader = By.xpath(".//*[@id='root']/div/div[2]/div[1]");
    private final By nameInput = By.xpath(".//*[@id='root']//input[@placeholder='* Имя']");
    private final By surnameInput = By.xpath(".//*[@id='root']//input[@placeholder='* Фамилия']");
    private final By addressInput =
            By.xpath(".//*[@id='root']//input[@placeholder='* Адрес: куда привезти заказ']");

    private final By select = By.className("select-search__value");
    private final By selectStation = By.className("select-search__row");
    private final By stationInput = By.xpath(".//*[@id='root']//input[@placeholder='* Станция метро']");
    private final By phoneNumberInput =
            By.xpath(".//*[@id='root']//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//*[@id='root']//button[text()='Далее']");

    public OrderForm(WebDriver driver) {
        this.driver = driver;
    }

    public String getOrderFormHeader(){
        return driver.findElement(orderFormHeader).getText();
    }

    public void setName(String name){
        driver.findElement(nameInput).sendKeys(name);
    }

    public void setSurname(String surname){
        driver.findElement(surnameInput).sendKeys(surname);
    }

    public void setAddress(String address){
        driver.findElement(addressInput).sendKeys(address);
    }

    public void setStation(String station) {
        driver.findElement(select).click();
        driver.findElement(stationInput).sendKeys(station);
        WebElement[] stations = driver.findElements(selectStation).toArray(new WebElement[0]);
        WebElement selectedStation = stations[0];
        selectedStation.click();
    }

    public void setPhoneNumber(String phoneNumber){
        driver.findElement(phoneNumberInput).sendKeys(phoneNumber);
    }

    public void clickNextButton(){
        driver.findElement(nextButton).click();
    }
}
