package me.ranol.jnationspeech;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class PictureBox extends Canvas {
	private static final long serialVersionUID = 1L;
	private Image image;

	public PictureBox(String dir) {
		this(Toolkit.getDefaultToolkit().createImage(dir));
	}

	public PictureBox() {
		image = null;
	}

	private PictureBox(Image image) {
		if (image != null)
			this.image = image;
	}

	public void setImage(Image newImage) {
		if (newImage != null)
			this.image = newImage;
		repaint();
	}

	public Image getImage() {
		return this.image;
	}

	@Override
	public void paint(Graphics g) {
		if (image != null) {
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}
	}
}
