/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labarynthian;

import java.awt.Dimension;
import map.Map;

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
                
        }
        
        
        return map;
    }
    
}
