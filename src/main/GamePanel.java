package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    // Screen Settings
    final int originalTileSize = 16; // 16x16 tiles
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // Scale the tiles to 48x48

    public final int maxScreenCol = 15;
    public final int maxScreenRow = 10;

    public final int screenWidth = tileSize * maxScreenCol; // 720 pixels wide
    public final int screenHeight = tileSize * maxScreenRow; // 480 pixels tall

    // World Settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 40;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;	
    TileManager tileM = new TileManager(this);
    
    public Player player = new Player(this, keyH);
    public AssetSetter aSetter = new AssetSetter(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public SuperObject obj[] = new SuperObject[5];

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    
    public void setupGame() {
    	aSetter.setObject();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; // 0.01666 seconds
        double delta = 0;

        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        @SuppressWarnings("unused")
		int drawCount = 0;

        // Game loop
        while(gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        // Tiles
        tileM.draw(g2);
        
        // Objects
        for (int i = 0; i < obj.length; i++) {
        	if (obj[i] != null) {
        		obj[i].draw(g2, this);
        	}
        }
        
        // Player
        player.draw(g2);

        g2.dispose();
    }

}
