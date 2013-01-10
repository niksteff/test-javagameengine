package NovelHelper;

public class OSHelper {

	private static String _OSTYPE = null;

	private static final String _win = "WINDOWS";
	private static final String _lin = "LINUX";
	private static final String _mac = "MACOSX";
	private static final String _def = "DEFAULT";

	private static final String _eqWIN = "win";
	private static final String _eqLIN = "nux";
	private static final String _eqMAC = "mac";

	/**
	 * Determines the OS Type
	 * Returns a String for the OS Type e.g. WINDOWS, LINUX, MACOSX
	 * @return String OSTYPE
	 */
	public static String GetOSType() {
		if (_OSTYPE == null) {
			_OSTYPE = DetermineOS();
		}
		return _OSTYPE;
	}

	/**
	 * Checks for the OS the program is running on
	 * 
	 * @return String constant for the OS
	 */
	private static String DetermineOS() {
		String res = null;

		if (IsWin()) {
			res = _win;
		} else if (IsLinux()) {
			res = _lin;
		} else if (IsMac()) {
			res = _mac;
		} else {
			res = _def;
		}

		return res;
	}

	/**
	 * Checks for Windows
	 * 
	 * @return boolean
	 */
	private static boolean IsWin() {
		int wind = System.getProperty("os.name").toLowerCase().indexOf(_eqWIN);
		if (wind != -1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks for Linux
	 * 
	 * @return boolean
	 */
	private static boolean IsLinux() {
		int wind = System.getProperty("os.name").toLowerCase().indexOf(_eqLIN);
		if (wind != -1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks for Mac OS X
	 * 
	 * @return boolean
	 */
	private static boolean IsMac() {
		int wind = System.getProperty("os.name").toLowerCase().indexOf(_eqMAC);
		if (wind != -1) {
			return true;
		} else {
			return false;
		}
	}
}
