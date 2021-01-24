package logic;

import javafx.util.Pair;

import java.util.ArrayList;

public class HighScoreList extends ArrayList<ArrayList<Pair<String, Integer>>> {
    public HighScoreList(){
        super();
        for(int i = 15; i <= 50; i = i+5){
            this.add(new ArrayList<>());
            for(int k = 0; k < 10; ++k){
                this.get(i).add(new Pair<>("-", null));
            }
        }
    }

    @Override
    public ArrayList<Pair<String, Integer>> get(int index) {
        if(index % 5 != 0 || index < 15){
            throw new IndexOutOfBoundsException();
        }
        int realIndex = (index-15)/5;
        return super.get(realIndex);
    }
}
