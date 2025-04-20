package me.a8kj.imageprocessing;

import me.a8kj.imageprocessing.api.ImageProcessingAPI;
import me.a8kj.imageprocessing.processer.ImageProcessor;
import me.a8kj.imageprocessing.processer.impl.HistogramEqualizationProcessor;

/**
 * The main entry point of the image processing application.
 * <p>
 * This class allows you to apply a selected image enhancement algorithm
 * (e.g., Histogram Equalization or Contrast Stretching) to an input image
 * and save the processed result to a file.
 * </p>
 *
 * Example usage:
 * 
 * <pre>
 *     java Main
 * </pre>
 */
public class Main {

    /**
     * The main method that runs the image processing operation.
     *
     * @param args Command-line arguments (not used in this version).
     * @throws Exception if an error occurs during image reading or writing.
     */
    public static void main(String[] args) throws Exception {
        // Choose the image processor to apply:
        ImageProcessor processor = new HistogramEqualizationProcessor();
        // Apply the processor to "input.jpg" and save the result to "output.jpg":
        ImageProcessingAPI.applyAndSave(processor, "input.jpg", "output.jpg");
    }
}
