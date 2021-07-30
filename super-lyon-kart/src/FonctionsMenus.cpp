#include "FonctionsMenus.h"

using namespace std;




//MENU PRINCIPAL

void FonctionsMenus::load_texture_m(SDL_Renderer *renderer)
{
    Texture = c.r.LoadTexture("data/Menu/background.png", renderer);
    Jouer = c.r.LoadTexture("data/Menu/Jouer.png", renderer);
    Options = c.r.LoadTexture("data/Menu/Options.png", renderer);
    Quitter = c.r.LoadTexture("data/Menu/Quitter.png", renderer);
    c.Curseur = c.r.LoadTexture("data/curseur.png", renderer);
}

void FonctionsMenus::rect_texture_m()
{
    texture.x = 0;
    texture.y = 0;
    texture.w = 1200;
    texture.h = 780;
    
    jouer.x = 475;
    jouer.y = 471;
    jouer.w = 250;
        jouer.h = 42;
    
    
    option.x = 475;
    option.y = 536;
    option.w = 250;
        option.h = 42;
    
    quitter.x = 475;
    quitter.y = 601;
    quitter.w = 250;
        quitter.h = 42;
    
    c.curseur.x=c.x;
    c.curseur.y=c.y;
    c.curseur.w=40;
    c.curseur.h=50;

}

void FonctionsMenus::copy_texture_m(SDL_Renderer *renderer)
{
    SDL_RenderCopy(renderer, Texture, NULL, &texture);
    SDL_RenderCopy(renderer, Jouer, NULL, &jouer);
    SDL_RenderCopy(renderer, Options, NULL, &option);
    SDL_RenderCopy(renderer, Quitter, NULL, &quitter);
}

void FonctionsMenus::fonctionnalite_jouer()
{
    if (c.x >= 475 && c.x <= 725 && c.y >= 471 && c.y <= 515)
    {
//        jouer = {450, 469, 300, 47};

        jouer.x = 450;
        jouer.y = 469;
        jouer.w = 300;
        jouer.h = 47;

        if(m==1)
        {
            c.br.play_bruitage(0);
            c.g=1;
            c.isRunning = false;
        }
    }
    else
    {
        //jouer = {475, 471, 250, 42};
        jouer.x = 475;
        jouer.y = 471;
        jouer.w = 250;
        jouer.h = 42;

        m=0;
    }
}

void FonctionsMenus::fonctionnalite_options()
{
    if (c.x >= 475 && c.x <= 725 && c.y >= 536 && c.y <= 578)
    {
        //option = {450, 533, 300, 47};
        option.x = 450;
        option.y = 533;
        option.w = 300;
        option.h = 47;

        if(o==1)
        {
            c.br.play_bruitage(0);
            c.g=2;
            c.isRunning = false;
        }
    }
    else
    {
        //option = {475, 536, 250, 42};
        option.x = 475;
        option.y = 536;
        option.w = 250;
        option.h = 42;

        o=0;
    }
}

void FonctionsMenus::fonctionnalite_quitter()
{
    if (c.x >= 475 && c.x <= 725 && c.y >= 601 && c.y <= 643 )
    {
        //quitter = {450, 598, 300, 47};
        quitter.x = 450;
        quitter.y = 598;
        quitter.w = 300;
        quitter.h = 47;

        if(n==1)
        {
            c.br.play_bruitage(0);
            c.g=4;
            c.isRunning = false;
        }
    }
    else
    {
        
        //quitter = {475, 601, 250, 42};
        quitter.x = 475;
        quitter.y = 601;
        quitter.w = 250;
        quitter.h = 42;

        n=0;
    }
}

void FonctionsMenus::destroy_texture_m()
{
    SDL_DestroyTexture(Texture);
    SDL_DestroyTexture(Jouer);
    SDL_DestroyTexture(Options);
    SDL_DestroyTexture(Quitter);
}


void FonctionsMenus::menu(SDL_Renderer *renderer)
{
    c.mus.a = 1;

    SDL_ShowCursor(0);

    load_texture_m(renderer);
    rect_texture_m();

    c.isRunning = true ;
    while(c.isRunning)
    {
        switch(c.event.type)
        {
        case SDL_QUIT:
            c.isRunning = false;
            SDL_Quit();
            break;
        case SDL_MOUSEBUTTONDOWN:
            m=1;
            n=1;
            o=1;
            break;
        }

        copy_texture_m(renderer);

        SDL_GetMouseState(&c.x, &c.y);

        fonctionnalite_jouer();
        fonctionnalite_options();
        fonctionnalite_quitter();

        c.param_curseur(renderer);

        SDL_RenderPresent(renderer);
        SDL_WaitEvent(&c.event);

    }

    destroy_texture_m();
}



