import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel {

    private boolean isMouseClicked = false;
    private Vector2D mouseClickedTowerPosition=null;

    public GamePanel() {
        this.setBackground(Color.DARK_GRAY);

        this.setFocusable(true); //For keyboard and mouse actions
        this.requestFocus();

        //Optional
        //Can be used to add Towers
        //Remove if not used
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int clickedX = e.getX();
                int clickedY = e.getY();

                int clickedRegionX = (clickedX - Commons.TowerZoneX) / Commons.TowerZoneDivideLength;
                int clickedRegionY = (clickedY - Commons.TowerZoneY) / Commons.TowerZoneDivideLength;

                Vector2D towerPosition = new Vector2D( ((double) Commons.TowerZoneX + Commons.TowerZoneDivideLength*clickedRegionX), ((double) Commons.TowerZoneY + Commons.TowerZoneDivideLength*clickedRegionY  ));

                /* Click outside of tower zone */
                if ( !(clickedX < Commons.TowerZoneX || clickedY < Commons.TowerZoneY || clickedX > Commons.TowerZoneX+Commons.TowerZoneWidth || clickedY > Commons.TowerZoneY + Commons.TowerZoneHeight ) ) {
                    isMouseClicked = true;
                    mouseClickedTowerPosition = towerPosition;

                }
                //Optional
            }
        });

        //Optional
        //Can be used to add Towers
        //Remove if not used
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                char keyboardInput = e.getKeyChar();
                if (isMouseClicked){
                    Game.getInstance().createTowerFactory(mouseClickedTowerPosition,keyboardInput);
                    mouseClickedTowerPosition = null;
                    isMouseClicked = false;
                }
            }
        });
    }



    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Commons.GamePanelWidth, Commons.GameHeight);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //Optional
        //You can make changes to the visuals
        //This is just an example
        g.setColor(Color.CYAN);
        g.fillRect(Commons.StartX, Commons.StartY, Commons.StartWidth, Commons.StartHeight);

        g.setColor(Color.WHITE);
        g.drawChars("Start Zone".toCharArray(), 0, 10, Commons.StartX, Commons.StartY+12);
        g.setColor(Color.WHITE);
        g.drawRect(Commons.TowerZoneX, Commons.TowerZoneY, Commons.TowerZoneWidth, Commons.TowerZoneHeight);


        //Optional
        //Maybe some additional Drawings


        //Draw Grid Lines
        g.setColor(Color.WHITE);

        for ( int i=1; i<4; i++ ) {
            g.drawLine( Commons.TowerZoneX, Commons.TowerZoneY + (Commons.TowerZoneDivideLength * i),
                    Commons.TowerZoneX + Commons.TowerZoneWidth,
                    Commons.TowerZoneY + (Commons.TowerZoneDivideLength * i));
            g.drawLine( Commons.TowerZoneX + (Commons.TowerZoneDivideLength * i), Commons.TowerZoneY,
                     Commons.TowerZoneX + (Commons.TowerZoneDivideLength * i),
                     Commons.TowerZoneY + Commons.TowerZoneHeight);
        }

        //TODO
        //Maybe some additional Drawings
        /**
         * fill with white while waiting for key press
         */
        if( isMouseClicked && mouseClickedTowerPosition != null){
            g.fillRect(mouseClickedTowerPosition.getIntX(),mouseClickedTowerPosition.getIntY(),Commons.TowerZoneDivideLength,Commons.TowerZoneDivideLength);
        }
        Game.getInstance().paint(g);

    }
}
