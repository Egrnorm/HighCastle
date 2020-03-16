package com.highcastle.game.GameAI.actions;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.math.Vector2;
import com.highcastle.game.CharacWorld;
import com.highcastle.game.Units;

import java.util.Vector;

public class UnitsAtack extends LeafTask<CharacWorld> {
    private CharacWorld world = null;


    @Override
    public Status execute(){
        if(world == null) {
            world = super.getObject();
        }
            if(world != null){
                Units enemy1 = world.getEnemyAt(0);
                Units enemy2 = world.getEnemyAt(1); //К примеру это главный герой

                Vector2 v = enemy1.getPosition();
                Vector2 v2 = enemy2.getPosition();
                Vector2 speed = new Vector2(v2);
                speed.sub(v);
                speed.nor();
                speed.scl(10);


                return Status.RUNNING;
            }


        return Status.FAILED;
    }


    @Override
    protected Task<CharacWorld> copyTo(Task<CharacWorld> task) {
        return null;
    }
}
