set SonDarPath=C:\SonDar\

set currentPath=E:\Development\SonDar\
set toJarPath=\target\

:: Core
call deploy_lib.bat Exception 1.0
call deploy_lib.bat FileModule 1.0
call deploy_lib.bat FileModulePC 1.0
call deploy_lib.bat FileSystem 1.0
call deploy_lib.bat Logger 1.0
call deploy_lib.bat LoggerFile 1.0
call deploy_lib.bat LoggerPC 1.0
call deploy_lib.bat Parser 1.0

:: Document Model
call deploy_lib.bat DocumentModel 1.0

call deploy_utils.bat DocumentFactory 1.0