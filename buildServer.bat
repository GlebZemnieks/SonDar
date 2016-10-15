echo off

:: PluginAPI
call buildItem.bat Plugins\DriverAPI
call buildItem.bat Plugins\DriverExcel
call buildItem.bat Plugins\DriverWord
call buildItem.bat Plugins\PluginAPI

:: Server
call buildItem.bat SDServer
:: Building client with no maven. Use gradle.
