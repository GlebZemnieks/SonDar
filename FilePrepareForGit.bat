git init
git add ObjectModel\src\main\java\*.java
git add ObjectModel\src\test\java\*.java
git add ObjectModel\pom.xml

call ObjectModel\JUnitTest\addTestFile.bat

git add Core\src\main\java\*.java
git add Core\src\test\java\*.java
git add Core\pom.xml

git add FilePrepareForGit.bat

echo ___________________________
echo Branches
git branch
echo ___________________________
echo Last Commit in  ALL Branch
git branch -v
echo ___________________________
echo Last Commit in NOW Branch
git log -4
echo ___________________________
git status
echo ___________________________
echo ___________________________