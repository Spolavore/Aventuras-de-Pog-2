package entities;


// Classe de entidades que possui funções já implementadas para serem herdadas pelas calsses filhas.
// Ela é abstract pois necessita que alguem a extenda, não havendo sentindo dela ser implementada

// Métodos com assinaturas auto-explicativas, por isso não serão documentados
public abstract class Entity {
    private int x,y;
    public Entity(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void updateXPosition(int x){
        this.x += x;
    }

    public void updateYPosition(int y){
        this.y +=  y;
    }

    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }
}
