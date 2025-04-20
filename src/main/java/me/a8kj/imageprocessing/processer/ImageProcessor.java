package me.a8kj.imageprocessing.processer;

import java.awt.image.BufferedImage;

/**
 * An interface for image processing algorithms.
 * <p>
 * Implementing classes must provide a method to process a {@link BufferedImage}
 * and return the processed version.
 * </p>
 *
 * This interface allows different image enhancement algorithms to be
 * used interchangeably through polymorphism.
 */
public interface ImageProcessor {

    /**
     * Applies the image processing algorithm to the input image.
     *
     * @param input the input grayscale image to be processed.
     * @return the processed image as a {@link BufferedImage}.
     */
    BufferedImage process(BufferedImage input);
}
