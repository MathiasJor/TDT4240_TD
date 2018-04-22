package com.grp12.towerdefense.views.PlayViews;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.grp12.towerdefense.Network.NetworkCommunicator;
import com.grp12.towerdefense.Network.NetworkGame;

/**
 * Created by Kristian on 4/22/2018.
 */

public class GameButton extends View {
    private NetworkGame game;
    private int x, y, width, height;
    private BitmapFont bmf;
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
        this.bmf = new BitmapFont();
    }


    @Override
    public void draw(SpriteBatch spriteBatch) {
        this.renderer.setProjectionMatrix(spriteBatch.getProjectionMatrix());
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.GRAY);
        renderer.rect(x, y, width, height);
        renderer.end();
        spriteBatch.end();
        spriteBatch.begin();

        String turnString = "";
        if(game.isMyTurn()){
            turnString = "True";
        }else{
            turnString = "False";
        }
        bmf.getData().setScale(4);
        bmf.draw(spriteBatch, String.format("Game nr: %d, Your turn: %s", game.getId(), turnString), x + 5, y + height);
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
