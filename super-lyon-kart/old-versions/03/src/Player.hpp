#ifndef _PLAYER_H
#define _PLAYER_H

#include<SDL2/SDL.h>
#include<SDL2/SDL_image.h>
#include<SDL2/SDL_ttf.h>
#include <string>
class Sprites;
class Player
{
private:

    float mPositionX;
    float mPositionY;
    float mAcc;
    float mAngle;
    float mAngleAcc;
    float mVectorLength;




public:
    SDL_Window *win;
    SDL_Renderer *renderer;
    SDL_Surface* vsur;
    SDL_Surface* vtex;
    SDL_Texture* vscr;
    SDL_Texture *sprite;
    SDL_Rect recSprite;
    Player();

    void processInputEvents();
    void update();
    SDL_Texture *loadImage(const char path[], SDL_Renderer *renderer);
    //void updateBmp(int step);

    inline float x() const { return mPositionX; }
    inline void setX(float x) { mPositionX = x; }

    inline float y() const { return mPositionY; }
    inline void setY(float y) { mPositionY = y; }

    inline float angle() const { return mAngle; }

    //inline BITMAP *bitmap() const { return mBitmap; }
};

#endif
