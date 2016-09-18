echo off
echo %1% : %2% : %3%

set SonDarPath=C:\SonDar\
set currentPath=E:\Development\SonDar\
set toJarPath=\target\

set temp1=%SonDarPath%%2%
set temp2=%temp1%-%3%.jar
del %temp2%
set temp3=%currentPath%%1%\
set temp4=%temp3%%2%
set temp5=%temp4%%toJarPath%
set temp6=%temp5%%2%
set temp7=%temp6%-%3%.jar
echo TO: %temp2%
echo FROM: %temp7%
copy %temp7% %temp2%