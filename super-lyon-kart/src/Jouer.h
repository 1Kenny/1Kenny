#ifndef JOUER_H_INCLUDED
#define JOUER_H_INCLUDED

#include "Ressource.h"
#include "Terrain.h"
#include "Player.h"

using namespace std;

class Jouer
{
public:

    Terrain t;
    Player p;

    SDL_Rect destR;

    void init_Jouer(SDL_Renderer *renderer);


    // Destroy application, called by destructor, don't call manually.
    void destroy();

    // Run application, called by your code.
    int run(int width, int height);

    void update();

    void Jeu_en_Marche(SDL_Renderer *renderer);


    // Called to process SDL event.
    void onEvent(SDL_Event* ev);

    // Called to render content into buffer.
    void Render(SDL_Renderer *renderer);

private:
    int cnt=0;
};



#endif // JOUER_H_INCLUDED
