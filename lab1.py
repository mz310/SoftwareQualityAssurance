from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import TimeoutException
import time
import os
driver = webdriver.Chrome()
driver.get("https://student.must.edu.mn")
driver.maximize_window()
screenshot_folder = "screenshots"
os.makedirs(screenshot_folder, exist_ok=True)

def take_screenshot(name):
    """Screenshots фолдерт timestamp-тай зураг хадгалах функц"""
    timestamp = int(time.time())
    path = os.path.join(screenshot_folder, f"{timestamp}_{name}.png")
    driver.save_screenshot(path)
    print(f"Screenshot хадгалагдлаа: {path}")

try:
    # Username
    username = WebDriverWait(driver, 10).until(
        EC.presence_of_element_located((By.ID, "username"))
    )
    username.send_keys("B222270035")  # оюутны код
    take_screenshot("username_entered")
    

    # Password
    password = driver.find_element(By.ID, "password")
    password.send_keys("nbavotelj23")  #нууц үг
    take_screenshot("password_entered")  # screenshot

    # Login товч
    login_button = driver.find_element(By.XPATH, '//input[@type="submit" and @value="Нэвтрэх"]')
    login_button.click()
    take_screenshot("after_login_click")  

    # # Нэвтэрсний дараа ОЮУТАН текстийг авах
    # student_status = WebDriverWait(driver, 10).until(
    #     EC.presence_of_element_located((By.XPATH, '//span[@class="text"]'))
    # )
    # print("Оюутны статус:", student_status.text)
    # take_screenshot("student_status")

    # Modal байгаа эсэхийг шалгах
    try:
        WebDriverWait(driver, 5).until_not(
            EC.presence_of_element_located((By.CLASS_NAME, "modal-backdrop"))
        )
        print("Modal ариллаа")
        take_screenshot("modal_gone")
    except TimeoutException:
        print("Modal байгаагүй")
        take_screenshot("no_modal")

    # "ОЮУТНЫ БАЙРНЫ БҮРТГЭЛ" товчийг дарна
    dorm_button = WebDriverWait(driver, 10).until(
        EC.element_to_be_clickable((By.XPATH, '//span[@class="text" and text()="ОЮУТНЫ БАЙРНЫ БҮРТГЭЛ"]'))
    )
    dorm_button.click()
    print("'ОЮУТНЫ БАЙРНЫ БҮРТГЭЛ' товчийг дарлаа")
    take_screenshot("dorm_button_clicked")

    time.sleep(3)
    # Хуудасны гарчигийг шалгах
    dorm_page_header = WebDriverWait(driver, 10).until(
        EC.visibility_of_element_located((By.XPATH, '//h1[contains(text(),"Оюутны байрны бүртгэл")]'))
    )
    if "Оюутны байрны бүртгэл" in dorm_page_header.text:
        print("Тест амжилттай: 'Оюутны байрны бүртгэл' хуудас зөв ачааллаа")
    else:
        print(f"Хуудас ачаалсан текст буруу: {dorm_page_header.text}")
    take_screenshot("dorm_page_loaded")
    time.sleep(3)
    # --- Logout хийх ---
    try:
        logout_button = WebDriverWait(driver, 10).until(
            EC.element_to_be_clickable((By.XPATH, '//span[@class="text" and text()="Гарах"]'))

        )
        logout_button.click()
        print("Амжилттай logout хийлээ")
        take_screenshot("after_logout")  # screenshot
    except TimeoutException:
        print("Logout товч олдсонгүй, аль хэдийн гарсан байж магадгүй")
        take_screenshot("logout_not_found")

except Exception as e:
    import traceback
    print("Алдаа гарлаа:")
    traceback.print_exc()
    take_screenshot("error")

finally:
    time.sleep(2)  # Хураангуй харуулах хугацаа
    driver.quit()
    print("Browser-ийг хаалаа")
