package entities;

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
}
