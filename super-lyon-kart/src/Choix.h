#ifndef CHOIX_H_INCLUDED
#define CHOIX_H_INCLUDED

#include "Music.h"
#include "Bruitage.h"
#include "Ressource.h"

class Choix
{
public:
    Music mus;
    Bruitage br;
    Ressource r;

    SDL_Event event;

    int JoueurChoisi, MapChoisi;
    int JoueurEnVue, MapEnVue = 0;


    int x,y;

    int g=0;
    int h=0;

    bool isRunning;

    SDL_Texture *Fond, *opacite_J, *opacite_M, *choix_J, *choix_M, *GO, *Curseur;

    SDL_Rect fond, Choix_J, Choix_M, go, curseur;
    SDL_Rect O_M1, O_M2, O_M3;
    SDL_Rect O_J1, O_J2, O_J3, O_J4, O_J5, O_J6, O_J7;

    void load_texture_c(SDL_Renderer *renderer);
    void rect_texture_c();
    void copy_texture_c(SDL_Renderer *renderer);

    void choix_Joueur();
    void choix_Map();

    void fonctinnalite_GO();

    void destroy_texture_c();

    void choix(SDL_Renderer *renderer);

    void param_curseur(SDL_Renderer *renderer);
};

#endif // CHOIX_H_INCLUDED
