package com.mygdx.game.model.Opp;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.CollisionReact;

public abstract class OppMissile {
    float x;
    float y;
    float damage;
    float speed;
    float targetX;
    float targetY;
    CollisionReact rect;
    boolean isDestroyed , canMove;

    public abstract void update(float delta);
    public abstract void render(SpriteBatch batch);
    public abstract void destroy();
}
