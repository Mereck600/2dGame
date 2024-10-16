package TileEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TileSelectionPanel extends JPanel {
    private TileManager tileManager;

    public TileSelectionPanel(TileManager tileManager) {
        this.tileManager = tileManager;
        setLayout(new GridLayout(1, tileManager.tile.length)); // Create a grid of buttons for each tile

        // Create buttons for each tile, displaying the tile's image
        for (int i = 0; i < tileManager.tile.length; i++) {
            if (tileManager.tile[i] != null && tileManager.tile[i].image != null) {
                final int tileIndex = i;
                JButton tileButton = new JButton(new ImageIcon(tileManager.tile[i].image));
                tileButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tileManager.setCurrentTile(tileIndex); // Set the selected tile
                    }
                });
                add(tileButton);
            }
        }
    }
}
