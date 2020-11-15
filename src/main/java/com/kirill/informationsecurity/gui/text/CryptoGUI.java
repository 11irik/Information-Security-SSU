package com.kirill.informationsecurity.gui.text;

import com.kirill.informationsecurity.algorithms.crypto.Crypto;

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
        setSize(400, 200);
        setContentPane(rootPanel);
        setVisible(true);

//        TODO: work with it
        int firstChar = 0;
        int alphabetCount = 1200;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        encodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enc = Crypto.encrypt(textEncodeInput.getText(), textEncodeKey.getText(), firstChar, alphabetCount);
                textEncodeOutput.setText(enc);
            }
        });
        buttonDecode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String decode = Crypto.decrypt(textDecodeInput.getText(), textDecodeKey.getText(), firstChar, alphabetCount);
                textDecodeOutput.setText(decode);
            }
        });
    }

    public static void main(String[] args) {
        new CryptoGUI();
    }

}
