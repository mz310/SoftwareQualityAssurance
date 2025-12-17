# LAB15 АЖЛУУДЫГ АЖИЛЛУУЛАХ ЗААВАР

## Шаардлага

- Python 3.7+ суулгасан байх
- pip package manager

## 1. Dependencies суулгах

Эхлээд шаардлагатай Python package-уудыг суулгана:

```bash
pip install -r requirements.txt
```

Эсвэл:

```bash
pip install pytest selenium
```

---

## 2. АЖИЛ 1: TEST CASES ХАРАХ

Ажил 1 нь markdown файл тул зөвхөн унших шаардлагатай.

### Арга 1: VS Code дээр нээх
```bash
# VS Code дээр файлыг нээнэ
code ajil1_test_cases.md
```

### Арга 2: Бүх text editor дээр нээх
- `ajil1_test_cases.md` файлыг дурын text editor эсвэл markdown viewer-оор нээнэ
- 20 test case-ийг харах боломжтой

---

## 3. АЖИЛ 2: UNIT TEST АЖИЛЛУУЛАХ

### Python unit test-үүдийг ажиллуулах:

```bash
# lab15 хавтас руу орох
cd lab15

# Бүх тестүүдийг ажиллуулах
pytest ajil2_unit_test.py -v

# Дэлгэрэнгүй мэдээлэлтэй
pytest ajil2_unit_test.py -v -s

# Зөвхөн нэг тест класс ажиллуулах
pytest ajil2_unit_test.py::TestIsValidEmail -v

# Зөвхөн нэг тест функц ажиллуулах
pytest ajil2_unit_test.py::TestIsValidEmail::test_valid_email_with_com -v
```

### Хүлээгдэж буй үр дүн:

```
============================= test session starts ==============================
collected 18 items

ajil2_unit_test.py::TestIsValidEmail::test_valid_email_with_com PASSED
ajil2_unit_test.py::TestIsValidEmail::test_valid_email_with_org PASSED
ajil2_unit_test.py::TestIsValidEmail::test_valid_email_with_edu PASSED
...
============================== 18 passed in 0.XXs ===============================
```

### Алдаа гарвал:

Хэрэв `pytest` олдохгүй байвал:
```bash
pip install pytest
```

---

## 4. АЖИЛ 3: SELF-HEALING AUTOMATION DEMO

### HTML Demo харах:

#### Арга 1: Browser дээр шууд нээх
```bash
# Windows дээр:
start ajil3_self_healing.html

# Эсвэл файлыг дээр дарж browser-оор нээнэ
```

#### Арга 2: VS Code Live Server ашиглах
1. VS Code дээр Live Server extension суулгах
2. `ajil3_self_healing.html` файлыг нээх
3. "Go Live" товч дарах

#### Арга 3: Python HTTP server ашиглах
```bash
# Python 3
python -m http.server 8000

# Дараа нь browser дээр нээх:
# http://localhost:8000/ajil3_self_healing.html
```

### Selenium жишээ код:

`ajil3_selenium_example.py` файл нь зөвхөн жишээ код тул бодит ажиллуулахын тулд:

1. ChromeDriver суулгах шаардлагатай
2. Бодит web application байх шаардлагатай

Жишээ код-ийг уншиж, self-healing зарчмыг ойлгох зорилготой.

---

## 5. АЖИЛ 4: SYNTHETIC DATA ҮҮСГЭХ

### Synthetic data generator ажиллуулах:

```bash
# lab15 хавтас руу орох
cd lab15

# Python скрипт ажиллуулах
python ajil4_synthetic_data.py
```

### Хүлээгдэж буй үр дүн:

