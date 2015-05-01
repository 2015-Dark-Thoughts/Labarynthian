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
    private GridDrawData drawData;
    private LocationValidatorIntf locationValidator;
    private boolean paused = false;
    private boolean alive = true;
    private Direction direction;
    private boolean moving;

    
    public Number98(Point position, LocationValidatorIntf locationValidator, GridDrawData drawData){
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
//</editor-fold>

    public void draw(Graphics graphics) {
        Point topLeft = drawData.getCellSystemCorrdinate(position);
        graphics.setColor(Color.WHITE);
        graphics.fillOval(topLeft.x, topLeft.y, drawData.getCellWidth(), drawData.getCellHeight());
    }

    private final int BODY_POSITION = 0;

    public void stop() {
        this.moving = false;
    }
    
    public void start() {
        this.moving = true;
    }
    
    
    
    void move(Direction direction) {
        if ((!paused) && (moving)) {
            Point newPosition = (Point) position.clone();

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
                position = locationValidator.validateLocation(newPosition);
            }
        }

    }

    void move() {
        move(direction);
    }
}
