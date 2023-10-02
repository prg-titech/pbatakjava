#/bin/sh

rm -rf tests/hash_02/class
mkdir tests/hash_02/class
java -jar pbatakjava.jar tests/hash_02/1/*.java tests/hash_02/2/*.java tests/hash_02/*.java -d tests/hash_02/class
