package image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DrawImage {
    public static void main(String[] args) throws IOException {
        DrawImage drawImage = new DrawImage();
        drawImage.drawImage(80, 40, "Hello", "E:\\my_desktop\\JavaCode\\java_web\\BMS_login\\test\\image\\1.png");
    }

    public void drawImage(int width, int height, String code, String outFile) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g2d = image.getGraphics();

        Font font = new Font("Consolas", Font.BOLD | Font.ITALIC, 20);
        g2d.setFont(font);
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.RED);
        g2d.drawString(code, 20, 15);

        try (FileOutputStream outputStream = new FileOutputStream(outFile)) {
            ImageIO.write(image, "png", outputStream);
        }

    }
}
