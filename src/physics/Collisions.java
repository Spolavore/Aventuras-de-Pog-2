package physics;

import entities.Player;
// As colisões são verificadas quando o usuário tenta se movimentar, ou seja, elas estão sendo 
// incorporadas em KeyboardInputs.java, entretando é no método update do GamePanel que a checagem de colisões
// é feita -> visto que tem que se checar colisões sempre que o fps ou ups for renovado
public class Collisions {
    private char[][] Map;
    private Player player;

    public Collisions(char[][] Map, Player player){
        this.Map = Map;
        this.player = player;
    }

    public void checkCollisions(){
        int playerXPosition = player.getX()+16;
        int playerYPosition = player.getY()+16;
        

        if(playerYPosition / 32 == 19){
            playerYPosition -= 16;
        }
        
        if(playerXPosition / 32 == 1){
            playerXPosition -= 16;
        }
        // x é a coluna y a linha, Matrix que possui as tranformação das coordenadas de tela para as coordenadas da matrix do mapa
        int[] playerMatrixPosition = { playerXPosition / 32 , playerYPosition / 32}; // 32 x 32 é o tamanho do tile;

        
        System.out.println("X: " + playerMatrixPosition[0] + '|' + "Y: "+ playerMatrixPosition[1]);


        // ** Abaixo estão as verificações de se o usuário pode ou não se movimentar para uma das 4 direções **/
        // Verificação se o player pode se mover para cima
        if(this.Map[playerMatrixPosition[1]-1][playerMatrixPosition[0]] == '#'){
            player.setPlayerCanMove(false, 0);
        } else{ 
            player.setPlayerCanMove(true, 0);
        }

        // Verificação se o player pode se mover para a esquerda
        if(this.Map[playerMatrixPosition[1]][playerMatrixPosition[0]-1] == '#'){
            player.setPlayerCanMove(false, 1);
        }else{
            player.setPlayerCanMove(true, 1);

        }

        // Verificação se o player pode se mover para baixo
        if(this.Map[playerMatrixPosition[1]+1][playerMatrixPosition[0]] == '#'){
            player.setPlayerCanMove(false, 2);
        }else{
            player.setPlayerCanMove(true, 2);
        }

        
        // Verificação se o player pode se mover para a direita 
        if(this.Map[playerMatrixPosition[1]][playerMatrixPosition[0]+1] == '#'){
            
            player.setPlayerCanMove(false, 3);
        }else{
            player.setPlayerCanMove(true, 3);
        }
    

    
    }


}
