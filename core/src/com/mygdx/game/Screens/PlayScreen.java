package com.mygdx.game.Screens;

import Tools.LightHandler;
import Tools.WorldContactListener;
import Tools.bodyWorldCreator;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.IntFloatMap;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MarioBros;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Sprites.Bullet;
import com.mygdx.game.Sprites.Enemy;
import com.mygdx.game.Sprites.Goomba;
import com.mygdx.game.Sprites.Mario;
import javafx.scene.input.KeyCode;


/**
 * Created by Ridhwaan on 5/8/2016.
 */


public class PlayScreen implements Screen {
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    //game object classes
    private Hud hud;
    private Mario mario;
    private MarioBros game;
   // private LightHandler lightHandler;
    private Bullet bullet;
    private Goomba goomba;


    private Music music;

    private TextureAtlas atlas;
    private Texture bulletTexture;


    private TiledMap map;

    private TmxMapLoader mapLoader;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d variables

    private World world;
    private Box2DDebugRenderer debugRenderer;


    public PlayScreen(MarioBros game) {
        world = new World(new Vector2(0, -10), true);
        atlas = new TextureAtlas("MarioAssests.atlas");
        bulletTexture = new Texture("bullet.png");

        mario = new Mario(this);

        world.setContactListener(new WorldContactListener());



        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(MarioBros.V_WIDTH/MarioBros.PPM, MarioBros.V_HEIGHT/MarioBros.PPM, gameCam);

        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("MarioMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map,1/ MarioBros.PPM);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);



      //  music = MarioBros.manager.get("gameMusic.mp3",  Music.class);
      //  music.setLooping(true);
      //  music.play();

        debugRenderer = new Box2DDebugRenderer();

         new bodyWorldCreator(this);

       // lightHandler = new LightHandler(world,gameCam,getBody());
        bullet = new Bullet(world, mario.body,getBulletTexture());
        goomba = new Goomba(this,32/MarioBros.PPM,32f/MarioBros.PPM );

    }

    public TextureAtlas getAtlas(){
        return  this.atlas;
    }
    public Texture getBulletTexture(){
     return this.bulletTexture;
    }
    public Body getBody(){
       return  mario.getBody();
    }
    public TiledMap getMap(){
        return map;
    }
    public World getWorld(){
        return world;
    }


    @Override
    public void show() {

    }


    public void update(float dt) {
        world.step(1/60f,6,2);

        handleInput(dt);

        mario.update(dt);
        goomba.update(dt);

        hud.update(dt);
       // lightHandler.update(dt);
        bullet.update(dt);
        gameCam.position.x = mario.body.getPosition().x;
        gameCam.position.y = mario.body.getPosition().y + 50/MarioBros.PPM;

        gameCam.update();



        renderer.setView(gameCam);



    }

    public void handleInput(float dt) {













    }
        @Override
        public void render ( float delta){
            Gdx.gl.glClearColor(0, 0, 0, 0);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            update(delta);

            renderer.render();

            debugRenderer.render(world, gameCam.combined);
           // lightHandler.render();

            game.batch.setProjectionMatrix(gameCam.combined);
            game.batch.begin();

            mario.draw(game.batch);
            goomba.draw(game.batch);

            bullet.draw(game.batch);

            game.batch.end();






            //set what we see as the HUD
            game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
            hud.stage.draw();



        }

        @Override
        public void resize ( int width, int height){
            gamePort.update(width, height);


        }

        @Override
        public void pause () {

        }

        @Override
        public void resume () {

        }

        @Override
        public void hide () {

        }

        @Override
        public void dispose () {

            map.dispose();
            renderer.dispose();
            world.dispose();
            debugRenderer.dispose();







        }


    }

