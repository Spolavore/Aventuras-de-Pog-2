package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import entities.Player;
import main.GamePanel;
import physics.Collisions;
import utils.Constants.Directions;
import utils.Constants.Directions.*;

public class KeyboardInputs implements KeyListener {
    private GamePanel gamePanel;
    private Player player;
    private Collisions collisions;
    private static ArrayList<Integer> directionStack = new ArrayList<>();

    public KeyboardInputs(GamePanel gamePanel, Player player, Collisions collisions) {
        this.gamePanel = gamePanel;
        this.player = player;
        this.collisions = collisions;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

        // verifica se todas as teclas foram soltadas;
        // se sim então significa que o player está parado
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

        System.out.println(directionStack);

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
        // uma
        // tecla de movimentação, se não o indice da direção continuára -1;
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

    @Override
    public void keyPressed(KeyEvent e) {
        char keyPressed = Character.toUpperCase((e.getKeyChar()));

        switch (keyPressed) {
            case 'A':
                if (player.canMove()[1]) {
                    player.setDirection(Directions.LEFT);
                    player.setTypeOfAnimation(2);
                    stackDirection(Directions.LEFT);
                    player.setMoving(true);

                }

                break;

            // Descomentar caso queira possibilitar que o jogador ande para baixo
            // case 'S':
            // if (player.canMove()[2]) {
            // player.setDirection(Directions.DOWN);
            // player.setTypeOfAnimation(4);
            // stackDirection(Directions.DOWN);
            // player.setMoving(true);

            // }
            // break;

            case 'D':
                if (player.canMove()[3]) {
                    player.setDirection(Directions.RIGHT);
                    player.setTypeOfAnimation(2);
                    stackDirection(Directions.RIGHT);
                    player.setMoving(true);

                }

                break;

            case ' ':
                player.setDirection(Directions.UP);
                player.setJumping(true);
                break;
            default:
                break;

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
}
