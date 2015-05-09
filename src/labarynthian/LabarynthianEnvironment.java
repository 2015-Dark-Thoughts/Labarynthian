
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labarynthian;

import environment.Environment;
import environment.LocationValidatorIntf;
import grid.Grid;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import map.Map;
import map.Portal;
import map.PortalEventHandlerIntf;

/**
 *
 * @author ilovesoccer127
 */
class LabarynthianEnvironment extends Environment implements LocationValidatorIntf, 
        GridDrawData, PortalEventHandlerIntf {

    private Map map;
    
    
    Grid grid;
    private Image title;
    private GameState gameState = GameState.STARTMENU;
    private Number98 character;
    private Number98 c98;
    
    private int counter = 0;

    public int MEDIUM_SPEED = 7;
    
    public int moveDelayLimit = MEDIUM_SPEED;
    public int moveDelayCounter = 7;

    private boolean showGrid = false;

    public LabarynthianEnvironment() {

    }

    @Override
    public void initializeEnvironment() {
        this.setBackground(Color.BLACK);

        setTitle(ResourceTools.loadImageFromResource("resources/title.jpeg"));

        grid = new Grid(100, 80, 10, 10, new Point(0, 0), new Color(0, 0, 0));

        setGameState(GameState.STARTMENU);

//        audio.AudioPlayer.play("/resources/MenuMusic.wav");

        character = new Number98(new Point(5, 5), this, this);
        
        c98 = new Number98(new Point(4, 0), this, this);
        setMap(MapFactory.getMap(MapFactory.MAP_START_ROOM, new Point(100, 300)));
    }

    @Override
    public void timerTaskHandler() {
        if (counter <= 200) {
            counter++;
        } else {
            counter = 80;
        }
        character.setGlowInt((int) counter / 2);

        if (character != null){
            character.move();
        }
        

        if (c98 != null){
            c98.setGlowInt((int) counter / 2);
            c98.move();
        }
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_1) {
            gameState = GameState.STARTROOM;
        } else if (e.getKeyCode() == KeyEvent.VK_2) {
            gameState = GameState.MAZESTART;
        } else if (e.getKeyCode() == KeyEvent.VK_3) {
            gameState = GameState.OASIS;
        } else if (e.getKeyCode() == KeyEvent.VK_4) {
            gameState = GameState.MAZEEXPLORE;
        } else if (e.getKeyCode() == KeyEvent.VK_5) {
            gameState = GameState.TREEROOM;
        } else if (e.getKeyCode() == KeyEvent.VK_6) {
            gameState = GameState.BOSSROOM;
        } else if (e.getKeyCode() == KeyEvent.VK_7) {
            gameState = GameState.BATTLE;
        } else if (e.getKeyCode() == KeyEvent.VK_8) {
            gameState = GameState.END;
        } else if (e.getKeyCode() == KeyEvent.VK_9) {
            gameState = GameState.DEAD;
        } else if (e.getKeyCode() == KeyEvent.VK_P) {
            gameState = GameState.PAUSED;
        } else if (e.getKeyCode() == KeyEvent.VK_G) {
            showGrid = !showGrid;
        } else if (e.getKeyCode() == KeyEvent.VK_V) {
            gameState = GameState.MAZESTART;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (character != null) {
                character.move(Direction.UP);
                character.start();

                move(Direction.UP);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (character != null) {
//                character.move(Direction.DOWN);
//                character.start();
                
                move(Direction.DOWN);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (character != null) {
//                character.move(Direction.LEFT);
//                character.start();
                
                move(Direction.LEFT);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (character != null) {
//                character.move(Direction.RIGHT);
//                character.start();
                
                move(Direction.RIGHT);
            }
        } 
//        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//            if (character != null) {
//                character.move(Direction.DOWN);
//            }
//        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//            if (character != null) {
//                character.move(Direction.LEFT);
//            }
//        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//            if (character != null) {
//                character.move(Direction.RIGHT);
//            }
//        }
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_UP) || 
            (e.getKeyCode() == KeyEvent.VK_DOWN) ||
            (e.getKeyCode() == KeyEvent.VK_LEFT) ||
            (e.getKeyCode() == KeyEvent.VK_RIGHT) ){
            if (character != null) {
                character.stop();
                c98.stop();
            }
        }
//        if (e.getKeyCode() == KeyEvent.VK_UP) {
//            if (character != null) {
//                character.stop();
//            }
//        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//            if (character != null) {
//                character.stop();
//            }
//        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
//            if (character != null) {
//                character.stop();
//            }
//        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
//            if (character != null) {
//                character.stop();
//            }
//        }
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {

    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        if (gameState != null) {

            switch (gameState) {
                //<editor-fold defaultstate="collapsed" desc="STARTMENU">
                case STARTMENU:
                    grid.setColumns(getWidth());
                    grid.setRows(getHeight());
                    grid.setColor(Color.BLACK);
                    grid.paintComponent(graphics);

                    graphics.drawImage(getTitle(), 400, 100, null);

                    graphics.setColor(Color.WHITE);
                    graphics.drawString("PRESS SPACE TO BEGIN", (getWidth() / 2) - 50, getHeight() / 2);

                    break;
//</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="STARTROOM">
                case STARTROOM:
                    
                    if (getMap() != null){
                        getMap().drawMap(graphics);
                    }
                    
                    if (c98 != null){
                        c98.draw(graphics);
                    }
                    
                    grid.setColumns(10);
                    grid.setRows(10);
                    grid.setColor(Color.LIGHT_GRAY);
                    grid.paintComponent(graphics);
                    grid.setPosition(new Point((getWidth() / 2) - 100, (getHeight() / 2) - 10));

//                    graphics.fillRect(593, 417, 100, 100);
                    int border = 5;
                    graphics.fillRect(grid.getPosition().x - border, grid.getPosition().y - border, 100 + (2 * border), 100 + (2 * border));

                    graphics.setColor(Color.BLACK);
//                    graphics.fillRect(628, 417, 30, 10);
                    graphics.fillRect(grid.getPosition().x, grid.getPosition().y, 100, 100);
//=======
//                    graphics.fillRect(grid.getPosition().x, 100, 100, 100);
//>>>>>>> Ethan-game-music
//                    graphics.fillRect(599, 422, 90, 90);
                    graphics.fillRect(grid.getPosition().x + (3 * grid.getCellWidth()), grid.getPosition().y - border, 100 - (6 * grid.getCellWidth()), 2 * border);
//                    graphics.fillRect(599, 422, 90, 90);

                    if (showGrid) {
                        grid.paintComponent(graphics);
                    }

//                    if (character != null) {
//                        character.draw(graphics);
//                    }
                    if (grid.getPosition() == grid.getCellLocationFromSystemCoordinate(1, 5)) {
                        gameState = GameState.MAZESTART;
                    }

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
//        System.out.println("Validate");
        if (point.x < 0) {
            point.x = 0;
        }

        if (point.x > grid.getColumns() - 1) {
            point.x = grid.getColumns() - 1;
        }

        if (point.y < 0) {
            point.y = 0;
        }

        if (point.y > grid.getRows() - 1) {
            point.y = grid.getRows() - 1;
        }
        
        if (map != null){
            map.validateLocation(point);
        }
        return point;
    }
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
    }

//<editor-fold defaultstate="collapsed" desc="PortalEventHandlerIntf">
    @Override
    public boolean portalEvent(Portal portal) {
        System.out.println("Portal Event");
        setMap(MapFactory.getMap(portal.getDestinationMapName(), new Point(100, 300)));
        c98.setPosition(portal.getDestinationLocation());
        return true;
    }
//</editor-fold>

    private void move(Direction direction) {
//        System.out.println("Move " + direction.toString());
        if (c98 != null){
            c98.move(direction);
            c98.start();
        }
    }

}
