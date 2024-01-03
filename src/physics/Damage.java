package physics;

import entities.Player;

public class Damage {
    private Player player;
    private  int blocksFell;
    private int lastBlockChecked;
    public Damage(Player player){
        this.player = player;

    }

    public void applyDamage(){
        verifyBlocksFelt();
       
        if(blocksFell >= 9){
            player.decreaseLife();
            blocksFell = 0;
        }
   
    }
  
    private void verifyBlocksFelt() {
        if (player.isFalling()){
            
            int yPosition = player.getY();
            int currentBlock = yPosition / 32;
            

            if(currentBlock != lastBlockChecked){
                this.lastBlockChecked = currentBlock;
                blocksFell += 1;
                
            }
           
        } else {
            blocksFell = 0;
            lastBlockChecked = -1;
        }
    }
}
