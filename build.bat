echo off
set SonDarPath=C:\SonDar\

:: Core.jar prepare
call buildItem.bat Exception
call buildItem.bat Logger
call buildItem.bat FileModule
call buildItem.bat LoggerPC
call buildItem.bat FileModulePC
call buildItem.bat LoggerFile
call buildItem.bat Parser
call buildItem.bat FileSystem
:: Core ready - go!

:: Document Model
call buildItem.bat DocumentModel
call buildItem.bat DocumentFactory