# pbi [Basic Stamp 1 (®) Emulator]

The πBASIC Interpreter
================

This is a java program that interprets the Basic Stamp 1 Basic language PBasic (®).

The πBASIC Interpreter is designed for rapid developement of PBASIC code. 

How to install the interpreter
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

How to install the IDE
--------------
1. Download the latest `pbi-win32.zip` (Windows), `pbi-linux.tar.gz2` (Linux -  coming soon) 
   or `pbi-mac.zip` (Mac - coming soon) and unzip it to a directory of your choice.
2. Run it on every system with double-clicking the executable (pbi).

Test
--------------

```

' {$STAMP BS1}
'language-specific unit test

DEBUG CR,"test references and composition.."
SYMBOL new = NEW 16
SYMBOL big = W0
SYMBOL small_right = B0
SYMBOL small_left = B1
SYMBOL tiny_beg = BIT0
SYMBOL tiny_end = BIT7
SYMBOL int1 = W0
SYMBOL int2 = B2
SYMBOL int3 = new
SYMBOL counter = W0
SYMBOL bool = BIT11
ASSERT new = 0
ASSERT big = 0
ASSERT small_left = 0
ASSERT small_right = 0
'BREAKPOINT


new = 1
W0 = new
ASSERT  big = new
ASSERT  small_left = 0
ASSERT    small_right = 1
ASSERT  tiny_beg = 1
ASSERT    tiny_end = 0
'BREAKPOINT


DEBUG "test comments.." 'test
'test
'BREAKPOINT


DEBUG "test arithmetics.."
int1 = 1
int2 = 2
int3 = 3
int1 = int1 + int2 * int3 MAX 5 / 3 MIN int1 // 2 & 1 - 1 + 65000 ** 65000 - 64467 | 0
'result=    3      9       9    3    1       1    1   0   65000  64468     1      1
ASSERT int1 = 1
'BREAKPOINT


DEBUG "test control-commands.."
counter = 0
label:
   IF counter = 10 THEN fin
   counter = counter + 1
   GOTO label
fin:
ASSERT counter = 10
'BREAKPOINT


counter = 0
branch_start:
BRANCH counter, (test1,test2,test3,branch_end)
test1:
   counter = 1
   GOTO branch_start
test2:
   counter = 2
   GOTO branch_start
test3:
   counter = 3
   GOTO branch_start
branch_end:
ASSERT counter = 3
'BREAKPOINT


DEBUG "test subroutines.."
GOSUB subroutine
ASSERT counter = 5
'BREAKPOINT


DEBUG "test power/time-commands.."
NAP 1
SLEEP 1
PAUSE 500
'BREAKPOINT


DEBUG "test io-commands.."
HIGH 1
ASSERT PIN1 = 1
ASSERT DIR1 = 1
'BREAKPOINT


LOW 1
ASSERT PIN1 = 0
ASSERT DIR1 = 1
'BREAKPOINT


OUTPUT 1
ASSERT DIR1 = 1
'BREAKPOINT


INPUT 1
ASSERT DIR1 = 0
'BREAKPOINT


TOGGLE 1
ASSERT PIN1 = 1
ASSERT DIR1 = 1
'BREAKPOINT


TOGGLE 1
ASSERT PIN1 = 0
ASSERT DIR1 = 1
'BREAKPOINT


REVERSE 1
ASSERT DIR1 = 0
'BREAKPOINT


REVERSE 1
ASSERT DIR1 = 1
'BREAKPOINT


SOUND 1, (80,50)
ASSERT DIR1 = 1
'BREAKPOINT


DEBUG "test loops"
bool = 1
FOR counter = 0 TO 10
IF bool = 1 THEN check_init
inside_for:
NEXT
ASSERT counter = 10
'BREAKPOINT


FOR counter = 10 TO 0 STEP -1
NEXT
ASSERT counter = 0
'BREAKPOINT


FOR int3 = 0 TO 9
FOR int2 = 0 TO 9
counter = counter + 1
NEXT
NEXT
ASSERT int3 = 9
ASSERT int2 = 9
ASSERT counter = 100
'BREAKPOINT


DEBUG "test numeric commands.."
int1 = 3
LOOKUP int1, ("ABRAKADABRA"), int2
ASSERT int1 = 3
ASSERT int2 = 65
'BREAKPOINT


LOOKDOWN int2, ("ABRAKADABRA"), int1
ASSERT int1 = 3
ASSERT int2 = 65
'BREAKPOINT


LOOKUP int1, (0,1,2,3), int2
ASSERT int1 = 3
ASSERT int2 = 3
'BREAKPOINT


LOOKDOWN int2, (0,1,2,3), int1
ASSERT int1 = 3
ASSERT int2 = 3
'BREAKPOINT


RANDOM new
'DEBUG "a random value: ", #new
'BREAKPOINT


DEBUG "test storage-modifying-commands.."
READ 0, int1
ASSERT int1 = 0
WRITE 0, int2
READ 0, int1
ASSERT int1 = 3
EEPROM int1, (3,4)
READ 4, int1
ASSERT int1 = 4
EEPROM (0,1,2)
READ 0, int1
ASSERT int1 = 0
DEBUG EEPROM
'BREAKPOINT


DEBUG "test debug.."
DEBUG CR,"text",W0," ",#W0," ",%W0," ",$W0, 0, @68
SEROUT 1, 1, ("text", W0, "text")
'BREAKPOINT


DEBUG "test formatters.."
DIRS = %00000011
PINS = $FF
new  = @A + 1
ASSERT DIRS = 3
ASSERT PINS = 255
ASSERT new = 66
'BREAKPOINT


END


check_init:
   bool = 0
   ASSERT counter = 0
   GOTO inside_for

subroutine:
   'BREAKPOINT
   counter = counter + 1
   GOSUB subsubroutine 'test nested subroutines
   RETURN

subsubroutine:
   'BREAKPOINT
   counter = counter + 1
   RETURN
   
```
