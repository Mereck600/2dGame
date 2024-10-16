package TileEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditorWindow {
    private TileManager tileManager;
    private TilePanel tilePanel; // Reference to TilePanel for zooming

    public EditorWindow() {
        JFrame window = new JFrame("Map Creator");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        tileManager = new TileManager();
        tilePanel = new TilePanel(tileManager); // Initialize tilePanel
        TileSelectionPanel selectionPanel = new TileSelectionPanel(tileManager);

        // Buttons
        JButton saveButton = new JButton("Save Map");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveMap();
            }
        });

        JButton loadButton = new JButton("Load Map");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadMap();
            }
        });

        JButton exportImageButton = new JButton("Export as Image");
        exportImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportMapAsImage();
            }
        });

     // Zoom buttons
        JButton zoomInButton = new JButton("Zoom In");
        zoomInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Zoom in at the center of the visible panel
                int centerX = tilePanel.getWidth() / 2;
                int centerY = tilePanel.getHeight() / 2;
                tilePanel.zoomIn(centerX, centerY); // Call zoomIn with center coordinates
            }
        });

        JButton zoomOutButton = new JButton("Zoom Out");
        zoomOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Zoom out at the center of the visible panel
                int centerX = tilePanel.getWidth() / 2;
                int centerY = tilePanel.getHeight() / 2;
                tilePanel.zoomOut(centerX, centerY); // Call zoomOut with center coordinates
            }
        });


        // Bottom panel to hold the buttons
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout()); // To align buttons nicely
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(exportImageButton);
        buttonPanel.add(zoomInButton); // Add zoom in button
        buttonPanel.add(zoomOutButton); // Add zoom out button

        bottomPanel.add(selectionPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);

        // Add the panels to the window
        window.setLayout(new BorderLayout());
        window.add(tilePanel, BorderLayout.CENTER);
        window.add(bottomPanel, BorderLayout.SOUTH);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    // Save the map to a file
    public void saveMap() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            tileManager.saveMap(filePath);
        }
    }

    // Load a map from a file
    public void loadMap() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            tileManager.loadMap(filePath);
            tilePanel.repaint(); // Repaint the TilePanel after loading the map
        }
    }

    // Export the map as an image
    public void exportMapAsImage() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            tileManager.exportMapAsImage(filePath);
        }
    }

    public static void main(String[] args) {
        new EditorWindow();
    }
}
