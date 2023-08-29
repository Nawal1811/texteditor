/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.texteditor;

/**
 *
 * @author Nawal Shahid
 */


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor {
    private JFrame frame;
    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenuItem openMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem exitMenuItem;
    private JMenuItem saveAndSubmitMenuItem;

    public TextEditor() {
        frame = new JFrame("Text Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(textArea);

        menuBar = new JMenuBar();

        openMenuItem = new JMenuItem("Open");
        openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });

        saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });

        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        saveAndSubmitMenuItem = new JMenuItem("Save and Submit");
        saveAndSubmitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();
                // Implement the submit logic here
                frame.dispose(); // Close the application after saving and submitting
            }
        });

        menuBar.add(openMenuItem);
        menuBar.add(saveMenuItem);
        menuBar.add(saveAndSubmitMenuItem);
        menuBar.add(exitMenuItem);

        frame.setJMenuBar(menuBar);
        frame.add(scrollPane);
        frame.setVisible(true);
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        fileChooser.setFileFilter(filter);

        int returnVal = fileChooser.showOpenDialog(frame);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                textArea.read(reader, null);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        fileChooser.setFileFilter(filter);

        int returnVal = fileChooser.showSaveDialog(frame);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                textArea.write(writer);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TextEditor();
            }
        });
    }
}
