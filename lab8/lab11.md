# 1. Introduction

Өмнөх лабораторийн ажлуудад бид Meeting Planner программын unit tests–ийг бичсэн.
Энэ лабораторийн зорилго нь эдгээр тестүүд үнэхээр чанартай, хүрэлцээтэй эсэхийг Mutation Testing ашиглан үнэлэх явдал юм.

Mutation testing гэдэг нь:

Кодны логикт зориудаар жижиг алдаа (mutant) оруулах

Тестүүд эдгээр алдааг илрүүлж чадсан уу гэдгийг шалгах

# 2. Mutants Created

Энэ лабораторид бид Calendar болон Meeting хоёр классын кодон дээр нийт 5 mutant үүсгэв.

## 2.1 Calendar Mutants
C-M1 — Logical Operator Mutant

Original:

if(start >= toCheck.getStartTime() && start <= toCheck.getEndTime())


Mutant:

if(start >= toCheck.getStartTime() || start <= toCheck.getEndTime())


Effect:
|| нь илүү өргөн тэлсэн тул ихэнх үед true болж → календар завгүй гэж худлаа үзнэ.

Killed by:
testIsBusyWhenFree()
→ Idle slot–ыг завгүй гэж худлаа буцаана.

C-M2 — Comparison Mutant (Month boundary)

Original (corrected code):

if(mMonth < 1 || mMonth > 12)


Mutant:

if(mMonth < 1 || mMonth >= 12)


Effect:
12-р сар хүчин төгөлдөр мөртлөө алдаатай гэж үзнэ.

Killed by:
testDecemberIsValidMonth()

C-M3 — Comparison Mutant (start/end)

Original:

if(mStart >= mEnd)


Mutant:

if(mStart > mEnd)


Effect:
start == end үед алдаа шидэхгүй → буруу.

Killed by:

try {
    Calendar.checkTimes(6, 10, 10, 10);
    fail();
} catch(TimeConflictException expected){}

## 2.2 Meeting Mutants
M-M1 — Arithmetic Mutant (end + 1)

Original:

this.end = end;


Mutant:

this.end = end + 1;


Effect:
Meeting(14,16) → (14,17) болж хувирна.

Killed by:

@Test
public void testToString_TimeOnlyMeeting() {
    Meeting m = new Meeting(7,3,14,16);
    String out = m.toString();
    assertTrue(out.contains("16"));   // FAIL if mutated
}

M-M2 — String Logic Mutant (Remove substring logic)

Original:

info.setLength(info.length() - 1);  // remove trailing comma


Mutant:

// info.setLength(info.length() - 1);


Effect:
Attending: Alice,Bob, → trailing comma алга болохгүй.

Killed by:

assertFalse(s.endsWith(","));

# 3. Mutation Table
Mutant ID	Location	Mutation	Killed by	Status
C-M1	Calendar.isBusy	&& → `		`
C-M2	Calendar.checkTimes	> 12 → >= 12	testDecemberIsValidMonth	Killed
C-M3	Calendar.checkTimes	>= → >	testStartEqualsEnd	Killed
M-M1	Meeting constructor	end → end + 1	testToString_TimeOnlyMeeting	Killed
M-M2	Meeting.toString	remove setLength	testToString_NoTrailingComma	Killed
# 4. Mutation Score

Formula:

Mutation Score = Killed / (Total - Equivalent)


Values:

Total Mutants = 5

Equivalent Mutants = 0

Killed = 5

Mutation Score = 5 / 5 = 1.00 (100%)

# 5. Analysis

Тестүүд логик болон boundary нөхцөлүүдийг сайн хамарсан:

Logical operator errors (&&, ||)

Comparison boundary (>=, >)

Arithmetic mistakes (+1 mutants)

String formatting errors

Meeting.toString() дээрх trailing comma тест маш сайн mutant-killer болсон.

Calendar.checkTimes boundary tests холбогдох мутантуудыг 100% барьж чадсан.

# 6. Conclusion

Энэ лабораторийн ажилд үүсгэсэн 5 mutant–аас бүгдийг unit тестүүд амжилттай илрүүлж KILL хийсэн бөгөөд төслийн Mutation Score нь:
 100% (Perfect)

Энэ нь:

Boundary testing

Logical operator coverage

String formatting verification

зэрэг чухал хэсгүүдийг unit tests маш сайн хамарч байгааг харуулж байна.

# 7. Appendix: Key Test Cases
@Test
public void testToString_NoTrailingComma() {
    ArrayList<Person> attendees = new ArrayList<>();
    attendees.add(new Person("Alice"));
    attendees.add(new Person("Bob"));
    Room room = new Room("R1", 10);

    Meeting m = new Meeting(10, 10, 9, 10, attendees, room, "Daily");
    String s = m.toString();
    assertFalse(s.endsWith(","));
}
