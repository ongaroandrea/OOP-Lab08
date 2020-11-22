package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import it.unibo.oop.lab.mvcio.Controller;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame("Simple GUI With File Chooser");

    private SimpleGUIWithFileChooser(final Controller c) {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());

        final JPanel browsePanel = new JPanel();
        browsePanel.setLayout(new BorderLayout());
        final JTextField txtField = new JTextField(c.getPathCurrentFile());
        txtField.setEditable(false);
        txtField.setColumns(100); //NOT RESPONSIVE
        browsePanel.add(txtField, BorderLayout.LINE_START);
        final JButton btnBrowse = new JButton("Browse...");

        btnBrowse.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser chooser = new JFileChooser();
                chooser.setSelectedFile(c.getCurrentFile());
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
                chooser.setFileFilter(filter);
                final var result = chooser.showSaveDialog(chooser);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        c.setCurrentFile(chooser.getSelectedFile());
                        txtField.setText(c.getPathCurrentFile());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    System.out.println("Nothing to do");
                }
            }
        });
        browsePanel.add(btnBrowse, BorderLayout.LINE_END);

        canvas.add(browsePanel, BorderLayout.NORTH);
        final JTextArea textArea = new JTextArea();
        final JButton btnSave = new JButton("Save");
        canvas.add(textArea);
        canvas.add(btnSave, BorderLayout.PAGE_END);
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                try {
                    c.saveContent(textArea.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        frame.setContentPane(canvas);

        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */
    public static void main(final String... args) {
        new SimpleGUIWithFileChooser(new Controller());
     } 
}
