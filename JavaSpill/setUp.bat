javac -d classes src\*.java

mkdir C:\game\classes
xcopy classes C:\game\classes

mkdir C:\game\lib
xcopy lib C:\game\lib

mkdir C:\game\localFiles
xcopy localFiles C:\game\localFiles

xcopy run.bat C:\game

pause