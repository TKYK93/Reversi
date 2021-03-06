package com.reversi.reversi;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class Table {
    public static final int STONE_RADIUS = 20;
    public static Player player1; // stone's color is white
    public static Player player2; // stone's color is black
    public static Tile[][] tiles;

    public Table(Player player1, Player player2){
        this.player1 = player1;
        player1.setMyTurn(true);
        this.player2 = player2;
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

    public int showWinner () {

        int whiteCounter = 0;
        int blackCounter = 0;

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Tile currentTile = tiles[i][j];
                Paint color = currentTile.getStone().getFill();
                if(color.equals(Color.WHITE)){
                    whiteCounter++;
                }

                if(color.equals(Color.BLACK)){
                    blackCounter++;
                }
            }
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        alert.setHeaderText(null);

        alert.showAndWait();
        if(whiteCounter > blackCounter){
            alert.setContentText("Player 1 won!");
        } else if(whiteCounter == blackCounter){
            alert.setContentText("Draw!");
        } else {
            alert.setContentText("Player 2 won!");
        }
        return Math.max(whiteCounter, blackCounter);
    }

    public static Player getPlayer1() {
        return player1;
    }

    public static Player getPlayer2() {
        return player2;
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

        if(y == 7){
            return tempTiles;
        }

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

        if(x == 7){
            return tempTiles;
        }

        for(int i = y+1; i < 8; i++){
            x++;
            Tile currentTile = tiles[i][x];
            if(currentTile.getStone() == null){
                tempTiles.clear();
                return tempTiles;
            }
            if (tile.getStone().getFill() == currentTile.getStone().getFill() ){
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

        if(x == 7){
            return tempTiles;
        }

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

        if(x == 7){
            return tempTiles;
        }

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

        if(y == 0) {
            return tempTiles;
        }

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

        if(x == 0){
            return tempTiles;
        }

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

        if(x == 0){
            return tempTiles;
        }

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

        if(x == 0){
            return tempTiles;
        }

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

    public static void checkCanPutStones(){
        int okCounter = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Tile currentTile = tiles[i][j];
                System.out.println("currenttile = " + currentTile);
                if(checkCanPutStone(currentTile, checkBottom(currentTile))){okCounter++;};
                if(checkCanPutStone(currentTile, checkBottomLeft(currentTile))){okCounter++;};
                if(checkCanPutStone(currentTile, checkBottomRight(currentTile))){okCounter++;};
                if(checkCanPutStone(currentTile, checkUp(currentTile))){okCounter++;};
                if(checkCanPutStone(currentTile, checkUpLeft(currentTile))){okCounter++;};
                if(checkCanPutStone(currentTile, checkUpRight(currentTile))){okCounter++;};
                if(checkCanPutStone(currentTile, checkLeft(currentTile))){okCounter++;};
                if(checkCanPutStone(currentTile, checkRight(currentTile))){okCounter++;};
                if(okCounter >= 1){
                    currentTile.markAsPuttable();
                }
                okCounter = 0;
            }
        }
    }

    private static boolean checkCanPutStone(Tile tile, List<Tile> tileArray){
        List<Tile> currentLine= new ArrayList<>(tileArray);
        if(!currentLine.isEmpty() ){
            if(currentLine.get(0).getStone().getFill() != tile.getStone().getFill()){
                return true;
            }
        };
        return false;
    }

    private static void switchPlayersTurn(){
        if(player1.isMyTurn()){
            player1.setMyTurn(false);
            player2.setMyTurn(true);
        } else {
            player1.setMyTurn(true);
            player2.setMyTurn(false);
        }
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
