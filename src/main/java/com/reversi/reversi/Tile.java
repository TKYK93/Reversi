package com.reversi.reversi;

import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class Tile extends StackPane{
    private boolean isEmpty;
    private boolean canPutStone;
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
            System.out.println(this);
            putTempStone();
            List<Tile> rightStones = Table.checkRight(this);
            List<Tile> leftStones = Table.checkLeft(this);
            List<Tile> upStones = Table.checkUp(this);
            List<Tile> bottomStones = Table.checkBottom(this);
            List<Tile> bottomRightStones = Table.checkBottomRight(this);
            List<Tile> bottomLeftStones = Table.checkBottomLeft(this);
            List<Tile> upRightStones = Table.checkUpRight(this);
            List<Tile> upLeftStones = Table.checkUpLeft(this);
            if(        rightStones.isEmpty()
                    && leftStones.isEmpty()
                    && upStones.isEmpty()
                    && bottomStones.isEmpty()
                    && bottomRightStones.isEmpty()
                    && bottomLeftStones.isEmpty()
                    && upRightStones.isEmpty()
                    && upLeftStones.isEmpty()
            ) {
                this.stone = null;
                return;
            }
            putStone();
            Table.reverseStones(rightStones);
            Table.reverseStones(leftStones);
            Table.reverseStones(bottomStones);
            Table.reverseStones(upStones);
            Table.reverseStones(bottomLeftStones);
            Table.reverseStones(bottomRightStones);
            Table.reverseStones(upRightStones);
            Table.reverseStones(upLeftStones);
        });

        //TODO: Check places where player can put stone
//        setOnMouseReleased(event -> {
//            System.out.println("mouse released!");
//            Table.checkCanPutStones();
//        });

        getChildren().addAll(border);
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public boolean isCanPutStone() {
        return canPutStone;
    }

    public void setCanPutStone(boolean canPutStone) {
        this.canPutStone = canPutStone;
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
        this.stone = null;
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

    //TODO: check if Visibility => none is possible, otherwise arrays will be empty.
    private void putTempStone () {
        this.stone = new Circle(10);
        if(Table.player1.isMyTurn()){
            stone.setFill(Color.WHITE);
        } else {
            stone.setFill(Color.BLACK);
        }
    }

    public void markAsPuttable () {
        this.stone = new Circle(5);
        stone.setFill(Color.RED);
        getChildren().add(stone);
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
