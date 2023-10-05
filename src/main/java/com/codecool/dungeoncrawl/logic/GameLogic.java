package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.inventory.Inventory;
import com.codecool.dungeoncrawl.data.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameLogic {
    private GameMap map;

    public GameLogic() {
        this.map = MapLoader.loadMap();
    }

    public double getMapWidth() {
        return map.getWidth();
    }

    public double getMapHeight() {
        return map.getHeight();
    }

    public void setup() {
    }

    public Cell getCell(int x, int y) {
        return map.getCell(x, y);
    }

    public String getPlayerHealth() {
        return Integer.toString(map.getPlayer().getCurrentHealth());
    }

    public String getPlayerWeapon() {
        return map.getPlayer().getActiveWeapon();
    }

    public GameMap getMap() {
        return map;
    }

    public int getPlayerY() {
        return map.getPlayer().getY();
    }

    public int getPlayerX() {
        return map.getPlayer().getX();
    }

    public Inventory getPlayerInventory() {
        return map.getPlayer().getInventory();
    }

    public void addToPlayerInventory(Item item) {
        map.getPlayer().addItemToInventory(item);
    }

    public Player getPlayer() {
        return map.getPlayer();
    }

    public List<Actor> getActors() {
        List<Actor> allActors = new ArrayList<>();
        for(int x = 0; x < getMapWidth() -1 ; x++){
            for(int y = 0; y < getMapHeight() - 1; y++){
                Optional<Actor> currentCell = Optional.ofNullable(getCell(x, y).getActor());
                currentCell.ifPresent(allActors::add);
            }
        }

        return allActors;
    }

    public List<Item> getItems() {
        List<Item> allItems = new ArrayList<>();
        for(int x = 0; x < getMapWidth() -1 ; x++){
            for(int y = 0; y < getMapHeight() - 1; y++){
                if(getCell(x,y).hasItem()){
                    allItems.add(getCell(x,y).getItem());
                }
            }
        }

        return allItems;
    }

    public void generateNewMap(){
        this.map = MapGenerator.generateRandomMap();
    }
}
