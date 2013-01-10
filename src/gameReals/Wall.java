package gameReals;

import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import gameAbstracts.Entity;

public class Wall extends Entity {
	/**
	 * Constructor
	 */
	public Wall(int x, int y, int width, int height, String textSrc, String gType, String name, String type, int id) {
		set_NAME(name);
		set_ID(id);
		set_TYPE(type);
		set_HEIGHT(height);
		set_WIDTH(width);
		set_TEXTURESRC(textSrc);
		set_GraphicType(gType.toUpperCase());
		set_X(x);
		set_Y(y);
		// Set the collision bounds for the object
		set_BOUNDS(new Rectangle((int)this._X, (int)this._Y, this._WIDTH, this._HEIGHT));

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

		System.out.println("Texture " + get_TEXTURESRC() + " loaded. ID: "
				+ tid);
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
		_TEXTURE.bind();
		glBegin(GL_QUADS);
		{
			glTexCoord2f(0f, 0f);
			glVertex2f(get_X(), get_Y());

			glTexCoord2f(1f, 0f);
			glVertex2f(get_X() + get_WIDTH(), get_Y());

			glTexCoord2f(1f, 1f);
			glVertex2f(get_X() + get_WIDTH(), get_Y() + get_HEIGHT());

			glTexCoord2f(1f, 0f);
			glVertex2f(get_X(), get_Y() + get_HEIGHT());
		}
		glEnd();
	}

	@Override
	public Wall InitializeTile(int x, int y, int width, int height) {
		set_Y(y);
		set_X(x);
		set_WIDTH(width);
		set_HEIGHT(height);
		
		return this;
	}

}
