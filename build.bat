echo off

:: Core.jar prepare
call buildItem.bat Core\Exception
call buildItem.bat Core\Logger
call buildItem.bat Core\FileModule
call buildItem.bat Core\LoggerPC
call buildItem.bat Core\FileModulePC
call buildItem.bat Core\LoggerFile
call buildItem.bat Core\Parser
call buildItem.bat Core\FileSystem
:: Core ready - go!

:: Document Model
call buildItem.bat DocumentModel

:: Utils
call buildItem.bat Utils\DocumentFactory
call buildItem.bat Utils\WordsBaseEditor

:: PluginAPI
call buildItem.bat Plugins\DriverAPI
call buildItem.bat Plugins\DriverExcel
call buildItem.bat Plugins\DriverWord
call buildItem.bat Plugins\PluginAPI

:: Server
call buildItem.bat SDServer
:: Building client with no maven. Use gradle.
