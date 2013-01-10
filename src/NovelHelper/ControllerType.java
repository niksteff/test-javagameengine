package NovelHelper;

/**
 * 
 * @author DSteffen
 */
public class ControllerType {
	private CONTROLLER_TYPE mainGamepadType;

	private enum CONTROLLER_TYPE {
		XBOX360, GENERIC, NONE
	}

	public void ControllerType() {
		this.mainGamepadType = CONTROLLER_TYPE.NONE;
	}

	public void SetXBOX360() {
		this.mainGamepadType = CONTROLLER_TYPE.XBOX360;
	}

	public void SetGENERIC() {
		this.mainGamepadType = CONTROLLER_TYPE.GENERIC;
	}

	public CONTROLLER_TYPE GetGamepadType() {
		return this.mainGamepadType;
	}

	public CONTROLLER_TYPE GetGamepad360Type() {
		return CONTROLLER_TYPE.XBOX360;
	}
}
