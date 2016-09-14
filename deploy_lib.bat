echo off
echo %1% : %2%

set SonDarPath=C:\SonDar\lib\
set currentPath=E:\Development\SonDar\
set toJarPath=\target\

set temp1=%SonDarPath%%1%
set temp2=%temp1%-%2%.jar
del %temp2%
set temp3=%currentPath%%1%
set temp4=%temp3%%toJarPath%
set temp5=%temp4%%1%
set temp6=%temp5%-%2%.jar
copy %temp6% %temp2%