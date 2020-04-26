all: run

clean:
		rm -f out/Main.jar out/FindDiv.jar out/Range.jar

out/Main.jar: out/parcs.jar src/Main.java src/Range.java
		@javac -cp out/parcs.jar src/Main.java src/Range.java
		@jar cf out/Main.jar -C src .
		@rm -rf src/*.class

out/FindDiv.jar: out/parcs.jar src/FindDiv.java src/Range.java
		@javac -cp out/parcs.jar src/FindDiv.java src/Range.java
		@jar cf out/FindDiv.jar -C src .
		@rm -rf src/*.class

build: out/Main.jar out/FindDiv.jar

run: out/Main.jar out/FindDiv.jar
		@cd out && java -cp 'parcs.jar:Main.jar' Main