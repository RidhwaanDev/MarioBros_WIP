package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MarioBros;

/**
 * Created by Ridhwaan on 7/12/2016.
 */
public class Bullet extends Sprite {
    private Body MarioBody;
    private Body bulletBody;
    private World world;

    //setPosition (sets the position of the texture)
    //setTexture (sets the texcture of the region )
    public Bullet (World world, Body body,Texture bulletTexture ){
        super(bulletTexture);
        bulletTexture = new Texture("bullet.png");
        this.MarioBody = body;
        this.world = world;

        bulletDefintion();





        }

public void update(float dt){
    setBounds((bulletBody.getPosition().x  - getWidth()/2)  , bulletBody.getPosition().y - getHeight()/2,10/MarioBros.PPM,10/MarioBros.PPM);
    handleInput();

}





    public BodyDef bulletDefintion(){

        BodyDef bulletDef = new BodyDef();
        bulletDef.type = BodyDef.BodyType.DynamicBody;
        bulletDef.position.set(32/MarioBros.PPM, 32/MarioBros.PPM);
        bulletBody = world.createBody(bulletDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(5/MarioBros.PPM);

        FixtureDef bulletFixtureDef = new FixtureDef();
        bulletFixtureDef.shape = shape;
        bulletFixtureDef.friction = 0f;
        bulletFixtureDef.filter.categoryBits = MarioBros.BULLET_BIT;
        bulletFixtureDef.filter.maskBits = MarioBros.GROUND_BIT;
        bulletBody.createFixture(bulletFixtureDef);


        bulletBody.setUserData("bullet");

        return bulletDef;
    }

    public void handleInput(){




    }





    }
