git init

git add DocumentModel\src\main\java\*.java
git add DocumentModel\src\test\java\*.java
git add DocumentModel\pom.xml

git add DocumentModel\JUnitTest\addTestFile.bat
call DocumentModel\JUnitTest\addTestFile.bat

git add Core\JUnitTest\addTestFile.bat
call Core\JUnitTest\addTestFile.bat

git add PluginAPI\src\main\java\*.java
git add PluginAPI\src\test\java\*.java
git add PluginAPI\pom.xml

git add DemoPlugin\src\main\java\*.java
git add DemoPlugin\src\test\java\*.java
git add DemoPlugin\pom.xml

git add Core\src\main\java\*.java
git add Core\src\test\java\*.java
git add Core\pom.xml

git add Client\app\src\main\*.java
git add Client\app\src\main\*.xml
git add Client/settings.gradle
git add Client/build.gradle
git add Client/gradle.properties
git add Client/gradle/.
git add Client/gradlew
git add Client/gradlew.bat
git add Client/settings.gradle

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
