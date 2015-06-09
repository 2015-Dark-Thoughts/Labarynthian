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
import map.ObstacleType;

/**
 *
 * @author ConnerCrabtree
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

    public static Map getMap(String mapName, Point position) {
        Map map = new Map(null, DEFAULT_CELL_SIZE, new Dimension(5, 5));
        map.setMapVisualizer(new LabarynthianMapVisualizer());
        map.setPosition(position);

        switch (mapName) {

            default:
            case MAP_START_ROOM:
                map.setGridSize(10, 10);
                map.addPortal(new Point(1, 1), MAP_MAZE_START, new Point(4, 1));
                map.addItem(new Item(new Point(4, 2), ITEM_KEY));
                System.out.println("MAP_START_ROOM");

            case MAP_MAZE_START:
                map.setGridSize(10, 50);
                map.addPortal(new Point(1, 1), MAP_START_ROOM, new Point(5, 1));
                map.addPortal(new Point(1, 2), MAP_MAZE_EXPLORE, new Point(6, 1));


                System.out.println("MAP_MAZE_START");

                break;

            case MAP_MAZE_EXPLORE:

                map.setGridSize(50, 60);
                map.addPortal(new Point(1, 1), MAP_MAZE_START, new Point(7, 1));
                map.addPortal(new Point(1, 1), MAP_OASIS, new Point(8, 1));
                System.out.println("MAP_MAZE_EXPLORE");
                
                  // TO DO: Make the maze start room spawn player at 28, 0
                //Rows originating from line 0
                //<editor-fold defaultstate="collapsed" desc="ETHAN PB">
                
                map.addObstacleRange(new Point(3, 3), new Point(0, 3), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(6, 0), new Point(6, 6), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(3, 6), new Point(12, 6), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(3, 6), new Point(3, 12), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(12, 3), new Point(12, 12), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(12, 9), new Point(15, 9), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(18, 0), new Point(18, 3), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(18, 3), new Point(21, 3), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(21, 3), new Point(21, 9), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(30, 0), new Point(30, 6), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(24, 6), new Point(33, 6), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(24, 3), new Point(24, 6), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(24, 3), new Point(27, 3), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(33, 6), new Point(33, 9), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(48, 0), new Point(48, 3), ObstacleType.BARRIER);
                
                
//</editor-fold>
                
                //<editor-fold defaultstate="collapsed" desc="CONNER PB?">
//                map.addObstacleRange(new Point(0, 47), new Point(2, 47), ObstacleType.BARRIER);
//                map.addObstacleRange(new Point(6, 41), new Point(6, 50), ObstacleType.BARRIER);
//                map.addObstacleRange(new Point(3, 44), new Point(6, 44), ObstacleType.BARRIER);
//                map.addObstacleRange(new Point(0, 41), new Point(3, 41), ObstacleType.BARRIER);
//                map.addObstacleRange(new Point(3, 38), new Point(3, 41), ObstacleType.BARRIER);
//                map.addObstacleRange(new Point(0, 27), new Point(3, 27), ObstacleType.BARRIER);
//                map.addObstacleRange(new Point(3, 27), new Point(2, 24), ObstacleType.BARRIER);
//                map.addObstacleRange(new Point(2, 24), new Point(6, 24), ObstacleType.BARRIER);
//                map.addObstacleRange(new Point(6, 24), new Point(6, 21), ObstacleType.BARRIER);
                //row 30
                map.addObstacleRange(new Point(3, 30), new Point(5, 30), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(12, 30), new Point(17, 30), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(30, 30), new Point(35, 30), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(45, 30), new Point(50, 30), ObstacleType.BARRIER);
                //row33
                map.addObstacleRange(new Point(0, 33), new Point(2, 33), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(15, 33), new Point(20, 33), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(24, 33), new Point(41, 33), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(54, 33), new Point(59, 33), ObstacleType.BARRIER);
                //row36
                map.addObstacleRange(new Point(6, 36), new Point(8, 36), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(12, 36), new Point(14, 36), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(27, 36), new Point(29, 36), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(33, 36), new Point(44, 36), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(51, 36), new Point(53, 36), ObstacleType.BARRIER);
                //row39
                map.addObstacleRange(new Point(0, 39), new Point(5, 39), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(9, 39), new Point(17, 39), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(24, 39), new Point(26, 39), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(47, 39), new Point(50, 39), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(57, 39), new Point(59, 39), ObstacleType.BARRIER);
                //row42
                map.addObstacleRange(new Point(3, 42), new Point(5, 42), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(12, 42), new Point(14, 42), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(27, 42), new Point(35, 42), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(45, 42), new Point(56, 42), ObstacleType.BARRIER);
                //row45
                map.addObstacleRange(new Point(3, 45), new Point(11, 45), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(15, 45), new Point(23, 45), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(30, 45), new Point(38, 45), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(51, 45), new Point(53, 45), ObstacleType.BARRIER);
                //row47
                map.addObstacleRange(new Point(0, 47), new Point(2, 47), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(30, 47), new Point(32, 47), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(36, 47), new Point(38, 47), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(45, 47), new Point(50, 47), ObstacleType.BARRIER);
                map.addObstacleRange(new Point(54, 47), new Point(56, 47), ObstacleType.BARRIER);
                
//</editor-fold>
                
                map.getGrid().setShowCellCoordinates(true);
                break;

            case MAP_OASIS:
                map.setGridSize(300, 300);
                map.addPortal(new Point(1, 1), MAP_MAZE_EXPLORE, new Point(9, 1));
                System.out.println("MAP_OASIS");

            case MAP_TREE_ROOM:

                break;

            case MAP_BOSS_ROOM:

                map.setGridSize(60, 60);
                map.addPortal(new Point(28, 0), MAP_MAZE_START, new Point(18, 5));

              

                // Rows originating from line 3
//                map.addObstacleRange(new Point(12,9), new Point(15,9), ObstacleType.BARRIER);
//                map.addObstacleRange(new Point(12,9), new Point(15,9), ObstacleType.BARRIER);
                break;

//            case MAP_MAZE_START:
//                map.setGridSize(10, 50);
//                map.addPortal(new Point(3, 9), MAP_START_ROOM, new Point(3, 1));
//                map.addPortal(new Point(49, 5), MAP_MAZE_EXPLORE, new Point(24, 0));
//                map.addPortal(new Point(49, 4), MAP_MAZE_EXPLORE, new Point(24, 0));
//
//                
//                break;
        }

        return map;
    }

    public static final Color WALL_COLOR = new Color(200, 200, 200, 200);  //Color.LIGHT_GRAY;//new Color(0, 0, 0);
    public static final Color PORTAL_COLOR = new Color(200, 0, 50, 200);

    public static final Color OBSTACLE_COLOR = new Color(200, 200, 50, 200);

    public static final Color BARRIER_COLOR = new Color(200, 240, 240, 128);

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

            graphics.setColor(PORTAL_COLOR);
            map.getObstacleLocations().stream().forEach((obstacleLocation) -> {
                Point tl = map.getGrid().getCellSystemCoordinate(obstacleLocation);
                graphics.fillRect(tl.x, tl.y, wallWidth, wallHeight);
//                System.out.println("obstacle....");
            });

            graphics.setColor(BARRIER_COLOR);
            map.getObstacleLocations().stream().forEach((barrierLocation) -> {
                Point topleft = map.getGrid().getCellSystemCoordinate(barrierLocation);
                graphics.fillRect(topleft.x, topleft.y, wallWidth, wallHeight);

            });

        }

    }
}
