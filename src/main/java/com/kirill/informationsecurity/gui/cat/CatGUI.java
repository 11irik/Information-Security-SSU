package com.kirill.informationsecurity.gui.cat;

import com.kirill.informationsecurity.algorithms.crypto.Crypto;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
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
    private JLabel encodeStatusLabel;

    private JPanel tabbedPaneDecode;
    private JTextField textDecodePath;
    private JTextField textDecodeKey;
    private JButton buttonDecode;
    private JButton pathDecodeButton;
    private JLabel decodeStatusLabel;
    private JTextField textEncodeTargetPath;
    private JButton pathEncodeTargetButton;
    private JTextField textDecodeDirPath;
    private JButton pathDecodeDirButton;

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

        pathEncodeTargetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooser = new JFileChooser();
                chooser.setCurrentDirectory(new java.io.File("."));
                chooser.setDialogTitle(CHOOSER_TITLE);
                chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter());
                chooser.setFileFilter(new FileNameExtensionFilter("Files ending in .kl", "kl"));

                if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();

                    if (file!=null) {
                        FileFilter fileFilter = chooser.getFileFilter();
                        if (fileFilter instanceof FileNameExtensionFilter && ! fileFilter.accept(file)) {
                            FileNameExtensionFilter fileNameExtensionFilter = (FileNameExtensionFilter) fileFilter;
                            String extension = fileNameExtensionFilter.getExtensions()[0];

                            String newName = file.getName() + "." + extension;
                            file = new File(file.getParent(), newName);
                        }
                    }
                    textEncodeTargetPath.setText(file.getAbsolutePath());
                }
            }
        });

        encodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Crypto.encryptCatalog(Paths.get(textEncodePath.getText()), textEncodeKey.getText(), Paths.get(textEncodeTargetPath.getText()));
                    encodeStatusLabel.setText("Success");
                } catch (IOException | IllegalArgumentException exception) {
                    encodeStatusLabel.setText("Failure");
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
                chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter());
                chooser.setFileFilter(new FileNameExtensionFilter("Files ending in .kl", "kl"));

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    textDecodePath.setText(chooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        pathDecodeDirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooser = new JFileChooser();
                chooser.setCurrentDirectory(new java.io.File("."));
                chooser.setDialogTitle(CHOOSER_TITLE);
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);

                if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    textDecodeDirPath.setText(chooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        buttonDecode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Crypto.decryptCatalog(Paths.get(textDecodePath.getText()), textDecodeKey.getText(), Paths.get(textDecodeDirPath.getText()));
                    decodeStatusLabel.setText("Success");
                } catch (IOException | IllegalArgumentException exception) {
                    decodeStatusLabel.setText("Failure");
                }
            }
        });
    }

    public static void main(String[] args) {
        new CatGUI();
    }
}