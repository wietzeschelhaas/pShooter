package com.wsgames.pshooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PShooter extends Game {

	static final float PPM  = 100;

	OrthographicCamera camera;

	public static final int SCREENWIDTH = 600;
	public static final int SCREENHEIGHT = 300;
	Viewport viewport;


	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.position.set(SCREENWIDTH  /2,SCREENHEIGHT/2,0);
		viewport = new FillViewport(SCREENWIDTH,SCREENHEIGHT,camera);

		setScreen(new GameScene(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		viewport.update(width,height,false);
		camera.update();
	}
}
