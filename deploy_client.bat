set SonDarPath=E:\Development\SonDar\
set ClientPath=E:\Development\SonDar\Client\app\libs\

::push file
copy %SonDarPath%Core\FileModule\target\FileModule-1.0.jar %ClientPath%FileModule-1.0.jar
copy %SonDarPath%DocumentModel\target\DocumentModel-1.0.jar %ClientPath%DocumentModel-1.0.jar
copy %SonDarPath%Core\Exception\target\Exception-1.0.jar %ClientPath%Exception-1.0.jar
copy %SonDarPath%Core\Logger\target\Logger-1.0.jar %ClientPath%Logger-1.0.jar
copy %SonDarPath%Core\LoggerFile\target\LoggerFile-1.0.jar %ClientPath%LoggerFile-1.0.jar
copy %SonDarPath%Core\Parser\target\Parser-1.0.jar %ClientPath%Parser-1.0.jar
copy %SonDarPath%Core\FileSystem\target\FileSystem-1.0.jar %ClientPath%FileSystem-1.0.jar
