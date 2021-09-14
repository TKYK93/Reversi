package com.reversi.reversi;

import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class Player {
    //TODO: add and show name function
    private String name;
    private List<Circle> stones;
    private boolean isMyTurn;

    public Player(){
        stones = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Circle> getStones() {
        return stones;
    }

    public void setStones(List<Circle> stones) {
        this.stones = stones;
    }

    public boolean isMyTurn() {
        return isMyTurn;
    }

    public void setMyTurn(boolean myTurn) {
        isMyTurn = myTurn;
    }

    public void addStones(List<Circle> newStones){
        this.stones.addAll(newStones);
    }

    public void addStone(Circle newStone){
        this.stones.add(newStone);
    }

}
