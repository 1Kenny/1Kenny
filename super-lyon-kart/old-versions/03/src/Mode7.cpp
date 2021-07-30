#include "Mode7.hpp"

#include <iostream>
#include <cmath>

namespace Mode7
{
    int width=640;
    int height=480;
SDL_Surface* surface = SDL_CreateRGBSurface(0, width, height, 32, 0, 0, 0, 0);

Uint32 SDL_get_pixel32(SDL_Surface *surface, int x, int y)
{
    Uint32 pixel;
 
    SDL_LockSurface(surface);
    //Convert the pixels to 32 bit
    Uint32 *pixels = (Uint32 *)surface->pixels;
 
    //Get the requested pixel
    pixel =  pixels[ ( y * surface->w ) + x ];
    SDL_UnlockSurface(surface);
    return pixel;
}
 
void SDL_put_pixel32(SDL_Surface *surface, int x, int y, Uint32 pixel)
{
    SDL_LockSurface(surface);
    //Convert the pixels to 32 bit
    Uint32 *pixels = (Uint32 *)surface->pixels;
     
    //Set the pixel
    pixels[ ( y * surface->w ) + x ] = pixel;
    SDL_UnlockSurface(surface);
}

void DrawMode7Stretched (SDL_Surface* bmp, SDL_Surface* tile, double angle, double cx, double cy, Params params)
{
    Uint32 pixel;
    //tile = SDL_ConvertSurface(tile, bmp->format, 0);
    // current screen position
    int screen_x, screen_y;
 
    // the distance and horizontal scale of the line we are drawing
    double distance, horizontal_scale;
 
    // masks to make sure we don't read pixels outside the tile
    int mask_x = (tile->w - 1);
    int mask_y = (tile->h - 1);
 
    // step for points in space between two pixels on a horizontal line
    double line_dx, line_dy;
 
    // current space position
    double space_x, space_y;
    screen_y = 0;
 
    for (((screen_y - params.horizon) < 0)?screen_y=params.horizon:screen_y=0; screen_y < bmp->h; screen_y++)
    {
        // first calculate the distance of the line we are drawing
        distance = (params.spaceZ * params.scaleY) / (screen_y - params.horizon);
        // then calculate the horizontal scale, or the distance between
        // space points on this horizontal line
        horizontal_scale = distance / params.scaleX;
 
            // calculate the dx and dy of points in space when we step
            // through all points on this line
        line_dx = cos(angle) * horizontal_scale;
        line_dy = sin(angle) * horizontal_scale;
 
            // calculate the starting position
        space_x = cx + (distance * sin(angle)) - bmp->w/2 * line_dx;
        space_y = -cy + (distance * -cos(angle)) - bmp->w/2 * line_dy;
 
        // go through all points in this screen line
        for (screen_x = 0; screen_x < bmp->w; screen_x++)
        {
            // get a pixel from the tile and put it on the screen
            //if ((space_x >= 0) && (space_x < tile->w) && (space_y >= 0) && (space_y < tile->h))
            //{
                pixel = SDL_get_pixel32(tile, (int)space_x & mask_x, (int)space_y & mask_y);
                SDL_put_pixel32(bmp, screen_x, screen_y, pixel);
            //            static_cast<Uint32*>(surface->pixels)[screen_y*surface->w + screen_x] = static_cast<Uint32*>(source->pixels)[(space_y&mask_y)*source->w + (space_x&mask_x)];

                //SDL_put_pixel32(bmp, screen_x, screen_y, SDL_get_pixel32(tile, space_x, space_y));
            //}
            // advance to the next position in space
            space_x += line_dx;
            space_y += line_dy;
        }
    }
}

void DrawObject(SDL_Surface* const bmp,
    float angle,
    float objectX,
    float objectY,
    float cameraX,
    float cameraY,
    Params params)
{
    float newObjectX = objectX - cameraX;
    float newObjectY = objectY - cameraY;

    float spaceX = (newObjectX * cos(angle)) + (newObjectY * sin(angle));
    float spaceY = (-(newObjectX * sin(angle))) + (newObjectY * cos(angle));

    int screenX = bmp->w / 2 + ((params.scaleX / spaceX) * spaceY);
    int screenY = ((params.spaceZ * params.scaleY) / spaceX) - params.horizon;

    screenY*=2;

    int width = bmp->w * (params.objScaleX / spaceX);
    int height = bmp->h * (params.objScaleY / spaceX);

   // stretch_sprite(target, bitmap, screenX - width / 2, screenY - height, width, height);
    //static_cast<Uint32*>(surface->pixels)[screenX - surface->w / 2 ] = static_cast<Uint32*>(source->pixels)[(spaceY&mask_y)*source->w + (spaceX&mask_x)];

}

};
