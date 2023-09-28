#/bin/sh

rm -rf tests/constructor_02/class
mkdir tests/constructor_02/class
java -jar pbatakjava.jar tests/constructor_02/1/*.java tests/constructor_02/2/* tests/constructor_02/3/*.java -d tests/constructor_02/class
