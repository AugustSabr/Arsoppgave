@echo off

TITLE Ignore

echo downloading game. This will close automatically after downloading all files
echo(

cd Javaspill/
javac -d classes src\*.java

if not exist "C:\game" mkdir C:\game

rmdir /s /q C:\game\classes
xcopy classes C:\game\classes /I

rmdir /s /q C:\game\icon
xcopy icon C:\game\icon /I

rmdir /s /q C:\game\lib
xcopy lib C:\game\lib /I

rmdir /s /q C:\game\localFiles
xcopy localFiles C:\game\localFiles /I

if not exist "C:\game\saves" mkdir C:\game\saves

cd batch/
del C:\game\run.bat
xcopy run.bat C:\game
del %USERPROFILE%\Desktop\Game.lnk
xcopy Game.lnk %USERPROFILE%\Desktop