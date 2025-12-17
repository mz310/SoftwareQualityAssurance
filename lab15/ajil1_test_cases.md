# АЖИЛ 1: ШААРДЛАГААС TEST CASE ҮҮСГЭХ

## Өгөгдсөн шаардлага:
1. Хэрэглэгч бүртгэлтэй имэйлээ оруулна.
2. Зөв нууц үг оруулбал Dashboard руу нэвтрэнэ.
3. Буруу нууц үг 3 удаа оруулбал Account temporarily locked.
4. Password visibility toggle байх ёстой.
5. Хоосон имэйл → Validation error.

## AI-аас үүссэн Test Cases

| Test Case ID | Title | Steps | Expected Result |
|--------------|-------|-------|-----------------|
| TC_LOGIN_001 | Зөв имэйл ба нууц үгээр нэвтрэх | 1. Бүртгэлтэй имэйл оруулах<br>2. Зөв нууц үг оруулах<br>3. Login товч дарах | Dashboard хуудас руу амжилттай нэвтрэнэ |
| TC_LOGIN_002 | Буруу нууц үгээр нэвтрэх оролдлого | 1. Бүртгэлтэй имэйл оруулах<br>2. Буруу нууц үг оруулах<br>3. Login товч дарах | "Invalid password" алдааны мессеж харагдана |
| TC_LOGIN_003 | Бүртгэлгүй имэйлээр нэвтрэх оролдлого | 1. Бүртгэлгүй имэйл оруулах<br>2. Ямар ч нууц үг оруулах<br>3. Login товч дарах | "Email not found" алдааны мессеж харагдана |
| TC_LOGIN_004 | Хоосон имэйл талбар | 1. Имэйл талбарыг хоосон үлдээх<br>2. Нууц үг оруулах<br>3. Login товч дарах | "Email is required" validation алдаа харагдана |
| TC_LOGIN_005 | Хоосон нууц үг талбар | 1. Имэйл оруулах<br>2. Нууц үг талбарыг хоосон үлдээх<br>3. Login товч дарах | "Password is required" validation алдаа харагдана |
| TC_LOGIN_006 | Буруу нууц үг 3 удаа оролдлого - Account lock | 1. Бүртгэлтэй имэйл оруулах<br>2. Буруу нууц үг 1-р удаа оруулах → Login<br>3. Буруу нууц үг 2-р удаа оруулах → Login<br>4. Буруу нууц үг 3-р удаа оруулах → Login | "Account temporarily locked" мессеж харагдана, цаашид нэвтрэх боломжгүй болно |
| TC_LOGIN_007 | Password visibility toggle - нууц үгийг харах | 1. Имэйл оруулах<br>2. Нууц үг оруулах (**** хэлбэрээр)<br>3. Eye icon товч дарах | Нууц үг тэмдэгт хэлбэрээр харагдана |
| TC_LOGIN_008 | Password visibility toggle - нууц үгийг нуух | 1. Имэйл оруулах<br>2. Нууц үг оруулах (харагдаж байгаа)<br>3. Eye icon товч дарах | Нууц үг дахин **** хэлбэрээр нуугдана |
| TC_LOGIN_009 | Буруу имэйл формат | 1. Буруу форматтай имэйл оруулах (жишээ: "test@")<br>2. Нууц үг оруулах<br>3. Login товч дарах | "Invalid email format" validation алдаа харагдана |
| TC_LOGIN_010 | Имэйл талбарт тусгай тэмдэгт оруулах | 1. SQL injection оролдлого (жишээ: "test@test.com' OR '1'='1")<br>2. Нууц үг оруулах<br>3. Login товч дарах | Validation алдаа эсвэл "Invalid email format" мессеж |
| TC_LOGIN_011 | Нууц үг талбарт XSS оролдлого | 1. Имэйл оруулах<br>2. XSS script оруулах (жишээ: "<script>alert('xss')</script>")<br>3. Login товч дарах | Script ажиллахгүй, validation алдаа эсвэл "Invalid password format" |
| TC_LOGIN_012 | Урт имэйл (boundary test) | 1. Маш урт имэйл оруулах (255+ тэмдэгт)<br>2. Нууц үг оруулах<br>3. Login товч дарах | "Email too long" validation алдаа эсвэл талбарт орохгүй |
| TC_LOGIN_013 | Богино нууц үг (boundary test) | 1. Имэйл оруулах<br>2. 1 тэмдэгт нууц үг оруулах<br>3. Login товч дарах | "Password must be at least X characters" validation алдаа |
| TC_LOGIN_014 | Том, жижиг үсэг бүхий нууц үг | 1. Имэйл оруулах<br>2. Том, жижиг үсэг агуулсан нууц үг оруулах<br>3. Login товч дарах | Хэрэв шаардлагатай бол validation алдаа эсвэл амжилттай нэвтрэнэ |
| TC_LOGIN_015 | Copy-paste нууц үг | 1. Имэйл оруулах<br>2. Нууц үгийг copy-paste хийх<br>3. Login товч дарах | Амжилттай нэвтрэх эсвэл security policy-д хамаарах |

## Засвар, нэмэлт тест кейсүүд:

| Test Case ID | Title | Steps | Expected Result |
|--------------|-------|-------|-----------------|
| TC_LOGIN_016 | Locked account-аас зөв нууц үгээр нэвтрэх оролдлого | 1. Account lock хийгдсэн имэйл оруулах<br>2. Зөв нууц үг оруулах<br>3. Login товч дарах | "Account temporarily locked. Please try again later" мессеж |
| TC_LOGIN_017 | Case-sensitive имэйл шалгалт | 1. Бүртгэлтэй имэйлийг том үсгээр оруулах (жишээ: "Test@Email.com")<br>2. Зөв нууц үг оруулах<br>3. Login товч дарах | Амжилттай нэвтрэх эсвэл case-insensitive байдлаар хүлээн авна |
| TC_LOGIN_018 | Whitespace-тай имэйл | 1. Имэйлийн эхэнд/төгсгөлд space оруулах (жишээ: " test@email.com ")<br>2. Нууц үг оруулах<br>3. Login товч дарах | Whitespace автоматаар арилгагдана эсвэл validation алдаа |
| TC_LOGIN_019 | Session timeout шалгалт | 1. Зөв credential-ээр нэвтрэх<br>2. Хугацааны дараа (session timeout) dashboard-д хандах | Login хуудас руу буцаж, "Session expired" мессеж |
| TC_LOGIN_020 | Remember me функц (хэрэв байгаа бол) | 1. Имэйл, нууц үг оруулах<br>2. "Remember me" checkbox сонгох<br>3. Login хийх, logout хийх<br>4. Дахин login хуудас руу орох | Имэйл талбарт өмнөх имэйл хадгалагдсан байна |

## Дүгнэлт:
- Нийт 20 test case үүсгэсэн
- Functional, Negative, Boundary, Security test cases-ийг багтаасан
- Edge cases-ийг нэмж, бүрэн тестийн хамрах хүрээг хангасан

