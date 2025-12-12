# Conflict Resolution Notes

## What happened
While merging `feature/multiplication-docs` into `develop`, both branches modified the comment above `Multiplication.multiply`, which produced a merge conflict in `lab14/src/main/java/lab14/sict/must/edu/mn/Multiplication.java`.

## How it was resolved
1. Ran `git merge --no-ff feature/multiplication-docs` on `develop` to reproduce the conflict.
2. Opened the conflicted file and combined both comment versions into a single Javadoc block.
3. Marked the file as resolved with `git add lab14/src/main/java/lab14/sict/must/edu/mn/Multiplication.java`.
4. Completed the merge with `git commit -m "Merge feature/multiplication-docs into develop"`.
5. Confirmed working tree was clean for the lab14 files.
