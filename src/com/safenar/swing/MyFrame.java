package com.safenar.swing;

import com.safenar.Main;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    public MyFrame() throws HeadlessException {
        super("Mobian Lang ver. "+ Main.version);
        JPanel myPanel=new MyPanel();
        add(myPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
