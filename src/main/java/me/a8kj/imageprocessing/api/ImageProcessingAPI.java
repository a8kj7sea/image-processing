package me.a8kj.imageprocessing.api;

import javax.imageio.ImageIO;

import me.a8kj.imageprocessing.processer.ImageProcessor;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class provides methods for processing images, including converting them to grayscale and applying custom processing.
 */
public class ImageProcessingAPI {

    /**
     * Applies a custom image processing algorithm to an input image and saves the result.
     * 
     * @param processor The {@link ImageProcessor} that defines the custom processing algorithm to apply to the image.
     * @param inputPath The path to the input image file.
     * @param outputPath The path where the processed image should be saved.
     * @throws IOException If an error occurs while reading or writing the image.
     */
    public static void applyAndSave(ImageProcessor processor, String inputPath, String outputPath) throws IOException {
        BufferedImage input = ImageIO.read(new File(inputPath)); // Read input image
        BufferedImage gray = toGrayscale(input); // Convert input image to grayscale
        BufferedImage result = processor.process(gray); // Apply custom processing
        ImageIO.write(result, "jpg", new File(outputPath)); // Save processed image
    }

    /**
     * Converts an image to grayscale using the luminance method.
     * 
     * @param img The input {@link BufferedImage} to be converted.
     * @return A new {@link BufferedImage} that represents the grayscale version of the input image.
     */
    private static BufferedImage toGrayscale(BufferedImage img) {
        BufferedImage gray = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                Color c = new Color(img.getRGB(x, y)); // Get the color of the current pixel
                int lum = (int) (0.299 * c.getRed() + 0.587 * c.getGreen() + 0.114 * c.getBlue()); // Calculate luminance
                int rgb = new Color(lum, lum, lum).getRGB(); // Convert luminance to grayscale color
                gray.setRGB(x, y, rgb); // Set the grayscale color to the output image
            }
        }
        return gray;
    }
}
