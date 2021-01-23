package logic;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Arrays;

public class Paints {
    private static ArrayList<Paint> paints = new ArrayList<>(Arrays.asList(Color.web("#2098DF"),Color.web("#C720DF"),Color.web("#DF6720"),Color.web("#38DF20")));
    public static Paint get(int i){
        if(i < paints.size()){
            return paints.get(i);
        }
        else throw new IndexOutOfBoundsException();
    }
}
