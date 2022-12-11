@echo off

TITLE Ignore

echo downloading game. This will close automatically after downloading all files
echo(

cd Javaspill/
javac -d classes src\*.java

mkdir C:\game\classes
xcopy classes C:\game\classes

mkdir C:\game\icon
xcopy icon C:\game\icon

mkdir C:\game\lib
xcopy lib C:\game\lib

mkdir C:\game\localFiles
xcopy localFiles C:\game\localFiles

mkdir C:\game\saves

cd batch/
xcopy run.bat C:\game
xcopy Game.lnk C:\Users\augus\Desktop