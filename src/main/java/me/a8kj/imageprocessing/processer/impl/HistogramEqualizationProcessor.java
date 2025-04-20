package me.a8kj.imageprocessing.processer.impl;

import java.awt.image.BufferedImage;
import me.a8kj.imageprocessing.processer.ImageProcessor;
import java.awt.Color;

/**
 * This class implements the {@link ImageProcessor} interface and performs histogram equalization on an image.
 * Histogram equalization improves the contrast of an image by redistributing pixel intensities across the full range of values (0-255).
 */
public class HistogramEqualizationProcessor implements ImageProcessor {

    /**
     * Processes the given image by applying histogram equalization.
     * Histogram equalization works by creating a histogram of the pixel intensities in the image,
     * computing the cumulative distribution function (CDF), and then applying a lookup table (LUT) to 
     * adjust the pixel intensities to the full range of values.
     * 
     * @param image The input {@link BufferedImage} to be processed.
     * @return A new {@link BufferedImage} with histogram equalization applied.
     */
    @Override
    public BufferedImage process(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int[] histogram = new int[256];
        int[] cdf = new int[256];
        int[] lut = new int[256];

        // Generate histogram of pixel intensities (only the red channel is considered for grayscale images)
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                histogram[new Color(image.getRGB(x, y)).getRed()]++;

        // Calculate the cumulative distribution function (CDF)
        cdf[0] = histogram[0];
        for (int i = 1; i < 256; i++)
            cdf[i] = cdf[i - 1] + histogram[i];

        // Find the minimum CDF value that is not zero
        int cdfMin = 0;
        for (int i = 0; i < 256; i++) {
            if (cdf[i] != 0) {
                cdfMin = cdf[i];
                break;
            }
        }

        // Calculate the lookup table (LUT) for histogram equalization
        int total = width * height;
        for (int i = 0; i < 256; i++) {
            lut[i] = Math.round(((float) (cdf[i] - cdfMin) / (total - cdfMin)) * 255);
        }

        // Apply the LUT to the image to adjust pixel values
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                int oldVal = new Color(image.getRGB(x, y)).getRed();
                int newVal = lut[oldVal]; // Map old pixel value to new value using the LUT
                int rgb = new Color(newVal, newVal, newVal).getRGB(); // Set the new pixel value
                output.setRGB(x, y, rgb);
            }

        return output;
    }
}
