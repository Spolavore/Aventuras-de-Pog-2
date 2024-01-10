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

        public static String FLOOR = "/tiles/grass_tile.png";
        public static String LOWER_FLOOR = "/tiles/Tile_24.png";
        public static String LEFT_LOWER_FLOOR = "/tiles/Tile_69.png";
        public static String RIGHT_LOWER_FLOOR = "/tiles/Tile_70.png";
        public static String LEFT_FLOOR = "/tiles/left_grass_tile.png";
        public static String RIGHT_FLOOR = "/tiles/right_grass_tile.png";
        public static String PLATAFORM_MIDDLE = "/tiles/Tile_07.png";
        public static String PLATAFORM_LEFT = "/tiles/Tile_06.png";
        public static String PLATAFORM_RIGHT = "/tiles/Tile_08.png";

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
                "                                     c ".toCharArray(),
                "                                     <_".toCharArray(),
                "                                       ".toCharArray(),
                "                                       ".toCharArray(),
                "                                 <>    ".toCharArray(),
                "                                       ".toCharArray(),
                "                              +        ".toCharArray(),
                "                            (===)      ".toCharArray(),
                "                            [---]      ".toCharArray(),
                "                      (==)             ".toCharArray(),
                "                      [--]             ".toCharArray(),
                "                (==)                   ".toCharArray(),
                "                [--]                   ".toCharArray(),
                "                                       ".toCharArray(),
                "          (==)                         ".toCharArray(),
                "          [--]                         ".toCharArray(),
                "     <_>                               ".toCharArray(),
                "                                       ".toCharArray(),
                "=======================================".toCharArray()
        };      /////////////////////////////////////////

        public static final char[][] Level2Map = {
                "                                       ".toCharArray(),
                "                                       ".toCharArray(),
                "                                       ".toCharArray(),
                "                                       ".toCharArray(),
                "                              +        ".toCharArray(),
                "                            <___>      ".toCharArray(),
                "         c                             ".toCharArray(),
                "        <__>           <_>             ".toCharArray(),
                "                                       ".toCharArray(),
                "             <>   <=>                  ".toCharArray(),
                "                                       ".toCharArray(),
                " c                                     ".toCharArray(),
                "<>                     <>              ".toCharArray(),
                "                                       ".toCharArray(),
                "    <>                                 ".toCharArray(),
                "        <_>         <_>                ".toCharArray(),
                "                                       ".toCharArray(),
                "             (===)                     ".toCharArray(),
                "                                       ".toCharArray(),
                "                                       ".toCharArray(),
                "=======================================".toCharArray()
        };

         public static final char[][] Level3Map = {
                "                                       ".toCharArray(),
                "                                       ".toCharArray(),
                "                                       ".toCharArray(),
                "            +                          ".toCharArray(),
                "           (==)                        ".toCharArray(),
                "           [--]                        ".toCharArray(),
                "                                       ".toCharArray(),
                "       <>         <___>                ".toCharArray(),
                "             c           <>            ".toCharArray(),
                "           (===)                       ".toCharArray(),
                "           [---]             <___>     ".toCharArray(),
                "                                       ".toCharArray(),
                "                       <__>            ".toCharArray(),
                "                                       ".toCharArray(),
                "                <___>               c  ".toCharArray(),
                "                                  (====".toCharArray(),
                "           <_>                    [----".toCharArray(),
                "                                       ".toCharArray(),
                "      <_>                              ".toCharArray(),
                "                                       ".toCharArray(),
                "=======================================".toCharArray()
        };
    }


}

// Distância Horizontal Máxima = 3 espaços
// Distância Vertical Máxima = 5