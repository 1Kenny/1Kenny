#include "Terrain.h"

void Terrain::init_texture_terrain(SDL_Renderer *Renderer)
{
    ciel = c.r.LoadTexture("data/Ciel.png", Renderer);

    s_Terrain1 = IMG_Load("data/Maps/map1.png");
    s_Terrain2 = IMG_Load("data/Maps/map2.png");
    s_Terrain3 = IMG_Load("data/Maps/map3.png");
}

void Terrain::surface_to_texture(SDL_Renderer *Renderer)
{

    if(c.MapChoisi == 1) t_Terrain= SDL_CreateTextureFromSurface(Renderer, s_Terrain1);
    if(c.MapChoisi == 2) t_Terrain= SDL_CreateTextureFromSurface(Renderer, s_Terrain2);
    if(c.MapChoisi == 3) t_Terrain= SDL_CreateTextureFromSurface(Renderer, s_Terrain3);
}

void Terrain::copy_texture_terrain(SDL_Renderer *Renderer)
{
    SDL_RenderCopy(Renderer, t_Terrain, NULL, NULL);
    SDL_RenderCopy(Renderer, ciel, NULL, &Ciel);
}

void Terrain::lockSurface()
{
    if(c.MapChoisi == 1){
    if(SDL_MUSTLOCK(s_Terrain1))
          {
            if(SDL_LockSurface(s_Terrain1)<0)
            {
              printf("Can't lock screen: %s.\n", SDL_GetError());

            throw std::runtime_error("Unable to lock screen (required by system).");
            }
          }
    }
    
    if(c.MapChoisi == 2){
    if(SDL_MUSTLOCK(s_Terrain2))
          {
            if(SDL_LockSurface(s_Terrain2)<0)
            {
              printf("Can't lock screen: %s.\n", SDL_GetError());

            throw std::runtime_error("Unable to lock screen (required by system).");
            }
          }
    }
    
    if(c.MapChoisi == 3){
    if(SDL_MUSTLOCK(s_Terrain3))
          {
            if(SDL_LockSurface(s_Terrain3)<0)
            {
              printf("Can't lock screen: %s.\n", SDL_GetError());

            throw std::runtime_error("Unable to lock screen (required by system).");
            }
          }
    }
}

void Terrain::texture_mode7()
{
    if(c.MapChoisi == 1) {m.texture = s_Terrain1; m.render(s_Terrain1);}
    if(c.MapChoisi == 2) {m.texture = s_Terrain2; m.render(s_Terrain2);}
    if(c.MapChoisi == 3) {m.texture = s_Terrain3; m.render(s_Terrain3);}
}

void Terrain::unlockSurface(){
    //unlock
    if(c.MapChoisi == 1) {
       if(SDL_MUSTLOCK(s_Terrain1))
       {
         SDL_UnlockSurface(s_Terrain1);
       }
    }
    
    if(c.MapChoisi == 2) {
    //unlock
       if(SDL_MUSTLOCK(s_Terrain2))
       {
         SDL_UnlockSurface(s_Terrain2);
       }
    }
    if(c.MapChoisi == 3) {
    //unlock
       if(SDL_MUSTLOCK(s_Terrain3))
       {
         SDL_UnlockSurface(s_Terrain3);
       }
    }
}

void Terrain::destroy_texture_terrain()
{
    SDL_FreeSurface(s_Terrain1);
    SDL_DestroyTexture(t_Terrain);
    SDL_DestroyTexture(ciel);
}
