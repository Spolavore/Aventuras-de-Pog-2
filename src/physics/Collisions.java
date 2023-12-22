package physics;

import entities.Player;
import utils.Constants.Directions;
// As colisões são verificadas quando o usuário tenta se movimentar, ou seja, elas estão sendo 
// incorporadas em KeyboardInputs.java, entretando é no método update do GamePanel que a checagem de colisões
// é feita -> visto que tem que se checar colisões sempre que o fps ou ups for renovado
public class Collisions {
    private char[][] Map;
    private Player player;
    private boolean firstPositionPassed = false;

    public Collisions(char[][] Map, Player player){
        this.Map = Map;
        this.player = player;
        
    }

    public void checkCollisions(){
        // Os numeros somados ao lado de player.getX ou Y refere-se ao fator de correção
        // para ajustar a posição do player. É necessário fazer isso pois o jogo avalia
        // as colisões considerando os blocos como 32x32, entretando o player possui 64x64
        // logo esse fator é necessário para converter por baixo dos panos o jogador para 32x32
        int playerXPosition = player.getX()+16;
        int playerYPosition = player.getY()+32;
        

        // Abaixo segue o fator de correção quando o player se movimenta para a esquerda
        // o código abaixo garente que a posição do player só será mudada (andando para a esquerda)
        // quando ele estive exatamente no meio do tile
        if(player.getDirection() == Directions.LEFT){
            int convertedPlayerXPos = playerXPosition / 32;

            if(convertedPlayerXPos == 2){
                // O fator quando o jogador esta em cima do tile 2 (esquerda para direita linha do X no plano 
                // cartesiano) é diferente para que seja possível o jogador "enconstar as costa na parede"
                playerXPosition += 35;
            }
            else{
                playerXPosition += 24;
            }
            
        }

        if(playerXPosition/32 == 1 && player.getDirection() == Directions.RIGHT){
            playerXPosition += 16;
        }



        // x é a coluna y a linha, Matrix que possui as tranformação das coordenadas de tela para as coordenadas da matrix do mapa
        int[] playerMatrixPosition = { playerXPosition / 32 , playerYPosition / 32}; // 32 x 32 é o tamanho do tile;

        
        // System.out.println("X: " + playerMatrixPosition[0] + '|' + "Y: "+ playerMatrixPosition[1]);


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
