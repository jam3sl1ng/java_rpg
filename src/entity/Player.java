package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
		
		solidArea = new Rectangle();
		solidArea.x = 9;
		solidArea.y = 16;
		solidArea.width = 30;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 24;
		worldY = gp.tileSize * 20;
		speed = 4;
		direction = "down";
		idleDirection = "down";
	}
	
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/move_up_0.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/move_up_1.png"));
			
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/move_down_0.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/move_down_1.png"));
			
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/move_left_0.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/move_left_1.png"));
			
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/move_right_0.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/move_right_1.png"));
			
			idleUp = ImageIO.read(getClass().getResourceAsStream("/player/idle_up.png"));
			
			idleDown = ImageIO.read(getClass().getResourceAsStream("/player/idle_down.png"));
			
			idleLeft = ImageIO.read(getClass().getResourceAsStream("/player/move_left_0.png"));
			
			idleRight = ImageIO.read(getClass().getResourceAsStream("/player/move_right_0.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
			if (keyH.upPressed) {
				direction = "up";
				idleDirection = "up";
			} else if (keyH.downPressed) {
				direction = "down";
				idleDirection = "down";
			} else if (keyH.leftPressed) {
				direction = "left";
				idleDirection = "left";
			} else if (keyH.rightPressed) {
				direction = "right";
				idleDirection = "right";
			}
			
			// Check tile collision
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			// If collision is false, player can move
			if (collisionOn == false) {
				switch (direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
			
			spriteCounter++;
			if (spriteCounter > 12) {
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		} 
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
			switch (direction) {
			case "up":
				if (spriteNum == 1) {
					image = up1;
				} else if (spriteNum == 2) {
					image = up2;
				}
				break;
			case "down":
				if (spriteNum == 1) {
					image = down1;
				} else if (spriteNum == 2) {
					image = down2;
				}
				break;
			case "left":
				if (spriteNum == 1) {
					image = left1;
				} else if (spriteNum == 2) {
					image = left2;
				}
				break;
			case "right":
				if (spriteNum == 1) {
					image = right1;
				} else if (spriteNum == 2) {
					image = right2;
				}
				break;
			}
		} else {
			switch (idleDirection) {
			case "up":
				image = idleUp;
				break;
			case "down":
				image = idleDown;
				break;
			case "left":
				image = idleLeft;
				break;
			case "right":
				image = idleRight;
				break;
			}
		}
		
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
	
}
