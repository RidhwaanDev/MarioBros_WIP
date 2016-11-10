package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MarioBros;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Screens.PlayScreen;

/**
 * Created by Ridhwaan on 6/19/2016.
 */
public class Coin extends InteractiveTileObject {

    private TiledMapTileSet tileset;
    private final int BLANK_COIN = 28;



    public Coin(PlayScreen screen, Rectangle bounds) {
        super(screen, bounds);
        tileset = map.getTileSets().getTileSet("tileset_gutter");

        fixture.setUserData(this);
        setCategoryFilter(MarioBros.COIN_BIT);


    }


    @Override
    public void onHeadHit() {
        Gdx.app.log("Coin", " collision");
        getCell().setTile(tileset.getTile(BLANK_COIN));
        setCategoryFilter(MarioBros.DESTRYOED_BIT);

       // MarioBros.manager.get("assets/Sounds/wtf.mp3", Sound.class).play();

        if(getCell().getTile().getId() == BLANK_COIN) {
           //MarioBros.manager.get("assets/Sounds/Fart_sound_effect.mp3", Sound.class).play();

       }

        Hud.addScore(200);
    }

}
