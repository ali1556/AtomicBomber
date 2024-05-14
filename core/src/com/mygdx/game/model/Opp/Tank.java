package com.mygdx.game.model.Opp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.AtomicBomber;
import com.mygdx.game.model.CollisionReact;
import com.mygdx.game.model.Fire;

public class Tank extends Opp {
    public Texture texture = new Texture("tank.png");
    public Sprite sprite = new Sprite(texture);
    private boolean moveRight = true;

    public Tank() {
        this.x = -200;
        this.y = 100;
        this.speed = 100 * AtomicBomber.difficulty;
        this.health = 30;
        this.damage = 0;
        this.react = null;
        this.reactAgainstOtherOpps = new CollisionReact(x , y , texture.getWidth() + 20 , 1);
        this.fire = new Fire();
    }
    public void setX(float x){
        this.x = x;
    }
    public void setY(float y){
        this.y = y;
    }

    @Override
    public void update(float delta) {
        // Move the tank left or right
        if (moveRight) {
            x += speed * delta;
        } else {
            x -= speed * delta;
        }

        // Reverse direction if tank reaches screen edges
        if (x <= 0 || x >= Gdx.graphics.getWidth() - texture.getWidth()) {
            moveRight = !moveRight;
        }
    }

    @Override
    public void destroy() {
        // Implement destruction logic if needed
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }


}
