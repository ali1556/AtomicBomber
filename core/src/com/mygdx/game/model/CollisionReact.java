package com.mygdx.game.model;

public class CollisionReact {
    float x, y;
    float width, height;

    public CollisionReact(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move(float x, float y){
        this.x = x;
        this.y = y;
    }
    public boolean collidesWith(CollisionReact react){
        return x < react.x + react.width && y < react.y + react.height && x + width > react.x && y + height > react.y;
    }
}
