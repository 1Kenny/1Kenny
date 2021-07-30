#include"Choix.h"



void Choix::load_texture_c(SDL_Renderer *renderer)
{
    Fond = r.LoadTexture("data/Choix/Choix.png", renderer);
    opacite_J = r.LoadTexture("data/Choix/OpacitePilote.png", renderer);
    opacite_M = r.LoadTexture("data/Choix/OpaciteMap.png", renderer);
    choix_J = r.LoadTexture("data/Choix/PersoChoisi.png", renderer);
    choix_M = r.LoadTexture("data/Choix/MapChoisi.png", renderer);
    GO = r.LoadTexture("data/Choix/GO.png", renderer);
}

void Choix::rect_texture_c()
{
    fond.x = 0;
	fond.y = 0;
	fond.w = 1200;
	fond.h = 780;

    Choix_J.x = 306;
	Choix_J.y = 17;
	Choix_J.w = 121;
	Choix_J.h = 456;

    Choix_M.x = 24;
	Choix_M.y = 513;
	Choix_M.w = 286;
	Choix_M.h = 256;
	
    go.x = 1014;
	go.y = 595;
	go.w = 130;
	go.h = 160;
	
	O_M1.x = 28;
	O_M1.y = O_M2.y = O_M3.y = 517;
	O_M1.w = O_M2.w = O_M3.w = 278;
	O_M1.h = O_M2.h = O_M3.h = 248;
	
	O_M2.x = 337;
	O_M3.x = 646;

    
	O_J1.x = 310;
    O_J2.x = 437;
    O_J3.x = 564;
    O_J4.x = 691;
    O_J5.x = 818;
    O_J6.x = 945;
    O_J7.x = 1072;

	O_J1.y = O_J2.y = O_J3.y = O_J4.y = O_J5.y = O_J6.y = O_J7.y = 21;
	O_J1.w = O_J2.w = O_J3.w = O_J4.w = O_J5.w = O_J6.w = O_J7.w = 113;
	O_J1.h = O_J2.h = O_J3.h = O_J4.h = O_J5.h = O_J6.h = O_J7.h = 448;	
}

void Choix::copy_texture_c(SDL_Renderer *renderer)
{
    SDL_RenderCopy(renderer, Fond, NULL, &fond);
    SDL_RenderCopy(renderer, GO, NULL, &go);
    SDL_RenderCopy(renderer, choix_J, NULL, &Choix_J);
    SDL_RenderCopy(renderer, choix_M, NULL, &Choix_M);

    if(JoueurChoisi != 1 && JoueurEnVue != 1) SDL_RenderCopy(renderer, opacite_J, NULL, &O_J1);
    if(JoueurChoisi != 2 && JoueurEnVue != 2) SDL_RenderCopy(renderer, opacite_J, NULL, &O_J2);
    if(JoueurChoisi != 3 && JoueurEnVue != 3) SDL_RenderCopy(renderer, opacite_J, NULL, &O_J3);
    if(JoueurChoisi != 4 && JoueurEnVue != 4) SDL_RenderCopy(renderer, opacite_J, NULL, &O_J4);
    if(JoueurChoisi != 5 && JoueurEnVue != 5) SDL_RenderCopy(renderer, opacite_J, NULL, &O_J5);
    if(JoueurChoisi != 6 && JoueurEnVue != 6) SDL_RenderCopy(renderer, opacite_J, NULL, &O_J6);
    if(JoueurChoisi != 7 && JoueurEnVue != 7) SDL_RenderCopy(renderer, opacite_J, NULL, &O_J7);

    if (MapChoisi != 1 && MapEnVue != 1) SDL_RenderCopy(renderer, opacite_M, NULL, &O_M1);
    if (MapChoisi != 2 && MapEnVue != 2) SDL_RenderCopy(renderer, opacite_M, NULL, &O_M2);
    if (MapChoisi != 3 && MapEnVue != 3) SDL_RenderCopy(renderer, opacite_M, NULL, &O_M3);
}

