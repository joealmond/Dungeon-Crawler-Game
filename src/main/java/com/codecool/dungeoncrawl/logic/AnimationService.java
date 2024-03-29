package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.gamesetup.Constants;
import com.codecool.dungeoncrawl.ui.UI;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class AnimationService {
    private UI ui;
    private GameLogic logic;
    private Duration movementDuration = Duration.seconds(1);
    private KeyFrame moveMonsters = new KeyFrame(movementDuration, actionEvent -> moveMonstersActionEvent());

    public AnimationService(UI ui, GameLogic logic) {
        this.ui = ui;
        this.logic = logic;
    }

    public void startMonsterMovement(){
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(moveMonsters);
        timeline.setCycleCount(Animation.INDEFINITE);

        timeline.playFromStart();
    }

    public void playSlashAnimation(Cell cell){
        CellType originalCellType = cell.getType();

        Timeline slashAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0), event -> {
                    cell.setType(CellType.SLASH);
                    ui.refresh();
                }),
                new KeyFrame(Duration.seconds(0.1), event -> {
                    cell.setType(originalCellType);
                    ui.refresh();
                })
        );
        slashAnimation.setCycleCount(1);
        slashAnimation.play();
    }

    public void playDeathAnimation(Cell cell){
        Timeline deathAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0.2), event -> {
                    cell.setType(CellType.CORPSE);
                    ui.refresh();
                }),
                new KeyFrame(Duration.seconds(2), event -> {
                    cell.setType(CellType.GRAVE);
                    ui.refresh();
                })
        );
        deathAnimation.setCycleCount(1);
        deathAnimation.play();
    }

    public void setWallToCrumbling(Cell cell){
        Timeline wallAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0.2), event -> {
                    cell.setType(CellType.CRUMBLING_WALL);
                    ui.refresh();
                })
        );

        wallAnimation.setCycleCount(1);
        wallAnimation.play();
    }

    public void resetTileToFloor(Cell cell) {
        Timeline resetAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0.2), event -> {
                    cell.setType(CellType.FLOOR);
                    ui.refresh();
                })
        );

        resetAnimation.setCycleCount(1);
        resetAnimation.play();
    }

    public void playActorGetHurtAnimation(Cell cell) {
        Actor actorOnCell = cell.getActor();

        Timeline hurtAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0), event -> {
                    cell.setActor(null);
                    cell.setType(CellType.HURT_ACTOR);
                    ui.refresh();
                }),
                new KeyFrame(Duration.seconds(0.1), event -> {

                    if(actorOnCell.getCurrentHealth() > 0){
                        cell.setType(CellType.FLOOR);
                        actorOnCell.getCell().setActor(actorOnCell);

                        ui.refresh();
                    } else {
                        playDeathAnimation(cell);
                    }
                })
        );

        hurtAnimation.setCycleCount(1);
        hurtAnimation.play();
    }
    public void playActorHealAnimation(Cell cell) {
        Actor actorOnCell = cell.getActor();

        Timeline healAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0), event -> {
                    cell.setActor(null);
                    cell.setType(CellType.HEAL_ACTOR);
                    ui.refresh();
                }),
                new KeyFrame(Duration.seconds(0.1), event -> {

                    if(actorOnCell.getCurrentHealth() > 0){
                        cell.setType(CellType.FLOOR);
                        actorOnCell.getCell().setActor(actorOnCell);

                        ui.refresh();
                    } else {
                        playDeathAnimation(cell);
                    }
                })
        );

        healAnimation.setCycleCount(1);
        healAnimation.play();
    }

    private void moveMonstersActionEvent() {
        List<Actor> monsters = findMonsters();

        for (Actor monster : monsters) {
            int DAMAGE = monster.getDamage();
            List<Integer> moveToCoordinates;

            if (isPlayerNearby(Constants.NPC_DETECTION_RADIUS, monster.getX(), monster.getY())) {
                moveToCoordinates = getDirectionToPlayer(monster.getX(), monster.getY());
            } else {
                moveToCoordinates = findRandomMovementDirection(monster.getX(), monster.getY());
            }

            attackPlayerIfClose(monster.getX(),monster.getY(), DAMAGE);
            monster.move(moveToCoordinates.get(0), moveToCoordinates.get(1));
            ui.refresh();
        }
    }

    private List<Actor> findMonsters() {
        List<Actor> allMonsters = new ArrayList<>();

        for(int x = 0; x < logic.getMapWidth() - 1 ; x++) {
            for(int y = 0; y < logic.getMapHeight() - 1; y++) {
                Optional<Actor> currentCell = Optional.ofNullable(logic.getCell(x, y).getActor());

                if(currentCell.isPresent() && !(currentCell.get() instanceof Player)) {
                    allMonsters.add(currentCell.get());
                }
            }
        }
        return allMonsters;
    }

    private List<Integer> findRandomMovementDirection(int x, int y) {
        List<List<Integer>> possibleCells = List.of(
                List.of(0,0),
                List.of(0,1),
                List.of(1,0),
                List.of(1,1),
                List.of(0,-1),
                List.of(-1,0),
                List.of(-1,-1)
        );

        List<List<Integer>> resultCoordinates = possibleCells
                .stream()
                .filter(coordinate -> {
                    Cell inspectedCell = logic.getCell(x, y).getNeighbor(coordinate.get(0), coordinate.get(1));
                    return isMoveOption(inspectedCell.getX(), inspectedCell.getY());
                })
                .collect(Collectors.toList());

        Collections.shuffle(resultCoordinates);
        return resultCoordinates.get(0) != null ? resultCoordinates.get(0) : List.of(0,0);
    }

    private boolean isPlayerNearby(int detectionRadius, int x, int y) {
        boolean result = false;

        for(int dx = (x+detectionRadius) * -1; dx < (x + detectionRadius); dx++){
            for(int dy = (y+detectionRadius) * -1; dy < (y + detectionRadius);dy++){
                if(isMoveOption(x + dx,y + dy)){
                    if((logic.getCell(x,y).getNeighbor(dx,dy).getActor()) instanceof  Player){
                        result = true;
                    }
                }
            }
        }

        return result;
    }

    private void attackPlayerIfClose(int x, int y, int DAMAGE) {
        int detectionRadius = 1;
        for (int dx = -detectionRadius; dx <= detectionRadius; dx++) {
            for (int dy = -detectionRadius; dy <= detectionRadius; dy++) {
                if (isValidCell(x + dx, y + dy)) {
                    if (logic.getCell(x + dx, y + dy).getActor() instanceof Player) {
                        Player player = (Player) logic.getCell(x + dx, y + dy).getActor();
                        int currentHealth = player.getCurrentHealth();

                        player.setCurrentHealth(currentHealth - DAMAGE);
                    }
                }
            }
        }
    }

    private List<Integer> getDirectionToPlayer(int x, int y){
        Player player = logic.getMap().getPlayer();
        int playerX = player.getX();
        int playerY = player.getY();
        int moveX = 0;
        int moveY = 0;

        if(playerX > x){
            moveX = 1;
        } else if(playerX < x){
            moveX = -1;
        }

        if(playerY > y){
            moveY = 1;
        } else if(playerY < y){
            moveY = -1;
        }
        if(isMoveOption(x+moveX,y+moveY)){
            return List.of(moveX,moveY);
        } else {
            return findRandomMovementDirection(x,y);
        }
    }

    private boolean isMoveOption(int x, int y) {
        if(x < 0 || x >= logic.getMapWidth() - 1 ) return false;
        if(y < 0 || y >= logic.getMapHeight() - 1) return false;

        Cell inspectedCell = logic.getCell(x,y);
        return Objects.equals(inspectedCell.getTileName(), "floor") || Objects.equals(inspectedCell.getTileName(),"empty");
    }
  
    private boolean isValidCell(int x, int y) {
        if(x < 0 || x >= logic.getMapWidth() - 1 ) return false;
        return y >= 0 && !(y >= logic.getMapHeight() - 1);
    }
}
