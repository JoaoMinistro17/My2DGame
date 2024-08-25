package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean up, down, left, right;
    //DEBUG
    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;

    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){ up = true;}
        if(code == KeyEvent.VK_S){ down = true;}
        if(code == KeyEvent.VK_A){ left = true;}
        if(code == KeyEvent.VK_D){ right = true;}
        if(code == KeyEvent.VK_ESCAPE){
            if(gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
            }
            else if(gp.gameState == gp.pauseState){
                gp.gameState = gp.playState;
            }
        }
        if(code == KeyEvent.VK_T){
            if(!checkDrawTime){
                checkDrawTime = true;
            } else if (checkDrawTime){
                    checkDrawTime = false;
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){ up = false;}
        if(code == KeyEvent.VK_S){ down = false;}
        if(code == KeyEvent.VK_A){ left = false;}
        if(code == KeyEvent.VK_D){ right = false;}
    }
}
