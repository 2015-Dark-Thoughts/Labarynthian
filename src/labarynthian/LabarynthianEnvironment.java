
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labarynthian;

import environment.Environment;
import environment.LocationValidatorIntf;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import map.Map;
import map.Obstacle;
import map.ObstacleEventHandlerIntf;
import map.Portal;
import map.PortalEventHandlerIntf;

/**
 *
 * @author ilovesoccer127
 */
class LabarynthianEnvironment extends Environment implements LocationValidatorIntf,
        GridDrawData, PortalEventHandlerIntf, ObstacleEventHandlerIntf  {

    private Map map;

//    Grid grid;
    private Image title;
    private GameState gameState = GameState.STARTMENU;
    private Number98 c98;
    private int counter = 0;
    public int MEDIUM_SPEED = 7;
    public int moveDelayLimit = MEDIUM_SPEED;
    public int moveDelayCounter = 7;
    private boolean showGrid = false;
    public int level;
    public BadGuys Enemy;
    
    public LabarynthianEnvironment() {

    }

    @Override
    public void initializeEnvironment() {
        this.setBackground(Color.BLACK);

        setTitle(ResourceTools.loadImageFromResource("resources/title.jpeg"));

        setGameState(GameState.STARTMENU);

        audio.AudioPlayer.play("/resources/MenuMusic.wav");
        c98 = new Number98(new Point(5, 5), this, this);
        setMap(MapFactory.getMap(MapFactory.MAP_START_ROOM, new Point(100, 300)));
    }

    @Override
    public void timerTaskHandler() {
        if (counter <= 200) {
            counter++;
        } else {
            counter = 80;
        }
        c98.setGlowInt((int) counter / 2);

        if (c98 != null) {
            c98.setGlowInt((int) counter / 2);
        }

    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_1) {
            setGameState(GameState.STARTROOM);
        } else if (e.getKeyCode() == KeyEvent.VK_2) {
            setGameState(GameState.MAZESTART);
        } else if (e.getKeyCode() == KeyEvent.VK_3) {
            setGameState(GameState.OASIS);
        } else if (e.getKeyCode() == KeyEvent.VK_4) {
            setGameState(GameState.MAZEEXPLORE);
        } else if (e.getKeyCode() == KeyEvent.VK_5) {
            setGameState(GameState.TREEROOM);
        } else if (e.getKeyCode() == KeyEvent.VK_6) {
            setGameState(GameState.BOSSROOM);
        } else if (e.getKeyCode() == KeyEvent.VK_7) {
            setGameState(GameState.BATTLE);
        } else if (e.getKeyCode() == KeyEvent.VK_8) {
            setGameState(GameState.END);
        } else if (e.getKeyCode() == KeyEvent.VK_9) {
            setGameState(GameState.DEAD);
        } else if (e.getKeyCode() == KeyEvent.VK_P) {
            setGameState(GameState.PAUSED);
        } else if (e.getKeyCode() == KeyEvent.VK_G) {
            showGrid = !showGrid;
        } else if (e.getKeyCode() == KeyEvent.VK_V) {
            setGameState(GameState.MAZESTART);
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (c98 != null) {
                c98.move(Direction.UP);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (c98 != null) {
                c98.move(Direction.DOWN);
                System.out.println("DOWN");
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (c98 != null) {
                c98.move(Direction.LEFT);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (c98 != null) {
                c98.move(Direction.RIGHT);
            }
        }
    }

    /**
     * @return the gameState
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * @param gameState the gameState to set
     */
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * @return the title
     */
    public Image getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(Image title) {
        this.title = title;
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {

        if ((e.getKeyCode() == KeyEvent.VK_UP)
                || (e.getKeyCode() == KeyEvent.VK_DOWN)
                || (e.getKeyCode() == KeyEvent.VK_LEFT)
                || (e.getKeyCode() == KeyEvent.VK_RIGHT)) {
            if (c98 != null) {
                c98.stop();
            }
        }
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
    }

    public int GetLevel(){
        return level;
    }
    
    
    @Override
    public void paintEnvironment(Graphics graphics) {
        if (gameState != null) {

            switch (gameState) {
                //<editor-fold defaultstate="collapsed" desc="STARTMENU">
                case STARTMENU:
//                    grid.setColumns(getWidth());
//                    grid.setRows(getHeight());
//                    grid.setColor(Color.BLACK);
//                    grid.paintComponent(graphics);

                    graphics.drawImage(getTitle(), 355, 100, null);

                    graphics.setColor(Color.WHITE);
                    graphics.setFont(new Font("Times New Roman", Font.BOLD, 14));
                    graphics.drawString("Developer Version Alpha 3.2", (getWidth() / 2) -90, getHeight() / 3);
                    graphics.setFont(new Font("Times New Roman", Font.BOLD, 24));
                    graphics.drawString("PRESS SPACE TO BEGIN", (getWidth() / 2) -140, getHeight() / 2);
                  

                    break;
//</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="STARTROOM">
                case STARTROOM:

                    if (getMap() != null) {
                        getMap().drawMap(graphics);
                    }

                    if (c98 != null) {
                        c98.draw(graphics);
                    }

//                    grid.setColumns(10);
//                    grid.setRows(10);
//                    grid.setColor(Color.LIGHT_GRAY);
//                    grid.paintComponent(graphics);
//                    grid.setPosition(new Point((getWidth() / 2) - 100, (getHeight() / 2) - 10));
//
////                    graphics.fillRect(593, 417, 100, 100);
//                    int border = 5;
//                    graphics.fillRect(grid.getPosition().x - border, grid.getPosition().y - border, 100 + (2 * border), 100 + (2 * border));
//
//                    graphics.setColor(Color.BLACK);
////                    graphics.fillRect(628, 417, 30, 10);
//                    graphics.fillRect(grid.getPosition().x, grid.getPosition().y, 100, 100);
////=======
////                    graphics.fillRect(grid.getPosition().x, 100, 100, 100);
////>>>>>>> Ethan-game-music
////                    graphics.fillRect(599, 422, 90, 90);
//                    graphics.fillRect(grid.getPosition().x + (3 * grid.getCellWidth()), grid.getPosition().y - border, 100 - (6 * grid.getCellWidth()), 2 * border);
////                    graphics.fillRect(599, 422, 90, 90);
//
//                    if (showGrid) {
//                        grid.paintComponent(graphics);
//                    }
//                    if (c98 != null) {
//                        c98.draw(graphics);
//                    }
//                    if (grid.getPosition() == grid.getCellLocationFromSystemCoordinate(1, 5)) {
//                        gameState = GameState.MAZESTART;
//                    }
                    break;
//</editor-fold>//</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="MAZESTART">
                case MAZESTART:

                    break;
//</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="MAZEEXPLORE">
                case MAZEEXPLORE:

                    break;
//</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="OASIS">
                case OASIS:

                    break;
//</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="TREEROOM">
                case TREEROOM:

                    break;
//</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="BOSSROOM">
                case BOSSROOM:

                    break;
//</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="PAUSED">
                case PAUSED:

                    break;
//</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="BATTLE">
                case BATTLE:
                   
//                    Enemy = new Enemy(c98.getPosition().x +10, c98.getPosition().y +10);
                    
                    
                    break;
//</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="DEAD">
                case DEAD:

                    break;
//</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="END">
                case END:

                    break;
//</editor-fold>

            }
        }
    }

//<editor-fold defaultstate="collapsed" desc="GridDrawData">
    @Override
    public int getCellHeight() {
//        return grid.getCellHeight();
        return map.getCellHeight();
    }

    @Override
    public int getCellWidth() {
//        return grid.getCellWidth();
        return map.getCellWidth();
    }

    @Override
    public Point getCellSystemCorrdinate(Point cellCorrdinate) {
//        return grid.getCellSystemCoordinate(cellCorrdinate);
        return map.getCellSystemCoordinate(cellCorrdinate);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="LocationValidatorIntf">
    @Override
    public Point validateLocation(Point point) {
        if (point.x < 0) {
            point.x = 0;
        }

        if (point.x > map.getGrid().getColumns() - 1) {
            point.x = map.getGrid().getColumns() - 1;
        }

        if (point.y < 0) {
            point.y = 0;
        }

        if (point.y > map.getGrid().getRows() - 1) {
            point.y = map.getGrid().getRows() - 1;
        }
        newLocation = point;

        if (map != null) {
            map.validateLocation(point);
        }

        return newLocation;
    }

    private Point newLocation;
//</editor-fold>

    /**
     * @return the map
     */
    public Map getMap() {
        return map;
    }

    /**
     * @param map the map to set
     */
    public void setMap(Map map) {
        this.map = map;
        this.map.setPortalEventHandler(this);
        this.map.setObstacleEventHandler(this);
    }

//<editor-fold defaultstate="collapsed" desc="PortalEventHandlerIntf">
    @Override
    public boolean portalEvent(Portal portal) {
        System.out.println("Portal Event");
        setMap(MapFactory.getMap(portal.getDestinationMapName(), new Point(100, 300)));
        newLocation = portal.getDestinationLocation();
        //c98.setPosition(portal.getDestinationLocation());
        return true;
    }
//</editor-fold>

    private void move(Direction direction) {
//        System.out.println("Move " + direction.toString());
        if (c98 != null) {
            c98.move(direction);
            c98.start();
        }
    }

//<editor-fold defaultstate="collapsed" desc="ObstacleEventHandlerIntf">
    @Override
    public boolean obstacleEvent(Obstacle obstacle) {
        System.out.println("Ouch : " + obstacle.getType());
        return false;
    }
//</editor-fold>
}
