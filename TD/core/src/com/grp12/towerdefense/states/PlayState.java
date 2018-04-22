package com.grp12.towerdefense.states;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import com.grp12.towerdefense.controllers.EnemyController;
import com.grp12.towerdefense.controllers.TowerController;

import com.grp12.towerdefense.models.Map;
import com.grp12.towerdefense.models.Node;
import com.grp12.towerdefense.models.PlayerStats;
import com.grp12.towerdefense.models.enemies.AbstractEnemy;
import com.grp12.towerdefense.models.enemies.BasicEnemy;
import com.grp12.towerdefense.models.enemies.FastEnemy;
import com.grp12.towerdefense.models.enemies.WaveGenerator;
import com.grp12.towerdefense.models.towers.AbstractTower;
import com.grp12.towerdefense.models.towers.BasicTower;
import com.grp12.towerdefense.models.towers.RocketTower;
import com.grp12.towerdefense.models.towers.StunTower;

import com.grp12.towerdefense.views.PlayViews.EnemyView;
import com.grp12.towerdefense.views.PlayViews.GameMenuView;
import com.grp12.towerdefense.views.PlayViews.GameOverView;
import com.grp12.towerdefense.views.PlayViews.MapView;
import com.grp12.towerdefense.views.PlayViews.SendEnemyMenuView;
import com.grp12.towerdefense.views.PlayViews.StartRoundButton;

