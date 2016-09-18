::call build.bat

git init
:: Core
call Project_PrepareCode.bat Core\Exception
call Project_PrepareCode.bat Core\Logger
call Project_PrepareCode.bat Core\FileModule
call Project_PrepareCode.bat Core\LoggerPC
call Project_PrepareCode.bat Core\FileModulePC
call Project_PrepareTest.bat Core\FileModulePC
call Project_PrepareCode.bat Core\LoggerFile
call Project_PrepareCode.bat Core\FileSystem
call Project_PrepareTest.bat Core\FileSystem
call Project_PrepareCode.bat Core\Parser

:: DocumentModule
call Project_PrepareCode.bat DocumentModel
call Project_PrepareTest.bat DocumentModel

:: Plugin parts
call Project_PrepareCode.bat Plugins\DriverAPI 
call Project_PrepareCode.bat Plugins\DriverExcel 
call Project_PrepareCode.bat Plugins\PluginAPI
call Project_PrepareCode.bat Plugins\DemoPlugin

:: Utils
call Project_PrepareCode.bat Utils\DocumentFactory
call Project_PrepareCode.bat Utils\WordsBaseEditor

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

:: Git scripts
git add SonDar_PrepareForGit.bat
git add Project_PrepareCode.bat
git add Project_PrepareTest.bat
:: Build scripts
git add build.bat
git add buildItem.bat
git add buildProject.bat
:: Deploy scripts
git add deploy.bat
git add deploy_lib.bat
git add deploy_utils.bat
git add deploy_client.bat

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
