# LAB15. AI-Д СУУРИЛСАН ЧАНАРЫН БАТАЛГАА БА ТЕСТ

## Агуулга

Энэ лабораторийн ажилд AI ашиглан дараах ажлуудыг гүйцэтгэсэн:

1. **Ажил 1:** Шаардлагаас Test Case үүсгэх - Login системийн 20 test case
2. **Ажил 2:** Code → Unit Test үүсгэх - Python `is_valid_email` функцийн 18 unit test
3. **Ажил 3:** Self-healing Automation ойлголт - HTML demo ба Selenium жишээ
4. **Ажил 4:** Synthetic Test Data үүсгэх - 20 user record

## Файлууд

- `ajil1_test_cases.md` - Test cases (20 кейс)
- `ajil2_unit_test.py` - Unit tests (pytest)
- `ajil3_self_healing.html` - Self-healing automation demo
- `ajil3_selenium_example.py` - Selenium жишээ код
- `ajil4_synthetic_data.py` - Synthetic data generator
- `synthetic_users.csv` - Үүссэн synthetic data
- `TAILAN.md` - Дэлгэрэнгүй тайлан

## Ажиллуулах

### ⚡ Quick Start:
```bash
# 1. Dependencies суулгах
pip install -r requirements.txt

# 2. Unit test ажиллуулах
pytest ajil2_unit_test.py -v

# 3. Synthetic data үүсгэх
python ajil4_synthetic_data.py

# 4. HTML demo нээх (Windows)
start ajil3_self_healing.html
```

### 📖 Дэлгэрэнгүй заавар:
**`HOW_TO_RUN.md`** файлыг уншина уу - бүх ажлуудыг хэрхэн ажиллуулах талаар дэлгэрэнгүй заавар байна.

## Дэлгэрэнгүй

- **Ажиллуулах заавар:** `HOW_TO_RUN.md`
- **Дэлгэрэнгүй тайлан:** `TAILAN.md`
- **Git commit заавар:** `GIT_INSTRUCTIONS.md`