import com.grp12.towerdefense.network.NetworkCommunicator;
import com.grp12.towerdefense.network.NetworkTower;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PlayState extends State {

    //models
    private Map map;
    private PlayerStats playerStats;
    private ArrayList<AbstractEnemy> enemies;
    private ArrayList<AbstractTower> towers;
    private WaveGenerator waveGenerator;
    private ArrayList<AbstractEnemy> listOfEnemyTypes;
    private int enemiesToSend;

    //views
    private MapView mapView;
    private EnemyView enemyView;
    private GameMenuView gameMenuView;
    private StartRoundButton srb;
    private BitmapFont bmf;
    private GameOverView gameOverView;
    private SendEnemyMenuView sendEnemyMenuView;

    //controllers
    private TowerController towerController;
    private EnemyController enemyController;

    //state
    private boolean playing;
    private boolean gameover;

    //variables
    private float gameoverTime = 3;
    private float gameoverTimer = 0;
    int buildThisTower =0;
    final boolean[] ready = {false};

    NetworkCommunicator networkCommunicator;


    public PlayState(GameStateManager gsm) {
        super(gsm);

        //models
        map = new Map();
        playerStats = new PlayerStats(NetworkCommunicator.getActiveGame().getPhoneUser().getGold(), NetworkCommunicator.getActiveGame().getPhoneUser().getHealth());
        enemies = new ArrayList<AbstractEnemy>();
        towers = new ArrayList<AbstractTower>();
        listOfEnemyTypes = new ArrayList<AbstractEnemy>();
        listOfEnemyTypes.add(new BasicEnemy(map.getWaypoints(), 1, 100));
        listOfEnemyTypes.add(new FastEnemy(map.getWaypoints(), 1, 100));
        waveGenerator = new WaveGenerator(listOfEnemyTypes);
        enemiesToSend = 0;

        //views
        mapView = new MapView(map.getGrid());
        enemyView = new EnemyView(enemies);
        gameMenuView = new GameMenuView(new Vector2(0,0));
        srb = new StartRoundButton(mapView.getMapHeight(), mapView.getMapWidth());
        bmf = new BitmapFont();
        sendEnemyMenuView = new SendEnemyMenuView();

        //controllers
        towerController = new TowerController(towers, enemies);
        enemyController = new EnemyController(playerStats, enemies);


        //Represents the three states of the game loop: Playing, waiting for next round, and game over
        playing = false;
        gameover = false;

        ArrayList<NetworkTower> networkTowers = NetworkCommunicator.getActiveGame().getPhoneUser().getTowers();

        for(NetworkTower t : networkTowers){
            AbstractTower tower;
            switch(t.getType()){
                case 0:
                    tower = new BasicTower();
                    map.getGrid()[(int)t.getY()][(int)t.getX()].setTower(tower);
                    tower.setNode(map.getGrid()[(int)t.getY()][(int)t.getX()]);
                    for(int i = 0; i < t.getLevel(); i++){
                        tower.upgradeTower();
                    }
                    towers.add(tower);
                    break;
                case 1:
                    tower = new StunTower();
                    map.getGrid()[(int)t.getY()][(int)t.getX()].setTower(tower);
                    tower.setNode(map.getGrid()[(int)t.getY()][(int)t.getX()]);
                    for(int i = 0; i < t.getLevel(); i++){
                        tower.upgradeTower();
                    }
                    towers.add(tower);
                    break;
                case 2:
                    tower = new RocketTower();
                    map.getGrid()[(int)t.getY()][(int)t.getX()].setTower(tower);
                    tower.setNode(map.getGrid()[(int)t.getY()][(int)t.getX()]);
                    for(int i = 0; i < t.getLevel(); i++){
                        tower.upgradeTower();
                    }
                    towers.add(tower);
                default:
                    continue;
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        mapView.draw(sb);
        if (!gameover) {
            if (playing) {
                enemyView.draw(sb);
            } else {
                gameMenuView.draw(sb);
                sendEnemyMenuView.draw(sb);
                if (NetworkCommunicator.getActiveGame().isMyTurn() && !gameover) {
                    srb.draw(sb);
                }
            }
            bmf.getData().setScale(6);
            String info = "HP: " + playerStats.getHealth() + "    $" + playerStats.getBalance() + "    Sending: " + enemiesToSend;
            bmf.draw(sb, info, 20, mapView.getMapHeight() - 20);
        } else {
            gameOverView.draw(sb);
        }

    }

    @Override
    public void update(float dt) {
        if (playing) {
            //Start: Spawn enemies in the start of the lane
            AbstractEnemy e = waveGenerator.getCurrentWave().popAttempt(dt);
            if (e != null) {
                enemies.add(e);
            }
            //Update enemy controller
            enemyController.update(dt);

            //calculate tower targeting and shooting
            towerController.update(dt);
        }
        //ends the round when all the enemies are gone
        if (playing && enemies.size() == 0 && waveGenerator.getCurrentWave().empty()) {
            playing = false;
            //Here comes the 'getti code
            int increaseInWave = 0;
            if(NetworkCommunicator.getActiveGame().isSecondPlayer())
                increaseInWave = 1;

            NetworkCommunicator.getActiveGame().setTurn(false);

            ArrayList<NetworkTower> networkTowers = new ArrayList<NetworkTower>();

            for (AbstractTower t:towers) {
                int type = 0;
                switch(t.getType()){
                    case Basic:
                        type = 0;
                        break;
                    case Stunner:
                        type = 1;
                        break;
                    case Rocket:
                        System.out.println(t.getType());
                        type = 2;
                        break;
                }

                networkTowers.add(new NetworkTower(type, t.getTowerLevel(), t.getNode().getX(), t.getNode().getY()));
            }

            NetworkCommunicator.sendEndTurnMessage(NetworkCommunicator.getActiveGame().getId(), playerStats, enemiesToSend, waveGenerator.getCurrentWaveNumber() + increaseInWave, networkTowers);

            enemiesToSend = 0;
            if (playerStats.getHealth() < 1) {
                gameOverView = new GameOverView(false);
                gameover = true;

            }
            if(!gameover){
                gsm.pop();
            }
        }
        if (gameover) {
            gameoverTimer += dt;
            if (gameoverTimer > gameoverTime) {
                gsm.pop();
            }
        }
    }

    @Override
    protected void handleInput(Vector3 pointer) {
        if (!playing && NetworkCommunicator.getActiveGame().isMyTurn()) {
            if (srb.clicked(pointer)) {
                int opponentHealth = NetworkCommunicator.getActiveGame().getOpponent().getHealth();
                if (opponentHealth < 1) {
                    gameOverView = new GameOverView(true);
                    gameover = true;
                } else {
                  waveGenerator.setReceivedEnemies(NetworkCommunicator.getActiveGame().getSentCreatures());
                  waveGenerator.setCurrentWaveNumber(NetworkCommunicator.getActiveGame().getWaveNumber());
                  waveGenerator.setNextWave();
                  playing = true;
                }
            }
            else{
                if (sendEnemyMenuView.clicked(pointer) && playerStats.getBalance() >= (listOfEnemyTypes.get(waveGenerator.getCurrentEnemyIndex()).getBounty() * 2)) {
                    playerStats.withdrawMoney(listOfEnemyTypes.get(waveGenerator.getCurrentEnemyIndex()).getBounty() * 2);
                    enemiesToSend += 1;
                }

                Node node = mapView.getNode(pointer);
                //open tower selection

                gameMenuView.isClickedSelectTower(pointer);

                if(gameMenuView.getShowElements()){
                    buildThisTower =gameMenuView.towerSelected(pointer, mapView.getMapHeight(), mapView.getMapWidth());
                    delayThis();
                    ready[0]=false;
                }
                if(gameMenuView.getShowOneTower()){
                    if(node.getTower()==null){
                        buildTowers(pointer,node);
                    }
                }
                else if(!gameMenuView.getShowOneTower()&&!gameMenuView.getShowElements()){
                        //sellTower(node)
                    if(node.getTower()!=null){
                        gameMenuView.sellAndUpgrade(pointer,node);
                    }
                    else{
                        if(gameMenuView.getSellAndUpgrade()){
                            Node pickedTower = gameMenuView.pickSellOrUpgrade(pointer);
                            if(pickedTower.getTower().getChoice()=='S'){
                                sellTower(pickedTower);
                            }
                            else if(pickedTower.getTower().getChoice()=='U'){
                                upgradeTower(pickedTower);
                            }
                        }
                    }
                }
            }
        }
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

    public AbstractTower newTower(int i){
        AbstractTower tower = null;
        switch (i){
            case 0: tower= new StunTower();
                    break;
            case 1: tower = new RocketTower();
                    break;
            case 2: tower = new BasicTower();
                    break;
        }
        return tower;
    }

    public void delayThis(){
        long delay = 2000;
        long period = 2000;
        Timer task = new Timer();
        task.schedule(new TimerTask() {
            @Override
            public void run() {
                ready[0] =true;
            }
        }, delay);


    }

    public void buildTowers(Vector3 pointer, Node node){
        if(gameMenuView.getShowOneTower()){
            delayThis();
            if(gameMenuView.finishedWithSelectedTower(pointer)){
                ready[0]=false;
            }
            //get node informastion
            if(ready[0]){
                //if not nodepath build a tower there
                if(node.getType()== Node.NodeType.TOWERNODE){
                    AbstractTower tower = newTower(buildThisTower);
                    if (playerStats.getBalance() >= tower.getCost() && node.setTower(tower)) {
                        tower.setNode(node);
                        towers.add(tower);
                        playerStats.withdrawMoney(tower.getCost());
                        playerStats.getBalance();
                        gameMenuView.setShowOneTower(false);

                    }
                }
            }
        }
    }

    public void sellTower(Node node){
        for(int i=0; i<towers.size();i++){
            if(node.getTower().getPosition()==towers.get(i).getPosition()){
                playerStats.addMoney(towers.get(i).getSellPrice());
               towers.remove(i);
               node.removeTower();
            }

        }
    }

    public void upgradeTower(Node pickedTower){
        if(playerStats.getBalance()>pickedTower.getTower().getUpgradeCost()){
            towerController.upgradeTower(pickedTower.getTower());
            gameMenuView.setShowSellAndUpgrade(false);
            playerStats.withdrawMoney(pickedTower.getTower().getUpgradeCost());
        }

    }
}
