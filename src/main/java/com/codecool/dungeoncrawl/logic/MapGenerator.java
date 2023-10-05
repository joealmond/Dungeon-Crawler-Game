package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.actors.Skeleton;
import com.codecool.dungeoncrawl.data.items.Door;
import com.codecool.dungeoncrawl.data.items.Key;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.items.Weak_Wall;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class MapGenerator {
    private static final int MAP_WIDTH = 40;
    private static final int MAP_HEIGHT = 20;
    private static final int PLAYER_START_X = 1;
    private static final int PLAYER_START_Y = 1;

    private static final char WALL_CHAR = '#';
    private static final char FLOOR_CHAR = '.';
    private static final char PLAYER_CHAR = '@';
    private static final char DOOR_CHAR = 'd';
    private static final char KEY_CHAR = 'k';
    private static final char SKELETON_CHAR = 's';
    private static final char WEAK_WALL_CHAR = 'w';

    private static final double WALL_DENSITY = 0.7;

    public static GameMap generateRandomMap() {
        char[][] mapData = new char[MAP_HEIGHT][MAP_WIDTH];

        for (int y = 0; y < MAP_HEIGHT; y++) {
            for (int x = 0; x < MAP_WIDTH; x++) {
                mapData[y][x] = WEAK_WALL_CHAR;
            }
        }

        // Generate random floors
        Random random = new Random();
        for (int y = 1; y < MAP_HEIGHT - 1; y++) {
            for (int x = 1; x < MAP_WIDTH - 1; x++) {
                if (random.nextDouble() < WALL_DENSITY) {
                    mapData[y][x] = FLOOR_CHAR;
                }
            }
        }

        mapData[PLAYER_START_Y][PLAYER_START_X] = PLAYER_CHAR;
        placeRandomly(mapData, CellType.DOOR);
        placeRandomly(mapData, CellType.KEY);

        int numberOfSkeletons = random.nextInt(10) + 1;
        for (int i = 0; i < numberOfSkeletons; i++) {
            placeSkeletonRandomly(mapData);
        }

        GameMap map = new GameMap(MAP_WIDTH, MAP_HEIGHT, CellType.EMPTY);
        for (int y = 0; y < MAP_HEIGHT; y++) {
            for (int x = 0; x < MAP_WIDTH; x++) {
                Cell cell = map.getCell(x, y);
                switch (mapData[y][x]) {
                    case WALL_CHAR:
                        cell.setType(CellType.WALL);
                        break;
                    case FLOOR_CHAR:
                        cell.setType(CellType.FLOOR);
                        break;
                    case PLAYER_CHAR:
                        cell.setType(CellType.FLOOR);
                        map.setPlayer(new Player(cell));
                        break;
                    case DOOR_CHAR:
                        cell.setType(CellType.DOOR);
                        new Door(cell);
                        break;
                    case KEY_CHAR:
                        cell.setType(CellType.KEY);
                        new Key(cell);
                        break;
                    case SKELETON_CHAR:
                        cell.setType(CellType.FLOOR);
                        new Skeleton(cell);
                        break;
                    case WEAK_WALL_CHAR:
                        cell.setType(CellType.WEAK_WALL);
                        new Weak_Wall(cell);
                        break;
                    default:
                        throw new RuntimeException("Invalid character in map data: " + mapData[y][x]);
                }
            }
        }

        return map;
    }

    private static void placeRandomly(char[][] mapData, CellType cellType) {
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(MAP_WIDTH);
            y = random.nextInt(MAP_HEIGHT);
        } while (mapData[y][x] != FLOOR_CHAR && !isSurroundingValid(mapData,x,y));

        mapData[y][x] = (cellType == CellType.DOOR) ? 'd' : ((cellType == CellType.KEY) ? 'k' : 's');
    }
    private static void placeSkeletonRandomly(char[][] mapData) {
        Random random = new Random();
        int x, y;
        do {
            x = random.nextInt(MAP_WIDTH - 1);
            y = random.nextInt(MAP_HEIGHT - 1);
        } while (mapData[y][x] != FLOOR_CHAR);

        mapData[y][x] = 's';
    }
    private static boolean isSurroundingValid(char[][] mapData,int x, int y){
        List<List<Integer>> possibleCells = List.of(
                List.of(0,1),
                List.of(1,0),
                List.of(0,-1),
                List.of(-1,0)
        );

        AtomicBoolean isAtEdgeOfMap = new AtomicBoolean(false);

        List<List<Integer>> resultCoordinates = possibleCells
                .stream()
                .filter(coordinate -> {
                    if((x + coordinate.get(0)) >= (MAP_WIDTH - 1)|| (x + coordinate.get(0)) < 0) {
                        isAtEdgeOfMap.set(true);
                        return false;
                    }
                    if((y + coordinate.get(1)) >= (MAP_HEIGHT - 1) || (y + coordinate.get(1)) < 0) {
                        isAtEdgeOfMap.set(true);
                        return false;
                    }
                    char inspectedCell = mapData[y + coordinate.get(1)][x + coordinate.get(0)];
                    return inspectedCell == FLOOR_CHAR;
                })
                .collect(Collectors.toList());

        return !resultCoordinates.isEmpty() && !isAtEdgeOfMap.get();
    }
}
