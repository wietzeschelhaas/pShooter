package com.wsgames.pshooter;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by wietze on 10/19/2017.
 */

public class MapBodyBuilder {
    private static World world;
    private static CreateWorldObjects crwo;
    private static final int PPM = 100;

    public MapBodyBuilder(Map map, World world, CreateWorldObjects crwo){
        this.crwo = crwo;
        this.world = world;
        buildShapes(map);
    }
    public void buildShapes(Map map) {
        MapObjects objects = map.getLayers().get("Object Layer 1").getObjects();



        for(MapObject object : objects) {

            if (object instanceof TextureMapObject) {
                continue;
            }
            if (object instanceof RectangleMapObject) {
                getRectangle((RectangleMapObject)object);
            }

        }
    }

    private static void getRectangle(RectangleMapObject rectangleObject) {
        Rectangle rectangle = rectangleObject.getRectangle();
        System.out.println(rectangle);
        crwo.CreateBox(world, BodyDef.BodyType.StaticBody,rectangle.x/PPM +((rectangle.width/PPM)/2),rectangle.y/PPM + ((rectangle.height/PPM)/2),
                (rectangle.width/PPM)/2,(rectangle.height/PPM)/2);

    }

}
