package com.kirill.informationsecurity.gui;

import com.kirill.informationsecurity.algorithms.crypto.CryptoString;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CryptoGUI extends JFrame {
    private JButton encodeButton;
    private JPanel rootPanel;
    private JTextField textEncodeInput;
    private JTextField textEncodeOutput;
    private JTextField textEncodeKey;
    private JTabbedPane tabbedPaneEncode;
    private JPanel encodePane;
    private JPanel tabbedPaneDecode;
    private JTextField textDecodeInput;
    private JTextField textDecodeKey;
    private JTextField textDecodeOutput;
    private JButton buttonDecode;

    public CryptoGUI() {
        setContentPane(rootPanel);
        setSize(400, 200);
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        encodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enc = CryptoString.encode(textEncodeInput.getText(), textEncodeKey.getText(), 1072, 33);
                textEncodeOutput.setText(enc);
            }
        });
        buttonDecode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String decode = CryptoString.decode(textDecodeInput.getText(), textDecodeKey.getText(), 1072, 33);
                textDecodeOutput.setText(decode);
            }
        });
    }

    public static void main(String[] args) {
        new CryptoGUI();
    }

}
