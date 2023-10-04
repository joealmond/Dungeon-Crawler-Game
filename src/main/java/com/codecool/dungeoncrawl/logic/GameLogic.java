package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.inventory.Inventory;
import com.codecool.dungeoncrawl.data.items.Item;

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
        return Integer.toString(map.getPlayer().getHealth());
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
        map.getPlayer().addToInventory(item);
    }

    public void pickUpItemIfPossible() {
        map.getPlayer().pickUpItemIfPossible();
        map.getPlayer().checkIfHasKeyAtDoor();
    }
}
