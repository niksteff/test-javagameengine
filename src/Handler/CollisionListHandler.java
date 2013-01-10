package Handler;

import java.util.LinkedList;
import java.util.List;

public class CollisionListHandler {

	private List<Object> _EntityLists;

	/**
	 * Constructor, just initializes the object
	 */
	public CollisionListHandler() {
		_EntityLists = new LinkedList<Object>();
	}

	/**
	 * Insert a list in the list of list ;)
	 * 
	 * @param List
	 *            <Object> list
	 * @return boolean - true for inserted false for exception
	 */
	public boolean InsertList(List<Object> list) {

		try {
			if (_EntityLists.add(list)) {
				return true;
			}
			return false;
		} catch (Exception e) {
			if (novelPacket.NovelPacket.isDebug()) {
				e.printStackTrace();
			}
			return false;
		}
	}

	/**
	 * Removes a specified list
	 * 
	 * @param List
	 *            <Object> list
	 * @return boolean - true for removed false for exception
	 */
	public boolean RemoveList(List<Object> list) {
		try {
			if (_EntityLists.remove(list)) {
				return true;
			}
			return false;
		} catch (Exception e) {
			if (novelPacket.NovelPacket.isDebug()) {
				e.printStackTrace();
			}
			return false;
		}
	}

}
