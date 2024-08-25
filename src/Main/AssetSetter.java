package Main;

import Entity.NPC_OldMan;
import Object.OBJ_Key;
import Object.OBJ_Door;
import Object.OBJ_Chest;
import Object.OBJ_Boots;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){

    }

    public void setNpc(){

        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize*21;
        gp.npc[0].worldY = gp.tileSize*21;
    }
}
