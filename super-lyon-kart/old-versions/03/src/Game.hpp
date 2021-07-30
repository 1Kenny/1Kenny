#ifndef _GAME_H
#define _GAME_H


#include<SDL2/SDL.h>
#include<SDL2/SDL_image.h>
#include<SDL2/SDL_ttf.h>
#include "Mode7.hpp"
//class Track;
class Camera;
class Player;
//class Opponent;
// Mode7;
class Game
{
private:
    bool mQuit;
    Mode7::Params mMode7Params;

    //Track *mCurrentTrack;
    Camera *mCamera;
    Player *mPlayer;

    //Opponent *mOpponents[2];

public:
    //const Uint8* key = SDL_GetKeyboardState(NULL);

    Game();
    ~Game();

    void processInputEvents();
    void update();
    void render();

    inline bool quit() const { return mQuit; }
    inline void setQuit(bool quit) { mQuit = quit; }
    SDL_Window *win;
    SDL_Renderer *renderer;
};

#endif
