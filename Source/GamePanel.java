import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel {

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

                //Optional
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
        Game.getInstance().paint(g);
    }
}
