# Conflict шийдвэрлэлтийн тэмдэглэл

## Юу болсон бэ?
`feature/multiplication-docs`-ыг `develop` руу нэгтгэх үед `Multiplication.multiply` дээрх тайлбар хоёуланд өөрчлөгдөж, `lab14/src/main/java/lab14/sict/must/edu/mn/Multiplication.java` файлд merge conflict гарсан.

## Хэрхэн шийдсэн бэ?
1. Conflict-ийг гаргахын тулд `develop` дээр `git merge --no-ff feature/multiplication-docs` ажиллуулсан.
2. Зөрчилтэй файлыг нээж, хоёр талын тайлбарыг нэг Javadoc блок болгон нэгтгэсэн.
3. `git add lab14/src/main/java/lab14/sict/must/edu/mn/Multiplication.java` ажиллуулж файлыг шийдэгдсэнээр тэмдэглэсэн.
4. `git commit -m "Merge feature/multiplication-docs into develop"` командаар merge-ийг дуусгасан.
5. lab14 файлууд цэвэр болсон эсэхийг шалгасан.
