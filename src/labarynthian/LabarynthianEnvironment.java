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
import java.util.ArrayList;

/**
 *
 * @author ilovesoccer127
 */
class LabarynthianEnvironment extends Environment implements LocationValidatorIntf, GridDrawData {

    Grid grid;
    private Image title;
    private GameState gameState = GameState.STARTMENU;
    private Number98 character;

    public LabarynthianEnvironment() {

    }

    @Override
    public void initializeEnvironment() {
        this.setBackground(Color.BLACK);

        setTitle(ResourceTools.loadImageFromResource("resources/title.jpeg"));

        grid = new Grid(100, 80, 10, 10, new Point(0, 0), new Color(0, 0, 0));

        setGameState(GameState.STARTMENU);

        character = new Number98(new Point(5, 5), this, this);

        

       

    }

    @Override
    public void timerTaskHandler() {

    }

    @Override
    public void keyPressedHandler(KeyEvent e) {

    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            gameState = GameState.STARTROOM;
        } else if (e.getKeyCode() == KeyEvent.VK_V) {
            gameState = GameState.MAZESTART;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (character != null){
                character.move(Direction.UP);
            }
        }

    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {

    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        if (gameState != null) {

            switch (gameState) {
                case STARTMENU:
                    grid.setColumns(getWidth());
                    grid.setRows(getHeight());
                    grid.setColor(Color.BLACK);
                    grid.paintComponent(graphics);

                    graphics.drawImage(getTitle(), 400, 100, null);

                    graphics.setColor(Color.WHITE);
                    graphics.drawString("PRESS SPACE TO BEGIN", (getWidth() / 2) - 50, getHeight() / 2);

                    break;

                case STARTROOM:
                    grid.setColumns(10);
                    grid.setRows(10);
                    grid.setColor(Color.WHITE);
                    grid.paintComponent(graphics);
                    grid.setPosition(new Point((getWidth() / 2) - 100, (getHeight() / 2) - 10));

                   
                    

                    graphics.fillRect(593, 417, 100, 100);

                    graphics.setColor(Color.BLACK);
                    graphics.fillRect(628, 417, 30, 10);
                    graphics.fillRect(599, 422, 90, 90);
                    
                    if (character != null){
                        character.draw(graphics);
                    }
                    
                    grid.paintComponent(graphics);

                    break;

                case MAZESTART:

                    break;

                case MAZEEXPLORE:

                    break;

                case OASIS:

                    break;

                case TREEROOM:

                    break;

                case BOSSROOM:

                    break;

                case PAUSED:

                    break;

                case BATTLE:

                    break;

                case DEAD:

                    break;

                case END:

                    break;

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

    @Override
    public int getCellHeight() {
        return grid.getCellHeight();
    }

    @Override
    public int getCellWidth() {
        return grid.getCellWidth();
    }

    @Override
    public Point getCellSystemCorrdinate(Point cellCorrdinate) {
        return grid.getCellLocationFromSystemCoordinate(cellCorrdinate);
    }

//<editor-fold defaultstate="collapsed" desc="LocationValidatorIntf">
    @Override
    public Point validateLocation(Point point) {
        return point;
    }
//</editor-fold>
}
