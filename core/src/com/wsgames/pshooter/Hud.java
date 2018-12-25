package com.wsgames.pshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.utils.viewport.FillViewport;

/**
 * Created by wietze on 10/19/2017.
 */

public class Hud {
    Stage stage;
    PShooter game;

    OrthographicCamera hudCam;

    Touchpad touchpad;

    FillViewport viewport;

    Skin skin;

    public Hud(PShooter game){
        this.game = game;
        hudCam = new OrthographicCamera(game.SCREENWIDTH/2,game.SCREENHEIGHT/2);

        viewport = new FillViewport(game.SCREENWIDTH,game.SCREENHEIGHT,hudCam);

        stage = new Stage(viewport);

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        touchpad = new Touchpad(25,skin);
        touchpad.setBounds(100,25,50,50);
        Gdx.input.setInputProcessor(stage);

        stage.addActor(touchpad);
    }


    public float knobX(){
        return touchpad.getKnobPercentX();
    }
    public float knobY(){
        return touchpad.getKnobPercentY();
    }

    public void draw(float delta){
        stage.act();
        stage.draw();

    }
}
