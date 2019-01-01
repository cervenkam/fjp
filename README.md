# Semestral work KIV/FJP
Minimalistic compiler from extended PL/0 to unreal mode x86 instructions.

## Prerequisities
It is designed to be run on Linux OS. If you are interested in another
OS, you will need to rewrite at least compilation script (`compile.sh`)
and execution script (`run.sh`) to make it work.
### Execution only
* JRE 1.8+
* qemu-system-i386 (highly recommended)
### For future development
* JDK 1.8+
* nasm
* objdump

## Fast execution
To test it really fast, you can use this fragment of bash code:

`git clone https://github.com/cervenkam/fjp.git && cd fjp &&chmod +x *.sh
&& ./compile.sh hilbert.pl0 out.bin && ./run.sh out.bin`
## Execution in nutshell
For compilation use **compile.sh**, it has two parameters, first is the
filename/path to the **pl0** source, second is output file (executable),
for example:

`./compile.sh hilbert.pl0 out.bin`

This command creates the output program **out.bin**, which can be executed
with **run.sh** script:

`./run.sh out.bin`

This command opens **QEMU** emulator and runs the program.

The second option
to run this _program_ is run it on bare hardware, but it runs in unreal
mode which means it can potentially corrupt anything (filesystem etc.),
especially when we talk about compiled program, which can contain anything.
Running program on bare hardware is not recommended, should be done only by
advanced user and it is on your own risk.
If you still want to run it on bare HW, generated file (for instance `out.bin`)
is actually a OS image, which can be booted, so commands like `dd` can be
usefull.

## Author
* Martin &Ccaron;ervenka
### Special thanks
OS kernel has been inspired by previously used kernel created by me and
these two guys:
* Petr &Scaron;techm&uuml;ller
* Anton&iacute;n Vrba
## Compiler in action
There are four testing **pl0** programs in the root folder of this project.
If you compile and run these programs, you get this output:
<p align="center">
	<img src="pictures/hilbert.png" width="400px"/>
	<img src="pictures/circle.png" width="400px"/>
	<img src="pictures/bounce.png" width="400px"/>
	<img src="pictures/fizzbuzz.png" width="400px"/>
</p>

First image (program `hilbert.pl0`) generates hilbert curve. It mainly aims to
test recursion and nested procedures. The second image (program `circle.pl0`)
generates concentric circles and tests loops. Third example
(program `bounce.pl0`) draws bouncing object in 45 degrees. It tests correct
compilation of conditions. The last example (program `fizzbuzz.pl0`)
(look it up if do not know fizzbuzz program) tests mainly arithmetic.
