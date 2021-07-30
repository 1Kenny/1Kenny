#ifndef PLAYER_H_INCLUDED
#define PLAYER_H_INCLUDED

#include "Choix.h"

class Player
{
public:
    Choix c;

    SDL_Texture *Mario,*Luigi,*Koopa,*Browser,*Peach,*Toad,*Yoshi;
    SDL_Rect Personnage;

    void init_texture_player(SDL_Renderer *Renderer);
    void init_rect_player();
    void copy_texture_player(SDL_Renderer *Renderer);
    void destroy_texture_player();
};

#endif // PLAYER_H_INCLUDED
