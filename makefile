IntToFloat.class: IntToFloat.java
	javac IntToFloat.java

build: *.class
	jar cfm IntToFloat.jar manifest.mf *.class

clean:
	rm -f *.class

