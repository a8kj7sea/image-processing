package me.a8kj.imageprocessing.processer.impl;

import java.awt.image.BufferedImage;
import me.a8kj.imageprocessing.processer.ImageProcessor;
import java.awt.Color;

/**
 * This class implements the {@link ImageProcessor} interface and performs contrast stretching on an image.
 * Contrast stretching enhances the contrast of the input image by stretching the pixel intensity values
 * to utilize the full range of possible values (0-255).
 */
public class ContrastStretchingProcessor implements ImageProcessor {

    /**
     * Processes the given image by applying contrast stretching.
     * The contrast stretching formula used is: 
     * <pre>
     * new = (val - min) * 255 / (max - min)
     * </pre>
     * where 'min' and 'max' are the minimum and maximum pixel values in the input image.
     * 
     * @param image The input {@link BufferedImage} to be processed.
     * @return A new {@link BufferedImage} with contrast stretching applied.
     */
    @Override
    public BufferedImage process(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        int min = 255, max = 0;

        // Get min & max pixel values
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                int val = new Color(image.getRGB(x, y)).getRed(); // Get the red channel value of the pixel
                if (val < min) min = val; // Update the minimum value
                if (val > max) max = val; // Update the maximum value
            }

        // Stretch formula: new = (val - min) * 255 / (max - min)
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                int val = new Color(image.getRGB(x, y)).getRed(); // Get the red channel value of the pixel
                int stretched = (val - min) * 255 / (max - min); // Apply contrast stretching formula
                int rgb = new Color(stretched, stretched, stretched).getRGB(); // Create a new color with stretched intensity
                output.setRGB(x, y, rgb); // Set the new color to the output image
            }

        return output;
    }
}
