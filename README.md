
# 📷 Image Enhancement Algorithms in Java

This project demonstrates two fundamental **image enhancement** algorithms implemented using **Java and Polymorphism**:

- `Contrast Stretching`
- `Histogram Equalization`

These techniques aim to improve the **visual quality** of grayscale images by modifying pixel intensity distributions.

---

## 🔧 How It Works

We use a shared interface:

```java
public interface ImageProcessor {
    BufferedImage process(BufferedImage input);
}
```

Each algorithm implements this interface and processes a grayscale image accordingly.

---

## 🧪 Algorithms

### 1. Contrast Stretching

This method increases the dynamic range of pixel intensities by stretching them linearly across the full range `[0, 255]`.

- **Formula:**

```
new_pixel = (pixel - min) * 255 / (max - min)
```

- Improves contrast when image pixels are clustered in a narrow intensity range.

---

### 2. Histogram Equalization

This method redistributes pixel intensities to produce a more uniform histogram (i.e., balanced contrast across the image).

- **Steps:**
  1. Calculate histogram.
  2. Compute CDF (Cumulative Distribution Function).
  3. Normalize using:

     ```
     new_pixel = round((CDF(pixel) - CDF_min) / (total_pixels - CDF_min) * 255)
     ```

- Useful when the image has uneven brightness or lacks detail.

---

## ⚖️ Comparison Table

| Feature                     | Contrast Stretching                      | Histogram Equalization                 |
|----------------------------|------------------------------------------|----------------------------------------|
| 🔍 Goal                    | Expand contrast range                    | Redistribute intensity levels          |
| 🧮 Formula                 | Linear mapping based on min & max        | Non-linear mapping using CDF           |
| 🟢 Best For                | Images with poor contrast                | Images with uneven intensity/brightness|
| ⚙️ Complexity              | Simple (linear)                          | Moderate (requires histogram + CDF)    |
| 💡 Output Style            | Preserves structure with more contrast   | Enhances global contrast               |
| 🎯 Risk of Over-enhancement| Low                                      | Moderate (can cause over-saturation)   |

---

## 🚀 Usage

1. Add your image to the root directory as `input.jpg`.

2. Choose algorithm:
```java
ImageProcessor processor = new ContrastStretchingProcessor(); 
// OR
ImageProcessor processor = new HistogramEqualizationProcessor();
```

3. Run the program:
```bash
java Main
```

4. Output saved as `output.jpg`.

---

## 📂 Project Structure

```
src/
│
├── Main.java
├── ImageProcessor.java
├── ContrastStretchingProcessor.java
├── HistogramEqualizationProcessor.java
├── ImageProcessingAPI.java
```

---

## 🧠 Future Work

- GUI interface using JavaFX/Swing.
- Color image support.
- Web API using Spring Boot.

---


