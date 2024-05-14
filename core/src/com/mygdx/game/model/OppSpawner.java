package com.mygdx.game.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Model.Opps.Building;
import com.mygdx.game.Model.Opps.KaleGhandi;
import com.mygdx.game.Model.Opps.Opp;
import com.mygdx.game.Model.Opps.Truck;
import com.mygdx.game.Model.Opps.Tank;
import com.mygdx.game.Model.Opps.Wall;
import com.mygdx.game.model.Opp.Opp;
import com.mygdx.game.model.Opp.Tank;

import java.util.ArrayList;
import java.util.Random;

public class OppSpawner {
    public static OppSpawner oppSpawner = new OppSpawner();
    public ArrayList<Opp> opps = new ArrayList<Opp>();
    private boolean hasSpawnedOneTimes = false;

    public void update(float delta , SpriteBatch batch) {
        for (int i = 0; i < opps.size(); i++) {
            opps.get(i).update(delta);
            opps.get(i).render(batch);
        }
        spawnOpp();
    }

    private void spawnOpp() {
//        spawnTruck();
        spawnTank();
        if (!hasSpawnedOneTimes) {
//            spawnBuilding();
//            spawnWall();
//            spawnKaleGhandi();
            hasSpawnedOneTimes = true;
        }
    }

//    private void spawnTruck() {
//        if (new java.util.Random().nextInt(1000) % 497 == 0) {
//            opps.add(new Truck());
//        }
//    }

//    private void spawnBuilding() {
//        for (int i = 0; i < WaveChanger.buildingCount; i++) {
//            opps.add(new Building(new Random().nextInt(Gdx.graphics.getWidth()), new Random().nextInt(150)));
//        }
//    }

//    private void spawnKaleGhandi() {
//        for (int i = 0; i < WaveChanger.kaleGhanaCount; i++) {
//            opps.add(new KaleGhandi(new Random().nextInt(Gdx.graphics.getWidth()), new Random().nextInt(150)));
//        }
//    }

    private void spawnTank() {
        if (new java.util.Random().nextInt(1000) % 497 == 0) {
            opps.add(new Tank());
        }
    }

//    private void spawnWall() {
//        for (int i = 0; i < WaveChanger.wallCount; i++) {
//            opps.add(new Wall(new Random().nextInt(Gdx.graphics.getWidth()), new Random().nextInt(150)));
//        }
//    }

    public OppSpawner() {
    }
}
