
CC=g++
#OBJS=obj/Pixel.o\ obj/Image.o\ obj/mainExemple.o\ obj/mainTest.o
CFLAGS=-Wall -c 

################################################################################ -Werror -Wextra

all:bin/run 

bin/run: obj/main.o obj/Mode7.o obj/Ressource.o obj/Music.o obj/Player.o obj/Terrain.o obj/Jouer.o obj/Jeu.o obj/FonctionsMenus.o obj/Choix.o obj/Bruitage.o
	g++ obj/main.o obj/Mode7.o obj/Ressource.o obj/Music.o obj/Player.o obj/Terrain.o obj/Jouer.o obj/Jeu.o obj/FonctionsMenus.o obj/Choix.o obj/Bruitage.o -o bin/run -lSDL2 -lSDL2_ttf -lSDL2_image -lSDL2_mixer

################################################################################

obj/main.o: src/main.cpp
	$(CC) $(CFLAGS) src/main.cpp -o obj/main.o

obj/Jeu.o: src/Jeu.cpp src/Jeu.h src/FonctionsMenus.h
	$(CC) $(CFLAGS) src/Jeu.cpp -o obj/Jeu.o

obj/Jouer.o: src/Jouer.cpp src/Player.h src/Terrain.h src/Ressource.h
	$(CC) $(CFLAGS) src/Jouer.cpp -o obj/Jouer.o

obj/Mode7.o: src/Mode7.cpp src/Mode7.h src/Ressource.h
	$(CC) $(CFLAGS) src/Mode7.cpp -o obj/Mode7.o

obj/Choix.o: src/Choix.cpp src/Choix.h src/Ressource.h src/Bruitage.h
	$(CC) $(CFLAGS) src/Choix.cpp -o obj/Choix.o


obj/FonctionsMenus.o: src/FonctionsMenus.cpp src/FonctionsMenus.h src/Choix.h
	$(CC) $(CFLAGS) src/FonctionsMenus.cpp -o obj/FonctionsMenus.o


obj/Bruitage.o: src/Bruitage.cpp src/Bruitage.h
	$(CC) $(CFLAGS) src/Bruitage.cpp -o obj/Bruitage.o


obj/Player.o: src/Player.cpp src/Player.h src/Choix.h src/Ressource.h
	$(CC) $(CFLAGS) src/Player.cpp -o obj/Player.o

obj/Terrain.o: src/Terrain.cpp src/Terrain.h src/Choix.h src/Mode7.h
	$(CC) $(CFLAGS) src/Terrain.cpp -o obj/Terrain.o

obj/Ressource.o: src/Ressource.cpp src/Ressource.h
	$(CC) $(CFLAGS) src/Ressource.cpp -o obj/Ressource.o

obj/Music.o: src/Music.cpp src/Music.h
	$(CC) $(CFLAGS) src/Music.cpp -o obj/Music.o



################################################################################


clean:
	rm -rf obj/* bin/*
