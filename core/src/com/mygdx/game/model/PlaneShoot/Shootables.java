package com.mygdx.game.model.PlaneShoot;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.CollisionReact;

public abstract class Shootables {
    public float x;
    public float y;
    public float velocityX;
    public float speed;
    public float damage;
    public CollisionReact react;
    public boolean isDestroyed = false;
    public boolean canMove = true;
    public abstract void update(float delta);
    public abstract void destroy();
    public abstract void render(SpriteBatch batch);
}
