@echo off

TITLE Ignore

echo downloading game. This will close automatically after downloading all files
echo(

cd Javaspill/
javac -d classes src\*.java

if not exist "C:\game" mkdir C:\game
echo 1
if exist "C:\game\classes" rmdir /s /q C:\game\classes
xcopy classes C:\game\classes /I
echo 2
if exist "C:\game\lib" rmdir /s /q C:\game\lib
xcopy lib C:\game\lib /I
echo 3
if exist "C:\game\localFiles" rmdir /s /q C:\game\localFiles
xcopy localFiles C:\game\localFiles /E/C/I
echo 4
cd batch/
del C:\game\run.bat
xcopy run.bat C:\game
del %USERPROFILE%\Desktop\Game.lnk
xcopy Game.lnk %USERPROFILE%\Desktop