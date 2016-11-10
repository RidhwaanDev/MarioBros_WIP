package com.mygdx.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MarioBros;
import com.mygdx.game.Screens.PlayScreen;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.sun.xml.internal.ws.client.AsyncResponseImpl;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

/**
 * Created by Ridhwaan on 5/15/2016.
 */
public class Mario extends Sprite {
        public enum STATE{ FALLING,JUMPING,STANDING,RUNNING};
        public STATE currentState;
        public  STATE previousState;




        private Animation marioRun;
        private Animation marioJump;


        private boolean isRunningRight;

        private float stateTimer;

    public World world;
    public Body body;

    private TextureRegion marioStand;


    public  Mario( PlayScreen screen){
        super(screen.getAtlas().findRegion("MarioBros/little_mario") );
        world = screen.getWorld();
        currentState = STATE.STANDING;
        previousState = STATE.STANDING;
        stateTimer = 0;
        isRunningRight = true;



        Array<TextureRegion> frames = new Array<TextureRegion>();
//populate frames Array with our Texture Region
        for(int i = 1; i< 4; i++){
            frames.add(new TextureRegion(getTexture(), i * 16,0,16,16));
       }


        marioRun = new Animation(.1f,frames);

       frames.clear();
//populate frames Array with our Texture Region

    for(int i = 4; i<6; i++){
     frames.add(new TextureRegion(getTexture(), i * 16,0,16,16));
    }
      marioJump = new Animation(.1f,frames);

        frames.clear();



        //big mario test

        defineMario();




        marioStand = new TextureRegion(getTexture(),0,0,16,16);
        setBounds(0,0,16/MarioBros.PPM,16/MarioBros.PPM);
        setRegion(marioStand);
    }








    public void defineMario(){

        BodyDef bdef = new BodyDef();
        bdef.position.set(32/MarioBros.PPM,32/MarioBros.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;


        body = world.createBody(bdef);
        body.setLinearDamping(2f);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5/MarioBros.PPM);
        fixtureDef.filter.categoryBits = MarioBros.MARIO_BIT;
        fixtureDef.filter.maskBits = MarioBros.GROUND_BIT | MarioBros.COIN_BIT | MarioBros.BRICK_BIT |
                MarioBros.ENEMY_BIT | MarioBros.ENEMY_HEAD_BIT;



        fixtureDef.shape = shape;

        body.createFixture( fixtureDef);


        EdgeShape head = new EdgeShape();
        head.set(new Vector2(-2/MarioBros.PPM,7/MarioBros.PPM), new Vector2(2/MarioBros.PPM,7/MarioBros.PPM));
        fixtureDef.shape = head;
        fixtureDef.isSensor = true;










        body.createFixture(fixtureDef).setUserData("head");




    }




    public Body getBody (){
        return  body;
    }

    public void update(float dt){
       setPosition(body.getPosition().x - getWidth()/2 ,body.getPosition().y - getHeight()/2);
        setRegion(getFrame(dt));
        handleInput(dt);


    }


   public TextureRegion getFrame(float dt){

        currentState = getState();


        TextureRegion region;
                switch(currentState){

                    case JUMPING:

                        region = marioJump.getKeyFrame(stateTimer);
                        break;
                    case RUNNING:
                       region =  marioRun.getKeyFrame(stateTimer,true);
                        break;
                    case FALLING:
                    case STANDING:
                        default:
                            region = marioStand;
                            break;







                }


        if((body.getLinearVelocity().x < 0 || !isRunningRight) && !region.isFlipX() ){
            region.flip(true,false);
            isRunningRight = false;
        }
        else if ((body.getLinearVelocity().x > 0 || isRunningRight) && region.isFlipX()){
            region.flip(true,false);
            isRunningRight = true;
        }
        stateTimer = currentState == previousState ? stateTimer + dt: 0;
        previousState = currentState;
        return region;
    }

    public STATE getState(){
        if(body.getLinearVelocity().y > 0 || body.getLinearVelocity().y < 0 && previousState == STATE.JUMPING){
            return STATE.JUMPING;
        }

       else if(body.getLinearVelocity().y < 0){
            return STATE.FALLING;
        }
        else if(body.getLinearVelocity().x != 0){
            return STATE.RUNNING;
        }
        else
            return STATE.STANDING;

    }


    public void handleInput(float dt) {


        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            if(getState() == STATE.RUNNING || getState() == STATE.STANDING) {


                body.applyLinearImpulse(new Vector2(0, 4f), body.getWorldCenter(), true);

            }
        }



        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && body.getLinearVelocity().x <= 2){

            body.applyLinearImpulse(new Vector2(0.1f,0), body.getWorldCenter(), true);


        }






        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && body.getLinearVelocity().x >= -2){

            body.applyLinearImpulse(new Vector2(-0.1f,0), body.getWorldCenter(), true);


        }

























    }










}
