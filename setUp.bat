@echo off

TITLE Ignore

echo downloading game. This will close automatically after downloading all files
echo(

cd Javaspill/
javac -d classes src\*.java
mkdir C:\game\saves

mkdir C:\game\classes
xcopy classes C:\game\classes

mkdir C:\game\lib
xcopy lib C:\game\lib

mkdir C:\game\localFiles
xcopy localFiles C:\game\localFiles

xcopy batch\run.bat C:\Users\augus\Desktop