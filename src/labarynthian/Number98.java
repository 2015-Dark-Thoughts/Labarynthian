/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labarynthian;

import environment.LocationValidatorIntf;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author ConnerCrabtree
 */
public class Number98 {

    private Point position;
    private int glowInt;
    private GridDrawData drawData;
    private LocationValidatorIntf locationValidator;
    private boolean paused = false;
    private boolean alive = true;

    private boolean candle = false;

    private Direction direction;
    private boolean moving;

    public Number98(Point position, LocationValidatorIntf locationValidator, GridDrawData drawData) {
        this.position = position;
        this.locationValidator = locationValidator;
        this.drawData = drawData;
        this.direction = direction;
        this.moving = false;
    }

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
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
     * @return the locationValidator
     */
    public LocationValidatorIntf getLocationValidator() {
        return locationValidator;
    }

    /**
     * @param locationValidator the locationValidator to set
     */
    public void setLocationValidator(LocationValidatorIntf locationValidator) {
        this.locationValidator = locationValidator;
    }

    /**
     * @return the paused
     */
    public boolean isPaused() {
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
     * @return the spreadRate
     */
//</editor-fold>
    

    
//    public Point getCoM(){
//        return new Point(position)
//    }

    public void draw(Graphics graphics) {
        Point topLeft = drawData.getCellSystemCorrdinate(getPosition());
        Point com = new Point(topLeft.x + (drawData.getCellWidth() / 2), topLeft.y + (drawData.getCellHeight() / 2));
        if (candle = true) {
            graphics.setColor(new Color(255, 255, 204, 150));
            graphics.fillOval(com.x - getRadiusSize(), com.y - getRadiusSize(), 
                    2 * getRadiusSize(), 2 * getRadiusSize());
//            graphics.fillOval(topLeft.x - (drawData.getCellWidth()), topLeft.y - (drawData.getCellHeight() / 2), 
//                    ((drawData.getCellWidth()) + getRadiusSize()) - 10, ((drawData.getCellHeight()) + getRadiusSize()) - 10);
        }
        graphics.setColor(Color.GRAY);
        graphics.fillOval(topLeft.x, topLeft.y, drawData.getCellWidth(), drawData.getCellHeight());

    }

    public void stop() {
        this.moving = false;
    }
    
    public void start() {
        this.moving = true;
    }
    
    
    
    void move(Direction direction) {
        if ((!paused) && (moving)) {
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
            if (locationValidator != null) {
                setPosition(locationValidator.validateLocation(newPosition));
            }
        }

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
}
