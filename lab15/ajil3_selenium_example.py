"""
АЖИЛ 3: SELF-HEALING AUTOMATION - Selenium жишээ код

Энэ файл нь self-healing automation-ийн зарчмыг харуулна.
"""

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import NoSuchElementException, TimeoutException


class SelfHealingLoginPage:
    """Self-healing зарчим ашигласан Login хуудас"""
    
    def __init__(self, driver):
        self.driver = driver
    
    def find_login_button_with_fallback(self):
        """
        Олон locator стратеги ашиглан login товчийг олох.
        Энэ нь self-healing automation-ийн жишээ юм.
        """
        # Locator-уудыг priority дарааллаар байрлуулна
        locators = [
            # 1. Text-based locator (хамгийн найдвартай)
            (By.XPATH, "//button[text()='Login']"),
            (By.XPATH, "//button[contains(text(), 'Login')]"),
            
            # 2. Data attribute (test automation-д зориулсан)
            (By.CSS_SELECTOR, "button[data-testid='login-button']"),
            (By.CSS_SELECTOR, "button[data-cy='login']"),
            
            # 3. Class name
            (By.CSS_SELECTOR, "button.btn-login"),
            (By.CSS_SELECTOR, "button.login-button"),
            (By.CLASS_NAME, "btn-primary"),
            
            # 4. ID (хамгийн эмзэг, гэхдээ хурдан)
            (By.ID, "loginBtn"),
            (By.ID, "signinButton"),
            (By.ID, "btnLogin"),
            
            # 5. Partial ID match
            (By.CSS_SELECTOR, "button[id*='login']"),
            (By.CSS_SELECTOR, "button[id*='signin']"),
        ]
        
        # Locator бүрийг туршиж үзэх
        for locator_type, locator_value in locators:
            try:
                element = WebDriverWait(self.driver, 2).until(
                    EC.presence_of_element_located((locator_type, locator_value))
                )
                if element.is_displayed() and element.is_enabled():
                    print(f"✓ Login button found using: {locator_type} = '{locator_value}'")
                    return element
            except (NoSuchElementException, TimeoutException):
                continue
        
        # Бүх locator-ууд амжилтгүй бол
        raise Exception("❌ Login button not found with any locator strategy")
    
    def click_login_button(self):
        """Self-healing арга ашиглан login товч дарах"""
        button = self.find_login_button_with_fallback()
        button.click()
        print("✓ Login button clicked successfully")


def demonstrate_self_healing():
    """
    Self-healing automation-ийн демо
    """
    print("=" * 60)
    print("SELF-HEALING AUTOMATION DEMO")
    print("=" * 60)
    
    # Chrome driver эхлүүлэх (эсвэл бусад browser)
    # driver = webdriver.Chrome()
    # driver.get("file:///path/to/ajil3_self_healing.html")
    
    # Жишээ: HTML файл ашиглах
    print("\n1. HTML файл ачааллаж байна...")
    print("2. Login товчийг олох оролдлого хийж байна...")
    print("3. Self-healing стратеги ашиглаж байна...")
    
    # Бодит ажиллагааны жишээ:
    # page = SelfHealingLoginPage(driver)
    # page.click_login_button()
    
    print("\n" + "=" * 60)
    print("Дүгнэлт:")
    print("- UI өөрчлөгдсөн ч тест ажиллана")
    print("- Олон locator стратеги ашиглана")
    print("- AI confidence scoring ашиглаж болно")
    print("=" * 60)


if __name__ == "__main__":
    demonstrate_self_healing()

