package com.grp12.towerdefense.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.grp12.towerdefense.gamelogic.Map;
import com.grp12.towerdefense.gamelogic.enemies.BasicEnemy;
import com.grp12.towerdefense.views.EnemyView;
import com.grp12.towerdefense.views.MapView;
import com.grp12.towerdefense.views.TowerView;
import com.grp12.towerdefense.views.View;

public class PlayState extends State {

    private BasicEnemy e;
    private Map map;

    //Views
    private MapView mapView;
    private EnemyView enemyView;
    private TowerView towerView;

    private boolean paused;

    public PlayState(GameStateManager gsm) {
        super(gsm);

        map = new Map();
        e = new BasicEnemy(map.getWaypoints(), 1, 100);

        //Views
        mapView = new MapView(map.getGrid());
        View.setTileHeight(mapView.getTileHeight());
        View.setTileWidth(mapView.getTileWidth());
        enemyView = new EnemyView();
        enemyView.addEnemy(e);
        towerView = new TowerView();
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        e.move(dt);
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
