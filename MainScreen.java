package com.highcastle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.badlogic.gdx.ai.btree.utils.BehaviorTreeParser;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.highcastle.game.GameAI.gamepf.PathFinderRRR;

import static java.lang.reflect.Array.set;

public class MainScreen implements Screen, InputProcessor {



    SpriteBatch batch;
    Texture img;
    float size = 100;
    int count = 5;

    private Units[] enemys = new Units[count];

    public static final float UNIT_SCALE = 1f / 16f;

    private World world;
    private TiledMap map;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRenderer renderer;

    private MyGame game;

    Sprite sprite;

    public float Xposition;
    public float Yposition;

    private float w,h;


    PathFinderRRR finder;

    private CharacWorld characWorld;

    private BehaviorTree<CharacWorld> unitsTree;
    Box2DDebugRenderer box2DDebugRenderer;

    public MainScreen(MyGame game){

        this.game = game;

        characWorld = new CharacWorld(5, img, world);
        BehaviorTreeParser par = new BehaviorTreeParser();
        unitsTree = par.parse(Gdx.files.internal("trees/UnitsTree"), characWorld);


        batch = new SpriteBatch();
        img = new Texture("vrag.ong");
        map = new TmxMapLoader().load("tmap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, UNIT_SCALE);
        camera = new OrthographicCamera(w, h);
        camera.setToOrtho(false, 100, 100);
        world = new World(new Vector2(), false);
        finder = PathFinderRRR.createPathFinderByMap(map);;
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getWidth();
        Xposition = 500;
        Yposition = 500;
        GameManager.initialize(w, h);
        //shapeRender.setAutoShapeType(true);

        box2DDebugRenderer = new Box2DDebugRenderer();
        enemys[0] = new Units(10, 10, world, img);
        enemys[1] = new Units(20, 10, world, img);
        enemys[2] = new Units(15, 10, world, img);
        enemys[3] = new Units(10, 20, world, img);
        enemys[4] = new Units(10, 15, world, img);
        enemys[5] = new Units(20, 15, world, img);
    }

    @Override
    public void show() {

    }

    //private ShapeRenderer shapeRender = new ShapeRenderer();

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        world.step(delta, 4, 4);
        unitsTree.step();

        camera.update();
        renderer.setView(camera);
        renderer.render();

        batch.setProjectionMatrix(camera.combined);
        InputManager.handleInput(camera, sprite);



        batch.begin();
        sprite.draw(batch);
        GameManager.renderGame(batch);

        characWorld.draw(batch);

        for(int i = 0; i < count; i++){
            enemys[i].draw(batch);
        }
       /* shapeRender.setProjectionMatrix(camera.combined);
        if(path != null){
            shapeRender.begin();
            shapeRender.setColor(1, 1, 0, 1);
            for(int i = 1; i < path.length; i++){
                shapeRender.line(path[i - 1], path[i]);
            }
            shapeRender.end();
        }

        if(startV != null){
            shapeRender.begin();
            shapeRender.setColor(1, 1, 0 ,1);
            shapeRender.circle(startV.x, startV.y, 1);
            shapeRender.end();
        }

        if(nodes != null){
            shapeRender.begin();
            shapeRender.setColor(0, 1, 0 , 1);
            for(Vector2 node : nodes){
                shapeRender.circle(node.x, node.y, 1);
            }
            shapeRender.end();
        }

        */



        batch.end();
        box2DDebugRenderer.render(world, camera.combined);

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        world.dispose();
        batch.dispose();
        GameManager.dispose();
        img.dispose();
    }



    @Override
    public boolean keyDown(int keycode){
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }


   /* private Vector3 tp = new Vector3();
    private Vector2 startV = null;
    private Vector2[] path = null;
    private float[] polygon = null;
    private Array<Vector2> nodes = null;

    */

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
     /*   camera.unproject(tp.set(screenX, screenY, 0));
        float x = tp.x;
        float y = tp.y;

        if(startV == null){
            startV = new Vector2(x, y);
        }
        else{
            path = finder.findPath(startV.x, startV.y, x, y);
            startV = null;
        }
         */
        return false;


    }



    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
