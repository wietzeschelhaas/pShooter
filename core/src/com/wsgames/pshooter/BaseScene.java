package com.wsgames.pshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;

/**
 * Created by wietze on 10/19/2017.
 */

public class BaseScene extends ScreenAdapter {
    PShooter game;

    private boolean keyHandled;

    public BaseScene(PShooter game){
        this.game = game;

        keyHandled = false;

        Gdx.input.setCatchBackKey(true);
        Gdx.input.setCatchMenuKey(true);
    }


    @Override
    public void render(float delta) {
        super.render(delta);

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            if (keyHandled) {
                return;
            }
            handleBackPress();
            keyHandled = true;
        } else {
            keyHandled = false;
        }
    }

    protected void handleBackPress(){
        System.out.println("back");
    }

}