//MENU OPTIONS

void FonctionsMenus::load_texture_o(SDL_Renderer *renderer)
{
    Texture2 = c.r.LoadTexture("data/Options/back_options.png", renderer);
    ON = c.r.LoadTexture("data/Options/ON.png", renderer);
    OFF = c.r.LoadTexture("data/Options/OFF.png", renderer);
    Retour = c.r.LoadTexture("data/Options/Retour.png", renderer);

}

void FonctionsMenus::rect_texture_o()
{
    texture2.x = 0;
    texture2.y = 0;
    texture2.w = 1200;
    texture2.h = 780;

    musique_ON.x = 741;
    musique_ON.y = 627;
    musique_ON.w  = 50;
    musique_ON.h  = 50;

    musique_OFF.x = 733;
    musique_OFF.y = 630;
    musique_OFF.w  = 57;
    musique_OFF.h  = 57;

    bruitage_ON.x = 741;
    bruitage_ON.y = 692;
    bruitage_ON.w  = 50;
    bruitage_ON.h  = 50;

    bruitage_OFF.x = 733;
    bruitage_OFF.y = 695;
    bruitage_OFF.w  = 57;
    bruitage_OFF.h  = 57;

    //retour = {30, 660, 230, 100};
    retour.x = 30;
    retour.y = 660;
    retour.w  = 230;
    retour.h  = 100;


}

void FonctionsMenus::copy_texture_o(SDL_Renderer *renderer)
{
    SDL_RenderCopy(renderer, Texture2, NULL, &texture2);
    SDL_RenderCopy(renderer, Retour, NULL, &retour);
}

void FonctionsMenus::fonctionnalite_retour()
{
    if (c.x >= 30 && c.x <= 260 && c.y >= 660 && c.y <= 760)
    {
        //retour = {15, 650, 260, 120};
        retour.x = 15;
        retour.y = 650;
        retour.w  = 260;
        retour.h  = 120;


        if(c.h==1)
        {
            c.br.play_bruitage(0);
            c.g=0;
            c.isRunning = false;
        }
    }
    else
    {

//        retour = {30, 660, 230, 100};
        retour.x = 30;
        retour.y = 660;
        retour.w  =230;
        retour.h  =100;

        c.h=0;
    }
}

void FonctionsMenus::fonctionnalite_musique(SDL_Renderer *renderer)
{
    if(c.mus.p%2==1)
    {
        SDL_RenderCopy(renderer, OFF, NULL, &musique_OFF);
        Mix_VolumeMusic(0);
    }

    if (c.mus.p%2==0)
    {
        SDL_RenderCopy(renderer, ON, NULL, &musique_ON);
        Mix_VolumeMusic(128);
    }
}

void FonctionsMenus::fonctionnalite_bruitage(SDL_Renderer *renderer)
{
    if (c.br.b%2==0)
    {
        SDL_RenderCopy(renderer, ON, NULL, &bruitage_ON);
        Mix_Resume(0);
    }

    if (c.br.b%2==1)
    {
        SDL_RenderCopy(renderer, OFF, NULL, &bruitage_OFF);
        Mix_Pause(-1);
    }
}

void FonctionsMenus::destroy_texture_o()
{
    SDL_DestroyTexture(Texture2);
    SDL_DestroyTexture(ON);
    SDL_DestroyTexture(OFF);
    SDL_DestroyTexture(Retour);
}


void FonctionsMenus::options(SDL_Renderer *renderer)
{
    load_texture_o(renderer);
    rect_texture_o();

    c.isRunning = true ;
    while(c.isRunning)
    {
        SDL_GetMouseState(&c.x, &c.y);

        switch(c.event.type)
        {
        case SDL_QUIT:
            c.isRunning = false;
            SDL_Quit();
            break;
        case SDL_MOUSEBUTTONDOWN:
            if(c.x >= 741 && c.x <= 782 && c.y >= 639 && c.y <= 679) {c.mus.p+=1; c.br.play_bruitage(0);}
            if(c.x >= 741 && c.x <= 782 && c.y >= 704 && c.y <= 744) {c.br.b+=1; c.br.play_bruitage(0);}
            c.h=1;
            break;
        }


        copy_texture_o(renderer);

        fonctionnalite_retour();
        fonctionnalite_musique(renderer);
        fonctionnalite_bruitage(renderer);

        c.param_curseur(renderer);

        SDL_RenderPresent(renderer);
        SDL_WaitEvent(&c.event);
    }

    destroy_texture_o();
}


//POUR TOUS

void FonctionsMenus::destroy_son()
{
    Mix_FreeMusic(c.mus.music_menu);
    Mix_FreeChunk(c.br.B_menu);
    Mix_CloseAudio();
}




















