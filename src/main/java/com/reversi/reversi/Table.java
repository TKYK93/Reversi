package com.reversi.reversi;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Map;

public class Table {
    public static final int STONE_RADIUS = 20;
    public static Player player1; // stone's color is white
    public static Player player2; // stone's color is black
    public static Tile[][] positions = new Tile[8][8];

    public Table(){
        player1 = new Player();
        player1.setMyTurn(true);
        player2 = new Player();
        player2.setMyTurn(false);
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
                    tile.getChildren().add(stone);
                    tile.setEmpty(false);
                }

                if((i == 3 && j == 4) || (i == 4 && j == 3)
                ){
                    Circle stone = new Circle(STONE_RADIUS, Color.BLACK);
                    player2.addStone(stone);
                    tile.getChildren().add(stone);
                    tile.setEmpty(false);

                }
                tile.setY(i);
                tile.setX(j);
            }
        }

        return root;
    }
}
