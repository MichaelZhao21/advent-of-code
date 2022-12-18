JAVA=java
JAVAC=javac

.SUFFIXES: $(SUFFIXES) .class .java

.java.class:
	$(JAVAC) $*.java

CLASSES = *.java

default: run

run: all
	$(JAVA) $(CLASS)

all: $(CLASSES:.java=.class)

clean:
	rm -f *.class *~ *.bak
