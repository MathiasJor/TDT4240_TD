package com.grp12.towerdefense.controllers;

import com.badlogic.gdx.math.Vector2;
import com.grp12.towerdefense.models.enemies.AbstractEnemy;
import com.grp12.towerdefense.models.towers.AbstractTower;

import java.util.ArrayList;

public class TowerController extends Controller {

    @Override
    public void update(float dt) {

    }

    public void fire(AbstractTower tower, float dt) {
        tower.addFrameTime(dt);
        tower.setRotation(findDegree(tower, tower.getTarget()));
        if (tower.getFrameTime() > tower.getReloadTime())
            tower.setCanShoot(true);
        targetUpdate(tower, AbstractTower.getEnemies());
        if (tower.getTarget() != null && tower.getCanShoot()) {
            tower.getTarget().takeDamage(tower.getDamage());
            tower.resetFrameTime();
            tower.setCanShoot(false);
        }
    }

    private AbstractEnemy findNextEnemy(AbstractTower tower, ArrayList<AbstractEnemy> listOfEnemies){
        float shortest = Float.MAX_VALUE;
        float tempDistance;
        AbstractEnemy returnEnemy = null;
        for (AbstractEnemy enemy : listOfEnemies){
            if (enemy.getHealth() > 0) {
                tempDistance = tower.getNode().getPosition().dst2(enemy.getPosition());
            } else {
                tempDistance = shortest + 1;
            }
            if(tempDistance < shortest){
                shortest = tempDistance;
                returnEnemy = enemy;
            }
        }
        return (shortest < tower.getRange()) ? returnEnemy : null;
    }

    private float findDegree(AbstractTower tower, AbstractEnemy target){
        if (target == null) {
            return tower.getRotation();
        }
        Vector2 targetCoords = target.getPosition();
        float degree = (float) Math.atan((targetCoords.x-tower.getNode().getPosition().x)/(targetCoords.y-tower.getNode().getPosition().y));
        degree= (float) Math.toDegrees(degree);

        if(tower.getNode().getPosition().x==targetCoords.x){
            if(targetCoords.y<tower.getNode().getPosition().y){
                degree = 90;
            }
            else{
                degree=270;
            }
        }
        else if(tower.getNode().getPosition().y==targetCoords.y){
            if(targetCoords.x> tower.getNode().getPosition().x){
                degree = 0;
            }
            else{
                degree=180;
            }
        }
        else{
            if(targetCoords.x>tower.getNode().getPosition().x){
                if(targetCoords.y<tower.getNode().getPosition().y){
                    degree=90+degree;
                }
                else{
                    degree = +degree-90; //done
                }
            }
            else{
                if(targetCoords.y>tower.getNode().getPosition().y){
                    degree=270+degree; //done
                }
                else{
                    degree=degree+90;
                }
            }
        }
        return degree;
    }

    private void upgradeTower(AbstractTower tower){
        tower.setDamage(tower.getDamage()*2);
        tower.setRange(tower.getRange()+2);
        tower.setReloadTime(tower.getReloadTime()/2);
        tower.setUpgradeCost();
        tower.setValue(tower.getUpgradeCost());
        tower.setUpgradeCost();
        tower.setSellPrice();
        tower.upgradeTower();
    }

    private boolean enemyOutOfRange(AbstractTower tower, AbstractEnemy abstractEnemy) {
        return tower.getNode().getPosition().dst2(abstractEnemy.getPosition()) > tower.getRange();
    }

    private void targetUpdate(AbstractTower tower, ArrayList<AbstractEnemy> enemies) {
        if (tower.getTarget() == null || tower.getTarget().getHealth() == 0 || enemyOutOfRange(tower, tower.getTarget())) {
            tower.setTarget(findNextEnemy(tower, enemies));
            if(tower.getTarget() != null){
                tower.setRotation(findDegree(tower, tower.getTarget()));
            }
        }
    }
}
