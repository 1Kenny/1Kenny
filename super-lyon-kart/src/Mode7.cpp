#include "Mode7.h"

Mode7::Mode7()
{
    spaceZ = 10;
    scaleX = 412;
    scaleY = 412;
    horizon = -90;
    objScaleX = 100;
    objScaleY = 100;
    fov = 100;
    scale = 200;
    horizon = -90;
    posX = 300;
    posY = 150;
    //angle=-2.03 ;
    
    //horizon = 16;
    //height = 50;
    //angle = 3.14159/2;
    //mPositionX = 940;
    //mPositionY = 630;
}


void Mode7::render(SDL_Surface *screen)
{
    Uint32 currentColor;
    // current screen position
        int screen_x, screen_y;
        // the distance and horizontal scale of the line we are drawing
        float distance;
        float horizontal_scale;

        // masks to make sure we don't read pixels outside the tile
        int mask_x = (screen->w - 1);
        int mask_y = (screen->h - 1);

        // step for points in space between two pixels on a horizontal line
        int lineDX, lineDY;

        // current space position
        int spaceX, spaceY;

    for (screen_y = -horizon; screen_y < texture->h; screen_y++) {
        distance = (spaceZ * scaleY) / (screen_y + horizon);
        horizontal_scale = (distance / scaleX);
        lineDX = -sin(angle) * horizontal_scale;
        lineDY = cos(angle) * horizontal_scale;
        spaceX = posX + (distance * cos(angle)) - texture->w * lineDX;
        spaceY = posY + (distance * sin(angle)) - texture->w * lineDY;

        for (screen_x = 0; screen_x < texture->w; screen_x++) {
            if ((spaceX<mask_x) && (spaceY<mask_y) && (spaceX>0) && (spaceY>0))
            {
                currentColor = r.getPixelFromSurface(texture, spaceX, spaceY);
                r.putPixelOnSurface(screen, screen_x, screen_y, currentColor);

            spaceX += lineDX + lineDX;
            spaceY += lineDY + lineDY;
            }
        }
    }

}
