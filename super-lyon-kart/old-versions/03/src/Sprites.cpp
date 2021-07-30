//============================================================================
// [Include Section]
// ============================================================================
#include<SDL2/SDL.h>
#include<SDL2/SDL_image.h>
#include<SDL2/SDL_ttf.h>
#include<math.h>

#include "Sprites.hpp"

//========================================================

SDL_Texture * Sprites::loadImage(const char path[], SDL_Renderer *renderer)
{
//je dois tous les images en une , comme ça animer chaque npixels de l'image (piste,nature..) celà pertrat de charger une seule
//image par element et la manipuler dans la boucle
    SDL_Surface *tmp = NULL;
    SDL_Texture *texture = NULL;
    tmp = IMG_Load(path);
    if(NULL == tmp)
    {
        fprintf(stderr, "Erreur SDL_LoadBMP : %s", SDL_GetError());
        return NULL;
    }
    texture = SDL_CreateTextureFromSurface(renderer, tmp);
    SDL_FreeSurface(tmp);
    if(NULL == texture)
    {
        fprintf(stderr, "Erreur SDL_CreateTextureFromSurface : %s", SDL_GetError());
        return NULL;
    }
    return texture;
}

