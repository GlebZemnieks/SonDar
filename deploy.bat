set SonDarPath=C:\SonDar\

set currentPath=E:\Development\SonDar\
set toJarPath=\target\

:: Core
call deploy_lib.bat Core Exception 1.0
call deploy_lib.bat Core FileModule 1.0
call deploy_lib.bat Core FileModulePC 1.0
call deploy_lib.bat Core FileModuleString 1.0
call deploy_lib.bat Core FileSystem 1.0
call deploy_lib.bat Core Logger 1.0
call deploy_lib.bat Core LoggerFile 1.0
call deploy_lib.bat Core LoggerPC 1.0
call deploy_lib.bat Core Parser 1.0

:: Document Model
call deploy_lib.bat "" DocumentModel 1.0

:: Utils
call deploy_utils.bat Utils DocumentFactory 1.0
call deploy_utils.bat Utils WordsBaseEditor 1.0

:: Server
call deploy_lib.bat Plugins DriverAPI 1.0
call deploy_lib.bat Plugins DriverExcel 1.0
call deploy_lib.bat Plugins DriverWord 1.0
call deploy_lib.bat Plugins PluginAPI 1.0

call deploy_utils.bat "" SDServer 1.0