##  Лабораторийн тайлан — Meeting Planner (JUnit4)
Meeting Planner төслийн үндсэн логик (хуанли, уулзалт, өрөө, ажилтан, байгууллага) дээр албан бус тестийн төлөвлөгөө боловсруулах, JUnit4 ашиглан нэгж тестүүдийг бичиж, эерэг/сөрөг тохиолдлуудыг системтэйгээр хамруулсан. 

Туршилтын хамрах хүрээ:
Calendar, Meeting, Room, Person, Organization классууд
Гол үйлдлүүд: уулзалт нэмэх, давхцал шалгах, өдрөөр/сараар agenda хэвлэх, өрөө/ажилтны завгүй төлөв шалгах, байхгүй нөөц хайх үед алдаа шидэх

### 1.a Амжилттай ажиллах тохиолдлууд

HP-01 — Өдөр чөлөөтэй: Хоосон календарь дээр isBusy(6,1,9,10) ⇒ false.
HP-02 — Бүх-өдөр амралт/баяр: new Meeting(6,26,"Midsommar") нэмэхэд 0–23 хооронд isBusy ⇒ true.
HP-03 — Давхцаагүй цагууд: (9–11) ба (12–13) нэг өдөрт зэрэг нэмэхэд аль аль нь амжилттай, isBusy зөв буцаана.
HP-04 — 12-р сар хүчинтэй: 12-р сарын уулзалт (12/1 9–10) амжилттай нэмэгдэнэ.
HP-05 — Agenda хэвлэл: printAgenda(month,day) нь нэмсэн уулзалтын тайлбарыг агуулна.

### 1.b Алдаатай/хил хязгаарын тохиолдлууд

NG-01 — Сар буруу: month=0 эсвэл 13 → TimeConflictException.
NG-02 — Өдөр буруу: day=32 → Exception.
NG-03 — Цаг буруу: start<0, end>23, эсвэл start==end → Exception.
NG-04 — Байхгүй өдөр: 2/30, 2/31, 4/31, 6/31, 9/31, 11/31 дээр уулзалт товлох → Exception.
NG-05 — Давхцах уулзалт (partial overlap): байхад (10–12), дараа нь (11–13) → Exception.
NG-06 — Давхцах уулзалт (wrap/containment): байхад (10–12), дараа нь (9–13) → Exception.
NG-07 — Төгсгөлийн захын тохиолдол: (9–11) байхад (11–12) нь давхцахгүй гэж тооцогдох (энд цагийн интервалын зарчмыг [start,end) гэж мөрдөх ёстой).
NG-08 — toString() NPE хамгаалалт: өрөө/оролцогчгүй уулзалтын toString() NPE үүсгэхгүй.

### Тэмдэглэл: Одоогийн өгөгдсөн кодод дараах эрсдэл ажиглагдсан:
(i) Calendar.checkTimes: if (mMonth < 1 || mMonth >= 12) → 12-р сарыг буруу хүчингүй болгодог.
(ii) Давхцал шалгалт зөвхөн start/end-ийн “дотор” орсон эсэхээр хэмждэг тул wrap/containment-ийг алдана.
(iii) Байхгүй өдөр-т тавьсан placeholder-уудыг “Day does not exist” гэж үл тоодог тул 2/30 гэх мэт дээр уулзалт нэмэгдчих боломжтой.
(iv) Meeting.toString() нь room.getID() болон attendees-ийг null хамгаалалтгүй дуудаж NPE үүсгэж болзошгүй.
(v) isBusy-ийн хил зааг — одоогийн логик төгсгөлийг (end) багтааж тооцож байгаа шинжтэй.

## 2. JUnit нэгжийн тестүүд (Implementation & Outcomes)
### 2.1 Тестийн бүтэц

src/test/java/edu/sc/csce747/MeetingPlanner/

CalendarTest.java — календарийн гол логик, хил зааг, давхцал

MeetingTest.java — toString() NPE, өгөгдөл агуулах байдал

OrganizationTest.java — ерөнхий өгөгдөл (өрөө/ажилтан) авах логик

PersonTest.java — хувь хүний календарь, мессеж боодолт (wrapped error)

RoomTest.java — өрөөний календарь, мессеж боодолт

### 2.2 Гол кейсүүд ба хүлээгдэж буй үр дүн

CalendarTest
testAddMeeting_holiday — pass (бүх-өдөр амжилттай).
testHappyPath_AddAndQuery — Fail (11–12)-ыг завсаргүй гэж тооцох ёстой ч одоогийн код end-ийг багтааж true болгож магадгүй.
testInvalidMonthZero/Thirteen/Day32/StartEqualsEnd — Pass (суурь шалгалтууд).
testOverlapWrapped_ShouldThrow (9–13 vs 10–12) — Fail (wrap/containment илрүүлэхгүй).
testNonExistentDay_Feb30_ShouldThrow — Fail (одоогийн код 2/30 дээр уулзалт нэмж орхиж магадгүй).
testDecemberIsValid — Fail (12-р сар хүчингүй гэж үзэж Exception шидэж магадгүй).

MeetingTest
testToString_NoNPE_WhenNoAttendeesOrRoom — Error/NPE (null хамгаалалт дутуу).
testToString_WithAttendeesAndRoom — pass.
OrganizationTest
testGetRoom_valid/invalid, testGetEmployee_valid/invalid — pass (өгөгдөл хайлт OK).


PersonTest
testAddMeeting_setsBusy — Pass.
testAddMeeting_conflictWrappedMessage (10–12 vs 11–13) — Pass (partial overlap илэрнэ).
testPrintAgenda_containsDescription — Pass.

RoomTest

testAddMeeting_setsBusy — Pass.
testAddMeeting_conflictWrappedMessage (10–12 vs 11–13) —  Pass.
testPrintAgenda_containsDescription —Pass.
### 2.3 Илэрсэн доголдлууд (Tests → Defects)

12-р сар хүчингүй — checkTimes нөхцөл: >= 12 → байх ёстой нь > 12.

Давхцал (wrap/containment) алдагдал — 9–13 нь 10–12-ыг бүхлээр нь хамрахад давхцал илрүүлэхгүй.

Зөв шалгалт: overlaps(a,b,c,d) = (a < d && b > c) буюу [start,end) зарчим.

Байхгүй өдөр дээр уулзалт нэмэгдэх боломж — placeholder "Day does not exist"-ийг үл тоомсорлож нэмчихэж магадгүй (жишээ: 2/30).

Зөв шийдэл: календарийн огнооны хүчинтэй байдлыг checkTimes-д сар тус бүрийн өдөртэй уялдуулан нягталж, буруу бол шууд Exception.

toString() NPE — room/attendees null үед NPE.

Зөв шийдэл: конструкторуудад attendees = new ArrayList<>(), roomId = (room!=null?room.getID():"(no-room)") гэх мэт баталгаажуулалт.
