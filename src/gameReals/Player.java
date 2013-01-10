package gameReals;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import gameAbstracts.Character;

/**
 * 
 * @author DSteffen This class is the player character
 */
public class Player extends Character {
	/**
	 * This is the SPECIFIC constructor for the player character
	 * 
	 * @param name
	 *            This is the objects name
	 * @param type
	 *            This is the type of the character
	 * @param id
	 *            This is the players id if more then one occurs
	 * @param isVul
	 *            Specifies if the character can be hurt
	 * @param lifePoints
	 *            The lifepoints the character has
	 * @param width
	 *            the width of the texture
	 * @param height
	 *            the height of the texture
	 * @param textSrc
	 *            location of the texture file
	 */
	public Player(String name, String type, int id, boolean isVul, int lifePoints, int width, int height, String textSrc, String gType, int x, int y) {
		set_NAME(name);
		set_ID(id);
		set_LifePoints(lifePoints);
		set_TYPE(type);
		set_IsVulnerable(isVul);
		set_HEIGHT(height);
		set_WIDTH(width);
		set_TEXTURESRC(textSrc);
		set_GraphicType(gType.toUpperCase());
		set_X(x);
		set_Y(y);
		set_BOUNDS(new Rectangle((int) this._X, (int) this._Y, this._WIDTH, this._HEIGHT));

		try {
			_texFile = new FileInputStream(new File(get_TEXTURESRC()));
		} catch (FileNotFoundException e) {
			if (novelPacket.NovelPacket.isDebug()) {
				e.printStackTrace();
			}
		}

		set_TEXTURE(LoadTexture());
		int tid = get_TEXTURE().getTextureID();
		glBindTexture(GL_TEXTURE_2D, tid);

		System.out.println("Texture " + get_TEXTURESRC() + " loaded. ID: " + tid);
	}

	@Override
	protected Texture LoadTexture() {
		try {
			Texture t = TextureLoader.getTexture(get_GraphicType(), _texFile);
			return t;
		} catch (IOException e) {
			if (novelPacket.NovelPacket.isDebug()) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public void Draw() {
		// Bounds first
		this._BOUNDS.setX((int)this.get_X());
		this._BOUNDS.setY((int)this.get_Y());
		
		_TEXTURE.bind();
		glBegin(GL_QUADS);
		{
			glTexCoord2f(0f, 0f);
			glVertex2f(get_X(), get_Y());

			glTexCoord2f(1f, 0f);
			glVertex2f((get_X() + get_WIDTH()), get_Y());

			glTexCoord2f(1f, 1f);
			glVertex2f((get_X() + get_WIDTH()), (get_Y() + get_HEIGHT()));

			glTexCoord2f(0f, 1f);
			glVertex2f(get_X(), (get_Y() + get_HEIGHT()));
		}
		glEnd();
	}
}