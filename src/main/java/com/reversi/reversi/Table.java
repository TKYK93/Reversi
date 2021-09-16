package com.reversi.reversi;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class Table {
    public static final int STONE_RADIUS = 20;
    public static Player player1; // stone's color is white
    public static Player player2; // stone's color is black
    public static Tile[][] tiles;

    public Table(){
        player1 = new Player();
        player1.setMyTurn(true);
        player2 = new Player();
        player2.setMyTurn(false);
        tiles = new Tile[8][8];
    }

    public Parent createContent(){
        Pane root = new Pane();
        root.setPrefSize(560, 560);
        root.setStyle(""
                + "-fx-background-color:green; "
                + "-fx-border-style: dashed solid solid solid;");
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Tile tile = new Tile();
                tile.setTranslateX(j * 70);
                tile.setTranslateY(i * 70);
                root.getChildren().add(tile);

                if((i == 3 && j == 3) || (i == 4 && j == 4)){
                    Circle stone = new Circle(STONE_RADIUS, Color.WHITE);
                    player1.addStone(stone);
                    tile.setStone(stone);
                    tile.getChildren().add(stone);
                    tile.setEmpty(false);
                }

                if((i == 3 && j == 4) || (i == 4 && j == 3)
                ){
                    Circle stone = new Circle(STONE_RADIUS, Color.BLACK);
                    player2.addStone(stone);
                    tile.setStone(stone);
                    tile.getChildren().add(stone);
                    tile.setEmpty(false);
                }
                tile.setY(i);
                tile.setX(j);
                tiles[i][j] = tile;
            }
        }
        return root;
    }

    public static Player getPlayer1() {
        return player1;
    }

    public static void setPlayer1(Player player1) {
        Table.player1 = player1;
    }

    public static Player getPlayer2() {
        return player2;
    }

    public static void setPlayer2(Player player2) {
        Table.player2 = player2;
    }

    public static Tile[][] getTiles() {
        return tiles;
    }

    public static void setTiles(Tile[][] tiles) {
        Table.tiles = tiles;
    }

    //TODO: Handle errors against the stones in the corners

    public static List checkBottom(Tile tile){
        List<Tile> tempTiles = new ArrayList<>();
        int x = tile.getX();
        int y = tile.getY();
        for(int i = y+1; i < 8; i++){
            Tile currentTile = tiles[i][x];
            if(currentTile.getStone() == null){
                tempTiles.clear();
                return tempTiles;
            }
            if (tile.getStone().getFill() == currentTile.getStone().getFill()){
                return tempTiles;
            }
            tempTiles.add(currentTile);
        }
        return tempTiles;
    }

    public static List checkBottomRight(Tile tile){
        List<Tile> tempTiles = new ArrayList<>();
        int x = tile.getX();
        int y = tile.getY();
        for(int i = y+1; i < 8; i++){
            x++;
            Tile currentTile = tiles[i][x];
            if(currentTile.getStone() == null){
                tempTiles.clear();
                return tempTiles;
            }
            if (tile.getStone().getFill() == currentTile.getStone().getFill()){
                return tempTiles;
            }
            tempTiles.add(currentTile);
        }
        return tempTiles;
    }

    public static List checkRight(Tile tile){
        List<Tile> tempTiles = new ArrayList<>();
        int x = tile.getX();
        int y = tile.getY();
        for(int i = x+1; i < 8; i++){
            Tile currentTile = tiles[y][i];
            if(currentTile.getStone() == null){
                tempTiles.clear();
                return tempTiles;
            }
            if (tile.getStone().getFill() == currentTile.getStone().getFill()){
                return tempTiles;
            }
            tempTiles.add(currentTile);
        }
        return tempTiles;
    }

    public static List checkUpRight(Tile tile){
        List<Tile> tempTiles = new ArrayList<>();
        int x = tile.getX();
        int y = tile.getY();
        for(int i = y-1; i >= 0; i--){
            x++;
            Tile currentTile = tiles[i][x];
            if(currentTile.getStone() == null){
                tempTiles.clear();
                return tempTiles;
            }
            if (tile.getStone().getFill() == currentTile.getStone().getFill()){
                return tempTiles;
            }
            tempTiles.add(currentTile);
        }
        return tempTiles;
    }

    public static List checkUp(Tile tile){
        List<Tile> tempTiles = new ArrayList<>();
        int x = tile.getX();
        int y = tile.getY();
        for(int i = y-1; i >= 0; i--){
            Tile currentTile = tiles[i][x];
            if(currentTile.getStone() == null){
                tempTiles.clear();
                return tempTiles;
            }
            if (tile.getStone().getFill() == currentTile.getStone().getFill()){
                return tempTiles;
            }
            tempTiles.add(currentTile);
        }
        return tempTiles;
    }

    public static List checkUpLeft(Tile tile){
        List<Tile> tempTiles = new ArrayList<>();
        int x = tile.getX();
        int y = tile.getY();
        for(int i = y-1; i >= 0; i--){
            x--;
            Tile currentTile = tiles[i][x];
            if(currentTile.getStone() == null){
                tempTiles.clear();
                return tempTiles;
            }
            if (tile.getStone().getFill() == currentTile.getStone().getFill()){
                return tempTiles;
            }
            tempTiles.add(currentTile);
        }
        return tempTiles;
    }

    public static List checkLeft(Tile tile){
        List<Tile> tempTiles = new ArrayList<>();
        int x = tile.getX();
        int y = tile.getY();
        for(int i = x-1; i >= 0; i--){
            Tile currentTile = tiles[y][i];
            if(currentTile.getStone() == null){
                tempTiles.clear();
                return tempTiles;
            }
            if (tile.getStone().getFill() == currentTile.getStone().getFill()){
                return tempTiles;
            }
            tempTiles.add(currentTile);
        }
        return tempTiles;
    }

    public static List checkBottomLeft(Tile tile){
        List<Tile> tempTiles = new ArrayList<>();
        int x = tile.getX();
        int y = tile.getY();
        for(int i = y+1; i < 8; i++){
            x--;
            Tile currentTile = tiles[i][x];
            if(currentTile.getStone() == null){
                tempTiles.clear();
                return tempTiles;
            }
            if (tile.getStone().getFill() == currentTile.getStone().getFill()){
                return tempTiles;
            }
            tempTiles.add(currentTile);
        }
        return tempTiles;
    }


    private static void reverseStone(Tile tile){
        if(tile.isEmpty()){ return; }
        if(tile.getStone().getFill() == Color.WHITE){
            tile.getStone().setFill(Color.BLACK);
        } else {
            tile.getStone().setFill(Color.WHITE);
        }
    }

    public static void reverseStones(List<Tile> tiles){
        if(tiles == null || tiles.isEmpty() ){ return; }
        for(int i = 0; i < tiles.size(); i++){
            reverseStone(tiles.get(i));
        }

    }

}
