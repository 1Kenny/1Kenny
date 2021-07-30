#ifndef FONCTIONSMENUS_H_INCLUDED
#define FONCTIONSMENUS_H_INCLUDED

#include<iostream>

#include "Choix.h"


class FonctionsMenus {

public:
    Choix c;

///MENU PRINCIPAL
    int m, n, o = 0;

    SDL_Texture *Texture, *Jouer, *Options, *Quitter;
    SDL_Rect texture, jouer, option, quitter;
    
    

    void load_texture_m(SDL_Renderer *renderer);
    void rect_texture_m();
    void copy_texture_m(SDL_Renderer *renderer);

    void fonctionnalite_jouer();
    void fonctionnalite_options();
    void fonctionnalite_quitter();

    void destroy_texture_m();

    void menu(SDL_Renderer *renderer);

///MENU OPTIONS

    SDL_Texture *Texture2, *ON, *OFF, *Retour;
    SDL_Rect texture2, musique_ON, musique_OFF, bruitage_ON, bruitage_OFF, retour;

    void load_texture_o(SDL_Renderer *renderer);
    void rect_texture_o();
    void copy_texture_o(SDL_Renderer *renderer);

    void fonctionnalite_retour();
    void fonctionnalite_musique(SDL_Renderer *renderer);
    void fonctionnalite_bruitage(SDL_Renderer *renderer);

    void destroy_texture_o();

    void options(SDL_Renderer *renderer);

///POUR TOUS
    void destroy_son();
};



#endif // FONCTIONSMENUS_H_INCLUDED
