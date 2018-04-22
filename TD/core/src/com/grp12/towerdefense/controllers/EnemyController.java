package com.grp12.towerdefense.controllers;

import com.badlogic.gdx.math.Vector2;
import com.grp12.towerdefense.models.enemies.AbstractEnemy;

import java.util.ArrayList;

public class EnemyController extends Controller {

    private ArrayList<AbstractEnemy> listOfEnemies;

    public EnemyController() {
        listOfEnemies = new ArrayList<AbstractEnemy>();

    }

    @Override
    public void update(float dt) {
        for(AbstractEnemy enemy : listOfEnemies) {
            move(dt, enemy);
        }
    }

    public ArrayList<AbstractEnemy> getListOfEnemies() {
        return listOfEnemies;
    }

    public void addEnemy(AbstractEnemy enemy) {
        listOfEnemies.add(enemy);
    }

    public void removeEnemy(AbstractEnemy enemy) {
        listOfEnemies.remove(enemy);
    }

    public AbstractEnemy createEnemy() {
        return null;
    }

    public void move(float dt, AbstractEnemy enemy) {
        if (enemy.getPosition().dst(enemy.getCurrentWaypoint().getPosition()) <= 0.1f) {
            findNextWaypoint(enemy);
        }

        //Calculate the direction and normalize vector
        Vector2 direction = new Vector2();
        Vector2 newPostion = enemy.getPosition();

        direction.setZero();
        direction.x = enemy.getCurrentWaypoint().getX() - enemy.getPosition().x;
        direction.y = enemy.getCurrentWaypoint().getY() - enemy.getPosition().y;
        direction = direction.nor();

        //Add direction * speed * dt to current position
        newPostion.x += (direction.x * enemy.getSpeed() * dt);
        newPostion.y += (direction.y * enemy.getSpeed() * dt);

        enemy.setPosition(newPostion);
    }

    public void takeDamage (AbstractEnemy enemy, int damage){
        enemy.setHealth(enemy.getHealth() - damage);
        if (enemy.getHealth() < 0)
            enemy.setHealth(0);
    }


    public void findNextWaypoint(AbstractEnemy enemy) {
        if (enemy.getWaypointIndex() < enemy.getWaypoints().size()) {
            enemy.setNextWaypoint(enemy.getWaypoints().get(enemy.getWaypointIndex()));
            enemy.seteRotation(findEDegree(enemy));
        } else {
            enemy.setFinished(true);
        }
    }


    public float findEDegree(AbstractEnemy enemy){
        Vector2 targetCoords = enemy.getCurrentWaypoint().getPosition();
        float degree = (float) Math.atan((targetCoords.x - enemy.getPosition().x)/(targetCoords.y-enemy.getPosition().y));
        degree= (float) Math.toDegrees(degree);
        //System.out.print("enemy= "+ position.x +", "+ position.y +" waypoint= "+ targetCoords.x+ ", "+targetCoords.y+ "deg= "+degree+"+\n");

        if(enemy.getPosition().x==targetCoords.x){
            if(targetCoords.y<enemy.getPosition().y){
                degree = 180;
            }
            else{
                degree=0;
            }
        }
        else if(enemy.getPosition().y==targetCoords.y){
            if(targetCoords.x> enemy.getPosition().x){
                degree = 90;
            }
            else{
                degree=-90;
            }
        }
        else{
            if(targetCoords.x>enemy.getPosition().x){
                if(targetCoords.y<enemy.getPosition().y){
                    degree=degree+180;
                }
            }
            else{
                if(!(targetCoords.y>enemy.getPosition().y)){
                    degree=degree+180;
                }
            }
        }
        return degree;
    }

}
