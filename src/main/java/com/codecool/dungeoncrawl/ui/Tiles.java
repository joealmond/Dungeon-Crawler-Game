package com.codecool.dungeoncrawl.ui;

import com.codecool.dungeoncrawl.data.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("sword", new Tile(2,28));
        tileMap.put("fire", new Tile(15,11));
        tileMap.put("campfire", new Tile(14,10));
        tileMap.put("door", new Tile(9,11));
        tileMap.put("opened-door", new Tile(8,10));
        tileMap.put("key", new Tile(16,23));
        tileMap.put("boss", new Tile(30,6));
        tileMap.put("torch", new Tile(10,25));
        tileMap.put("slash", new Tile(24,11));
        tileMap.put("health-potion", new Tile(16,30));
        tileMap.put("bread", new Tile(15,27));
        tileMap.put("corpse", new Tile(0,15));
        tileMap.put("grave", new Tile(1,14));
        tileMap.put("hurt-actor",new Tile(13,12));
        tileMap.put("weak-wall",new Tile(0,13));
        tileMap.put("crumbling-wall",new Tile(16,12));
        tileMap.put("heal-actor",new Tile(28,21));
        tileMap.put("farmer", new Tile(31, 2));
        tileMap.put("chicken", new Tile(26, 7));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
