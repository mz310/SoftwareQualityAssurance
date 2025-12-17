# Git Commit заавар

PowerShell-д Cyrillic тэмдэгттэй замтай асуудал гарч байгаа тул дараах аргуудыг ашиглана уу:

## Арга 1: Batch файл ашиглах (Хамгийн хялбар)

```bash
lab15\git_commit.bat
```

## Арга 2: Git Bash ашиглах

Git Bash нээж дараах командуудыг ажиллуулна:

```bash
cd "C:/Users/ACER/Documents/Чанарын баталгаа ба туршилт/SoftwareQualityAssurance"
git add lab15/
git commit -m "LAB15: AI-д суурилсан чанарын баталгаа ба тест - Бүх ажлууд дууссан"
```

## Арга 3: VS Code Source Control ашиглах

VS Code-ийн Source Control таб-аас:
1. lab15/ хавтасны бүх файлуудыг сонгох
2. "Stage All Changes" дарах
3. Commit message оруулах: "LAB15: AI-д суурилсан чанарын баталгаа ба тест"
4. Commit дарах

## Commit message жишээ:

```
LAB15: AI-д суурилсан чанарын баталгаа ба тест

- Ажил 1: Шаардлагаас 20 test case үүсгэсэн
- Ажил 2: Python is_valid_email функцийн 18 unit test үүсгэсэн
- Ажил 3: Self-healing automation ойлголт - HTML demo ба Selenium жишээ
- Ажил 4: 20 ширхэг synthetic user data үүсгэсэн
- Тайлан: Бүх ажлын үр дүн, AI-ийн алдаа, зассан арга, сургамж
```

