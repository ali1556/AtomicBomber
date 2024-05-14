// Player.java
package com.mygdx.game.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.AtomicBomber;
import com.mygdx.game.model.PlaneShoot.Shootables;

import java.util.ArrayList;

public class Player {
    public static Player player = new Player();
    public Texture plane = new Texture(GameAssets.gameAssets.activePlane);
    public Texture planeFlipped = new Texture(GameAssets.gameAssets.activePlaneFlipped);
    public Sprite planeSprite = new Sprite(plane);
    public float velocityX = 0;
    public float velocityY = 0;
    public float speed = 250;
    public float playerHealth = 100;
    public ArrayList<Shootables> activeShootables = new ArrayList<Shootables>();

    private Player() {
        planeSprite.setPosition(0, 100);
    }

    public static Player getCurrentPlayer() {
        return player;
    }

    public void shoot() {
        // Calculate initial velocity of the missile based on player's horizontal velocity
        float missileSpeedX = velocityX != 0 ? velocityX * 1.3f : 0; // Adjust multiplier as needed

        // Create and add a new missile to the game
        Missile missile = new Missile(player.planeSprite.getX() + player.planeSprite.getWidth() / 2,
                player.planeSprite.getY(), missileSpeedX);

        // Add the missile to the list of active shootables
        activeShootables.add(missile);
    }

}
