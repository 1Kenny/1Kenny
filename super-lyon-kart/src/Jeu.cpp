#include "Jeu.h"
using namespace std;

void Jeu::jeu()
{
    FonctionsMenus f;
    Jouer j;

    int b=1;


    f.c.r.sdlInit();
    f.c.mus.sdlInit_mixer();//init music
    f.c.br.sdlInit_mixer();//init bruitage

    SDL_Window *window = SDL_CreateWindow("Super Lyon Kart", SDL_WINDOWPOS_CENTERED, SDL_WINDOWPOS_CENTERED,1200, 780, SDL_WINDOW_SHOWN);
    SDL_Renderer *renderer = SDL_CreateRenderer(window, -1, SDL_RENDERER_ACCELERATED| SDL_RENDERER_PRESENTVSYNC);

    f.c.mus.play_music(f.c.mus.a);

    while (f.c.g <=3)
    {
        if(f.c.g == 0 ) f.menu(renderer);

        if(f.c.g == 1 ) f.c.choix(renderer);

        if(f.c.g == 2 ) f.options(renderer);

        j.t.c.MapChoisi = f.c.MapChoisi;
        j.p.c.JoueurChoisi = f.c.JoueurChoisi;

        if (b != f.c.mus.a) {f.c.mus.play_music(f.c.mus.a); b = f.c.mus.a;}

        if(f.c.g == 3 ) j.Jeu_en_Marche(renderer);
    }

    f.destroy_son();
    SDL_Quit();
    SDL_DestroyWindow(window);
}
