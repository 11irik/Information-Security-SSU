package com.kirill.informationsecurity.gui.cat;

import com.kirill.informationsecurity.algorithms.crypto.Crypto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CatGUI extends JFrame {
    private JPanel rootPanel;
    private JTabbedPane tabbedPane;

    private JFileChooser chooser;
    private static final String CHOOSER_TITLE = "TITLE";

    private JPanel tabbedPaneEncode;
    private JTextField textEncodePath;
    private JTextField textEncodeKey;
    private JButton pathEncodeButton;
    private JButton encodeButton;

    private JPanel tabbedPaneDecode;
    private JTextField textDecodePath;
    private JTextField textDecodeKey;
    private JButton buttonDecode;
    private JButton pathDecodeButton;

    Path tempFile = Paths.get("./temp");

    public CatGUI() {
        setSize(600, 200);
        setContentPane(rootPanel);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pathEncodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooser = new JFileChooser();
                chooser.setCurrentDirectory(new java.io.File("."));
                chooser.setDialogTitle(CHOOSER_TITLE);
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    textEncodePath.setText(chooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        encodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Crypto.encryptCatalog(Paths.get(textEncodePath.getText()), textEncodeKey.getText(), tempFile);
                } catch (Exception ignored) {
                }
            }
        });

        pathDecodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooser = new JFileChooser();
                chooser.setCurrentDirectory(new java.io.File("."));
                chooser.setDialogTitle(CHOOSER_TITLE);
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    textDecodePath.setText(chooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        buttonDecode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Crypto.decryptCatalog(Paths.get(textDecodePath.getText()), textDecodeKey.getText());
                } catch (Exception ignored) {
                }
            }
        });

    }

    public static void main(String[] args) {
        new CatGUI();
    }
}