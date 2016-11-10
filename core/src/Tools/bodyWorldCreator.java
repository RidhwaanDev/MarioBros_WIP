package Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.MarioBros;
import com.mygdx.game.Screens.PlayScreen;
import com.mygdx.game.Sprites.Brick;
import com.mygdx.game.Sprites.Coin;

/**
 * Created by Ridhwaan on 6/19/2016.
 */
public class bodyWorldCreator {


    public bodyWorldCreator(PlayScreen screen){
        World world = screen.getWorld();
        TiledMap map = screen.getMap();
            BodyDef bodyDef = new BodyDef();
            PolygonShape shape = new PolygonShape();
            FixtureDef fixtureDef = new FixtureDef();
            Body body;




        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();


            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2)/ MarioBros.PPM, (rect.getY() + rect.getHeight() / 2)/MarioBros.PPM);

            body = world.createBody(bodyDef);

            shape.setAsBox((rect.getWidth() / 2)/MarioBros.PPM, (rect.getHeight() / 2)/MarioBros.PPM);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }
        // pipes
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();


            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set((rect.getX() + rect.getWidth() / 2)/MarioBros.PPM, (rect.getY() + rect.getHeight() / 2)/MarioBros.PPM);
            body = world.createBody(bodyDef);

            shape.setAsBox((rect.getWidth() / 2)/MarioBros.PPM, (rect.getHeight() / 2)/MarioBros.PPM);
            fixtureDef.shape = shape;
            fixtureDef.filter.categoryBits = MarioBros.GROUND_BIT;
            body.createFixture(fixtureDef);

        }
        //brick
        for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Brick(screen,rect);

        }


        //coins
        for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Coin(screen,rect);

        }







    }


}
