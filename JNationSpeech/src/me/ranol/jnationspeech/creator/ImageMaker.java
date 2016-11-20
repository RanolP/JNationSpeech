package me.ranol.jnationspeech.creator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageMaker {
	private static String layer = "images\\layer.png";
	private static Font font;
	private static Color colorLeft = Color.decode(Integer.parseInt("3D4E7D", 16) + "");
	static {
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts\\SpoqaHanSans-bold.ttf"));
		} catch (Exception e) {
			font = new Font("한고딕", Font.PLAIN, 15);
			e.printStackTrace();
		}
	}

	private ImageMaker() {
	}

	public static BufferedImage make(String dir, String left, String center) throws Exception {
		BufferedImage bg;
		if (dir == null)
			bg = new BufferedImage(1280, 720, BufferedImage.TYPE_INT_RGB);
		else {
			bg = ImageIO.read(new File(dir));
		}
		if (dir != null && (bg.getWidth() % 16 != 0 || bg.getHeight() % 9 != 0)) {
			int rw = bg.getWidth();
			int rh;
			if (rw < 640) {
				rw = 640;
				rh = 360;
			} else if (rw < 960) {
				rw = 960;
				rh = 540;
			} else if (rw < 1280) {
				rw = 1280;
				rh = 720;
			} else {
				rw -= rw % 16;
				rh = rw * 9 / 16;
			}
			if (dir.matches(".+\\.(jpg|jpeg)"))
				bg = resizeJpg(dir, rw, rh);
			else
				bg = resize(dir, rw, rh);
		}
		int w = bg.getWidth();
		int h = bg.getHeight();
		Graphics2D bgGraphics = bg.createGraphics();
		bgGraphics.drawImage(bg, 0, 0, null);
		BufferedImage bi = resize(layer, w, h);
		bgGraphics.drawImage(bi, 0, 0, null);

		BufferedImage lImg = new BufferedImage(left.length() * 28, h / 17, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D lGraphic = lImg.createGraphics();
		lGraphic.setFont(font.deriveFont(w / 80.0f));
		lGraphic.setColor(colorLeft);
		lGraphic.drawString(left, 0, lImg.getHeight() - h / 180);
		lGraphic.dispose();
		bgGraphics.drawImage(resize(lImg, (int) (w * 0.5), (int) (h * 0.1)), (int) (w * 0.05), (int) (h * 0.06), null);

		BufferedImage cImg = new BufferedImage(center.length() * 25, h / 20, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D cGraphic = cImg.createGraphics();
		cGraphic.setFont(font.deriveFont(w / 40.0f));
		cGraphic.setColor(Color.WHITE);
		cGraphic.drawString(center, 0, cImg.getHeight() - h / 90);
		cGraphic.dispose();
		bgGraphics.drawImage(resize(cImg, (int) (w * 0.77), (int) (h * 0.1)), (int) (w * 0.13), (int) (h * 0.8), null);
		bgGraphics.dispose();
		return bg;
	}

	static BufferedImage resize(String dir, int w, int h) throws IOException, InterruptedException {
		BufferedImage img = ImageIO.read(new File(dir));
		return resize(img, w, h);
	}

	static BufferedImage resize(Image img, int w, int h) throws IOException, InterruptedException {
		Image grapOnly = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		int[] pixels = new int[w * h];
		PixelGrabber pg = new PixelGrabber(grapOnly, 0, 0, w, h, pixels, 0, w);
		pg.grabPixels();
		BufferedImage result = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR);
		result.setRGB(0, 0, w, h, pixels, 0, w);
		return result;
	}

	static BufferedImage resizeJpg(String dir, int w, int h) throws IOException, InterruptedException {
		Image grapOnly = new ImageIcon(dir).getImage();
		return resize(grapOnly, w, h);
	}
}
