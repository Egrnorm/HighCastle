package com.highcastle.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

public class CharacWorld {

    /*--------------------------------
    тут  надо создать юнитов
    К примеру
    private Ball[] balls;
    */
    private Units[] enemys;

    public CharacWorld(int count, Texture img, World world){
        enemys = new Units[count];
        enemys[0] = new Units(10, 10, world, img);
        enemys[1] = new Units(20, 10, world, img);
        enemys[2] = new Units(15, 10, world, img);
        enemys[3] = new Units(10, 20, world, img);
        enemys[4] = new Units(10, 15, world, img);
        enemys[5] = new Units(20, 15, world, img);
    }
    public Units getEnemyAt(int index){
    return enemys[index];
    }

    public void draw(SpriteBatch batch){
        for(int i = 0; i < enemys.length; i++){
            enemys[i].draw(batch);
        }
    }



}
