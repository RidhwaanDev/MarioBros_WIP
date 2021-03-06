package com.mygdx.game.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.MarioBros;
import com.mygdx.game.Screens.PlayScreen;

import static com.badlogic.gdx.scenes.scene2d.ui.Table.Debug.cell;

/**
 * Created by Ridhwaan on 6/19/2016.
 */
public abstract class InteractiveTileObject {


    protected World world;
    protected TiledMap map;
    protected TiledMapTile tiled;
    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;
public InteractiveTileObject(PlayScreen screen, Rectangle bounds){

    this.world = screen.getWorld();
    this.map = screen.getMap();
    this.bounds = bounds;

    BodyDef bodyDef = new BodyDef();
    FixtureDef fixtureDef = new FixtureDef();
    PolygonShape shape = new PolygonShape();

    bodyDef.type = BodyDef.BodyType.StaticBody;
    bodyDef.position.set((bounds.getX() + bounds.getWidth() / 2)/ MarioBros.PPM, (bounds.getY() + bounds.getHeight() / 2)/MarioBros.PPM);

    body = world.createBody(bodyDef);

    shape.setAsBox((bounds.getWidth() / 2)/MarioBros.PPM, (bounds.getHeight() / 2)/MarioBros.PPM);
    fixtureDef.shape = shape;
    fixture = body.createFixture(fixtureDef);


}

    public abstract void onHeadHit();
          public  void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);

    }

    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);


        return layer.getCell((int)(body.getPosition().x * MarioBros.PPM/16), (int)(body.getPosition().y * MarioBros.PPM/16));


    }



}
