#ifndef MODE7_H_INCLUDED
#define MODE7_H_INCLUDED

#include "Ressource.h"
#include <math.h>


class Mode7
{
public:
    Ressource r;

    SDL_Surface* texture;
    float posX, posY;
    float angle;
    float height;
    float spaceZ;
    float scaleX, scaleY;
    float objScaleX, objScaleY;


private:
    int horizon;
    int fov;
    int scale;


  //Methods
public:
  Mode7();
  void render(SDL_Surface *screen);

};

#endif // MODE7_H_INCLUDED
