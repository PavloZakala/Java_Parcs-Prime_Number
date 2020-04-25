all: run

clean:
	rm -f out/Main.jar out/Multiple.jar out/VectorMutiple.jar

out/Main.jar: out/parcs.jar src/Main.java src/Pair.java
	@javac -cp out/parcs.jar src/Main.java src/Pair.java
	@jar cf out/Main.jar -C src Main.class -С src Pair.class
	@rm -f src/Main.class src/Pair.class

out/VectorMutiple.jar: out/parcs.jar src/VectorMutiple.java src/Pair.java
	@javac -cp out/parcs.jar src/VectorMutiple.java src/Pair.java
	@jar cf out/VectorMutiple.jar -C src VectorMutiple.class -С src Pair.class
	@rm -f src/VectorMutiple.class src/Pair.class

build: out/Main.jar out/VectorMutiple.jar

run: out/Main.jar out/VectorMutiple.jar
	@cd out && java -cp 'parcs.jar:Main.jar' Main
