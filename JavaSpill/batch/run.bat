@echo off

TITLE Ignore

echo this will close after you closed the game
echo(

cd C:\game
java -cp lib\mysql-connector-j-8.0.31.jar;classes Game

exit