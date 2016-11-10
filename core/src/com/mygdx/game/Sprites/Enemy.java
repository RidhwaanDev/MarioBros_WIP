package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Screens.PlayScreen;

/**
 * Created by Ridhwaan on 7/22/2016.
 */
public abstract class Enemy extends Sprite {
        public World world;
        public  PlayScreen screen;
        public Body body;

    public Enemy(PlayScreen screen, float x, float y){

        this.world = screen.getWorld();
        this.screen = screen;
            this.body = screen.getBody();
        setPosition(x,y);
        defineEnemy();
    }

    protected abstract void defineEnemy();
    public abstract void hitOnHead();
}
