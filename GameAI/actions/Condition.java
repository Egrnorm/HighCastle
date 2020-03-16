package com.highcastle.game.GameAI.actions;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.highcastle.game.CharacWorld;
import com.highcastle.game.Units;

public class Condition extends LeafTask<CharacWorld> {
    private CharacWorld world = null;


    public Condition(){

    }

    public Condition(CharacWorld world){
        this.world = world;
    }
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
                Vector2 vleft = new Vector2(v);
                Vector2 vright = new Vector2(v);
                vleft.add(-30, 30);
                vright.add(30, 30);

                 if(Intersector.isPointInTriangle(v2, v, vleft, vright)){
                    return Status.SUCCEEDED;

                }
                 else {
                     return Status.FAILED;

            }

        }

        return Status.FAILED;
    }

    @Override
    protected Task<CharacWorld> copyTo(Task<CharacWorld> task){
        return new Condition(world);
    }
}
