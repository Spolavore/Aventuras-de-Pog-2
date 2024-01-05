package utils;

import java.awt.image.BufferedImage;

public abstract class Constants {

    public static class Directions {
        public static final int LEFT = -1;
        public static final int RIGHT = 1;
        public static final int UP = 2;
        public static final int DOWN = -2;
    }

    public static class GameDimentions {
        public final static int TILES_DEFAULT_SIZE = 32;
        public final static float SCALE = 1.5f;
        public final static int TILES_IN_WIDTH = 26;
        public final static int TILES_IN_HEIGHT = 14;
        public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
        public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
        public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    }

    public static class Sprites {
        public final static String PLAYER = "/player/char_blue.png";
        public final static String GROUND_TILE = "/tiles/Tile_10.png";
        public final static String GROUND_TILE_2 = "/tiles/Tile_11.png";
        public final static String LEFT_GROUND_TILE = "/tiles/Tile_09.png";
        public final static String RIGHT_GROUND_TILE = "/tiles/Tile_12.png";
        public final static String PLATAFORM_STONE_TILE = "/tiles/Tile_30.png";

    }

    public static class LevelDefaultAssets {

        public static String LEVEL_1_FLOOR = Sprites.GROUND_TILE;
        public static String LEVEL_1_LEFT_FLOOR_BORDER = "/tiles/left_grass_tile.png";
        public static String LEVEL_1_RIGHT_FLOOR_BORDER = "/tiles/right_grass_tile.png";
        public static String LEVEL_1_GRASS_BLOCK = "/tiles/grass_tile.png";
        public static String LEVEL_1_RIGHT_GRASS_BORDER = "/tiles/right_border_grass_tile.png";
        public static String LEVEL_1_LIMITOR = "/tiles/left_border.png";
        public static String LEVEL_1_PLATAFORM = Sprites.PLATAFORM_STONE_TILE;
        public static String LEVEL_1_STONE_PLATAFORM = "/tiles/block_stone.png";
        
        //public static String LEVEL_2_FLOOR =
        //public static String LEVEL_2_LEFT_FLOOR_BORDER = 
        //public static String LEVEL_2_RIGHT_FLOOR_BORDER = 

        //public static String LEVEL_3_FLOOR = 
        //public static String LEVEL_3_LEFT_FLOOR_BORDER = 
        //public static String LEVEL_3_RIGHT_FLOOR_BORDER = 

        public static String END_POINT = "/tiles/Tile_95.png";
    }

    public static class BufferedImagesAssets{
        public static BufferedImage playerLifeImg = AssetsHandler.LoadAssets("/player/player_life.png");
        public static BufferedImage chestImage = AssetsHandler.LoadAssets("/objects/Chests.png");
    }

    public static class LevelMatrix {
        // Como a tela está setada para 1248 672 precisamos de 39 tyles de chão e 21 de
        // altura
        public static final char[][] Level1Map = {
                "                                       ".toCharArray(),
                "                                       ".toCharArray(),
                "                                       ".toCharArray(),
                "                        c              ".toCharArray(),
                "                       ===             ".toCharArray(),
                "                        =              ".toCharArray(),
                "                            ===        ".toCharArray(),
                "                             =         ".toCharArray(),
                "                                     + ".toCharArray(),
                "                                 ======".toCharArray(),
                "                                  =====".toCharArray(),
                "                          ======   ====".toCharArray(),
                "                           ====    ====".toCharArray(),
                "                   =====    ==      ===".toCharArray(),
                "                    ===             ===".toCharArray(),
                "                                    ===".toCharArray(),
                "            =====                    ==".toCharArray(),
                "             ===                     ==".toCharArray(),
                "      ====                            =".toCharArray(),
                "    ======                            =".toCharArray(),
                "(=====================================)".toCharArray()
        };

        public static final char[][] Level2Map = {
                "                                       ".toCharArray(),
                "                                       ".toCharArray(),
                "                                       ".toCharArray(),
                "                                       ".toCharArray(),
                "                                       ".toCharArray(),
                "                                       ".toCharArray(),
                "         c                  +          ".toCharArray(),
                "        ====             =====         ".toCharArray(),
                "                          ===          ".toCharArray(),
                "           ==      ===     =           ".toCharArray(),
                "                  =====                ".toCharArray(),
                "   c        ====      ==               ".toCharArray(),
                "   =                                   ".toCharArray(),
                "       ===                             ".toCharArray(),
                "                   =======             ".toCharArray(),
                "                    =====              ".toCharArray(),
                "            =====     =                ".toCharArray(),
                "             ===                       ".toCharArray(),
                "                                       ".toCharArray(),
                "                                       ".toCharArray(),
                "(=====================================)".toCharArray()
        };

         public static final char[][] Level3Map = {
                "                                       ".toCharArray(),
                "                                       ".toCharArray(),
                "                                       ".toCharArray(),
                "           +                           ".toCharArray(),
                "          ======                       ".toCharArray(),
                "           ====                        ".toCharArray(),
                "                                       ".toCharArray(),
                "                  =====                ".toCharArray(),
                "             c     ===   ==            ".toCharArray(),
                "           =====                       ".toCharArray(),
                "            ===              ======== =".toCharArray(),
                "                              ======= =".toCharArray(),
                "                      =====    ====== =".toCharArray(),
                "                       ===       ==== =".toCharArray(),
                "                ====               c  =".toCharArray(),
                "                 ==               =====".toCharArray(),
                "           ===                      ===".toCharArray(),
                "            =                        ==".toCharArray(),
                "      ===                             =".toCharArray(),
                "     ===                               ".toCharArray(),
                "(=====================================)".toCharArray()
        };
    }


}

// Distância Horizontal Máxima = 3 espaços
// Distância Vertical Máxima = 5