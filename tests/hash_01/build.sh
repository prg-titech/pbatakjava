#/bin/sh

rm -rf tests/hash_01/class
mkdir tests/hash_01/class
java -jar pbatakjava.jar tests/hash_01/1/*.java tests/hash_01/2/*.java -d tests/hash_01/class
