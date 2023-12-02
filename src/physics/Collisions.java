package physics;

import entities.Player;
// As colisões são verificadas quando o usuário tenta se movimentar, ou seja, elas estão sendo 
// incorporadas em KeyboardInputs.java, entretando é no método update do GamePanel que a checagem de colisões
// é feita -> visto que tem que se checar colisões sempre que o fps ou ups for renovado
public class Collisions {
    private char[][] Map;
    private Player player;
    private char keyPressed = ' '; // inicia setando para branco, pois nenhuma tecla é clicada logo no inicio

    public Collisions(char[][] Map, Player player){
        this.Map = Map;
        this.player = player;
    }

    public void checkCollisions(){
        int playerXPosition = player.getX();
        int playerYPosition = player.getY();
      
        // x é a coluna y a linha
        int[] playerMatrixPosition = { playerXPosition / 32 , playerYPosition / 32}; // 32 x 32 é o tamanho do tile;
        System.out.println("x: " + playerMatrixPosition[0] + '|' + "y: "+ playerMatrixPosition[1]);


    
    }

    public void setKeyPressed(char keyPressed) {
        this.keyPressed = keyPressed;
    }
}
