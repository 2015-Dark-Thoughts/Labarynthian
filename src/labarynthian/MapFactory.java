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
//    public static final String MAP_ = "";
    
    
    public static Map getMap(String mapName){
        Map map;

        switch (mapName){
            
            case MAP_MAZE_START:
                map = new Map(null, DEFAULT_CELL_SIZE, new Dimension(10, 10));
                
                break;
                
            default:
            case MAP_START_ROOM:
                map = new Map(null, DEFAULT_CELL_SIZE, new Dimension(10, 10));
                map.setMapVisualizer(new LabarynthianMapVisualizer());
        }
        
        return map;
    }
    
    public static final Color WALL_COLOR = Color.LIGHT_GRAY;//new Color(0, 0, 0);
    
    private static class LabarynthianMapVisualizer implements MapVisualizerIntf {
        
        @Override
        public void draw(Map map, Graphics graphics) {
            // draw full outline
            graphics.setColor(WALL_COLOR);
            
            int wallHeight = map.getGrid().getCellHeight();
            int wallWidth = map.getGrid().getCellWidth();
            Dimension roomInterior = new Dimension(map.getGrid().getCellWidth() * map.getGrid().getColumns(),
                                                   map.getGrid().getCellHeight() * map.getGrid().getRows());
            Dimension roomExterior = new Dimension(map.getGrid().getCellWidth() * (map.getGrid().getColumns() + 2),
                                                   map.getGrid().getCellHeight() * (map.getGrid().getRows() + 2));
            Point topLeft = new Point(map.getPosition().x - map.getGrid().getCellWidth(), 
                                map.getPosition().y - map.getGrid().getCellHeight());
            
            graphics.fill3DRect(topLeft.x, topLeft.y, wallWidth, roomExterior.height, true); //left wall
            graphics.fill3DRect(topLeft.x, topLeft.y, roomExterior.width, wallHeight, true); // top wall
            graphics.fill3DRect(topLeft.x + roomInterior.width, topLeft.y, wallWidth, roomExterior.height, true); //right wall
            graphics.fill3DRect(topLeft.x, topLeft.y + roomExterior.height, roomExterior.width, wallHeight, true); // bottom wall
            
//            graphics.fill3DRect(map.getPosition().x - map.getGrid().getCellWidth(), 
//                                map.getPosition().y - map.getGrid().getCellHeight(), 
//                                map.getGrid().getCellWidth(), 
//                                map.getGrid().getCellHeight() * (map.getGrid().getRows() + 2), true);
            
            // overlay
            
            
        }
        
    }
    
}
