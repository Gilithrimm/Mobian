package old.safenar.swing;

import javax.swing.*;
import java.awt.*;

import static old.safenar.swing.MyFrame.frameSize;

public class MyPanel extends JPanel {
    private static final JTextArea INPUT =new JTextArea();
    private static final JEditorPane OUTPUT=new JEditorPane();
    public static Font mono=new Font(Font.MONOSPACED, Font.PLAIN,14);
    private final ColorTheme dark=new ColorTheme(Color.BLACK,Color.DARK_GRAY,Color.WHITE,Color.WHITE);

    public MyPanel() {
        super(new BorderLayout());
//        setBackground(Color.BLACK);
        setSize(frameSize.getSize());
        display();

    }
    private void display(){
        JPanel io=new JPanel(new GridLayout(1,2));
        INPUT.setBackground(dark.getInputBg());
        INPUT.setCaretColor(dark.getCaret());
        INPUT.setForeground(dark.getText());
        INPUT.setFont(mono);
        io.add(INPUT);
        OUTPUT.setEditable(false);
        OUTPUT.setBackground(dark.getOutputBg());
        OUTPUT.setForeground(dark.getText());
        OUTPUT.setCaretColor(dark.getCaret());
        OUTPUT.setFont(mono);
        io.add(OUTPUT);
        add(io,BorderLayout.CENTER);
    }
    //KeyListener?
}
