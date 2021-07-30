#include<SDL2/SDL.h>
#include<SDL2/SDL_image.h>
#include<SDL2/SDL_ttf.h>
#include "Game.hpp"

#include <iostream>

int main(int argc, const char *argv[])
{
    // Notre fenêtre
    Game game;
       SDL_Window* fenetre(0);
       SDL_Event evenements;
       bool terminer(false);
       
       
       // Initialisation de la SDL
       
       if(SDL_Init(SDL_INIT_VIDEO) < 0)
       {
           std::cout << "Erreur lors de l'initialisation de la SDL : " << SDL_GetError() << std::endl;
           SDL_Quit();
           
           return -1;
       }
       
       
       // Création de la fenêtre

       fenetre = SDL_CreateWindow("Super Lyon Kart", SDL_WINDOWPOS_CENTERED, SDL_WINDOWPOS_CENTERED, 800, 600, SDL_WINDOW_SHOWN);

       if(fenetre == 0)
       {
           std::cout << "Erreur lors de la creation de la fenetre : " << SDL_GetError() << std::endl;
           SDL_Quit();

           return -1;
       }
       
       
       // Boucle principale
       
       while(!terminer)
       {
       SDL_WaitEvent(&evenements);
    
       if(evenements.window.event == SDL_WINDOWEVENT_CLOSE)
           terminer = true;
           game.render();
       }
       
       
       // On quitte la SDL
       
       SDL_DestroyWindow(fenetre);
       SDL_Quit();
       
       return 0;
}
