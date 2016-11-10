package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MarioBros;

import java.awt.*;


/**
 * Created by Ridhwaan on 5/8/2016.
 */
public class Hud implements Disposable {

    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private static Integer score;

    private OrthographicCamera gameCam;



    Label countdownLabel;
    static Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label worldLabel;
    Label marioLabel;

    public Hud(SpriteBatch sb){
        worldTimer = 300;
        timeCount = 0;
        score = 0;
        gameCam = new OrthographicCamera();
        viewport = new FitViewport(MarioBros.V_WIDTH,MarioBros.V_HEIGHT,gameCam);
        stage = new Stage(viewport,sb);







        Table table = new Table();
        table.top();
        table.setFillParent(true);

         countdownLabel = new Label(String.format("%03d",worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
         scoreLabel = new Label(String.format("%06d",score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
         timeLabel= new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
         levelLabel= new Label("1 -1 ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
         worldLabel= new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
         marioLabel= new Label("FEGIT", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

            table.add(marioLabel).expandX().pad(10);
            table.add(worldLabel).expandX().pad(10);
            table.add(timeLabel).expandX().pad(10);
            table.row();
            table.add(scoreLabel).expandX();
            table.add(levelLabel).expandX();
            table.add(countdownLabel).expandX();

            stage.addActor(table);






        }


    public  void update (float dt){
        timeCount+=dt;
        if (timeCount >= 1){
            worldTimer--;
            countdownLabel.setText(Integer.toString(worldTimer));
            timeCount = 0;
        }
    }

    public static void addScore(int value){
        score += value;
        scoreLabel.setText(Integer.toString(score));

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
