package com.safenar.swing;

import java.awt.*;

public class FontOption extends CmdOption{
    private int size;
    private Font font;
    private String fontName;
    private int fontStyle;

    public FontOption(String name, String desc, int size, String fontName, int fontStyle) {
        super(name, desc);
        this.size = size;
        this.fontName = fontName;
        this.fontStyle = fontStyle;
    }

    public FontOption(String name, String desc, Font font) {
        super(name, desc);
        this.font = font;
    }

    public FontOption(String name, String desc) {
        super(name, desc);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public int getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
    }
}
