set SonDarPath=E:\Development\SonDar\
set ClientPath=E:\Development\SonDar\Client\app\libs\

::push file
copy %SonDarPath%FileModule\target\FileModule-1.0.jar %ClientPath%FileModule-1.0.jar
copy %SonDarPath%DocumentModel\target\DocumentModel-1.0.jar %ClientPath%DocumentModel-1.0.jar
copy %SonDarPath%Exception\target\Exception-1.0.jar %ClientPath%Exception-1.0.jar
copy %SonDarPath%Logger\target\Logger-1.0.jar %ClientPath%Logger-1.0.jar
copy %SonDarPath%Parser\target\Parser-1.0.jar %ClientPath%Parser-1.0.jar
copy %SonDarPath%FileSystem\target\FileSystem-1.0.jar %ClientPath%FileSystem-1.0.jar