```
============================================================
АЖИЛ 4: SYNTHETIC TEST DATA ҮҮСГЭХ
============================================================

1. 20 ширхэг synthetic user data үүсгэж байна...
2. Өгөгдлийг шалгаж байна...

Шалгалтын үр дүн:
  - Нийт user: 20
  - Давхардсан email: 0
  - Хүчингүй age: 0
  - Бүх өгөгдөл хүчинтэй: Тийм

====================================================================================================
SYNTHETIC USER DATA
====================================================================================================
ID    Name                 Email                          Age   Country         Role          
----------------------------------------------------------------------------------------------------
1     Бат Батбаяр          user1_бат@example.com         32    Монгол          Admin         
2     Цэцэг Цэцэгмаа        user2_цэцэг@testmail.com      28    Америк          User          
...
====================================================================================================

3. CSV файлд хадгалж байна...
✓ 20 user-ийн мэдээлэл synthetic_users.csv файлд хадгалагдлаа

============================================================
Дүгнэлт:
✓ 20 ширхэг synthetic user data амжилттай үүсгэгдлээ
✓ PII агуулаагүй
✓ Давхардал байхгүй
✓ Логик хязгаарт age утгууд
============================================================
```

### CSV файл харах:

```bash
# CSV файлыг нээх
# Windows дээр:
start synthetic_users.csv

# Эсвэл Excel, VS Code дээр нээнэ
```

---

## 6. БҮХ АЖЛУУДЫГ НЭГ ДАХИН АЖИЛЛУУЛАХ

### Windows PowerShell/Command Prompt:

```powershell
# lab15 хавтас руу орох
cd lab15

# 1. Dependencies суулгах
pip install -r requirements.txt

# 2. Unit test ажиллуулах
pytest ajil2_unit_test.py -v

# 3. Synthetic data үүсгэх
python ajil4_synthetic_data.py

# 4. HTML demo нээх
start ajil3_self_healing.html
```

### Bash (Git Bash, Linux, Mac):

```bash
# lab15 хавтас руу орох
cd lab15

# 1. Dependencies суулгах
pip install -r requirements.txt

# 2. Unit test ажиллуулах
pytest ajil2_unit_test.py -v

# 3. Synthetic data үүсгэх
python ajil4_synthetic_data.py

# 4. HTML demo нээх (Linux/Mac)
open ajil3_self_healing.html  # Mac
xdg-open ajil3_self_healing.html  # Linux
```

---

## 7. АЛДААНЫ ШИЙДЭЛ

### Алдаа: `pytest` олдохгүй байна
```bash
pip install pytest
```

### Алдаа: `python` олдохгүй байна
```bash
# Windows дээр:
py ajil4_synthetic_data.py

# Эсвэл:
python3 ajil4_synthetic_data.py
```

### Алдаа: Module not found
```bash
# Dependencies дахин суулгах
pip install -r requirements.txt
```

### Алдаа: Encoding алдаа (Cyrillic тэмдэгт)
```bash
# Windows дээр PowerShell-д:
chcp 65001
python ajil4_synthetic_data.py
```

---

## 8. ҮР ДҮН ШАЛГАХ

### Unit Test үр дүн:
- ✅ Бүх 18 тест амжилттай гүйцэх ёстой
- ❌ Хэрэв зарим тест fail болвол, функцийн логик алдаа байж болно

### Synthetic Data үр дүн:
- ✅ `synthetic_users.csv` файл үүсэх ёстой
- ✅ 20 мөр user data байх ёстой
- ✅ Давхардсан email байхгүй байх ёстой
- ✅ Age утгууд 18-65 хооронд байх ёстой

### HTML Demo:
- ✅ Browser дээр зөв харагдах ёстой
- ✅ Self-healing automation-ийн тайлбар харагдах ёстой

---

## 9. ДЭЛГЭРЭНГҮЙ МЭДЭЭЛЭЛ

- **Тайлан:** `TAILAN.md` файлыг уншина уу
- **README:** `README.md` файлыг уншина уу
- **Git заавар:** `GIT_INSTRUCTIONS.md` файлыг уншина уу

---

## 10. QUICK START (ХУРААНГУЙ)

```bash
# 1. Dependencies
pip install pytest selenium

# 2. Unit test
pytest ajil2_unit_test.py -v

# 3. Synthetic data
python ajil4_synthetic_data.py

# 4. HTML demo
start ajil3_self_healing.html  # Windows
```

---

**Амжилт хүсье! 🚀**

