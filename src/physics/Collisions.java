package physics;

import entities.Player;
import inputs.KeyboardInputs;
import objects.ChestHandler;
import utils.Constants.Directions;

// As colisões são verificadas quando o usuário tenta se movimentar, ou seja, elas estão sendo 
// incorporadas em KeyboardInputs.java, entretando é no método update do GamePanel que a checagem de colisões
// é feita -> visto que tem que se checar colisões sempre que o fps ou ups for renovado
public class Collisions {
    private char[][] Map;
    private Player player;
    private int playerXPosition;
    private int playerYPosition;
    private int hitboxX = 0; // fator de correção eixo X para cenários específicos
    private int hitboxY = 32; // fator de correção eixo Y para cenários específicos
    private boolean playerIsInRightBorder = false; // verifica se tem uma borda a direita do player
    private boolean playerIsInLeftBorder = false; // verifica se tem uma borda a esquerda do player

    public Collisions(char[][] Map, Player player) {
        this.Map = Map;
        this.player = player;
        // Os numeros somados ao lado de player.getX ou Y refere-se ao fator de correção
        // para ajustar a posição do player. É necessário fazer isso pois o jogo avalia
        // as colisões considerando os blocos como 32x32, entretando o player possui
        // 64x64
        // logo esse fator é necessário para converter por baixo dos panos o jogador
        // para 32x32
        this.playerXPosition = player.playerXPosition() + 16;
        this.playerYPosition = player.playerYPosition() + 32;

    }

    public void checkCollisions() {
        hitBoxCorrection();
        // x é a coluna y a linha, Matrix que possui as tranformação das coordenadas de
        // tela para as coordenadas da matrix do mapa
        int[] playerMatrixPosition = { playerXPosition / 32, playerYPosition / 32 }; // 32 x 32 é o tamanho do tile;

        //System.out.println("X: " + playerMatrixPosition[0] + '|' + "Y: " +
         //       playerMatrixPosition[1]);

        // ** Abaixo estão as verificações de se o usuário pode ou não se movimentar
        // para uma das 4 direções **/

        // Verificacao de limites de borda
        if (checkBoardLimits(playerMatrixPosition)) {

            checkIfPlayerIsFalling(playerMatrixPosition);

            if (checkIfPlayerInEndpoint(playerMatrixPosition, 0, 0)) {
                player.setIsInEndpoint(true);
            } else {
                player.setIsInEndpoint(false);
            }

            if (checkIfPlayerInChest(playerMatrixPosition, 0, 0)) {
                player.setIsInChest(true);
                if (player.isTryingtoOpenChest()) {
                    ChestHandler.openChestCallBack(player);
                }
            } else {
                player.setIsInChest(false);
            }

            // Verificação se o player pode se mover para cima
            if (this.Map[playerMatrixPosition[1] - 1][playerMatrixPosition[0]] != ' '
                    && !checkIfPlayerInEndpoint(playerMatrixPosition, 0, -1)) {

                player.setPlayerCanMove(false, 0);

            } else {
                player.setPlayerCanMove(true, 0);
            }

            // Verificação se o player pode se mover para a esquerda
            if (this.Map[playerMatrixPosition[1]][playerMatrixPosition[0] - 1] != ' '
                    && !checkIfPlayerInEndpoint(playerMatrixPosition, -1, 0)
                    && !checkIfPlayerInChest(playerMatrixPosition, -1, 0)) {
                player.setPlayerCanMove(false, 1);

            } else {
                player.setPlayerCanMove(true, 1);

            }

            // Verificação se o player pode se mover para baixo
            if (this.Map[playerMatrixPosition[1] + 1][playerMatrixPosition[0]] != ' '
                    && !checkIfPlayerInEndpoint(playerMatrixPosition, 0, 1)
                    && !checkIfPlayerInChest(playerMatrixPosition, 0, 1)) {
                player.setPlayerCanMove(false, 2);

            } else {
                player.setPlayerCanMove(true, 2);
            }

            // Verificação se o player pode se mover para a direita
            if (this.Map[playerMatrixPosition[1]][playerMatrixPosition[0] + 1] != ' '
                    && !checkIfPlayerInEndpoint(playerMatrixPosition, 1, 0)
                    && !checkIfPlayerInChest(playerMatrixPosition, 1, 0)) {
                player.setPlayerCanMove(false, 3);
            } else {
                player.setPlayerCanMove(true, 3);
            }

            boolean isRightDiagonalEmpty = this.Map[playerMatrixPosition[1] + 1][playerMatrixPosition[0] + 1] == ' ';
            boolean isLeftDiagonalEmpty = this.Map[playerMatrixPosition[1] + 1][playerMatrixPosition[0] - 1] == ' ';

            // Verifica se o player está numa borda (Esquerda)
            if (this.Map[playerMatrixPosition[1]][playerMatrixPosition[0] - 1] == ' ' && isLeftDiagonalEmpty) {
                playerIsInLeftBorder = true;

            } else {
                playerIsInLeftBorder = false;

            }
            // Verifica se o player está numa borda (Direita)
            if (this.Map[playerMatrixPosition[1]][playerMatrixPosition[0] + 1] == ' ' && isRightDiagonalEmpty) {
                playerIsInRightBorder = true;
            } else {
                playerIsInRightBorder = false;

            }
        }

        // Atualiza as posições
        playerYPosition = player.playerYPosition() + hitboxY;
        playerXPosition = player.playerXPosition() + hitboxX;

    }

