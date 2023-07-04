package ru.praktikum_services.qa_scooter;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.hamcrest.CoreMatchers.is;

public class OrderButtonTest {
    private WebDriver driver;
    private final String serviceLink = "https://qa-scooter.praktikum-services.ru/";
    public final String expectedHeader = "Для кого самокат";

    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void clickUpperOrderButton() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.get(serviceLink);

        Order order = new Order(driver);
        order.clickUpperOrderButton();
        order.waitOrderFormIsOpen();

        OrderForm orderForm = new OrderForm(driver);
        String actualHeader = orderForm.getOrderFormHeader();
        MatcherAssert.assertThat(expectedHeader, is(actualHeader));
    }

    @Test
    public void clickLowerOrderButton() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.get(serviceLink);

        CookieButton cookieButton = new CookieButton(driver);
        cookieButton.clickCookieButton();

        Order order = new Order(driver);
        order.clickLowerOrderButton();
        order.waitOrderFormIsOpen();

        OrderForm orderForm = new OrderForm(driver);
        String actualHeader = orderForm.getOrderFormHeader();
        MatcherAssert.assertThat(expectedHeader, is(actualHeader));
    }
}