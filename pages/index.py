import allure
from selenium.webdriver.common.by import By


class OrderButton:

    """ Класс для работы с кнопкой заказа"""

    button = (By.XPATH, '//*[@id="root"]//button[text()="Заказать"]')

    def __init__(self, driver):
        self.driver = driver

    def click_order_button(self):
        self.driver.find_element(*self.button).click()


class Cookies:

    """ Класс для работы с cookies"""

    cookie_button = [By.ID, 'rcc-confirm-button']
    cookies = None

    @allure.step('Инициализируем драйвер в объекте Cookies')
    def __init__(self, driver):
        self.driver = driver

    @allure.step('Находим кнопку для установки cookies и кликаем по ней')
    def click_consent_button(self):
        self.driver.find_element(*self.cookie_button).click()

    @allure.step('Сохраняем cookies')
    def get_cookies(self):
        self.click_consent_button()
        return self.driver.get_cookies()


class FaqQuestions:

    """ Класс для работы с FAQ"""

    header = (By.XPATH, '//*[@id="root"]//div[text()="Вопросы о важном"]')
    questions = (By.XPATH, '//*[@id="root"]//div[@class="accordion"]//div[@class="accordion__item"]')
    question_button = (By.CLASS_NAME, 'accordion__button')
    answer = (By.CLASS_NAME, 'accordion__panel')
    faq_items = []

    @allure.step('Инициализируем драйвер в объекте FaqQuestions')
    def __init__(self, driver):
        self.driver = driver

    @allure.step('Ищем заголовок FAQ')
    def get_header(self):
        return self.driver.find_element(*self.header)

    @allure.step('Скроллим к списку FAQ')
    def scroll_to_header(self):
        header = self.get_header()
        self.driver.execute_script('arguments[0].scrollIntoView();', header)

    @allure.step('Возвращаем все пункты из FAQ')
    def get_questions(self):
        return self.driver.find_elements(*self.questions)

    @allure.step('Получаем пункт FAQ по вопросу')
    def get_faq_by_question(self, question):
        try:
            return self.driver.find_element(By.XPATH,
                                            f'//*[@class="accordion__button" and text()='
                                            f'"{question}"]/parent::*/parent::*')
        except AttributeError:
            return None

    @allure.step('Ищем кнопку в пункте FAQ')
    def get_question_button(self, question):
        return question.find_element(*self.question_button)

    @allure.step('Раскрываем пункт в списке FAQ')
    def click_question_button(self, question):
        self.get_question_button(question).click()

    def get_answer(self, question):
        answer = question.find_element(*self.answer)
        return answer.find_element(By.XPATH, './/p').text


class IndexPage:
    """Класс для описания объектов на индексной странице"""

    base_url = 'https://qa-scooter.praktikum-services.ru/'
    image = (By.XPATH, '//div[@id="root"]//img[@alt="Scooter blueprint"]')
    logo_link = (By.XPATH, '//a[@href="/"]')
    yandex_link = (By.XPATH, '//a[@href="//yandex.ru"]')

    @allure.step('Инициализация драйвера в объекте IndexPage')
    def __init__(self, driver):
        self.driver = driver

    @allure.step('Открываем главную страницу сервиса Самокат')
    def open_index_page(self):
        self.driver.get(self.base_url)
        self.driver.maximize_window()

    @allure.step('Ищем изображение самоката')
    def get_scooter_images(self):
        return self.driver.find_elements(*self.image)

    @allure.step('Кликаем по лого Самокат')
    def click_logo_link(self):
        self.driver.find_element(*self.logo_link).click()

    @allure.step('Кликаем по лого Yandex')
    def click_yandex_link(self):
        self.driver.find_element(*self.yandex_link).click()

