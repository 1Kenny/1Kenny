//
//  SDL.hpp
//  engine
//
//  Created by Ebagnitchie Charles=Emmanuel on 15/06/2019.
//  Copyright © 2019 Trash. All rights reserved.
//

#ifndef MODE7_hpp
#define MODE7_hpp
    // ============================================================================
    // [Include Section]
    // ============================================================================
    #include <SDL2/SDL.h>
    #include <stdio.h>

/*
 * Algoritme Super Mode 7 de texture mapping créer par NINTENDO pour
 * donner l'impression d'une pseudo vue 3D a partir d'un Sprite 2D.
 */

namespace Mode7
{
    struct Params
    {
        float spaceZ;
        int horizon;
        float scaleX, scaleY;
        float objScaleX, objScaleY;
    };
Uint32 SDL_get_pixel32(SDL_Surface *surface, int x, int y);
void SDL_put_pixel32(SDL_Surface *surface, int x, int y, Uint32 pixel);

void DrawMode7Stretched(SDL_Surface* bmp,
        SDL_Surface* tile,
        double angle,
        double cx,
        double cy,
        Params params);

    void DrawObject(SDL_Surface* const bmp,
        float angle,
        float objectX,
        float objectY,
        float cameraX,
        float cameraY,
        Params params);
};

#endif
