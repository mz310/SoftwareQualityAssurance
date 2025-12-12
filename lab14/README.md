# Лаб 14 – Maven ба Git workflow демо

Энэ төсөл нь жижиг тооны машин бөгөөд Git flow, GitHub Actions CI, Checkstyle, JUnit 5, JaCoCo 100% branch coverage-ийг жишээгээр харуулна.

## Барих ба тестлэх
- `mvn test` – JUnit 5 тестүүдийг ажиллуулна
- `mvn checkstyle:check` – `checkstyle.xml` дахь дүрмийг мөрдүүлнэ
- `mvn jacoco:report` – `target/site/jacoco/index.html` хавтас дахь HTML хамралтын тайланг гаргана
- `mvn jacoco:check` – 100% branch coverage-ийн шаардлагыг шалгана (CI-д хэрэглэнэ)
- JaCoCo замын анхааруулга: төсөл Mongolian/non-ASCII замд байрласан тул `pom.xml` Jacoco-ийн `destFile`/`dataFile` нь `%TEMP%/jacoco.exec` рүү заадаг; ажиллуулахад тусгай тохиргоо шаардлагагүй.

### IntelliJ дээр
1. `File > Open` → `lab14` хавтасыг сонгоод Project SDK-г 17 (эсвэл JDK24 + language level 17) болгож тохируул.
2. Maven tool window-оос `Lifecycle > test` эсвэл Run Anything (`Ctrl+Ctrl`) → `mvn test`/`mvn jacoco:check`/`mvn checkstyle:check` командыг ажиллуул.
3. Coverage тайлан: `mvn jacoco:report` хийсний дараа `target/site/jacoco/index.html`-ийг нээ.

## Төслийн бүтэц
- Эх код: `src/main/java/lab14/sict/must/edu/mn`
- Тест: `src/test/java/lab14/sict/must/edu/mn`
- CI workflow: `.github/workflows/ci.yml` (`develop`, `release/*` руу push/PR дээр ажиллана)

## Git workflow (GitHub дээр хэрэгжүүлэх)
1. `main` салааг хамгаал: PR шаард, статусын шалгалт (CI) шаард, салбар шинэ байхыг шаард, bypass-ийг хоригло.
2. `develop` дээр ажиллаж, онцлог салбарууд (`feature/add-multiplication`, `feature/add-division` гэх мэт) үүсгээд PR-ээр "Create a merge commit" сонголтоор нэгтгэнэ.
3. Release салбарууд (`release/v1.0.0`) болон tag-ууд (`v1.0.0`, `v1.0.1`) үүсгэнэ, шаардлагатай бол hotfix салбар нэмнэ.
4. Merge conflict-ийн шийдлүүдийг `conflict_resolution.md` файлд тэмдэглэ.
