#include "Game.hpp"
#include "Player.hpp"
#include "Camera.hpp"
#include "Mode7.hpp"
#include<SDL2/SDL.h>
#include<SDL2/SDL_image.h>
#include<SDL2/SDL_ttf.h>
#include <iostream>
#include <sstream>
#include <fstream>
#include <cmath>

using namespace std;

Game::Game():
    mQuit(false)
{
    //mCurrentTrack = new Track(1);
    mCamera = new Camera();
    mPlayer = new Player();
   
   // TrackInfo info = mCurrentTrack->info();

    /*mOpponents[0] = new Opponent(Opponent::Luigi);
    mOpponents[0]->setX(913);
    mOpponents[0]->setY(604);

    mOpponents[1] = new Opponent(Opponent::Peach);
    mOpponents[1]->setX(943);
    mOpponents[1]->setY(627);

*/
//    mCamera->setX(info.startCameraX);
//    mCamera->setY(info.startCameraY);
}

Game::~Game()
{
    
}

void Game::processInputEvents()
{
    const Uint8* key = SDL_GetKeyboardState(NULL);

    if (key[SDLK_ESCAPE])
        mQuit = true;

    mPlayer->processInputEvents();

/*   if (key[KEY_UP]) {
       mCamera->setY(mCamera->y()+sin(mCamera->angle())*2);
       mCamera->setX(mCamera->x()+cos(mCamera->angle())*2);
   }
   if (key[KEY_DOWN]) {
       mCamera->setY(mCamera->y()-sin(mCamera->angle())*2);
       mCamera->setX(mCamera->x()-cos(mCamera->angle())*2);
   }

   if (key[KEY_LEFT])
       mCamera->setAngle(mCamera->angle() - 0.1);

   if (key[KEY_RIGHT])
       mCamera->setAngle(mCamera->angle() + 0.1);*/
}

void Game::update()
{
    mPlayer->update();
    mCamera->update(mPlayer);
    //mPlayer->updateBmp(mCamera->angle());

//for (int i = 0; i < (sizeof(mOpponents) / sizeof(*mOpponents)); i++)
       // mOpponents[i]->update();
}

void Game::render()
{
    const int W = 450;
    const int H = 200;
    /*const int W = 600;
    const int H = 380;*/
    const int pxs = 2;
    Camera();
    Player();
    //BITMAP *buffer = AllegroSystem::Instance()->screen()->bitmap();
    SDL_Surface* sur = SDL_LoadBMP("back1.bmp");
           SDL_Surface* tex = SDL_ConvertSurfaceFormat(sur, SDL_PIXELFORMAT_RGBA32, 0);
           SDL_Texture* scr = SDL_CreateTexture(renderer, SDL_PIXELFORMAT_RGBA32, SDL_TEXTUREACCESS_STREAMING, W, H);
    //SDL_FreeSurface(sur);

    
       mMode7Params.spaceZ = 10;
       mMode7Params.scaleX = 412;
       mMode7Params.scaleY = 412;
       mMode7Params.objScaleX = 100;
       mMode7Params.objScaleY = 100;
       mMode7Params.horizon = -90;
    
       DrawMode7Stretched(sur, tex, 0, 0, 0,mMode7Params);
    // Renderiza a pista
    /*Mode7::DrawMode7Stretched(sur,tex,
        mCamera->angle(),
        mCamera->x(),
        mCamera->y(),
        mCamera->mode7Params());
*/

    // Debug
    /*textprintf_ex(buffer, font, 10, 10, makecol(0, 0, 255), -1, "Camera X     = %f", mCamera->x());
    textprintf_ex(buffer, font, 10, 20, makecol(0, 0, 255), -1, "Camera Y     = %f", mCamera->y());
    textprintf_ex(buffer, font, 10, 30, makecol(0, 0, 255), -1, "Player X     = %f", mPlayer->x());
    textprintf_ex(buffer, font, 10, 40, makecol(0, 0, 255), -1, "Player Y     = %f", mPlayer->y());
    textprintf_ex(buffer, font, 10, 50, makecol(0, 0, 255), -1, "Camera Angle = %f", mCamera->angle());
    textprintf_ex(buffer, font, 10, 60, makecol(0, 0, 255), -1, "Player Angle = %f", mPlayer->angle());
    textprintf_ex(buffer, font, 10, 70, makecol(0, 0, 255), -1, "FPS          = %i", AllegroSystem::LastFps);*/

    // Renderiza o jogador
    /*SuperMode7::DrawObject(buffer,
        mPlayer->bitmap(),
        mCamera->angle(),
        mPlayer->x(),
        mPlayer->y(),
        mCamera->x(),
        mCamera->y(),
        mCamera->mode7Params());

    for (int i = 0; i < (sizeof(mOpponents) / sizeof(*mOpponents)); i++) {
        Opponent *opponent = mOpponents[i];

        SuperMode7::DrawObject(buffer,
            opponent->bitmap(),
            mCamera->angle(),
            (mPlayer->x()+opponent->x())-mCamera->x(),
            (mPlayer->y()+opponent->y())-mCamera->y(),
            opponent->x()-(opponent->x()-mPlayer->x()),
            opponent->y()-(opponent->y()-mPlayer->y()),
            mCamera->mode7Params());
    }

    // Joga tudo na tela
    blit(buffer, screen, 0, 0, 0, 0, SCREEN_W, SCREEN_H);*/
}
