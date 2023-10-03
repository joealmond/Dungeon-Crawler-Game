package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.ui.UI;
import com.codecool.dungeoncrawl.ui.keyeventhandler.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Game extends Application {
    private UI ui;
    private GameLogic logic;
    private Set<KeyHandler> keyHandlers;
    private Duration movementDuration = Duration.seconds(1);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.keyHandlers = Set.of(new Up(), new Down(), new Left(), new Right());
        this.logic = new GameLogic();
        this.ui = new UI(logic, keyHandlers);

        ui.setUpPain(primaryStage);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();

        startMonsterMovement();

    }

    private void startMonsterMovement(){
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(moveMonsters);
        timeline.setCycleCount(Animation.INDEFINITE);

        timeline.playFromStart();
    }
    private KeyFrame moveMonsters = new KeyFrame(movementDuration,actionEvent -> {
        List<Actor> monsters = findMonsters();

        for(Actor monster : monsters){
            List<Integer> moveToCoordinates;

            if(isPlayerNearby(3,monster.getX(),monster.getY())){
                moveToCoordinates = getDirectionToPlayer(monster.getX(),monster.getY());
            } else {
                moveToCoordinates = findRandomMovementDirection(monster.getX(),monster.getY());
            }

            monster.move(moveToCoordinates.get(0),moveToCoordinates.get(1));
            ui.refresh();
        }
    });

    private List<Actor> findMonsters(){
        List<Actor> allMonsters = new ArrayList<>();
        for(int x = 0; x < logic.getMapWidth() -1 ; x++){
            for(int y = 0; y < logic.getMapHeight() - 1; y++){
                Optional<Actor> currentCell = Optional.ofNullable(logic.getCell(x, y).getActor());
                if(currentCell.isPresent() && !(currentCell.get() instanceof Player)){
                    allMonsters.add(currentCell.get());
                }
            }
        }
        return allMonsters;
    }

    private List<Integer> findRandomMovementDirection(int x, int y){
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
                    return isValidMoveOption(inspectedCell.getX(), inspectedCell.getY());
                })
                .collect(Collectors.toList());

        Collections.shuffle(resultCoordinates);
        return resultCoordinates.get(0) != null ? resultCoordinates.get(0) : List.of(0,0);
    }

    private boolean isPlayerNearby(int detectionRadius, int x, int y){
        boolean result = false;
        for(int dx = (x+detectionRadius) * -1; dx < (x + detectionRadius); dx++){
            for(int dy = (y+detectionRadius) * -1; dy < (y + detectionRadius);dy++){
                if(isValidMoveOption(x + dx,y + dy)){
                    if((logic.getCell(x,y).getNeighbor(dx,dy).getActor()) instanceof  Player){
                        System.out.println("Found");
                        result = true;
                    }
                }
            }
        }
        return result;
    }
    private List<Integer> getDirectionToPlayer(int x, int y){
        System.out.println("Player is in scope");
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
        if(isValidMoveOption(x+moveX,y+moveY)){
            System.out.println("Moving to player");
            return List.of(moveX,moveY);
        } else {
            return findRandomMovementDirection(x,y);
        }

    }
    private boolean isValidMoveOption(int x, int y){
        if(x < 0 || x >= logic.getMapWidth() - 1 ) return false;
        if(y < 0 || y >= logic.getMapHeight() - 1) return false;
        Cell inspectedCell = logic.getCell(x,y);
        boolean cellIsValidType = Objects.equals(inspectedCell.getTileName(), "floor") || Objects.equals(inspectedCell.getTileName(),"empty");
        return  cellIsValidType && inspectedCell.getActor() == null;
    }
}
