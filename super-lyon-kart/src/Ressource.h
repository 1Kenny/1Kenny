#ifndef RESSOURCE_H_INCLUDED
#define RESSOURCE_H_INCLUDED

#include <SDL2/SDL.h>
#include <SDL2/SDL_image.h>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>

using namespace std;


class Ressource
{
public:

    void sdlInit();

    SDL_Texture *LoadTexture(string filePath, SDL_Renderer *renderer);

    void putPixelOnSurface(SDL_Surface *surface, int x, int y, Uint32 pixelColor);

    Uint32 getPixelFromSurface(SDL_Surface *surface, int x, int y);
};

#endif // RESSOURCE_H_INCLUDED
