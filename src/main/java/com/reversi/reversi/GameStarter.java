package com.reversi.reversi;

public class GameStarter {
    private static Player player1;
    private static Player player2;

    public GameStarter() {
        this.player1 = new Player();
        this.player2 = new Player();
    }

    public Table getTableInstance (){
        Table table = new Table(player1, player2);
        return table;
    }

    public void startGame(){
        player1.setMyTurn(true);
        player2.setMyTurn(false);
        while(true){

        }
    }
}
