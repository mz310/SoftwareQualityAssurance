# Лаб 13 – Автомат тест үүсгэлт (FastAPI Todo API)

## Зорилго
- Search-based (Pynguin), property-based (Hypothesis), AI/LLM-д суурилсан тест үүсгэлтийн аргуудыг нэг төсөл дээр туршиж харьцуулах.
- Coverage хэмжих, илэрсэн асуудлыг тайлагнах, автоматжуулалтын үр ашгийг дүгнэх.

## Төслийн сонголт
- Сонгосон төсөл: `blog_tutorials/FastAPI_Todo_App/Todo_Part2`
- Шалтгаан: жижиг CRUD гадаргуу, FastAPI + Pydantic стек, in-memory өгөгдөлтэй тул тестлэх, рефактор хийхэд хурдан.

## Онолын товч тайлбар
- **Search-based testing (Pynguin):** Генетик хайлтаар API/функцийн оролт, дуудагдал хослолыг судалж тест үүсгэнэ. Framework-н хамааралгүй цэвэр Python логик дээр хамгийн үр дүнтэй.
- **Property-based testing (Hypothesis):** Олон янзын санамсаргүй оролтыг property-аар тодорхойлж, инвариант/алдааны замыг системтэй илрүүлнэ. Shrinking механизмаар бага жишээгээр алдааг тайлбарлах давуу талтай.
- **LLM-based testing:** Том хэлний загварууд (Copilot/ChatGPT гэх мэт) кодын утгыг тайлбарлан pytest бүтэц, mock/fixture санал болгодог. Хурдан эхлэл өгөх ч хүний баталгаажуулалт шаардлагатай.

## Хийгдсэн ажил
- Логикуудыг `service.py` файлд салгаж, FastAPI-г `main.py`-д нимгэлж Pynguin-д нийцтэй болгосон.
- Pynguin ашиглан `service.py` дээр smoke тестүүд үүсгэсэн (`tests_pynguin/`).
- Hypothesis ашиглан 6 property бичиж CRUD инвариант, алдааны замыг шалгасан (`tests_hypothesis/`).
- LLM prompt ашиглан TestClient-тэй API тестүүд бичиж CRUD болон 404 замуудыг хамарсан (`tests_ai/`).
- Coverage HTML тайлангуудыг `coverage_pynguin/html`, `coverage_hypothesis/html`, `coverage_ai/html` замд хадгалсан.
- Лаб тайланг `report_Ovog_Ner.docx` (өөрийн нэрээр солих) болон README-г монголоор бичсэн.

## Тестүүд ба үр дүн
- Pynguin: service-д ~36% line coverage (smoke).
- Hypothesis: service-д ~96% line coverage; KeyError, UUID/ID инвариант, required keys, unique ID.
- AI (LLM): main 94%, service 100%, нийт ~96% line coverage; CRUD + 404 замууд TestClient-ээр.

## Алдаа, асуудал, шийдэл
- Pynguin FastAPI/Pydantic дээр pickling алдаа өгсөн → логикийг цэвэр Python `service.py` болгож салгасан.
- In-memory state-ийн нөлөөг багасгахын тулд тест бүрт `reset_todos` fixture ашигласан.
- Windows long-path алдаа гарч болзошгүй → шаардлагатай багцыг салгаж суулгах эсвэл long-path support идэвхжүүлэх.

## Дүгнэлт
- Property-based (Hypothesis) ба AI тестүүд өндөр coverage, алдааны замуудыг илүү сайн илрүүлсэн.
- Search-based (Pynguin) нь framework-салгасан логик дээр л ашигтай; архитектурын салгалт чухал.
- Ирээдүйд: core логикийг framework-гүй байлгах, property-based шалгалтыг AI-санаа авч өргөжүүлэх, search-based хэрэгслийг туслах байдлаар ашиглах.***
