package com.safenar.swing;

import com.safenar.Main;

import javax.swing.*;
import java.awt.*;

import static com.safenar.swing.MyFrame.frameSize;

public class MyPanel extends JPanel {
    private static final JTextArea PRINT_AREA =new JTextArea();
    public static Font mono=new Font(Font.MONOSPACED, Font.PLAIN,12);

    public MyPanel() {
        super();
        display();
    }
    private void display(){
        PRINT_AREA.setBackground(Color.BLACK);
        PRINT_AREA.setCaretColor(Color.WHITE);
        PRINT_AREA.setForeground(Color.WHITE);
        PRINT_AREA.setFont(mono);
        PRINT_AREA.setSize(frameSize.getSize());
        Main.println(frameSize.getSize());
        this.add(PRINT_AREA);
    }
    //KeyListener?
}
