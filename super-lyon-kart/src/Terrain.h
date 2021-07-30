#ifndef TERRAIN_H_INCLUDED
#define TERRAIN_H_INCLUDED


#include "Mode7.h"
#include "Choix.h"


class Terrain
{
public:
    Mode7 m;
    Choix c;



    SDL_Texture *ciel, *t_Terrain;

    SDL_Surface *s_Terrain1, *s_Terrain2, *s_Terrain3;

    SDL_Rect Ciel = {0, 0, 1200, 224};

    void init_texture_terrain(SDL_Renderer *Renderer);
    void surface_to_texture(SDL_Renderer *Renderer);
    void copy_texture_terrain(SDL_Renderer *Renderer);
    void lockSurface();
    void texture_mode7();
    void unlockSurface();


    void destroy_texture_terrain();
};


#endif // TERRAIN_H_INCLUDED
