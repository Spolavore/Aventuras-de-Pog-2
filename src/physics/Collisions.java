package physics;

import entities.Player;
import inputs.KeyboardInputs;
import utils.Constants.Directions;
// As colisões são verificadas quando o usuário tenta se movimentar, ou seja, elas estão sendo 
// incorporadas em KeyboardInputs.java, entretando é no método update do GamePanel que a checagem de colisões
// é feita -> visto que tem que se checar colisões sempre que o fps ou ups for renovado
public class Collisions {
    private char[][] Map;
    private Player player;
    private int playerXPosition;
    private int playerYPosition;
    private  int hitboxX = 0; // fator de correção eixo X para cenários específicos
    private   int hitboxY= 32; // fator de correção eixo Y para cenários específicos
    private boolean firstPositionPassed = false;

    public Collisions(char[][] Map, Player player){
        this.Map = Map;
        this.player = player;
        // Os numeros somados ao lado de player.getX ou Y refere-se ao fator de correção
        // para ajustar a posição do player. É necessário fazer isso pois o jogo avalia
        // as colisões considerando os blocos como 32x32, entretando o player possui 64x64
        // logo esse fator é necessário para converter por baixo dos panos o jogador para 32x32
        this.playerXPosition = player.playerXPosition()+16;
        this.playerYPosition = player.playerYPosition()+32;

        
    }

    public void checkCollisions(){

        int playerXConverted =  playerXPosition / 32;
        int playerYConverted = playerYPosition / 32;
    
        if(playerXConverted == 1){
            hitboxX = 16;
        }
        

        if(KeyboardInputs.getLastDirectionRegistred() == Directions.LEFT){
            if(player.isJumping() || player.isFalling()){
                hitboxX = 16;
                
            }else  {
                hitboxX = 32; // trocar para  16 funciona
            }

            
        }
      

        if(KeyboardInputs.getLastDirectionRegistred() == Directions.RIGHT ){
            if(player.isJumping() || player.isFalling()){
                hitboxX = 32;
                
            } else if(playerXConverted != 1) {
                hitboxX = 32;
            }

        }

        System.out.println(playerXPosition);
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



        // Verificação se o player está caindo
        if(this.Map[playerMatrixPosition[1]+1][playerMatrixPosition[0]] == ' '){
            if(!player.isJumping()){
                player.setFalling(true);
                player.setPlayerCanMove(true, 2);
            }
        }else{
            player.setFalling(false);
            Jump.reset();
            player.setPlayerCanMove(false, 2);
            
        }

  
            playerYPosition = player.playerYPosition()+ hitboxY;
            playerXPosition = player.playerXPosition() + hitboxX; 

  
    }
   

}
