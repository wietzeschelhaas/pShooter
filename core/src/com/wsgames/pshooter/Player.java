package com.wsgames.pshooter;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by wietze on 10/19/2017.
 */

public class Player {
    Body body;
    PShooter game;
    CreateWorldObjects crwo;
    OrthographicCamera box2dCam;
    private Sprite playerSprite;
    World world;
    Hud hud;

    float velocity = 0.02f;

    public Player(PShooter game, CreateWorldObjects crwo, OrthographicCamera box2dCam, World world, Texture texture,Hud hud){
        this.game = game;
        this.crwo = crwo;
        this.box2dCam = box2dCam;
        this.world = world;
        this.hud = hud;

        playerSprite = new Sprite(texture);
        playerSprite.setSize(playerSprite.getWidth()/game.PPM,playerSprite.getHeight()/game.PPM);
        playerSprite.setPosition(0,0);

        Rectangle playerRec = this.playerSprite.getBoundingRectangle();
        this.body = crwo.CreateBox(world, BodyDef.BodyType.DynamicBody,this.playerSprite.getX(),this.playerSprite.getY(),
                (playerRec.getWidth()/2)-0.04f,(playerRec.getHeight()/2)-0.04f);

        body.setFixedRotation(true);

    }
    public void update() {

        body.applyLinearImpulse(new Vector2(hud.knobX()*velocity, hud.knobY()*velocity), body.getWorldCenter(), true);
        this.playerSprite.setPosition(body.getPosition().x -(playerSprite.getWidth()/2),
                body.getPosition().y-(playerSprite.getHeight()/2));

        body.setLinearDamping(15);
    }
    public Sprite getSprite(){
        return this.playerSprite;
    }

    public void draw(SpriteBatch batch){
        playerSprite.draw(batch);
    }
}
