package old.safenar.swing;

import java.awt.*;

public class ColorTheme {
    private Color inputBg;
    private Color outputBg;
    private Color text;
    private Color caret;

    public ColorTheme(Color inputBg, Color outputBg,Color text, Color caret) {
        this.inputBg = inputBg;
        this.outputBg = outputBg;
        this.text = text;
        this.caret = caret;
    }

    public Color getInputBg() {
        return inputBg;
    }

    public void setInputBg(Color inputBg) {
        this.inputBg = inputBg;
    }

    public Color getOutputBg() {
        return outputBg;
    }

    public void setOutputBg(Color outputBg) {
        this.outputBg = outputBg;
    }

    public Color getText() {
        return text;
    }

    public void setText(Color text) {
        this.text = text;
    }

    public Color getCaret() {
        return caret;
    }

    public void setCaret(Color caret) {
        this.caret = caret;
    }
}
