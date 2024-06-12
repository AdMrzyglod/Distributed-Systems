package org.watches;


import javax.swing.*;
import java.awt.*;

public class ZNodeGUI {

    private ZNodeWatcher zNodeWatcher;
    private JFrame frame;
    private JLabel text;
    private JButton button;
    private JTextArea textArea;
    private boolean show = false;

    public ZNodeGUI(ZNodeWatcher zNodeWatcher) {
        this.zNodeWatcher = zNodeWatcher;

        this.frame = new JFrame();
        this.frame.setSize(700, 700);
        this.frame.setLocationRelativeTo(null);
        this.frame.setLayout(new BorderLayout());

        JLabel description = new JLabel("Number of nodes:");
        this.text = new JLabel("0");
        description.setHorizontalAlignment(JLabel.CENTER);
        this.text.setHorizontalAlignment(JLabel.CENTER);
        this.text.setFont(new Font("Arial", Font.PLAIN, 20));
        description.setFont(new Font("Arial", Font.PLAIN, 20));

        this.button = new JButton("Print tree");
        this.textArea = new JTextArea("");
        this.button.setFont(new Font("Arial", Font.PLAIN, 20));
        this.textArea.setFont(new Font("Arial",Font.PLAIN, 17));
        this.textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(this.textArea);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        this.button.addActionListener(e->{
            this.show = !this.show;
            if(this.show){
                updateTree(this.zNodeWatcher.printZNodeTree());
                this.button.setText("Hide tree");
            }
            else{
                updateTree("");
                this.button.setText("Print tree");
            }
        });

        JPanel panel = new JPanel(new GridLayout(4, 1));
        panel.add(description);
        panel.add(this.text);
        panel.add(button);

        this.frame.add(panel,BorderLayout.NORTH);
        this.frame.add(scrollPane, BorderLayout.CENTER);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(false);
    }

    public void updateNodeNumber(int number) {
        this.text.setText(String.valueOf(number));
    }

    public void updateTree(String tree){
        this.textArea.setText(tree);
    }

    public void updateFrame(boolean value){
        if(this.frame.isVisible()==value){
            return;
        }
        this.frame.setVisible(value);
    }
}