void Choix::choix_Joueur()
{
    ///J1
    if (x>310 && x<423 && y>21 && y<469)
    {
        JoueurEnVue = 1;
        if (h==1)
        {
			br.play_bruitage(0);
			JoueurChoisi = 1;
		  
			Choix_J.x = 306;
        }
        else h=0;
    }
    else JoueurEnVue = 0;

    ///J2
    if (x>437 && x<550 && y>21 && y<469)
    {
        JoueurEnVue = 2;
        if (h==1)
        {
          br.play_bruitage(0);
          JoueurChoisi = 2;
		  
			Choix_J.x = 433;
        }
        else h=0;
    }

    ///J3
    if (x>564 && x<677 && y>21 && y<469)
    {
        JoueurEnVue = 3;
        if (h==1)
        {
          br.play_bruitage(0);
          JoueurChoisi = 3;
		  Choix_J.x = 560;
        }
        else h=0;
    }

    ///J4
    if (x>691 && x<804 && y>21 && y<469)
    {
        JoueurEnVue = 4;
        if (h==1)
        {
          br.play_bruitage(0);
          JoueurChoisi = 4;
		  Choix_J.x = 687;
        }
        else h=0;
    }

    ///J5
    if (x>818 && x<931 && y>21 && y<469)
    {
        JoueurEnVue = 5;
        if (h==1)
        {
          br.play_bruitage(0);
          JoueurChoisi = 5;
		  Choix_J.x = 814;
        }
        else h=0;
    }

    ///J6
    if (x>945 && x<1058 && y>21 && y<469)
    {
        JoueurEnVue = 6;
        if (h==1)
        {
          JoueurChoisi = 6;
          br.play_bruitage(0);
		  Choix_J.x = 941;
        }
        else h=0;
    }

    ///J7
    if (x>1072 && x<1185 && y>21 && y<469)
    {
        JoueurEnVue = 7;
        if (h==1)
        {
          JoueurChoisi = 7;
          br.play_bruitage(0);
		  Choix_J.x = 1068;
        }
        else h=0;
    }

}

void Choix::choix_Map()
{
    ///MAP1
    if (x>28 && x<306 && y>517 && y<765)
    {
        MapEnVue = 1;
        if (h==1)
        {
            br.play_bruitage(0);
            MapChoisi = 1;
			
			Choix_M.x = 24;
        }
        else h=0;
    }
    else MapEnVue = 0;

    ///MAP2
    if (x>337 && x<615 && y>517 && y<765)
    {
        MapEnVue = 2;
        if (h==1)
        {
            br.play_bruitage(0);
            MapChoisi = 2;
			
			Choix_M.x = 333;
        }
        else h=0;
    }

    ///MAP3
    if (x>646 && x<924 && y>517 && y<765)
    {
        MapEnVue = 3;
        if (h==1)
        {
            br.play_bruitage(0);
            MapChoisi = 3;
			
			Choix_M.x = 643;
        }
        else h=0;
    }

}

void Choix::fonctinnalite_GO()
{
    if (x >= 1014 && x <= 1144 && y >= 595 && y <= 755)
    {
	    go.x = 1000;
		go.y = 578;
		go.w = 158;
		go.h = 194;
        
		if(h==1)
        {
            br.play_bruitage(1);
            g=3;
            mus.a = 2;
            isRunning = false;
        }
    }
    else
    {
		go.x = 1014;
		go.y = 595;
		go.w = 130;
		go.h = 160;
        h=0;
    }
}

void Choix::destroy_texture_c()
{
    SDL_DestroyTexture(Fond);
    SDL_DestroyTexture(opacite_J);
    SDL_DestroyTexture(opacite_M);
    SDL_DestroyTexture(choix_J);
    SDL_DestroyTexture(choix_M);
    SDL_DestroyTexture(GO);
}

void Choix::choix(SDL_Renderer *renderer)
{
    JoueurChoisi = MapChoisi = 1;

    load_texture_c(renderer);
    rect_texture_c();

    isRunning = true ;
    while(isRunning)
    {
        switch(event.type)
        {
        case SDL_QUIT:
            isRunning = false;
            SDL_Quit();
            break;
        case SDL_MOUSEBUTTONDOWN:
            h=1;
            break;
        }

        copy_texture_c(renderer);

        SDL_GetMouseState(&x, &y);

        choix_Joueur();
        choix_Map();
        fonctinnalite_GO();

        param_curseur(renderer);

        SDL_RenderPresent(renderer);
        SDL_WaitEvent(&event);

    }
    destroy_texture_c();

}

void Choix::param_curseur(SDL_Renderer *renderer)
{
    curseur.x = x;
    curseur.y = y;
    if(x >0 && y > 0) SDL_RenderCopy(renderer, Curseur, NULL, &curseur);
}


