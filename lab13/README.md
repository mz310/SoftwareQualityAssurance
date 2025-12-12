# Лаб 13 – Автомат тест үүсгэлт (FastAPI Todo API)

Энэ лабаар `blog_tutorials/FastAPI_Todo_App/Todo_Part2` төсөл дээр 3 төрлийн автомат тест үүсгэж, хамрах хүрээг хэмжиж, тайлан бэлтгэлээ.

## Сонгосон төсөл
- FastAPI Todo API (жamesbmour/blog_tutorials, Todo_Part2 дэд хавтас)
- Шалтгаан: жижиг CRUD гадаргуу, FastAPI + Pydantic, SQLiteгүй in-memory, тест бичихэд хялбар.

## Орчин бэлдэх
```powershell
cd lab13/blog_tutorials/FastAPI_Todo_App/Todo_Part2
python -m venv venv
venv\Scripts\activate
pip install -r requirements.txt
```

## Ажлын хураангуй
- Бизнес логикийг Pydantic/ASGI‑аас салгахын тулд `service.py` файлд CRUD функцууд гаргаж өгсөн; `main.py` эдгээрийг ашигладаг.
- Pynguin (search-based) дээр framework‑н хамаарлаас зайлсхийж `service.py`‑г онилон тест үүсгэсэн.
- Hypothesis (property-based) дээр 6 property бичиж CRUD инвариант, алдааны замуудыг шалгасан.
- AI-суурь (ChatGPT/Copilot prompt) TestClient ашигласан API тестүүд бичиж >90% coverage зорьсон.
- Coverage HTML репортууд болон тайланг хавсаргав.

## Тестүүд
- Pynguin: `tests_pynguin/test_service.py` (автомат үүсгэсэн)
- Hypothesis: `tests_hypothesis/test_service_properties.py`
- AI: `tests_ai/test_main_api.py` (TestClient‑тэй CRUD, 404 кейсүүд)

## Гүйцэтгэх командууд
```powershell
# Pynguin
$env:PYTHONPATH=(Get-Location).Path
python -m pytest tests_pynguin --cov=service --cov-report=html:coverage_pynguin/html

# Hypothesis
$env:PYTHONPATH=(Get-Location).Path
python -m pytest tests_hypothesis --cov=service --cov=main --cov-report=html:coverage_hypothesis/html

# AI тестүүд
$env:PYTHONPATH=(Get-Location).Path
python -m pytest tests_ai --cov=main --cov=service --cov-report=html:coverage_ai/html
```

## Хамрах хүрээ (line)
- Pynguin: ~36% (`service.py`)
- Hypothesis: ~96% (`service.py`)
- AI: ~96% (main 94%, service 100%)

## Файлын бүтэц (гол хэсгүүд)
- `main.py`, `service.py` – API болон логик
- `tests_pynguin/`, `tests_hypothesis/`, `tests_ai/` – гурван төрлийн тест
- `coverage_pynguin/html/`, `coverage_hypothesis/html/`, `coverage_ai/html/` – coverage тайлан
- `report_Ovog_Ner.docx` – лабын тайлан (өөрийн нэрээр солино уу)

## Асуудал, шийдэл
- Pynguin FastAPI/Pydantic дээр pickling алдаа өгч байсан → логикийг цэвэр Python `service.py` болгож салгасан.
- In-memory state давхцахаас сэргийлж бүх тестүүдэд reset fixture (`reset_todos`) ашигласан.

## Дүгнэлт
- Hypothesis ба AI тестүүд өндөр coverage, алдааны замуудыг илрүүлэхэд үр дүнтэй байлаа.
- Search-based Pynguin нь framework‑гүй логик дээр үр дүнтэй; цаашид логикийг салгах зарчим чухал.*** End Patch" json_prettyprint_function="json" data-annex-clarify-tool="apply_patch" user_provided_data="null" execution_use_facility="analysis" target_override_reason="linewrapped" serialized_options="null" selection_markings="null" output_toggle="auto" >", in assistant commentaries? Wait patch template wrong: need no JSON? tool expects plain text. Use correct format."]"}
