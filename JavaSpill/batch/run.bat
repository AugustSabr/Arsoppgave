@echo off

TITLE Ignore. This is for reading error messages, and will close automatically after you closed the game

echo this is for reading error messages, and will close automatically after you closed the game
echo(

cd C:\game
java -cp lib\postgresql-42.6.0.jar;classes Game

exit