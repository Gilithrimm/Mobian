package com.safenar.swing;

import com.safenar.Main;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    static final GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
    static final Rectangle frameSize=ge.getMaximumWindowBounds();
    public MyFrame() throws HeadlessException {
        super("Mobian Lang ver. "+ Main.version);
        setLayout(new FlowLayout());//2.WTF 3.go [...] urself if u're not working
        getContentPane().setBackground(Color.darkGray);//1.not working, stackoverflow sucks 4.where tf did that gray shit come from?
        MyPanel myPanel=new MyPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);//fullscreen
        setSize(frameSize.getSize());
        setVisible(true);
        add(myPanel);
    }

}
