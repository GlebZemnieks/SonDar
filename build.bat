echo off
set currentPath=E:\Development\SonDar
set SonDarPath=C:\SonDar\

::Part with no dependencies
call buildProject.bat Exception
cd %currentPath%
call buildProject.bat Logger
cd %currentPath%

::Part with Exception dependency
call buildProject.bat FileModule
cd %currentPath%

::Part with Logger dependency
call buildProject.bat LoggerPC
cd %currentPath%
call buildProject.bat FileModulePC
cd %currentPath%

::Part with FileModule dependency
call buildProject.bat LoggerFile
cd %currentPath%


call buildProject.bat LoggerFile
cd %currentPath%

call buildProject.bat FileSystem
cd %currentPath%

call buildProject.bat Parser
cd %currentPath%

:: Core ready - go!
