### Лаборатор 11 Мутацийн тест

Энэ лабораторийн зорилго нь өмнөх лабораторийн Meeting Planner програм дээр бичсэн unit tests–ийн чанар болон хамрах хүрээг Mutation Testing ашиглан үнэлэх явдал юм.

Mutation Testing нь дараах зарчим дээр суурилдаг:

Кодонд зориуд жижиг алдаа (mutant) оруулна

Тестүүд энэ алдааг илрүүлж чадаж байна уу гэдгийг шалгана

Илрүүлж чадвал → Killed

Илрүүлж чадахгүй бол → Survived

### 2. Мутац үүсгэсэн

Энэ лабораторид бид Calendar болон Meeting классууд дээр нийт 5 mutant гар аргаар үүсгэсэн.
### C-M1 — Logical Operator Mutant

Анх:

    if (start >= toCheck.getStartTime() && start <= toCheck.getEndTime()) {


Мутац оруулалт:

    if (start >= toCheck.getStartTime() || start <= toCheck.getEndTime()) {


Нөлөө нь: || нь илүү өргөн логик тул бараг бүх үед busy = true болно
Killed by: testIsBusyWhenFree()

### C-M2 — Мутацийн харьцуулалт (Month boundary)

Анх (зөв хувилбар):

    if (mMonth < 1 || mMonth > 12)


Мутац:

    if (mMonth < 1 || mMonth >= 12)


Нөлөө нь: 12-р сар хүчингүй болно
Killed by: testDecemberIsValidMonth()

### C-M3 — Мутацийн харьцуулалт (start/end)

Анх:

    if (mStart >= mEnd)


Мутац:

    if (mStart > mEnd)


 Нөлөө: start == end үед алдаа барихгүй
 Killed by:

    try {
        Calendar.checkTimes(6, 10, 10, 10);
        fail();
    } catch(TimeConflictException expected) {}

##  2.2 Meeting Мутац
###  M-M1 — Arithmetic Мутац (end + 1)

Анх:

    this.end = end;


Мутац хэрэгжүүлэлт:
    
    this.end = end + 1;


 Нөлөө: Meeting(14,16) → 14–17 болж хувирна
 Killed by:

    assertTrue(out.contains("16"));

### M-M2 — String Logic Мутац (Remove trailing comma fix)

Анх:

    info.setLength(info.length() - 1);


Мутац:

    // info.setLength(info.length() - 1);


Нөлөө:
"Attending: Alice,Bob," → trailing comma үлдэнэ
Killed by:

    assertFalse(s.endsWith(","));

##  3. Мутацийн хүснэгт байдлаар
| Мутац  ID | Байрлал             | Мутац                | Test Killing Mutant            | Үр дүн   |                      |          |
| --------- | ------------------- | -------------------- | ------------------------------ | -------- | -------------------- | -------- |
| **C-M1**  | Calendar.isBusy     | `&&` → `             |                                | `        | `testIsBusyWhenFree` |  Killed |
| **C-M2**  | Calendar.checkTimes | `>12` → `>=12`       | `testDecemberIsValidMonth`     |  Killed |                      |          |
| **C-M3**  | Calendar.checkTimes | `>=` → `>`           | `testStartEqualsEnd`           |  Killed |                      |          |
| **M-M1**  | Meeting constructor | `end = end + 1`      | `testToString_TimeOnlyMeeting` |  Killed |                      |          |
| **M-M2**  | Meeting.toString    | Remove comma removal | `testToString_NoTrailingComma` |  Killed |                      |          |

## 4. Шинжилгээ
Unit тестүүд:

    Logical operator errors
    Boundary conditions
    Arithmetic deviations
    String formatting bugs
    ийг маш сайн барьж чадсан.
    Ялангуяа:
        testToString_NoTrailingComma
        testIsBusyWhenFree

гэдэг хоёр тест mutation-killer үүргийг сайн гүйцэтгэсэн.
    Equivalent mutant илрээгүй нь тестүүд бүх логикийг сайн хучиж байгааг харуулж байна.
## 5 Дүгнэлт
Бүх 5 мутант тестүүдээр дамжуулан KILL хийгдсэн.
Meeting болон Calendar класс дээрх boundary & logic шалгалтууд сайн хамгаалагдсан.
→ тестүүд маш чанартай, логик алдаа барих чадвартай.
