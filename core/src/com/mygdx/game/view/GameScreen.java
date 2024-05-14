// GameScreen.java
package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.AtomicBomber;
import com.mygdx.game.controller.GameUIController;
import com.mygdx.game.model.Opp.Tank;
import com.mygdx.game.model.PlaneShoot.Shootables;
import com.mygdx.game.model.Missile;
import com.mygdx.game.model.Player;

import java.util.List;

public class GameScreen implements Screen {
    AtomicBomber game;
    public SpriteBatch batch;
    private Stage stage;
    private final Player player;
    private Texture background;
    private ShapeRenderer shapeRenderer;
    private Texture road;
    private List<Tank> tanks;

    private float timeSinceLastShot = 1;
    private static final float SHOOT_INTERVAL = 0.5f; // Interval between shots in seconds

    public GameScreen(AtomicBomber game, Player player) {
        this.game = game;
        this.batch = game.batch;
        this.player = player;
    }

    @Override
    public void show() {
        background = new Texture("sky.png");
        road = new Texture("road.png");
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        shapeRenderer = new ShapeRenderer();

        // Add UI elements from GameUIController
        stage.addActor(GameUIController.gameUIController.table);
    }

    @Override
    public void render(float delta) {
        // Check for input and update game state
        handleInput();

        // Update the position of active missiles
        for (Shootables shootable : player.activeShootables) {
            if (shootable instanceof Missile) {
                ((Missile) shootable).update(delta);
            }
        }

        // Update the timer
        timeSinceLastShot += delta;

        // Render the game
        if (!game.isPaused) {
            renderGameScreen();
            // Update UI elements
            GameUIController.gameUIController.updateKillCount();
            GameUIController.gameUIController.updateWaveNumber();
            GameUIController.gameUIController.updateRadioActiveBombCount();
            GameUIController.gameUIController.updateClusterBombCount();
            GameUIController.gameUIController.updatePlayerHealth();
        } else {
            addAndRemoveFreezeScreen();
        }

        // Draw the UI stage
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    private void addTank(float x, float y) {
        Tank tank = new Tank();
        tank.setX(x);
        tank.setY(y);
        tanks.add(tank);
    }
    private void handleInput() {
        // Handle movement input
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.velocityY = player.speed;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.velocityY = -player.speed;
        } else {
            player.velocityY = 0;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.isPaused = !game.isPaused;
            GameUIController.gameUIController.pauseWindow(game);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.G)) {
            AtomicBomber.radioActiveBombCount++;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_LEFT)) {
            AtomicBomber.clusterBombCount++;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
            // Spawn tank at a random position
            float randomX = MathUtils.random(0, Gdx.graphics.getWidth() - 100);
            float Y = -200;
            addTank(randomX,Y);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.H)) {
            player.playerHealth = 100;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)) {
            addAndRemoveFreezeScreen();
        }

        // Shoot missiles at the specified interval
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && timeSinceLastShot >= SHOOT_INTERVAL) {
            // Call the shoot method of the player
            Player.getCurrentPlayer().shoot();
            timeSinceLastShot = 0; // Reset the timer after shooting
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.velocityX = -player.speed;
            // Set flipped texture when moving to the left
            player.planeSprite.setTexture(player.planeFlipped);
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.velocityX = player.speed;
            // Set regular texture when moving to the right
            player.planeSprite.setTexture(player.plane);
        } else {
            player.velocityX = 0;
        }

        // Update player position based on velocity
        float newX = player.planeSprite.getX() + player.velocityX * Gdx.graphics.getDeltaTime();
        float newY = player.planeSprite.getY() + player.velocityY * Gdx.graphics.getDeltaTime();

        // Ensure the player stays within the screen boundaries
        if (newX < 0) {
            newX = Gdx.graphics.getWidth() - player.planeSprite.getWidth();
        } else if (newX > Gdx.graphics.getWidth() - player.planeSprite.getWidth()) {
            newX = 0;
        }
        else {
            newX = Math.max(0, Math.min(newX, Gdx.graphics.getWidth() - player.planeSprite.getWidth()));
            newY = Math.max(0, Math.min(newY, Gdx.graphics.getHeight() - player.planeSprite.getHeight()));
        }

        player.planeSprite.setPosition(newX, newY);
    }


    @Override
    public void resize(int width, int height) {
        // Implement if needed
    }

    @Override
    public void pause() {
        // Pause game logic
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.isPaused = !game.isPaused;
            GameUIController.gameUIController.pauseWindow(game);
        }
    }

    @Override
    public void resume() {
        // Resume game logic
    }

    @Override
    public void hide() {
        // Hide game screen, if needed
    }

    @Override
    public void dispose() {
        // Dispose resources
        this.stage.dispose();
        this.background.dispose();
        this.shapeRenderer.dispose();
        this.road.dispose();
    }

    private void renderGameScreen() {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(player.planeSprite, player.planeSprite.getX(), player.planeSprite.getY());
        batch.draw(road, 0, -195, Gdx.graphics.getWidth() + 100, 500); // Adjust the height as needed

        // Render tanks
        if (tanks != null) {
            for (Tank tank : tanks) {
                tank.render(batch);
            }
        }

        // Render active shootables (missiles)
        for (Shootables shootable : player.activeShootables) {
            shootable.render(batch);
        }

        batch.end();
    }
    private void addAndRemoveFreezeScreen() {
        // Implement if needed
    }

    public void fullDispose() {
        // Implement if needed
    }
}
