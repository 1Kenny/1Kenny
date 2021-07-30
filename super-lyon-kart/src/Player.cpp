#include "Player.h"


void Player::init_texture_player(SDL_Renderer* Renderer)
{
    Mario = c.r.LoadTexture("data/Persos/Mario.png",Renderer);
    Luigi = c.r.LoadTexture("data/Persos/Luigi.png",Renderer);
    Koopa = c.r.LoadTexture("data/Persos/Koopa.png",Renderer);
    Browser = c.r.LoadTexture("data/Persos/Browser.png",Renderer);
    Peach = c.r.LoadTexture("data/Persos/Peach.png",Renderer);
    Toad = c.r.LoadTexture("data/Persos/Toad.png",Renderer);
    Yoshi = c.r.LoadTexture("data/Persos/Yoshi.png",Renderer);
}

void Player::init_rect_player()
{
    Personnage.x = 550;
    Personnage.y = 350;
    Personnage.w = 100;
    Personnage.h = 120;

}

void Player::copy_texture_player(SDL_Renderer *Renderer)
{
    if (c.JoueurChoisi == 1) SDL_RenderCopy(Renderer, Mario, NULL, &Personnage);
    if (c.JoueurChoisi == 2) SDL_RenderCopy(Renderer, Luigi, NULL, &Personnage);
    if (c.JoueurChoisi == 3) SDL_RenderCopy(Renderer, Peach, NULL, &Personnage);
    if (c.JoueurChoisi == 4) SDL_RenderCopy(Renderer, Toad, NULL, &Personnage);
    if (c.JoueurChoisi == 5) SDL_RenderCopy(Renderer, Koopa, NULL, &Personnage);
    if (c.JoueurChoisi == 6) SDL_RenderCopy(Renderer, Yoshi, NULL, &Personnage);
    if (c.JoueurChoisi == 7) SDL_RenderCopy(Renderer, Browser, NULL, &Personnage);

}

void Player::destroy_texture_player()
{
    SDL_DestroyTexture(Mario);
    SDL_DestroyTexture(Luigi);
    SDL_DestroyTexture(Koopa);
    SDL_DestroyTexture(Browser);
    SDL_DestroyTexture(Peach);
    SDL_DestroyTexture(Toad);
    SDL_DestroyTexture(Yoshi);
}
