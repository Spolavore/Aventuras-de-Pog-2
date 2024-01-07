package inputs;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import entities.Player;
import levels.Level;
import levels.LevelHandler;
import main.Game;
import main.GamePanel;
import physics.Collisions;
import soundtrack.SoundHandler;
import utils.Constants.Directions;
import utils.Constants.Directions.*;

public class KeyboardInputs implements KeyListener {
    private GamePanel gamePanel;
    private Player player;
    private static int lastDirectionRegistred;
    private static ArrayList<Integer> directionStack = new ArrayList<>();

    public KeyboardInputs(GamePanel gamePanel, Player player) {
        this.gamePanel = gamePanel;
        this.player = player;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

        char keyReleased = Character.toUpperCase(e.getKeyChar());

        // O código abaixo segue a seguinte lógica:
        /*
         * Verifica qual tecla que foi clicada, se a posição que ela representa
         * é uma possibilidade de movimentação do usuário ou representa a última
         * direção que o usuário está indo então prossegue salvando qual direção
         * ela representa e pegando o índice dela na pilha de direções.
         */
        int indexOfDirection = -1;
        int direction;

        switch (keyReleased) {
            case 'W':
                if (player.canMove()[0] || player.getDirection() == Directions.UP) {
                    direction = Directions.UP;

                    indexOfDirection = directionStack.indexOf(direction);
                }
                break;
            case 'A':
                if (player.canMove()[1] || player.getDirection() == Directions.LEFT) {
                    direction = Directions.LEFT;
                    indexOfDirection = directionStack.indexOf(direction);
                }
                break;
            case 'S':
                if (player.canMove()[2] || player.getDirection() == Directions.DOWN) {
                    direction = Directions.DOWN;
                    indexOfDirection = directionStack.indexOf(direction);
                }
                break;
            case 'D':
                if (player.canMove()[3] || player.getDirection() == Directions.RIGHT) {

                    direction = Directions.RIGHT;
                    indexOfDirection = directionStack.indexOf(direction);
                }
                break;
            default:

                break;
        }

        // Se a tecla entrar em algum dos casos do switch significa que o usuário soltou
        // uma tecla de movimentação, se não o indice da direção continuára -1;
        if (indexOfDirection != -1) {

            directionStack.remove(indexOfDirection); // remove da pilha a direção da tecla soltada.
        }
        // O Código remove a direção e essa parte vai selecionar qual é a direção
        // anterior a que foi removida. Isso serve para caso o usuário clique duas
        // teclas e solte uma, então a tecla que continua pressionada vai funcionar
        // normalmente. OBS: a verificação de -1 é caso só estiver uma tecla na pilha.
        int indexLastDirection = directionStack.size() - 1;
        if (indexLastDirection != -1) {

            player.setDirection(directionStack.get(indexLastDirection));
        }

        // Caso a pilha de direções estiver vazia significa que o usuário não está
        // apertando
        // alguma tecla de movimentação, ou seja não está se movimentando.
        if (directionStack.isEmpty()) {
            player.setMoving(false);
            player.setTypeOfAnimation(0);
        }

        if (player.canMove()[3] == false) {
            indexOfDirection = directionStack.indexOf(1);
            if (indexOfDirection != -1) {
                directionStack.remove(indexOfDirection);
            }

        }
    }

     /* Função responsável pelo controle de qual tecla foi clicada
      * e qual ação dentro do jogo deve ser tomada
      */
    @Override
    public void keyPressed(KeyEvent e) {
        char keyPressed = Character.toUpperCase((e.getKeyChar()));
        String gameState = gamePanel.getGameState();


        // Se o estados do jogo é Lose ou Victory, significa que o 
        // jogador está ou na tela de derrota ou de vitória. Independente
        // do caso, qualquer tecla q ele clicar o jogo deve ser resetado
        // permitindo que o jogador jogue novamente
        if (gameState == "Lose" || gameState == "Victory") {
            switch (keyPressed) {
                default:
                    gamePanel.resetToInitStatus();
                    break;
            }

        } else {

            // Caso o gameState for outro significa que o jogador está em jogo
            // ou seja faz as verificações das teclas, colocando na pilha as teclas
            // de movimentação
            switch (keyPressed) {
                case 'A':
                    if (player.canMove()[1] && !player.isFalling()) {
                        player.setDirection(Directions.LEFT);
                        stackDirection(Directions.LEFT);
                        player.setMoving(true);
                        lastDirectionRegistred = Directions.LEFT;
                        if (!player.isJumping() && !player.isFalling()) {
                            player.setTypeOfAnimation(2); // permite a animação de corrida
                                                                          // apenas se o jogador não está pulando ou caindo
                        }

                    }

                    break;

                case 'D':
                    if (player.canMove()[3] && !player.isFalling()) {
                        player.setDirection(Directions.RIGHT);
                        stackDirection(Directions.RIGHT);
                        player.setMoving(true);
                        lastDirectionRegistred = Directions.RIGHT;
                        if (!player.isJumping() && !player.isFalling()) {
                            player.setTypeOfAnimation(2);

                        }
                    }

                    break;

                case 'E':
                    // Tecla E é uma tecla de interação que caso o jogador
                    // esteja no endpoint e ele clicar E então ele deve prosseguir 
                    // para a próxima fase
                    if (player.isInEndpoint()) {
                        Level.goNextLevel();
                    }
                    // Caso ele esteja em cima de um baú e clique E ele está tentando abri-lo
                    // então a flag abaixo é setada para true, caso contrário, false.
                    // Essa flaga ajuda o ChestHandler a saber se o báu deve ser aberto ou não
                    if (player.isInChest()) {
                        player.setIsTryingtoOpenChest(true);
                    } else {
                        player.setIsTryingtoOpenChest(false);
                    }
                    break;
                case ' ':


                    // Tecla de ESPAÇO
                    if (!player.isFalling()) {
                        if (!player.isJumping()) {

                            SoundHandler.playSound("sounds/Retro Jump Classic.wav");
                        }
                        player.setJumping(true);
                    }

                    break;
                default:
                    break;

            }
        }

    }

    // Função reponsável por colocar um item na pilha de direções
    // só colocará o item se ele ainda não estiver na pilha.
    private void stackDirection(int dir) {
        int index = directionStack.indexOf(dir);
        if (index == -1) {
            directionStack.add(dir);
        }
    }

    // Limpa a Fila de Stack quando o jogo é minimizado
    public static void clearStack() {
        KeyboardInputs.directionStack.clear();
    }

    public static int getLastDirection() {
        if (directionStack.size() != 0) {
            int lastDirectionIndex = directionStack.size() - 1;
            return directionStack.get(lastDirectionIndex);
        } else {
            return 400; // erro
        }
    }

    // A diferenção entre esse get e o getLastDirection é que este está guarando
    // em memória a última tecla processada, estão ela soltada ou não. Já
    // a getLastDirection retorta a última direção contida na pilha. (Caso o usuário
    // solte as teclas a pilha é limpada)
    public static int getLastDirectionRegistred() {
        return lastDirectionRegistred;
    }

    public static int sizeDirectionStack() {
        return directionStack.size();
    }
}
