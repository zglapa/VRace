package logic;

import static java.lang.Integer.*;

public class Sizes {
    static int HEIGHT;
    static int WIDTH;
    static int ROWS;
    static int COLUMNS;
    static double RATIO;
    static double SMALLDOTSIZE;
    static double BIGDOTSIZE;
    static double STROKEWIDTH;
    static double VECTORWIDTH;
    static double TRACKWIDTH;
    static double BOARDINDENT;
    static double NOTICEFONT;
    public static void set(int H, int W, int R, int C){
        HEIGHT = H;
        WIDTH = W;
        ROWS = R;
        COLUMNS = C;
        RATIO = min(HEIGHT, WIDTH)/900f * 50f/max(ROWS, COLUMNS);
        SMALLDOTSIZE =(3f/4)*RATIO;
        BIGDOTSIZE = (3f)*RATIO;
        STROKEWIDTH = (2f)*RATIO;
        VECTORWIDTH = (3f)*RATIO;
        TRACKWIDTH = 2f/3*VECTORWIDTH;
        BOARDINDENT = 17f*RATIO;
        NOTICEFONT = 20f*RATIO;
    }
    public static double getSMALLDOTSIZE(){
        return SMALLDOTSIZE;
    }
    public static double getBIGDOTSIZE() {
        return BIGDOTSIZE;
    }
    public static double getSTROKEWIDTH() {
        return STROKEWIDTH;
    }
    public static double getVECTORWIDTH() {
        return VECTORWIDTH;
    }
    public static double getTRACKWIDTH() {
        return TRACKWIDTH;
    }
    public static double getBOARDINDENT(){
        return BOARDINDENT;
    }
    public static double getNOTICEFONT() {
        return NOTICEFONT;
    }
    public static int getCOLUMNS(){
        return COLUMNS;
    }
    public static int getROWS(){
        return ROWS;
    }
    public static int getHEIGHT(){
        return HEIGHT;
    }
    public static int getWIDTH(){
        return WIDTH;
    }
}
