package Tools;

import box2dLight.RayHandler;
import box2dLight.PointLight;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MarioBros;
import com.mygdx.game.Sprites.Mario;



/**
 * Created by Ridhwaan on 7/12/2016.
 */
public class LightHandler {
    private RayHandler handler;
    private OrthographicCamera gameCam;
    private box2dLight.PointLight myLight;
    private Body marioBody;
    public LightHandler(World world, OrthographicCamera camera,Body body ){
        this.gameCam = camera;
        this.marioBody = body;
        handler = new RayHandler(world);

        myLight = new box2dLight.PointLight(handler, 20 ,Color.WHITE, (1/MarioBros.PPM)/2, marioBody.getPosition().x/MarioBros.PPM,body.getPosition().y/ MarioBros.PPM);
        myLight.setSoftnessLength(0f);





    }



    public void update(float dt){

        handler.setCombinedMatrix( gameCam.combined.cpy().scl(MarioBros.PPM));
        handler.update();
        myLight.setPosition(marioBody.getPosition().x/ MarioBros.PPM,marioBody.getPosition().y/MarioBros.PPM);

    }


public void render(){
    handler.render();
}



}
