package Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import Object.OBJ_Key;

public class UI {
    //USER INTERFACE
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
//    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("0.00");

    public UI(GamePanel gp){

        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
//        OBJ_Key key = new OBJ_Key(gp);
//        keyImage = key.image;
    }
    public void showMessage(String text){

        message = text;
        messageOn = true;
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        if(gp.gameState == gp.playState){
            //Do playState stuff later

        }
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
    }

    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenterText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }
    public int getXforCenterText(String text){
        //Displaying text at the center of the screen
        int lengh = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - lengh/2;
        return x;
    }
}
