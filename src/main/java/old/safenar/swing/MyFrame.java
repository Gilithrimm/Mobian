package old.safenar.swing;

import old.safenar.Main;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    static final GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
    static final Rectangle frameSize=ge.getMaximumWindowBounds();
    public MyFrame() throws HeadlessException {
        super("Mobian Lang ver. "+ Main.version);
        setJMenuBar(new JMenuBar());
        makeMenuBar(getJMenuBar());
        setLayout(new BorderLayout());//2.WTF 3.go [...] yourself if you're not working
        getContentPane().setBackground(Color.darkGray);//1.not working, stackoverflow sucks 4.where tf did that gray shit come from?
        MyPanel myPanel=new MyPanel();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);//fullscreen
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        add(myPanel);
    }
    private void makeMenuBar(JMenuBar mBar){
        JMenu file=new JMenu("File");
        JMenuItem[] fileItems=new JMenuItem[4];
//        file.add("Clear");
        fileItems[0]=file.add("Open");
        fileItems[1]=file.add("Save");
        file.addSeparator();
        fileItems[2]=file.add("Settings");
        fileItems[3]=file.add("Exit");
        fileItems[3].addActionListener(e -> System.exit(0));
        mBar.add(file);
        JMenu edit=new JMenu("Edit");
        edit.add("Undo");
        edit.add("Redo");
        edit.addSeparator();
        edit.add("Copy");
        edit.add("Cut");
        edit.add("Paste");
        edit.add("Delete");
        edit.add("Find");
        mBar.add(edit);
        JMenu view= new JMenu("View");
        view.add(new JCheckBoxMenuItem("Fullscreen"));
        JMenu theme=new JMenu("Theme");
        theme.add(new JCheckBoxMenuItem("Light"));
        theme.add(new JCheckBoxMenuItem("Dark"));
        view.add(theme);
        mBar.add(view);
    }
}