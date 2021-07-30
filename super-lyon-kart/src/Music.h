#ifndef MUSIC_H_INCLUDED
#define MUSIC_H_INCLUDED

#include<iostream>
#include <SDL2/SDL.h>
#include <SDL2/SDL_mixer.h>
using namespace std;

class Music
{
public:
    int p=0;
    int a =1;

    Mix_Music *music_menu;
    Mix_Music *music_jeu;

    void sdlInit_mixer();
    void init_music();
    void load_music(Mix_Music *music);
    void play_music(int i);
};



#endif // MUSIC_H_INCLUDED
