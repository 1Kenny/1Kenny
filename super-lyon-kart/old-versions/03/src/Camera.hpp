//
//  Camera.hpp
//  kart
//
//  Created by Ebagnitchie Charles=Emmanuel on 28/03/2020.
//  Copyright © 2020 Trash. All rights reserved.
//

#ifndef _CAMERA_H
#define _CAMERA_H

#include "Mode7.hpp"

class Game;
class Player;

class Camera
{
private:
    friend class Game;
    Mode7::Params mMode7Params;

    float mAngle;
    float mPositionX;
    float mPositionY;


public:
    Camera();

    void update(Player *player);

    inline float x() const { return mPositionX; }
    inline void setX(float x) { mPositionX = x; }

    inline float y() const { return mPositionY; }
    inline void setY(float y) { mPositionY = y; }

    inline float angle() const { return mAngle; }
    inline void setAngle(float angle) { mAngle = angle; }

    inline Mode7::Params mode7Params() const { return mMode7Params; }
};

#endif /* Camera_hpp */
