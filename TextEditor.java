import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor extends JFrame implements ActionListener {
    private JTextArea textArea;
    private JFileChooser fileChooser;

    public TextEditor() {
        setTitle("Text Editor");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");
        JMenuItem clearItem = new JMenuItem("Clear");
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);
        clearItem.addActionListener(this);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        JMenu editMenu = new JMenu("Edit");
        editMenu.add(clearItem);
        menuBar.add(editMenu);
        setJMenuBar(menuBar);

        fileChooser = new JFileChooser(".");
        setVisible(true);
    }

    public static void main(String[] args) {
        new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Open")) {
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    reader.close();
                    textArea.setText(stringBuilder.toString());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (command.equals("Save")) {
            int returnValue = fileChooser.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
                    writer.write(textArea.getText());
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else if (command.equals("Exit")) {
            System.exit(0);
        } else if (command.equals("Clear")) {
            textArea.setText("");
        }
    }
}
