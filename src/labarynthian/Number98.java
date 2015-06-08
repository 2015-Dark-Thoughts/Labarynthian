
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labarynthian;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author ConnerCrabtree
 */
public class Number98 {

//<editor-fold defaultstate="collapsed" desc="Constructor">
    public Number98(Point position, MovementValidatorIntf movementValidatorIntf, GridDrawData drawData) {
        this.position = position;
        this.movementValidator = movementValidatorIntf;
        this.moving = true;
        
        this.drawData = drawData;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Properties">
    private Point position;
    private int glowInt;
    private GridDrawData drawData;

    private boolean paused = false;
    private boolean alive = true;
    private boolean candle = false;

    private Direction direction;
    private boolean moving;
    private MovementValidatorIntf movementValidator;

    /**
     * @return the drawData
     */
    public GridDrawData getDrawData() {
        return drawData;
    }

    /**
     * @param drawData the drawData to set
     */
    public void setDrawData(GridDrawData drawData) {
        this.drawData = drawData;
    }

    /**
     * @return the movementValidator
     */
    public MovementValidatorIntf getMovementValidatorIntf() {
        return movementValidator;
    }

    /**
     * @param movementValidatorIntf the movementValidator to set
     */
    public void setMovementValidatorIntf(MovementValidatorIntf movementValidatorIntf) {
        this.movementValidator = movementValidatorIntf;
    }

    /**
     * @return the paused
     */
    public boolean isPaused() {
        this.stop();
        return paused;
    }

    /**
     * @param paused the paused to set
     */
    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    /**
     * @return the alive
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * @param alive the alive to set
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * @return the glowInt
     */
    public int getGlowInt() {
        return glowInt;
    }

    /**
     * @param glowInt the glowInt to set
     */
    public void setGlowInt(int glowInt) {
        this.glowInt = glowInt;
    }
    
    /**
     * @return the spreadRate
     */
    private int getRadiusSize(){
        return Math.abs(70 - glowInt) + 20;
    }

    public void move() {
        move(direction);
    }

    /**
     * @return the position
     */
    public Point getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Point position) {
        this.position = position;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Drawing">
    public void draw(Graphics graphics) {
        Point topLeft = drawData.getCellSystemCorrdinate(getPosition());
        Point com = new Point(topLeft.x + (drawData.getCellWidth() / 2), topLeft.y + (drawData.getCellHeight() / 2));
        graphics.setColor(Color.WHITE);
        
        if (candle = true) {
            
            graphics.setColor(new Color(255, 255, 204, 150));
            
            graphics.fillOval(com.x - getRadiusSize(), com.y - getRadiusSize(),
                    2 * getRadiusSize(), 2 * getRadiusSize());
        }
        graphics.setColor(Color.GRAY);
        graphics.fillOval(topLeft.x, topLeft.y, drawData.getCellWidth(), drawData.getCellHeight());
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        graphics.drawString("98", drawData.getCellSystemCorrdinate(getPosition()).x + 1 , drawData.getCellSystemCorrdinate(getPosition()).y + 9);
        
        
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Movement">
    public void stop() {
        this.moving = false;
    }
    
    public void start() {
        this.moving = true;
    }
    
    void move(Direction direction) {
//        if ((!paused) && (moving)) {
        if (true) {
            Point newPosition = (Point) getPosition().clone();
            
            if (direction == Direction.DOWN) {
                newPosition.y++;
            } else if (direction == Direction.UP) {
                newPosition.y--;
            } else if (direction == Direction.LEFT) {
                newPosition.x--;
            } else if (direction == Direction.RIGHT) {
                newPosition.x++;
            }
            
            if ((movementValidator != null) && (!movementValidator.validateMove(newPosition))){
                return;
            }
            
            setPosition(newPosition);
        }
    }
//</editor-fold>
    
}

