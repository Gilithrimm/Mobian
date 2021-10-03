package com.safenar.swing;

import com.safenar.Main;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    private static final JTextArea PRINT_AREA =new JTextArea();

    public MyPanel() {
        super();
        display();
    }
    private void display(){
        PRINT_AREA.setBackground(Color.DARK_GRAY);
        PRINT_AREA.setCaretColor(Color.WHITE);
        PRINT_AREA.setForeground(Color.WHITE);
        PRINT_AREA.setFont(new Font(Font.MONOSPACED, Font.PLAIN,12));
        PRINT_AREA.setSize(Main.frame[0].getSize());
        this.add(PRINT_AREA);
    }
    //KeyListener?
}
