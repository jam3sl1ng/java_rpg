package main;

import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Sign;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new OBJ_Sign();
		gp.obj[0].worldX = 26 * gp.tileSize;
		gp.obj[0].worldY = 17 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Sign();
		gp.obj[1].worldX = 33 * gp.tileSize;
		gp.obj[1].worldY = 26 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Key();
		gp.obj[2].worldX = 13 * gp.tileSize;
		gp.obj[2].worldY = 16 * gp.tileSize;
		
		gp.obj[3] = new OBJ_Door();
		gp.obj[3].worldX = 28 * gp.tileSize;
		gp.obj[3].worldY = 16 * gp.tileSize;
		
		gp.obj[4] = new OBJ_Door();
		gp.obj[4].worldX = 35 * gp.tileSize;
		gp.obj[4].worldY = 25 * gp.tileSize;
	}
	
}
