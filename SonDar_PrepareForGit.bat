::call build.bat

git init
:: Core
call Project_PrepareCode.bat Exception
call Project_PrepareCode.bat Logger
call Project_PrepareCode.bat FileModule
call Project_PrepareCode.bat LoggerPC
call Project_PrepareCode.bat FileModulePC
call Project_PrepareTest.bat FileModulePC
call Project_PrepareCode.bat LoggerFile
call Project_PrepareCode.bat FileSystem
call Project_PrepareTest.bat FileSystem
call Project_PrepareCode.bat Parser
:: ??
call Project_PrepareCode.bat Core
call Project_PrepareTest.bat Core
:: Plugin parts
call Project_PrepareCode.bat PluginAPI
call Project_PrepareCode.bat DemoPlugin

::DocumentModule
call Project_PrepareCode.bat DocumentModel
call Project_PrepareTest.bat DocumentModel

:: Utils
call Project_PrepareCode.bat WordsBaseEditor

:: Client
git add Client\app\src\main\*.java
git add Client\app\src\main\*.xml
git add Client/settings.gradle
git add Client/build.gradle
git add Client/gradle.properties
git add Client/gradle/.
git add Client/gradlew
git add Client/gradlew.bat
git add Client/settings.gradle

:: Build scripts
git add SonDar_PrepareForGit.bat
git add Project_PrepareCode.bat
git add Project_PrepareTest.bat
git add buildProject.bat
git add build.bat

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
