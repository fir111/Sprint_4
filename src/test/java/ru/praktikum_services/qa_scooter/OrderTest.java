package ru.praktikum_services.qa_scooter;

import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String station;
    private final String phoneNumber;
    private final String rentTerm;
    private final String colour;
    private final String comment;

    @After
    public void tearDown() {
        driver.quit();
    }
    public OrderTest(String name, String surname, String address, String station, String phoneNumber,
                     String rentTerm, String colour, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.station = station;
        this.phoneNumber = phoneNumber;
        this.rentTerm = rentTerm;
        this.colour = colour;
        this.comment = comment;
    }
    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][] {
                { "Мария", "Иванова", "Проспект Мира, 2-123", "ВДНХ", "89035553377", "сутки",
                        "серая безысходность", "коммент"},
                { "Иван", "Петров", "ул. Тверская, 3-235", "Маяковская", "89037775577", "трое суток",
                 "чёрный жемчуг", "коммент"},
        };
    }

    @Test
    public void checkOrder()  {
        ChromeOptions options = new ChromeOptions();

        // System.setProperty("webdriver.gecko.driver", "/usr/local/Cellar/geckodriver/0.33.0/bin/geckodriver");
        // driver = new FirefoxDriver();

        driver = new ChromeDriver(options);
        String serviceLink = "https://qa-scooter.praktikum-services.ru/";
        driver.get(serviceLink);

        CookieButton cookieButton = new CookieButton(driver);
        cookieButton.clickCookieButton();

        Order order = new Order(driver);
        order.clickUpperOrderButton();
        order.waitOrderFormIsOpen();

        OrderForm orderForm = new OrderForm(driver);
        orderForm.setName(name);
        orderForm.setSurname(surname);
        orderForm.setAddress(address);
        orderForm.setStation(station);
        orderForm.setPhoneNumber(phoneNumber);
        orderForm.clickNextButton();

        RentForm rentForm = new RentForm(driver);
        rentForm.waitRentFormIsOpen();
        String dateTime = RentForm.convertDatetimeToString();
        rentForm.setDatePickerInput(dateTime);
        rentForm.setRentTerm(rentTerm);
        rentForm.setColour(colour);
        rentForm.setComment(comment);
        rentForm.clickOrderButton();

        OrderModal modalElements = new OrderModal(driver);
        WebElement modalForm = modalElements.getModal();
        Assert.assertNotNull(modalForm);

        String actualModalHeader = modalElements.getModalHeader();

        String expectedOrderModalHeader = "Хотите оформить заказ?";
        MatcherAssert.assertThat(actualModalHeader.strip(), is(expectedOrderModalHeader));

        modalElements.clickOrderSubmitButton();

        OrderStatusModal orderStatusModal = new OrderStatusModal(driver);
        orderStatusModal.waitStatusModalIsOpen();

        String actualOrderStatusModalText = orderStatusModal.getOrderStatusModalText();
        String expectedOrderStatusModalHeader = "Заказ оформлен";
        MatcherAssert.assertThat(actualOrderStatusModalText, startsWith(expectedOrderStatusModalHeader));
    }
}