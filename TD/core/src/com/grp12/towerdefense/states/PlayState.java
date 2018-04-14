package com.grp12.towerdefense.states;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.grp12.towerdefense.gamelogic.Map;
import com.grp12.towerdefense.gamelogic.enemies.BasicEnemy;

public class PlayState extends State {

    private BasicEnemy e;
    private Map map;

    public PlayState(GameStateManager gsm) {
        super(gsm);

        map = new Map();
        e = new BasicEnemy(map.getWaypoints(), 10, 100);

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

    }

    @Override
    public void dispose() {

    }
}
