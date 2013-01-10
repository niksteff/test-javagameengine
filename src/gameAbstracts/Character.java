package gameAbstracts;

import java.io.File;
import java.io.InputStream;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;

/**
 * 
 * @author DSteffen
 */
public abstract class Character {

	// State stuff
	protected String _TYPE = "";
	protected int _ID = 0;

	// Game mechanic
	protected int _LifePoints = 0;
	protected boolean _IsVulnerable = false;
	protected float _X = 0;
	protected float _Y = 0;
	protected Rectangle _BOUNDS;

	// OpenGL stuff
	private String _TEXTURESRC = ""; /*
									 * This should be like
									 * '../res/picture.filetype'
									 */
	protected int _HEIGHT = 0;
	protected int _WIDTH = 0;
	protected Texture _TEXTURE;
	protected InputStream _texFile;
	protected String _GraphicType = "GIF";

	/*
	 * Draws itself on the screen
	 * 
	 * @overwrite
	 * 
	 * @return void
	 */
	public abstract void Draw();

	protected abstract Texture LoadTexture();

	/**
	 * Returns the center point X of the entity
	 * 
	 * @return
	 */
	public float GetCenterX() {
		return this._X + (this._WIDTH / 2);
	}

	/**
	 * Returns the center point Y of the entity
	 * 
	 * @return
	 */
	public float GetCenterY() {
		return this._Y + (this._WIDTH / 2);
	}

	/**
	 * @return the _NAME
	 */
	public String get_NAME() {
		return _NAME;
	}

	/**
	 * @param _NAME
	 *            the _NAME to set
	 */
	public void set_NAME(String _NAME) {
		this._NAME = _NAME;
	}

	/**
	 * @return the _TYPE
	 */
	public String get_TYPE() {
		return _TYPE;
	}

	/**
	 * @param _TYPE
	 *            the _TYPE to set
	 */
	public void set_TYPE(String _TYPE) {
		this._TYPE = _TYPE;
	}

	/**
	 * @return the _ID
	 */
	public int get_ID() {
		return _ID;
	}

	/**
	 * @param _ID
	 *            the _ID to set
	 */
	public void set_ID(int _ID) {
		this._ID = _ID;
	}

	/**
	 * @return the _LifePoints
	 */
	public int get_LifePoints() {
		return _LifePoints;
	}

	/**
	 * @param _LifePoints
	 *            the _LifePoints to set
	 */
	public void set_LifePoints(int _LifePoints) {
		this._LifePoints = _LifePoints;
	}

	/**
	 * @return the _IsVulnerable
	 */
	public boolean is_IsVulnerable() {
		return _IsVulnerable;
	}

	/**
	 * @param _IsVulnerable
	 *            the _IsVulnerable to set
	 */
	public void set_IsVulnerable(boolean _IsVulnerable) {
		this._IsVulnerable = _IsVulnerable;
	}

	protected String _NAME = "";

	/**
	 * @return the _TEXTURESRC
	 */
	public String get_TEXTURESRC() {
		return _TEXTURESRC;
	}

	/**
	 * @param _TEXTURESRC
	 *            the _TEXTURESRC to set
	 */
	public void set_TEXTURESRC(String _TEXTURESRC) {
		this._TEXTURESRC = _TEXTURESRC;
	}

	/**
	 * @return the _HEIGHT
	 */
	public int get_HEIGHT() {
		return _HEIGHT;
	}

	/**
	 * @param _HEIGHT
	 *            the _HEIGHT to set
	 */
	public void set_HEIGHT(int _HEIGHT) {
		this._HEIGHT = _HEIGHT;
	}

	/**
	 * @return the _WIDTH
	 */
	public int get_WIDTH() {
		return _WIDTH;
	}

	/**
	 * @param _WIDTH
	 *            the _WIDTH to set
	 */
	public void set_WIDTH(int _WIDTH) {
		this._WIDTH = _WIDTH;
	}

	/**
	 * @return the _TEXTURE
	 */
	public Texture get_TEXTURE() {
		return _TEXTURE;
	}

	/**
	 * @param _TEXTURE
	 *            the _TEXTURE to set
	 */
	public void set_TEXTURE(Texture _TEXTURE) {
		this._TEXTURE = _TEXTURE;
	}

	/**
	 * @return the _GraphicType
	 */
	public String get_GraphicType() {
		return _GraphicType;
	}

	/**
	 * @param _GraphicType
	 *            the _GraphicType to set
	 */
	public void set_GraphicType(String _GraphicType) {
		this._GraphicType = _GraphicType;
	}

	/**
	 * @return the _X
	 */
	public float get_X() {
		return _X;
	}

	/**
	 * @param _X
	 *            the _X to set
	 */
	public void set_X(float _X) {
		this._X = _X;
	}

	/**
	 * @return the _Y
	 */
	public float get_Y() {
		return _Y;
	}

	/**
	 * @param _Y
	 *            the _Y to set
	 */
	public void set_Y(float _Y) {
		this._Y = _Y;
	}

	/**
	 * @return the _BOUNDS
	 */
	public Rectangle get_BOUNDS() {
		return _BOUNDS;
	}

	/**
	 * @param _BOUNDS the _BOUNDS to set
	 */
	public void set_BOUNDS(Rectangle _BOUNDS) {
		this._BOUNDS = _BOUNDS;
	}

}
