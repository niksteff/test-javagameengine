package Handler;

import gameReals.*;

public class EntityHandler {
	public static enum EntityTypes {
		GRAS, STONE, AIR, SKY;
	}

	public static Object GetEntityFromEnum(EntityTypes enumVal) {
		Object retObj = null;

		switch (enumVal) {
		case GRAS:
			retObj = new TextureTile(0, 0, 32, 32, "res/gras.png", "PNG", "gras", "TILE", 0); 
			break;
		case STONE:
			retObj = new TextureTile(0, 0, 32, 32, "res/stone.png", "PNG", "stone", "TILE", 0);
			break;
		case AIR:
			retObj = new TextureTile(0, 0, 32, 32, "res/air.png", "PNG", "air", "TILE", 0);
			break;
		case SKY:
			retObj = new TextureTile(0, 0, 32, 32, "res/sky.png", "PNG", "sky", "TILE", 0);
			break;
		default:
			// return nothing here
			break;
		}

		return retObj;
	}
}
