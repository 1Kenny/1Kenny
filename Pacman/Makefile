SRCDIR = src/pacman
BINDIR = bin/pacman
SOURCES := $(wildcard $(SRCDIR)/Main.java $(SRCDIR)/model/*.java $(SRCDIR)/view/*.java $(SRCDIR)/controller/*.java)
CLASSES := $(subst $(SRCDIR), $(BINDIR), $(SOURCES:.java=.class))

all: run

$(CLASSES): $(SOURCES)
	javac -d bin/ $(SOURCES)

jar: $(CLASSES) bin/manifest.mf
	jar cfm bin/pacman.jar bin/manifest.mf $(CLASSES)

run: $(CLASSES)
	java -classpath bin/ pacman.Main

clean:
	rm -rf bin/pacman/* bin/pacman.jar
