package me.ranol.jnationspeech;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

public class DataResolver {

	public static InputStream resolveStream(String dir) {
		URL url = ClassLoader.getSystemResource(dir);
		if (url == null)
			return null;
		try {
			return url.openStream();
		} catch (Exception e) {
		}
		return null;
	}

	public static Font resolveFont(String dir) {
		InputStream resolve = resolveStream(dir);
		try {
			if (resolve != null)
				return Font.createFont(Font.TRUETYPE_FONT, resolve);
		} catch (Exception e) {
		}
		return null;
	}

	public static BufferedImage resolveImage(String dir) {
		InputStream resolve = resolveStream(dir);
		try {
			if (resolve != null)
				return ImageIO.read(resolve);
		} catch (Exception e) {
		}
		return null;
	}
}
