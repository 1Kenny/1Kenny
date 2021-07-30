#ifndef BRUITAGE_H_INCLUDED
#define BRUITAGE_H_INCLUDED

#include<iostream>
#include <SDL2/SDL.h>
#include <SDL2/SDL_mixer.h>
using namespace std;

class Bruitage
{
public:
    int b=0;

    Mix_Chunk *B_menu;
    Mix_Chunk *B_go;

    void sdlInit_mixer();
    void init_bruitage();
    void load_bruitage(Mix_Chunk *bruitage, int n);
    void play_bruitage(int n);
};

#endif // BRUITAGE_H_INCLUDED
