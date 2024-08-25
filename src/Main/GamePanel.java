package Main;

import Entity.Entity;
import Entity.Player;
import Object.SuperObject;
import Tiles.TileManager;
import java.awt.*;
import javax.swing.*;
public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; //48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    //public final int worldWidth = tileSize * maxWorldCol; useless
    //public final int worldHeight = tileSize * maxWorldRow; useless

    //FPS
    int FPS = 60;

    //SYSTEM
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    //ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject[] obj = new SuperObject[10];
    public Entity npc[] = new Entity[10];

    //GAME STATE
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void setupGame() {

        aSetter.setObject();
        aSetter.setNpc();
        playMusic(0);
        //stopMusic();
        gameState = playState;
    }
    public void startGameThread() {
        gameThread = new Thread(this); //instanciar a thread
        gameThread.start();
    }

    @Override
    /**
     * SLEEP METHOD
     */
/*    public void run() { //core of the game

        double drawInterval = 1000000000 / (double)FPS; //0.01666 sec
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){

            long currentTime = System.nanoTime(); // retorna o tempo em nanosegundos
            System.out.println("Current Time: " + currentTime);
            //long currentTime2 = System.currentTimeMillis();
            //System.out.println("The game loop is running");

            //TWO popular loop methods
            //SLEEP METHOD

            //1. UPDATE: update information such as character positions
            update();
            //2. DRAW: draw the screen with the updated information
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0){
                    remainingTime=0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    } */
    /**
     * DELTA METHOD
     */
    public void run() {
        double drawInterval = 1000000000 / (double)FPS; //0.01666 sec
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;

            timer += currentTime - lastTime;

            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;

                drawCount++;
            }
            if(timer >= 1000000000){
                System.out.println("FPS: "+drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
        //Caso o jogo esteja em pausa deixa de dar update
        if(gameState == playState) {
            //PLAYER
            player.update();
            //NPC
            for(int i=0; i< npc.length; i++){
                    if(npc[i] != null){
                        npc[i].update(); 
                    }
            }
        }
        if(gameState == pauseState){

        }
    }

    public void paintComponent(Graphics g){
        //super é obrigatório existir
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g; //graphics2d has a bit more funcions

        //DEBUG
        long drawStart = 0;
        if(keyH.checkDrawTime){
            drawStart = System.nanoTime();
        }

        //TILE
        tileM.draw(g2);

        //OBJECT
        for(int i=0; i<obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }

        //NPC
        for(int i=0; i<npc.length; i++){
            if(npc[i] != null){
                npc[i].draw(g2);
            }
        }

        //PLAYER
        player.draw(g2);

        //UI
        ui.draw(g2);

        //DEBUG
        if(keyH.checkDrawTime) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.BLACK);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }
        g2.dispose();
    }
    public void playMusic(int i){

        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){

        music.stop();
    }
    public void playSE(int i){

        se.setFile(i);
        se.play();
    }
}
