package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MarioBros;
import com.mygdx.game.Screens.PlayScreen;

/**
 * Created by Ridhwaan on 6/19/2016.
 */
public class Brick extends InteractiveTileObject {
    @Override
    public void onHeadHit() {
        Gdx.app.log("Brick", " collision");
    }

    public Brick(PlayScreen screen, Rectangle bounds) {
        super(screen,bounds);
        fixture.setUserData(this);
        setCategoryFilter(MarioBros.BRICK_BIT);








    }
}
