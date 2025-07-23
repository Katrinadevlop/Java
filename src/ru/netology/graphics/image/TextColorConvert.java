package ru.netology.graphics.image;

public class TextColorConvert implements TextColorSchema {
    private final char[] chars = {'#', '$', '@', '%', '*', '+', '-', '\''};

    @Override
    public char convert(int color) {
        int index = (int) ((color / 255.0) * (chars.length - 1));
        return chars[index];
    }
}
