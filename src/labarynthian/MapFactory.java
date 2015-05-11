/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labarynthian;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import map.Item;
import map.Map;
import map.MapVisualizerIntf;

/**
 *
 * @author kevin.lawrence
 */
public class MapFactory {
    public static final Dimension DEFAULT_CELL_SIZE = new Dimension(10, 10);
    
    public static final String MAP_START_ROOM = "Start Room";
    public static final String MAP_MAZE_START = "Maze Start";
    public static final String MAP_MAZE_EXPLORE = "Maze Explore";
    public static final String MAP_OASIS = "Oasis";
    public static final String MAP_TREE_ROOM = "Tree Room";
    public static final String MAP_BOSS_ROOM = "Boss Room";

    public static final String ITEM_KEY = "Key";
    
    public static Map getMap(String mapName, Point position){
        Map map = new Map(null, DEFAULT_CELL_SIZE, new Dimension(5, 5));
        map.setMapVisualizer(new LabarynthianMapVisualizer());
        map.setPosition(position);

        switch (mapName){
            
            case MAP_MAZE_EXPLORE:
                map.setGridSize(40, 60);
                map.addPortal(new Point(25, 4), MAP_MAZE_START, new Point(18, 5));
                
                break;
                
            case MAP_MAZE_START:
                map.setGridSize(10, 50);
                map.addPortal(new Point(3, 9), MAP_START_ROOM, new Point(3, 1));
                map.addPortal(new Point(6, 6), MAP_MAZE_EXPLORE, new Point(22, 4));
                
                break;
                
                
            default:
            case MAP_START_ROOM:
                map.setGridSize(10, 10);
                map.addPortal(new Point(3, 0), MAP_MAZE_START, new Point(3, 8));
                map.addItem(new Item(new Point(4, 2), ITEM_KEY));
        }
        
        return map;
    }
    
    public static final Color WALL_COLOR =  new Color(200, 200, 200, 200);  //Color.LIGHT_GRAY;//new Color(0, 0, 0);
    public static final Color PORTAL_COLOR =  new Color(200, 0, 50, 200);  
    
    private static class LabarynthianMapVisualizer implements MapVisualizerIntf {
        
        @Override
        public void draw(Map map, Graphics graphics) {
            // draw full outline
            graphics.setColor(WALL_COLOR);
            
            int wallHeight = map.getGrid().getCellHeight();
            int wallWidth = map.getGrid().getCellWidth();
            Dimension roomInterior = new Dimension(map.getCellWidth() * map.getGrid().getColumns(),
                                                   map.getCellHeight() * map.getGrid().getRows());
            Dimension roomExterior = new Dimension(map.getCellWidth() * (map.getGrid().getColumns() + 2),
                                                   map.getCellHeight() * (map.getGrid().getRows() + 2));
            Point topLeft = new Point(map.getPosition().x - map.getGrid().getCellWidth(), 
                                map.getPosition().y - map.getGrid().getCellHeight());
            
            graphics.fill3DRect(topLeft.x, topLeft.y, wallWidth, roomExterior.height, true); //left wall
            graphics.fill3DRect(topLeft.x, topLeft.y, roomExterior.width, wallHeight, true); // top wall
            graphics.fill3DRect(topLeft.x + roomInterior.width + map.getCellWidth(), topLeft.y, wallWidth, roomExterior.height, true); //right wall
            graphics.fill3DRect(topLeft.x, topLeft.y + roomInterior.height + map.getCellHeight(), roomExterior.width, wallHeight, true); // bottom wall
            
            
            // design and debugging stuff
            map.getGrid().paintComponent(graphics);
            
            graphics.setColor(PORTAL_COLOR);
            map.getPortalLocations().stream().forEach((portalLocation) -> {
                Point tl = map.getGrid().getCellSystemCoordinate(portalLocation);
                graphics.fillOval(tl.x, tl.y, wallWidth, wallHeight);
            });
        }
        
    }
    
}
