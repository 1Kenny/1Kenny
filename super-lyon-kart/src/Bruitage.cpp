#include "Bruitage.h"

void Bruitage::sdlInit_mixer()
{
// SDL_mixer initialisation
    if (Mix_OpenAudio(MIX_DEFAULT_FREQUENCY, MIX_DEFAULT_FORMAT, 2, 4096) == -1) {
      SDL_LogCritical(SDL_LOG_CATEGORY_APPLICATION,
                      "SDL_mixer init error: %s\n   PERHAPS NO "
                      "HEADPHONES/SPEAKERS CONNECTED\n",
                      Mix_GetError());
    //  return 1;
    }
    else SDL_Log("SDL_mixer initialised OK!\n");

    Mix_AllocateChannels(2);
}

void Bruitage::init_bruitage()
{
    B_menu = Mix_LoadWAV("data/Son/br_menu.wav");
    B_go = Mix_LoadWAV("data/Son/br_go.wav");
}

void Bruitage::load_bruitage(Mix_Chunk *Bruitage, int n)
{
    if (!Bruitage)
    {
        SDL_LogWarn(SDL_LOG_CATEGORY_APPLICATION, "Mix_LoadWAV error: %s\n",
        Mix_GetError());
    }
    else SDL_Log("SDL_mixer loaded Bruitage OK!\n");

    Mix_PlayChannel(n,Bruitage,0); //Jouer infiniment la musique
}

void Bruitage::play_bruitage(int n)
{
    init_bruitage();

    if(n==0)
    {
        if(!Mix_Paused(-1)) load_bruitage(B_menu, n);
    }
    if(n==1)
    {
        if(!Mix_Paused(-1)) load_bruitage(B_go, n);
    }

}

