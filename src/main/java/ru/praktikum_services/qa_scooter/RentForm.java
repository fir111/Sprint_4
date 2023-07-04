package ru.praktikum_services.qa_scooter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RentForm {
    private final WebDriver driver;
    private final By rentFormHeader = By.xpath("/html/body/div/div/div[2]/div[1]");
    private final By datePickerInput =
            By.xpath(".//*[@id='root']//input[@placeholder='* Когда привезти самокат']");
    private final By rentTerm = By.className("Dropdown-arrow");
    private final By colourGrey = By.id("grey");
    private final By colourBlack = By.id("black");
    private final By commentInput =
            By.xpath(".//*[@id='root']//input[@placeholder='Комментарий для курьера']");
    private final By orderButton =
            By.xpath(".//div/div/div[2]/div[3]/button[text()='Заказать']");

    public RentForm(WebDriver driver) {
        this.driver = driver;
    }
    public void waitRentFormIsOpen(){
        WebElement header = driver.findElement(rentFormHeader);
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(header));
    }

    public static String convertDatetimeToString(){
        String pattern = "MM/dd/yyyy";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        cal.add(Calendar.DATE, 1);
        return sdf.format(cal.getTime());
    }

    public void setDatePickerInput(String date){
        driver.findElement(datePickerInput).sendKeys(date);
    }

    public void setRentTerm(String term) {
        driver.findElement(rentTerm).click();
        String path = String.format(".//*[@class='Dropdown-option' and text()='%s']", term);
        WebElement element = driver.findElement(By.xpath(path));
        element.click();
    }

    public void setColour(String colour){
        if (colour.equals("серая безысходность")){
            driver.findElement(colourGrey).click();
        }
        else{
            driver.findElement(colourBlack).click();
        }
    }

    public void setComment(String comment){
        driver.findElement(commentInput).sendKeys(comment);
    }

    public void clickOrderButton(){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        WebElement button = driver.findElement(orderButton);
        jse.executeScript("arguments[0].click();", button);
    }
}
