package com.grp12.towerdefense.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.grp12.towerdefense.MainGame;
import com.grp12.towerdefense.gamelogic.Map;
import com.grp12.towerdefense.gamelogic.Node;
import com.grp12.towerdefense.gamelogic.enemies.AbstractEnemy;
import com.grp12.towerdefense.gamelogic.enemies.BasicEnemy;
import com.grp12.towerdefense.gamelogic.enemies.Wave;
import com.grp12.towerdefense.gamelogic.towers.AbstractTower;
import com.grp12.towerdefense.gamelogic.towers.BasicTower;
import com.grp12.towerdefense.views.EnemyView;
import com.grp12.towerdefense.views.GameMenuView;
import com.grp12.towerdefense.views.MapView;
import com.grp12.towerdefense.views.TowerView;
import com.grp12.towerdefense.views.View;

import java.util.ArrayList;

public class PlayState extends State {

    private BasicEnemy e;
    private Map map;

    //Views
    private MapView mapView;
    private EnemyView enemyView;
    private TowerView towerView;
    private GameMenuView gameMenuView;
    private Wave currentWave;
    private ArrayList<AbstractEnemy> enemies;
    private ArrayList<AbstractTower> towers;



    public PlayState(GameStateManager gsm) {
        super(gsm);

        map = new Map();


        //Views
        mapView = new MapView(map.getGrid());
        View.setTileHeight(mapView.getTileHeight());
        View.setTileWidth(mapView.getTileWidth());
        enemyView = new EnemyView();
        towerView = new TowerView();
        //TODO: #5: Implement GameMenuView
        gameMenuView = new GameMenuView();


        e = new BasicEnemy(map.getWaypoints(), 1, 100);
        currentWave = new Wave(e, 15);
        enemies = new ArrayList<AbstractEnemy>();
        towers = new ArrayList<AbstractTower>();
        AbstractTower tower = new BasicTower(new Vector2(1,16));
        towers.add(tower);
        AbstractTower.setEnemyList(enemies);
        towerView.addTower(tower);
    }

    @Override
    protected void handleInput() {
        //TODO: #10: Implement input handling
        if(Gdx.input.isTouched()){
            //get x and y from where finger touch
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            //convert so axes are 0,0 down to left and not up to the left
            y= Gdx.graphics.getHeight()-y;
            Vector2 nodeIsClicked = new Vector2(x,y);
            //get node informastion
            Node node =mapView.getNode(nodeIsClicked, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
            //if not nodepath build a tower there
            if(node.getType()== Node.NodeType.TOWERNODE){
                Vector2 setNode =new Vector2(node.getX(),node.getY());
                AbstractTower tower = new BasicTower(setNode);
                towers.add(tower);
                AbstractTower.setEnemyList(enemies);
                towerView.addTower(tower);
            }

        }
    }

    @Override
    public void update(float dt) {
        //Start: Spawn enemies in the start of the lane
        AbstractEnemy e = currentWave.popAttempt(dt);
        if (e != null) {
            enemies.add(e);
            enemyView.addEnemy(e);
        }
        //calculate enemy movement
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getHealth() == 0) {
                enemyView.removeEnemy(enemies.get(i));
                enemies.remove(i);

            } else {
                enemies.get(i).move(dt);
            }
        }
        //calculate tower targeting and shooting
        for (AbstractTower tower : towers) {
            tower.fire(dt);
        }
        handleInput();
        //TODO: #6: Implement a check to test if the wave is over
        //TODO: #7: Implement code to send information about the ended wave

        //Send result and enemies wave to competitor
        //TODO: #8: Implement waiting and buy phase
        //Start waiting and a buy phase
        //TODO: #9: In waiting and buy phase, check if competitors turn is over and allow a new round to start
        //TODO: #16: Fetch the results from competitors wave and enemies sent to device


    }

    @Override
    public void render(SpriteBatch sb) {
        mapView.draw(sb);
        enemyView.draw(sb);
        towerView.draw(sb);
    }

    @Override
    public void dispose() {
        mapView.dispose();
        enemyView.dispose();
        towerView.dispose();

    }

    @Override
    public int getViewportWidth() {
        return mapView.getMapWidth();
    }

    @Override
    public int getViewportHeight() {
        return mapView.getMapHeight();
    }
}
