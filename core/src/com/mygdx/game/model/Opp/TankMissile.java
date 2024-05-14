//package com.mygdx.game.model.Opp;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g3d.model.Animation;
//import com.mygdx.game.AtomicBomber;
//import com.mygdx.game.model.CollisionReact;
//import com.mygdx.game.model.Player;
//
//public class TankMissile extends OppMissile{
//    public Texture texture = new Texture("tank_missile.png");
//    public Sprite sprite = new Sprite(texture);
//    public float time = 0;
//    private float timeSeconds = 0f;
//    private float period = 5f;
//
//    public TankMissle(float x, float y, float speed){
//        this.x = x;
//        this.y = y;
//        this.speed = speed;
//        this.damage = 5;
//        this.rect = new CollisionReact(x, y, texture.getWidth(), texture.getHeight());
//        this.targetX = Player.player.planeSprite.getX();
//        this.targetY = Player.player.planeSprite.getY();
//        this.isDestroyed = false;
//        this.canMove = true;
//        OppMissileController.oppMissles.add(this);
//    }
//
//    @Override
//    public void update(float delta) {
//        if (!isDestroyed){
//            this.targetX = Player.player.planeSprite.getX();
//            this.targetY = Player.player.planeSprite.getY();
//            if (canMove && !AtomicBomber.isPaused){
//                float angle = (float) Math.atan2(targetY - y, targetX - x);
//                float xVel = (float) (speed * Math.cos(angle));
//                float yVel = (float) (speed * Math.sin(angle));
//                x += xVel * delta;
//                y += yVel * delta;
//                sprite.setRotation((float) (angle * (float) 180 / Math.PI));
//                rect.move(x, y);
//                dealDamage();
//                destroyAfterSomeTime();
//            }
//            if (y < -50 || y > Gdx.graphics.getHeight() || x > Gdx.graphics.getWidth() || x < 0 || !canMove){
//                destroy();
//            }
//        }
//
//    }
//
//    private void destroyAfterSomeTime() {
//    }
//
//    private void dealDamage() {
//    }
//
//    @Override
//    public void render(SpriteBatch batch) {
//
//    }
//    @Override
//
//    public void destroy() {
//    }
//}
