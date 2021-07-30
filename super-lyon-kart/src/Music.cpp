#include "Music.h"

void Music::sdlInit_mixer()
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
}

void Music::init_music()
{
    music_menu = Mix_LoadMUS("data/Son/son_menu.wav");
    music_jeu = Mix_LoadMUS("data/Son/son_jeu.wav");
}

void Music::load_music(Mix_Music *music)
{
    if (!music)
    {
        SDL_LogWarn(SDL_LOG_CATEGORY_APPLICATION, "Mix_LoadMUS error: %s\n",
        Mix_GetError());
    }
    else SDL_Log("SDL_mixer loaded music OK!\n");

    Mix_PlayMusic(music,-1); //Jouer infiniment la musique
}

void Music::play_music(int i)
{
    init_music();

    if(i==1)
    {
        load_music(music_menu);
    }
    else load_music(music_jeu);
}



