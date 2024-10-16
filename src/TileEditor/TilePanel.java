package TileEditor;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class TilePanel extends JPanel {
    private TileManager tileManager;
    private int tileSize; // Dynamic tile size based on zoom
    private final int screenWidth = 768; // Visible window width
    private final int screenHeight = 576; // Visible window height
    private double zoomLevel = 1.0; // Zoom factor (adjustable)
    private int offsetX = 0; // X-axis offset for panning
    private int offsetY = 0; // Y-axis offset for panning
    private int lastMouseX; // To track last mouse position for dragging
    private int lastMouseY;

    public TilePanel(TileManager tileManager) {
        this.tileManager = tileManager;
        this.tileSize = tileManager.tileSize; // Initial tile size
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));

        // Center the map initially
        centerMapOnStartup();

        // Make the panel focusable so it can capture key events
        this.setFocusable(true);
        this.requestFocusInWindow();

        // Key listener for zooming in and out with keyboard (+ and -)
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                if (e.getKeyCode() == KeyEvent.VK_PLUS || e.getKeyCode() == KeyEvent.VK_EQUALS) {
                    zoomIn(centerX, centerY);
                } else if (e.getKeyCode() == KeyEvent.VK_MINUS) {
                    zoomOut(centerX, centerY);
                }
            }
        });

        // Mouse listeners for panning and zooming
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastMouseX = e.getX();
                lastMouseY = e.getY();

                // Handle tile editing
                if (e.getButton() == MouseEvent.BUTTON1) {
                    editTileAtMousePosition(e.getX(), e.getY());
                }
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int deltaX = e.getX() - lastMouseX;
                int deltaY = e.getY() - lastMouseY;
                offsetX -= deltaX / zoomLevel;
                offsetY -= deltaY / zoomLevel;
                lastMouseX = e.getX();
                lastMouseY = e.getY();
                repaint();
            }
        });

        // Mouse wheel listener for zooming in and out
        this.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getPreciseWheelRotation() < 0) {
                    zoomIn(e.getX(), e.getY());
                } else {
                    zoomOut(e.getX(), e.getY());
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Apply panning and zoom transformations
        g2.translate(-offsetX, -offsetY); // Smooth panning
        g2.scale(zoomLevel, zoomLevel); // Apply zoom

        // Draw the full map dynamically, adjusting for screen size and zoom
        drawVisibleMap(g2);
    }

    // Dynamically draw the visible portion of the map
    private void drawVisibleMap(Graphics2D g2) {
        int visibleCols = (int) Math.ceil(screenWidth / (tileSize * zoomLevel)) + 2;
        int visibleRows = (int) Math.ceil(screenHeight / (tileSize * zoomLevel)) + 2;

        int startCol = Math.max(0, offsetX / tileSize);
        int startRow = Math.max(0, offsetY / tileSize);

        for (int col = startCol; col < startCol + visibleCols && col < tileManager.maxWorldCol; col++) {
            for (int row = startRow; row < startRow + visibleRows && row < tileManager.maxWorldRow; row++) {
                tileManager.drawTile(g2, col, row, tileSize);
            }
        }
    }

    // Handle tile editing when clicking on the map
    private void editTileAtMousePosition(int mouseX, int mouseY) {
        // Convert mouse position to map coordinates
        int worldX = (int) ((mouseX + offsetX) / zoomLevel);
        int worldY = (int) ((mouseY + offsetY) / zoomLevel);

        int col = worldX / tileSize;
        int row = worldY / tileSize;

        // Set the tile at the clicked position
        tileManager.setTileAt(col, row);
        repaint();
    }

    // Ensure the entire map fits into the view initially
    private void centerMapOnStartup() {
        int totalMapWidth = tileManager.maxWorldCol * tileSize;
        int totalMapHeight = tileManager.maxWorldRow * tileSize;

        // Calculate the initial zoom level to fit the map within the screen
        double zoomX = (double) screenWidth / totalMapWidth;
        double zoomY = (double) screenHeight / totalMapHeight;

        // Use the smaller zoom factor to fit the entire map
        zoomLevel = Math.min(zoomX, zoomY);

        // Calculate the initial offsets to center the map
        offsetX = (int) ((totalMapWidth * zoomLevel - screenWidth) / 2);
        offsetY = (int) ((totalMapHeight * zoomLevel - screenHeight) / 2);
    }

    // Zoom in around a specific point (centerX, centerY)
    public void zoomIn(int centerX, int centerY) {
        double prevZoomLevel = zoomLevel;
        zoomLevel += 0.1; // Increase zoom level

        // Adjust offsets to keep the zoom centered on the point
        offsetX = (int) ((centerX + offsetX) * (zoomLevel / prevZoomLevel) - centerX);
        offsetY = (int) ((centerY + offsetY) * (zoomLevel / prevZoomLevel) - centerY);

        repaint();
    }

    // Zoom out around a specific point (centerX, centerY)
    public void zoomOut(int centerX, int centerY) {
        if (zoomLevel > 0.2) { // Prevent zooming out too much
            double prevZoomLevel = zoomLevel;
            zoomLevel -= 0.1;

            // Adjust offsets to keep the zoom centered on the point
            offsetX = (int) ((centerX + offsetX) * (zoomLevel / prevZoomLevel) - centerX);
            offsetY = (int) ((centerY + offsetY) * (zoomLevel / prevZoomLevel) - centerY);

            repaint();
        }
    }
}
