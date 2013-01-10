package Handler;

import java.util.LinkedList;
import java.util.List;

import gameReals.Player;
import gameReals.Wall;
import gameReals.Mob;

public class CollisionHandler {

	CollisionListHandler _ListHandler;
	Player _Player;

	List<Wall> _Wall;
	List<Mob> _Mob;

	/**
	 * Constructor, initializes the object Requires a player object to be given
	 * at first initialization
	 * 
	 * @param Player
	 *            p
	 */
	public CollisionHandler(Player p) {
		this._ListHandler = new CollisionListHandler();
		this._Player = p;

		this._Wall = new LinkedList<Wall>();
		this._Mob = new LinkedList<Mob>();
	}

	/**
	 * checks the collision with the games player object Only! With the World
	 */
	public void CheckWorldWithPlayer() {
		for (Wall w : this._Wall) {

			if (w.get_BOUNDS().intersects(this._Player.get_BOUNDS())) {
				System.out.println("Collision...");
				
				// TODO implement collision logic here
			}

			/* TODO delete this...
			 * // Init the center points float wX = w.GetCenterX(); float wY =
			 * w.GetCenterY(); float pX = this._Player.GetCenterX(); float pY =
			 * this._Player.GetCenterY();
			 * 
			 * // TODO test for the y range and x range, too etc // Left bound
			 * 
			 * if (wX - (w.get_WIDTH() / 2) == pX + (this._Player.get_WIDTH() /
			 * 2)) { System.out.println("Collision! >"); } // Right Bound if (wX
			 * + (w.get_WIDTH() / 2) == pX - (this._Player.get_WIDTH() / 2)) {
			 * System.out.println("Collsion! <"); } // Top Bound if (wY -
			 * (w.get_HEIGHT() / 2) == pY + (this._Player.get_HEIGHT() / 2)) {
			 * System.out.println("Collsion! v"); } if (wY + (w.get_HEIGHT() /
			 * 2) == pY - (this._Player.get_HEIGHT() / 2)) {
			 * System.out.println("Collsion! ^"); }
			 */
		}
	}

	/**
	 * Checks the collision with the games player object Only! With the mobs
	 */
	public void CheckMobWithPlayer() {
		for (Mob m : this._Mob) {
			// TODO Check for collision here blabla
			
			if (m.get_BOUNDS().intersects(this._Player.get_BOUNDS())) {
				System.out.println("Collision...");
				
				// TODO implement collision logic here
			}
		}
	}

	/**
	 * Inserts a wall tile in the _Wall list
	 * 
	 * @param Wall
	 *            w
	 * @return boolean - true for success
	 */
	public boolean InsertWallTile(Wall w) {
		if (this._Wall.add(w)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Inserts a mob to the _Mob list
	 * 
	 * @param Mob
	 *            m
	 * @return boolean - true for success
	 */
	public boolean InsertMob(Mob m) {
		if (this._Mob.add(m)) {
			return true;
		} else {
			return false;
		}
	}

}
