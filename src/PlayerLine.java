//proper imports
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Random;
import java.util.Scanner;

public class PlayerLine {
    //constructor class that initializes a tree map called pLine that holds integers as keys and an array as the values
    TreeMap<Integer, Integer[]> pLine;
    Integer[] fLine;
    ArrayList<ArrayList<Integer>> linePlacements = new ArrayList<ArrayList<Integer>>();
    public PlayerLine(){
        pLine = new TreeMap<Integer, Integer[]>();
        fLine = new Integer[7];
        //add an array of 1 integer to the treemap
        pLine.put(0, new Integer[]{3});
        //add an array of 2 nulls to the treemap
        pLine.put(1, new Integer[]{3, 3});
        //add an array of 3 nulls to the treemap
        pLine.put(2, new Integer[]{3, 3, 3});
        //add an array of 4 nulls to the treemap
        pLine.put(3, new Integer[]{3, 3, 3, 3});
        //add an array of 5 nulls to the treemap
        pLine.put(4, new Integer[]{3, 3, 3, 3, 3});
        //FILL THE FLOOR LINE WITH INTEGERS 0-7
        fLine[0] = 0;
        fLine[1] = 1;
        fLine[2] = 2;
        fLine[3] = 2;
        fLine[4] = 3;
        fLine[5] = 1;
        fLine[6] = 2;
        getLineContents();
    }
    //return linePlacements
    public ArrayList<ArrayList<Integer>> getLinePlacements(){
        System.out.println(linePlacements);
        return linePlacements;
    }
    //transform the floorline into an arraylist of integers
    public ArrayList<Integer> getFloorLine(){
        ArrayList<Integer> floorLine = new ArrayList<Integer>();
        for (int i = 0; i < fLine.length; i++){
            floorLine.add(fLine[i]);
        }
        return floorLine;
    }
    //gets the  amount of space left in an array in the treemap
    public int getRowSpace(Integer row){
        int spaceLeft = 0;
        for (int i = 0; i < pLine.get(row).length; i++){
            if (pLine.get(row)[i] == 6){
                spaceLeft++;
            }
        }
        return spaceLeft;
    }
    //gets the type of tiles in an array in the treemap
    public int getRowType(Integer row){
        Integer type = null;
        for (int i = 0; i < pLine.get(row).length; i++){
            if (pLine.get(row)[i] != 6){
                type = pLine.get(row)[i];
            }
        }
        return type;
    }
    //checks if a an array in the treemap is complete
    public boolean isRowComplete(Integer row){
        boolean complete = true;
        for (int i = 0; i < pLine.get(row).length; i++){
            if (pLine.get(row)[i] == 6){
                complete = false;
            }
        }
        return complete;
    }
    // empties an array in the treemap
    public void emptyRow(Integer row){
        for (int i = 0; i < pLine.get(row).length; i++){
            pLine.get(row)[i] = 6;
        }
    }
    //adds an arraylist of tiles to an array in the treemap
    public void addTilesToRow(Integer row, ArrayList<Integer> tiles){
        //check if the row has space for the tiles
        if (getRowSpace(row) >= tiles.size()){
            for (int i = 0; i < pLine.get(row).length; i++){
                if (pLine.get(row)[i] == 6){
                    pLine.get(row)[i] = tiles.get(0);
                    tiles.remove(0);
                }
            }
        }   
    }
    //find the amouunt of tiles in a row
    public int getRowSize(Integer row){
        int size = 0;
        for (int i = 0; i < pLine.get(row).length; i++){
            if (pLine.get(row)[i] != 6){
                size++;
            }
        }
        return size;
    }
    //get treemap contents in the form of an arraylist, the first value will store the type, the second value will store the amount of tiles, and the third will store the row
    public void getLineContents(){
        for (int i = 0; i < pLine.size(); i++){
            ArrayList<Integer> lineContents = new ArrayList<Integer>();
            if (getRowType(i) > -1){
                lineContents.add(getRowType(i));
                lineContents.add(getRowSize(i));
                lineContents.add(i);
            }
            linePlacements.add(lineContents);
        }
    }
    //add starter tile of value 5 to an available spot on the floor line
    public void addStarterTile(){
        for (int i = 0; i < fLine.length; i++){
            if (fLine[i] == 6){
                fLine[i] = 5;
                break;
            }
        }
    }
    //finds the rows that can hold the tiles in the arraylist -- USE TO HIGHLIGHT
    public ArrayList<Integer> getAvailableRowsP(ArrayList<Integer> tiles){
        ArrayList<Integer> rows = new ArrayList<Integer>();
        for (int i = 0; i < pLine.size(); i++){
            if (getRowSpace(i) >= tiles.size()){
                rows.add(i);
            }
        }
        return rows;
    }
    //checks if a row is available to hold the tiles in the arraylist
    public boolean isRowAvailableP(Integer row, ArrayList<Integer> tiles){
        if (getRowType(row) == tiles.get(0) && getRowSpace(row) >= tiles.size()){
            return true;
        }
        return false;
    }
    //empty the floor line
    public void emptyFloorLine(){
        for (int i = 0; i < fLine.length; i++){
            fLine[i] = 6;
        }
    }
    //return the number of tiles in the floor line
    public int getFloorLineSize(){
        int size = 0;
        for (int i = 0; i < fLine.length; i++){
            if (fLine[i] != 6){
                size++;
            }
        }
        return size;
    }
    //gets the space left in the floor line
    public int getFloorLineSpace(){
        int space = 0;
        for (int i = 0; i < fLine.length; i++){
            if (fLine[i] == 6){
                space++;
            }
        }
        return space;
    }
    //is floor line available for an arraylist of tiles
    public boolean isFloorLineAvailable(ArrayList<Integer> tiles){
        if (getFloorLineSpace() >= tiles.size()){
            return true;
        }
        return false;
    }
    //add an arraylist of tiles to the floor line
    public void addFloorLine(ArrayList<Integer> tiles){
        if(isFloorLineAvailable(tiles)){
            for (int i = 0; i < fLine.length; i++){
                if (fLine[i] == 6){
                    fLine[i] = tiles.get(0);
                    tiles.remove(0);
                }
            }
        }
    }
    //to string method that returns the treemap as a string with new lines after every key and the floor line 
    public String toString(){
        String s = "";
        for (int i = 0; i < pLine.size(); i++){
            s += "Row " + i + " : ";
            for (int j = 0; j < pLine.get(i).length; j++){
                s += pLine.get(i)[j] + " ";
            }
            s += "\n";
        }
        s += "Floor Line: ";
        for (int i = 0; i < fLine.length; i++){
            s += fLine[i] + " ";
        }
        return s;
    }
    //checks if the floor line has a starter tile
    public boolean hasStarterTile(){
        for (int i = 0; i < fLine.length; i++){
            if (fLine[i] == 5){
                return true;
            }
        }
        return false;
    }
}
