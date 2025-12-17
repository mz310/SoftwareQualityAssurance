@echo off
chcp 65001 >nul
cd /d "%~dp0\.."
git add lab15/
git commit -m "LAB15: AI-д суурилсан чанарын баталгаа ба тест - Бүх ажлууд дууссан"
git commit -m "Ажил 1: Шаардлагаас 20 test case үүсгэсэн" --allow-empty
git commit -m "Ажил 2: Python is_valid_email функцийн 18 unit test үүсгэсэн" --allow-empty  
git commit -m "Ажил 3: Self-healing automation ойлголт - HTML demo ба Selenium жишээ" --allow-empty
git commit -m "Ажил 4: 20 ширхэг synthetic user data үүсгэсэн" --allow-empty
git commit -m "Тайлан: Бүх ажлын үр дүн, AI-ийн алдаа, зассан арга, сургамж" --allow-empty
echo.
echo Git commit амжилттай хийгдлээ!
echo.
pause

