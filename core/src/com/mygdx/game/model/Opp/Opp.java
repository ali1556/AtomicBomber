package com.mygdx.game.model.Opp;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.CollisionReact;
import com.mygdx.game.model.Fire;

public abstract class Opp {
    public float speed;
    public float health;
    public float damage;
    public float x;
    public float y;
    public CollisionReact react;
    public CollisionReact reactAgainstOtherOpps;
    public Fire fire;

    public boolean isDestroyed = false;
    public abstract void update(float delta);
    public abstract void destroy();
    public abstract void render(SpriteBatch batch);
}
