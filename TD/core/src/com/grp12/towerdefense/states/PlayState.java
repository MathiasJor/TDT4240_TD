package com.grp12.towerdefense.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.grp12.towerdefense.MainGame;
import com.grp12.towerdefense.Network.ServerConnection;
import com.grp12.towerdefense.gamelogic.Map;
import com.grp12.towerdefense.gamelogic.Node;
import com.grp12.towerdefense.gamelogic.PlayerStats;
import com.grp12.towerdefense.gamelogic.enemies.AbstractEnemy;
import com.grp12.towerdefense.gamelogic.enemies.BasicEnemy;
import com.grp12.towerdefense.gamelogic.enemies.Wave;
import com.grp12.towerdefense.gamelogic.enemies.WaveGenerator;
import com.grp12.towerdefense.gamelogic.towers.AbstractTower;
import com.grp12.towerdefense.gamelogic.towers.BasicTower;
import com.grp12.towerdefense.views.EnemyView;
import com.grp12.towerdefense.views.GameMenuView;
import com.grp12.towerdefense.views.MapView;
import com.grp12.towerdefense.views.StartRoundButton;
import com.grp12.towerdefense.views.TowerView;
import com.grp12.towerdefense.views.View;

import java.util.ArrayList;

public class PlayState extends State {

    //models
    private Map map;
    private PlayerStats playerStats;
    //private Wave currentWave;
    private ArrayList<AbstractEnemy> enemies;
    private ArrayList<AbstractTower> towers;
    private WaveGenerator waveGenerator;
    private ArrayList<AbstractEnemy> listOfEnemyTypes;

    //Views
    private MapView mapView;
    private EnemyView enemyView;
    private GameMenuView gameMenuView;
    private StartRoundButton srb;
    private BitmapFont bmf;

    //state
    private boolean playing;
    private boolean nextRoundReady;

    ServerConnection serverConnection;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        //models
        map = new Map();
        playerStats = new PlayerStats(100, 100);
        enemies = new ArrayList<AbstractEnemy>();
        towers = new ArrayList<AbstractTower>();
        AbstractTower.setEnemyList(enemies);
        listOfEnemyTypes = new ArrayList<AbstractEnemy>();
        listOfEnemyTypes.add(new BasicEnemy(map.getWaypoints(), 1, 100));
        waveGenerator = new WaveGenerator(listOfEnemyTypes);

        //Views
        mapView = new MapView(map.getGrid());
        enemyView = new EnemyView();
        gameMenuView = new GameMenuView(new Vector2(0,0));
        srb = new StartRoundButton(mapView.getMapHeight(), mapView.getMapWidth());
        bmf = new BitmapFont();


        //Represents the three states of the game loop: Playing, waiting for next round, and next is ready
        nextRoundReady = true;
        playing = true;

        serverConnection = new ServerConnection();
        init();
    }

    @Override
    public void render(SpriteBatch sb) {
        mapView.draw(sb);
        if (playing) {
            enemyView.draw(sb);
        } else {
            gameMenuView.draw(sb);
            if (nextRoundReady) {
                srb.draw(sb);

            }
        }
        bmf.getData().setScale(6);
        bmf.draw(sb, Integer.toString(playerStats.getBalance()), 20, mapView.getMapHeight()-20 );

    }

    @Override
    public void update(float dt) {
        if (playing) {
            //Start: Spawn enemies in the start of the lane
            AbstractEnemy e = waveGenerator.getCurrentWave().popAttempt(dt);
            if (e != null) {
                enemies.add(e);
                enemyView.addEnemy(e);
            }
            //calculate enemy movement
            for (int i = 0; i < enemies.size(); i++) {
                if (enemies.get(i).getHealth() == 0) {
                    enemyView.removeEnemy(enemies.get(i));
                    playerStats.addMoney(enemies.get(i).getBounty());
                    enemies.remove(i);

                } else {
                    enemies.get(i).move(dt);
                    //Remove enemies that have completed the path
                    if (enemies.get(i).isFinished()) {
                        playerStats.removeHealth(1);
                        enemyView.removeEnemy(enemies.get(i));
                        enemies.remove(i);
                    }
                }
            }
            //calculate tower targeting and shooting
            for (AbstractTower tower : towers) {
                tower.fire(dt);
            }
        }
        if (enemies.size() == 0 && waveGenerator.getCurrentWave().empty()) {
            playing = false;
            serverConnection.sendResult();
        }



    }

    @Override
    protected void handleInput(Vector3 pointer) {

        if (nextRoundReady && !playing) {
            if (srb.clicked(pointer)) {
                //currentWave = serverConnection.result()
                //currentWave = new Wave(new BasicEnemy(map.getWaypoints(), 1, 100), 15);
                waveGenerator.setNextWave();
                playing = true;
            }
        }
        //get node informastion
        Node node = mapView.getNode(pointer);
        //if not nodepath build a tower there
        if(node.getType()== Node.NodeType.TOWERNODE){
            AbstractTower tower = new BasicTower();
            if (playerStats.getBalance() >= tower.getCost() && node.setTower(tower)) {
                tower.setNode(node);
                towers.add(tower);
                playerStats.withdrawMoney(tower.getCost());
                playerStats.getBalance();
            }
        }
    }

    //Set up the game
    private void init() {
        playerStats.addMoney(500);
        //currentWave = new Wave(new BasicEnemy(map.getWaypoints(), 1, 100), 15);
    }

    @Override
    public void dispose() {
        mapView.dispose();
        enemyView.dispose();
        gameMenuView.dispose();
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
