package com.example;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Load
{
	private static final int MAX_RETRIES = 3;

	public static Image squareImage(String imagePath, int requiredSize) {
		Image image = null;
		boolean imageLoaded = false;
		int retryCount = 0;

		while (!imageLoaded && retryCount < MAX_RETRIES) {
			try	{
				BufferedImage bufferedImage = ImageIO.read(new File(imagePath));
				image = bufferedImage.getScaledInstance(requiredSize, requiredSize, Image.SCALE_SMOOTH);
				imageLoaded = true;
			} catch (IOException e)	{
				retryCount++;
			}
		}

		if (!imageLoaded) {
			System.err.println("Failed to load the image after multiple attempts.");
		}

		return image;
	}
}
