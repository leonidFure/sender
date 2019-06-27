package com.lndc.sender;

import javax.swing.*;

public class App {
    private JPanel panelMain;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JTextField textField6;
    private JTextField textField7;
    private JComboBox comboBox1;
    private JButton считатьДанныеИзФайлаButton;
    private JButton отправитьButton;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Sender");
        jFrame.setContentPane(new App().panelMain);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
