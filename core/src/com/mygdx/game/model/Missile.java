// Missile.java
package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.PlaneShoot.Shootables;

public class Missile extends Shootables {
    private Sprite sprite;
    private float speed;
    private float damage;
    private float speedX;
    public Missile(float x, float y, float speedX) {
        // Load missile texture and create sprite
        Texture missileTexture = new Texture("ironbomb.png"); // Replace "ironbomb.png" with your missile texture file
        sprite = new Sprite(missileTexture);
        this.speedX = speedX;

        // Set initial position of the missile
        sprite.setPosition(x, y);

        // Set speed and damage of the missile
        speed = 500; // Adjust speed as needed
        damage = 20; // Adjust damage as needed
    }

    @Override
    public void destroy() {
        // Implement logic to handle missile destruction, such as removing it from the game world
    }

    @Override
    public void render(SpriteBatch batch) {
        // Render the missile sprite
        sprite.draw(batch);
    }

    public void update(float deltaTime) {
        // Update missile position based on speed and deltaTime
        float newX = sprite.getX() + speedX * deltaTime;
        float newY = sprite.getY() - speed * deltaTime; // Adjusted for downward movement
        sprite.setPosition(newX, newY);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public float getDamage() {
        return damage;
    }
}
