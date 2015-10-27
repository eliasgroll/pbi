# pbi

The πBASIC Interpreter
================

This is a java program that interprets the Basic Stamp 1 Basic language PBasic (®).

How to install
--------------
1. Download the latest release .zip from https://github.com/EliasGroll/pbi/releases and unzip it to a directory of your choice.
2. Run it on linux/mac with pbi.sh or on windows with pbi.bat (You can also run it on every system with java -jar pbi.jar).
Use the filename as last argument.

Additional flags on the command line:
* `-noinfo` (removes the output of additional information about the actual command),
* `-nowarn` (removes the output of warnings),
* `-fast`   (does not slow down the execution to the level of bs1) and
* `-pic`    (print the result of the parser).

Additional elements of the language: `ACTIVATEINFO` (a command that activates the output of additional information at runtime), `DEACTIVATEINFO` (a command that deactivates the output of additional information at runtime), `DEBUG EEPROM` (a debug argument that outputs the composition of the EEPROM at runtime), `SYMBOL new = NEW 16` (a command that adds a new variable of 16 bits to the system), `SYSCLOCK` (A variable which stores the system-time in seconds), `BREAKPOINT` (a command that loops the evaluation of the user-input as a single command) and `ASSERT` (a command that creates an error if the following expression is false).

An IDE (based on eclipse) is coming soon.
(written by Elias Groll, Jonas Reichmann)
