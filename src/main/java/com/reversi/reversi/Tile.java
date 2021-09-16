package com.reversi.reversi;

import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Tile extends StackPane{
    private boolean isEmpty;
    private int x;
    private int y;
    Circle stone;

    public Tile () {
        this.isEmpty = true;
        Rectangle border = new Rectangle(70, 70);
        border.setFill(null);
        border.setStroke(Color.valueOf("000"));
        setAlignment(Pos.CENTER);

        setOnMouseClicked(event -> {
            putStone();
            Table.reverseStones(Table.checkRight(this));
            Table.reverseStones(Table.checkLeft(this));
            Table.reverseStones(Table.checkUp(this));
            Table.reverseStones(Table.checkBottom(this));
            Table.reverseStones(Table.checkBottomRight(this));
            Table.reverseStones(Table.checkBottomLeft(this));
            Table.reverseStones(Table.checkUpRight(this));
            Table.reverseStones(Table.checkUpLeft(this));
        });
        getChildren().addAll(border);
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Circle getStone() {
        return stone;
    }

    public void setStone(Circle stone) {
        this.stone = stone;
    }

    private void putStone() {
        if(this.isEmpty){
            stone = new Circle(Table.STONE_RADIUS);
            if(Table.player1.isMyTurn()){
                stone.setFill(Color.WHITE);
                Table.player1.addStone(stone);
                Table.player1.setMyTurn(false);
                Table.player2.setMyTurn(true);
            } else {
                stone.setFill(Color.BLACK);
                Table.player2.addStone(stone);
                Table.player2.setMyTurn(false);
                Table.player1.setMyTurn(true);
            }
            getChildren().add(stone);
        }
        setEmpty(false);
    }

    @Override
    public String toString() {
        return "Tile{" +
                "isEmpty=" + isEmpty +
                ", x=" + x +
                ", y=" + y +
                ", stone=" + stone +
                '}';
    }
}