    // Função que verifica as colisões de borda antes da colisões de bloco
    // faz verificações para impedir que o player saia do mapa em todas
    // as direções. Se nenhum dos testes passarem a função retorna true
    // e as demais verificações de colisão da função checkColisions são executadas
    private boolean checkBoardLimits(int[] playerMatrixPosition) {
        // Primeiro partimos assumindo que o jogador pode se mover para todos os lados
        player.setPlayerCanMove(true, 0);
        player.setPlayerCanMove(true, 1);
        player.setPlayerCanMove(true, 2);
        player.setPlayerCanMove(true, 3);
        checkIfPlayerIsFalling(playerMatrixPosition); // temos que chegar se o player esta caindo
                                                      // para impedir pulos
        if (playerMatrixPosition[0] == 0 && playerMatrixPosition[1] == 0) {
            // impede que o player ande pra esquerda/cima
            player.setPlayerCanMove(false, 1);
            player.setPlayerCanMove(false, 0);

            return false;
        } else if (playerMatrixPosition[0] == 38 && playerMatrixPosition[1] == 0) {
            // impede que o player ande pra direita/cima
            player.setPlayerCanMove(false, 3);
            player.setPlayerCanMove(false, 0);
            return false;
        } else if (playerMatrixPosition[0] == 0) {
            // impede que player ande pra esquerda
            player.setPlayerCanMove(false, 1);
            return false;
        } else if (playerMatrixPosition[0] == 38) {
            // impede que player ande pra direita
            player.setPlayerCanMove(false, 3);
            return false;
        } else if (playerMatrixPosition[1] == 0) {
            // impede que player ande pra cima
            player.setPlayerCanMove(false, 0);
            return false;
        }
        return true;

    }

    private void checkIfPlayerIsFalling(int[] playerMatrixPosition) {
        if (this.Map[playerMatrixPosition[1] + 1][playerMatrixPosition[0]] == ' '
                || checkIfPlayerInEndpoint(playerMatrixPosition, 0, 1)
                || checkIfPlayerInChest(playerMatrixPosition, 0, 1)) {
            if (!player.isJumping()) {
                player.setFalling(true);
                player.setTypeOfAnimation(4);
                player.setPlayerCanMove(true, 2);
            }
        } else {

            player.setFalling(false);
            Jump.reset();
            player.setPlayerCanMove(false, 2);
        }
    }

    // Função responsável por corrigir a hitbox do personagem em diversos casos
    // , a fim de traz um fluidez para o jogo e para o bom funcionamento do contato
    // player-ambiente
    private void hitBoxCorrection() {
        int playerXConverted = playerXPosition / 32;
        int playerYConverted = playerYPosition / 32;

        if (playerXConverted == 1) {
            hitboxX = 32;
        }

        if (player.isJumping()) {
            hitboxY = 40;

        } else {
            hitboxY = 32;
        }

        if (KeyboardInputs.getLastDirectionRegistred() == Directions.LEFT) {
            if (playerIsInRightBorder) {
                hitboxX = 20;

            } else {
                hitboxX = 32;
            }

        }
        if (KeyboardInputs.getLastDirectionRegistred() == Directions.RIGHT) {
            if (playerIsInLeftBorder) {
                hitboxX = 35;

            }

            else if (playerXConverted != 1) {
                hitboxX = 16;
            }

        }
    }

    private boolean checkIfPlayerInChest(int[] playerMatrixPosition, int xAcres, int yAcres) {

        return this.Map[playerMatrixPosition[1] + yAcres][playerMatrixPosition[0] + xAcres] == 'c' ||
                this.Map[playerMatrixPosition[1] + yAcres][playerMatrixPosition[0] + xAcres] == 'C';

    }

    // Função auxiliar para o método check colissions para saber se o player está em
    // cima do endpoint
    private boolean checkIfPlayerInEndpoint(int[] playerMatrixPosition, int xAcres, int yAcres) {
        return this.Map[playerMatrixPosition[1] + yAcres][playerMatrixPosition[0] + xAcres] == '+';
    }

    public void updateLevelToCheck(char[][] newMap) {
        this.Map = newMap;
    }
}
