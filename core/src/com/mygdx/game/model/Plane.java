package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;

public class Plane {
    Texture plane = new Texture("extra/plane.png");
    int x;
    int y;
    int width;
    int height;
    int speed;

    public Plane() {
        this.x = 0;
        this.y = 0;
        this.width = 100;
        this.height = 100;
        this.speed = 5;
    }

}
