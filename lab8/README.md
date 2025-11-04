##  Лаборатори-8-ийн тайлан — Meeting Planner White box testing
Анхны байдлаар зөвхөн functional утгатай хэдхэн тест ажиллуулж,
IntelliJ-ээр “Run with Coverage” хийж үзэхэд:

Package edu.sc.csce747.MeetingPlanner

Class coverage ≈ 50% (6/12 клаcc)

Method coverage ≈ 20% (18/89 метод)

Line coverage ≈ 18%

Branch coverage ≈ 12%
## Лаб 08 – White Box Structural Testing Report

#### Coverage Tool: IntelliJ IDEA Code Coverage Runner
#### Project: Meeting Planner
#### Testing Level: Structural White Box Test (statement + branch focus)
#### Coverage tool: IntelliJ IDEA Built-in Coverage Runner
#### Coverage Result:

Лаб 07 дээр бичсэн functional unit test-үүд дээр суурилж white-box structural coverage test нэмсэн. Кодын дотоод салбарлалт (branch) болон statement хамрах хүрээг нэмэгдүүлэх зорилгоор Calendar болон Meeting классууд дээр boundary болон edge-case scenario-ууд давхар тестлэсэн.

Class Coverage: 91%

Method Coverage: 65%

Line Coverage: 48%

Branch Coverage: 31%

#### 3. Нэмэлтээр нэмсэн функүүд

Calendar.addMeeting overlap path
Calendar.checkTimes invalid input branch test
Meeting.toString минимал болон өргөтгөсөн (with room & attendees) формат
Busy / Free time window ялгалт

White-box test нэмснээр өмнө functional test-ээр хамрагдаагүй branch-ууд test coverage-д орсон.
Code өөрчлөлт / bug-fix хийгээгүй. Зөвхөн test-case нэмсэн.
