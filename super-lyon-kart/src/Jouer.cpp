#include "Jouer.h"

void Jouer::init_Jouer(SDL_Renderer *renderer)
{
    ///INIT_PLAYER
     p.init_texture_player(renderer);
     p.init_rect_player();

    ///INIT MAP
    t.init_texture_terrain(renderer);

    ///DRAW MAP
    t.lockSurface();
    t.texture_mode7();
    t.unlockSurface();
    t.surface_to_texture(renderer);
}

void Jouer::update()
{
    cnt++;

    destR.w = 200;
    destR.h = 200;
    destR.x = 1200/2-(destR.w/2);
    destR.y = 780/2-(destR.h/2);
}

void Jouer::destroy()
{
    p.destroy_texture_player();
    t.destroy_texture_terrain();
}

void Jouer::Render(SDL_Renderer *renderer)
{
    SDL_RenderClear(renderer);

    t.copy_texture_terrain(renderer);
    p.copy_texture_player(renderer);


    SDL_RenderPresent(renderer);
}

void Jouer::Jeu_en_Marche(SDL_Renderer *renderer)
{
    init_Jouer(renderer);

    t.c.isRunning = true;

    while(t.c.isRunning)
    {
        switch(t.c.event.type)
        {
        case SDL_QUIT:
            t.c.isRunning = false;
            SDL_Quit();
            break;
        }
        update();

        Render(renderer);
        SDL_WaitEvent(&t.c.event);
    }
    destroy();
}

