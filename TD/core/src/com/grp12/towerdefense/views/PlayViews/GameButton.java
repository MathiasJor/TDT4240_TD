package com.grp12.towerdefense.views.PlayViews;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.grp12.towerdefense.network.NetworkGame;

/**
 * Created by Kristian on 4/22/2018.
 */

public class GameButton extends View {
    private NetworkGame game;
    private int x, y, width, height;
    private BitmapFont bmf;
    private SpriteBatch sbp;
    private Texture button;

    private ShapeRenderer renderer;

    public GameButton(int x, int y, int width, int height, NetworkGame game) {
        this.game = game;
        button = new Texture("startButton.png");
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.renderer = new ShapeRenderer();
        this.sbp = new SpriteBatch();
        this.bmf = new BitmapFont();
    }


    @Override
    public void draw(SpriteBatch spriteBatch) {
        this.renderer.setProjectionMatrix(spriteBatch.getProjectionMatrix());
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.GRAY);
        renderer.rect(x, y, width, height);
        renderer.end();

        String turnString = "";
        if(game.isMyTurn()){
            turnString = "True";
        }else{
            turnString = "False";
        }
        sbp.setProjectionMatrix(spriteBatch.getProjectionMatrix());
        sbp.begin();
        bmf.getData().setScale(4);
        bmf.draw(sbp, String.format("Game nr: %d, Your turn: %s", game.getId(), turnString), x + 5, y + height);
        sbp.end();
    }
    public boolean clicked(Vector3 pointer) {

        if (pointer.x >= x && pointer.x <= y + width) {
            if (pointer.y >= y && pointer.y <= y + height) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void dispose() {

    }

    public NetworkGame getGame() {
        return game;
    }
}
