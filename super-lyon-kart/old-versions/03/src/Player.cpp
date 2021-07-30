#include "Player.hpp"
#include "Sprites.hpp"

#include <iostream>
#include <cmath>


const Uint8* keys = SDL_GetKeyboardState(NULL);
SDL_Renderer *renderer =NULL;
SDL_Texture *Player::loadImage(const char path[], SDL_Renderer *renderer)

{
//je dois tous les images en une , comme ça animer chaque npixels de l'image (piste,nature..) celà pertrat de charger une seule
//image par element et la manipuler dans la boucle
    SDL_Surface *tmp = NULL;
    SDL_Texture *texture = NULL;
    tmp = IMG_Load(path);
    if(NULL == tmp)
    {
        fprintf(stderr, "Erreur SDL_LoadBMP : %s", SDL_GetError());
        return NULL;
    }
    fprintf(stderr, " SDL_LoadBMP : réussi");

    texture = SDL_CreateTextureFromSurface(renderer, tmp);
    SDL_FreeSurface(tmp);
    if(NULL == texture)
    {
        fprintf(stderr, "Erreur SDL_CreateTextureFromSurface : %s", SDL_GetError());
        return NULL;
    }
    fprintf(stderr, " SDL_CreateTextureFromSurface : réussi");

    return texture;
}
Player::Player()
{
    /*SDL_Surface* vsur = IMG_Load("data/Mario1.png");
    SDL_Surface* vtex = SDL_ConvertSurfaceFormat(vsur, SDL_PIXELFORMAT_RGBA32, 0);
    SDL_FreeSurface(vsur);
    SDL_Texture* vscr = SDL_CreateTexture(renderer, SDL_PIXELFORMAT_RGBA32, SDL_TEXTUREACCESS_STREAMING, 48, 50);*/
    SDL_Texture *sprite=this->loadImage("data/Mario1.png",renderer);
    SDL_Rect recSprite={mPositionX,mPositionY,100,120};

    mPositionX = 913;
    mPositionY = 652;

    mAcc = 0;
    mAngle = -1.57;
    mAngleAcc = -1.57;
    mVectorLength = 0;

    SDL_RenderCopy(renderer, sprite, NULL, &recSprite);

}

void Player::processInputEvents()
{

    if (keys[SDL_SCANCODE_UP])
        mAcc += 0.03;
    else
        mAcc -= 0.03;

    if (keys[SDL_SCANCODE_DOWN])
        mAcc -= 0.2;
    else if (keys[SDL_SCANCODE_LEFT])
        mAngleAcc -= (6 - mAcc) / 100;

    if (keys[SDL_SCANCODE_RIGHT])
        mAngleAcc += (6 - mAcc) / 100;
}

void Player::update()
{
    if (mAcc > 4.0)
        mAcc = 4.0;
    if (mAcc < 0)
        mAcc = 0;

    float delta = mAngle - mAngleAcc;

    /*if (delta > 0)
        mBitmap = load_bitmap("mario15.bmp", NULL);
    else if (delta < 0)
        mBitmap = load_bitmap("mario3.bmp", NULL);
    else if (delta == 0)
        mBitmap = load_bitmap("mario1.bmp", NULL);*/

    mPositionX += mAcc * cos(mAngle);
    mPositionY += mAcc * sin(mAngle);

    if (mAngle < (M_PI * 2) * - 1)
    {
        mAngle = delta * -1;
        mAngleAcc = delta * -1;
    }
    else if (mAngle > (M_PI * 2))
    {
        mAngle = delta * -1;
        mAngleAcc = delta * -1;
    }
    else
    {
        mAngle = mAngleAcc;
    }
}

/*void Player::updateBmp(int step)
{
    //blit(mSprite, mBitmap, step*32, 0, 0, 0, 32, 32);
}*/
