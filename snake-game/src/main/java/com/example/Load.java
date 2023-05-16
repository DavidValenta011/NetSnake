package com.example;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Load
{
	private static final int MAX_RETRIES = 3;

	/**
	 * 
	 * @param imagePath
	 * @param requiredSize
	 * @return
	 */
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
	
	/**
	 * 
	 * @param soundPath
	 * @return
	 */
	public static Clip sound(String soundPath) {
		Clip clip = null;
		boolean soundLoaded = false;
	    int maxRetries = 3;
	    int retryCount = 0;

	    while (!soundLoaded && retryCount < maxRetries) {
	        try {
	            // Load the sound file
	            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundPath));
	            
	            // Get a Clip instance
	            clip = AudioSystem.getClip();
	            
	            // Open the audio stream
	            clip.open(audioInputStream);
	            
	            // Close the audio stream
	            audioInputStream.close();
	            
	            soundLoaded = true; // Set the flag to true if the sound is loaded successfully
	        } catch (Exception e) {
	            System.out.println("Failed to load sound: " + e.getMessage());
	            e.printStackTrace();
	            retryCount++;
	        }
	    }

	    if (!soundLoaded) {
	        System.out.println("Failed to load the sound after multiple attempts.");
	    }        
	    
	    return clip;
    }
}
