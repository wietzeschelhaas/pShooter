package com.wsgames.pshooter;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FillViewport;

/**
 * Created by wietze on 10/19/2017.
 */

public class GameScene extends BaseScene {
   World world;
   CreateWorldObjects crwo;

   SpriteBatch batch;

   OrthographicCamera box2DCam;
   FillViewport viewport;

   TiledMap map;
   OrthogonalTiledMapRenderer mapRenderer;

   Player player;
   Hud hud;

   public GameScene(PShooter game){
      super(game);

      this.game = game;
      batch = new SpriteBatch();

      box2DCam = new OrthographicCamera(game.SCREENWIDTH/game.PPM,game.SCREENHEIGHT/game.PPM);
      viewport = new FillViewport(game.SCREENWIDTH / game.PPM,game.SCREENHEIGHT/game.PPM,box2DCam);

      world = new World(new Vector2(0,0),true);
      crwo = new CreateWorldObjects();

      map = new TmxMapLoader().load("map.tmx");

      mapRenderer = new OrthogonalTiledMapRenderer(map);

      new MapBodyBuilder(map,world,crwo);
      hud = new Hud(game);

      player = new Player(game,crwo,box2DCam,world,new Texture("example.png"),hud);


   }

   @Override
   public void render(float delta) {
      super.render(delta);
      Gdx.gl.glClearColor(0,0,0,1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


      // move the camera to player pos
      game.camera.position.x = player.body.getPosition().x*game.PPM;
      game.camera.position.y = player.body.getPosition().y*game.PPM;
      game.camera.update();

      box2DCam.position.x = player.body.getPosition().x;
      box2DCam.position.y = player.body.getPosition().y;
      box2DCam.update();

      // TILE RENDER
      batch.setProjectionMatrix(game.camera.combined);
      batch.begin();
      mapRenderer.setView(game.camera);
      mapRenderer.render(new int[]{0,1});
      batch.end();

      // Box2D RENDER
      batch.setProjectionMatrix(box2DCam.combined);
      batch.begin();
      player.getSprite().draw(batch);
      batch.end();

      hud.draw(delta);

      update();
      world.step(1/60f,6,2);



   }

   private void update(){
      player.update();
   }

   @Override
   public void show() {
      super.show();
   }

   @Override
   protected void handleBackPress() {
      super.handleBackPress();
   }
}
