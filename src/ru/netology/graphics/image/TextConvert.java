package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;

public class TextConvert implements TextGraphicsConverter {

    private int maxWidth = Integer.MAX_VALUE;
    private int maxHeight = Integer.MAX_VALUE;
    private double maxRatio = Double.MAX_VALUE;
    private TextColorSchema schema = new TextColorConvert();
    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        BufferedImage img = ImageIO.read(new URL(url));

        int originalWidth = img.getWidth();
        int originalHeight = img.getHeight();
        double imageRatio = (double) originalWidth / originalHeight;
        double imageRatios = (double)originalHeight / originalWidth ;
        if (imageRatio > maxRatio && imageRatios > maxRatio){
            throw new BadImageSizeException(imageRatio, maxRatio);
        }

        double scaleW = (double) originalWidth / maxWidth;
        double scaleH = (double) originalHeight / maxHeight;
        double scale = Math.max(scaleW, scaleH);
        if (scale < 1) scale = 1;

        int newWidth = (int) (originalWidth / scale);
        int newHeight = (int) (originalHeight / scale);

        Image scaledImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage bwImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = bwImg.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        WritableRaster bwRaster = bwImg.getRaster();
        int[] arrayInt = new int[3];

        char[][] arrayImage = new char[newHeight][newWidth];
        for (int j = 0; j < newHeight; j++) {
            for (int i = 0; i < newWidth; i++) {
                int color = bwRaster.getPixel(i, j, arrayInt)[0];
                char c = schema.convert(color);
                arrayImage[j][i] = c;
            }
        }

        StringBuilder stringImage = new StringBuilder();
        for (int j = 0; j < newHeight; j++) {
            for (int i = 0; i < newWidth; i++) {
                stringImage.append(arrayImage[j][i]).append(arrayImage[j][i]);
            }
            stringImage.append("\n");
        }

        return stringImage.toString();
    }

    @Override
    public void setMaxWidth(int width) {
        maxWidth = width;
    }

    @Override
    public void setMaxHeight(int height) {
        maxHeight = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;
    }
}
