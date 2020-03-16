package com.highcastle.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;

import javax.swing.text.Position;

public class Units {
    private Body body;
    private Texture img;


    public static float RADIUS = 20f;

    public Units(float x, float y, World world, Texture imh){
    body = createGlavGer(x, y, 20f, world);
    this.img = img;
    }


    public void draw(SpriteBatch batch){
        float divR = RADIUS * MainScreen.UNIT_SCALE;
        batch.draw(img, body.getPosition().x - divR, body.getPosition().y - divR, 2 * RADIUS * MainScreen.UNIT_SCALE, 2 * RADIUS * MainScreen.UNIT_SCALE);
    }


    public Vector2 getPosition(){
        return body.getPosition();
    }

    private Body createGlavGer(float x, float y, float radius, World world)
    {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.fixedRotation = true;
        Body body = world.createBody(def);

        CircleShape circle = new CircleShape();
        circle.setPosition(new Vector2(0, 0));
        circle.setRadius(radius);
        Fixture f = body.createFixture(circle, 1f);
        circle.dispose();

        body.setTransform(x, y, 0);
        return body;
    }



}

