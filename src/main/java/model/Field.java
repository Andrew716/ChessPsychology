package model;

import java.util.*;

/**
 * Created by phoenix on 21.12.16.
 */
public class Field {

    private int x;
    private int y;
    private static Map<Integer, Character> horizontal;
    private static Map<Integer, Integer> vertical;
    private static Map<Character, Integer> invertedHorizontal;
    private static Map<Integer, Integer> invertedVertical;

    static {
        invertedVertical = new LinkedHashMap<Integer, Integer>();
        invertedVertical.put(8, 0);
        invertedVertical.put(7, 1);
        invertedVertical.put(6, 2);
        invertedVertical.put(5, 3);
        invertedVertical.put(4, 4);
        invertedVertical.put(3, 5);
        invertedVertical.put(2, 6);
        invertedVertical.put(1, 7);
    }

    static {
        invertedHorizontal = new LinkedHashMap<Character, Integer>();
        invertedHorizontal.put('a', 0);
        invertedHorizontal.put('b', 1);
        invertedHorizontal.put('c', 2);
        invertedHorizontal.put('d', 3);
        invertedHorizontal.put('e', 4);
        invertedHorizontal.put('f', 5);
        invertedHorizontal.put('g', 6);
        invertedHorizontal.put('h', 7);
    }

    static {
        horizontal = new LinkedHashMap<Integer, Character>();
        horizontal.put(0, 'a');
        horizontal.put(1, 'b');
        horizontal.put(2, 'c');
        horizontal.put(3, 'd');
        horizontal.put(4, 'e');
        horizontal.put(5, 'f');
        horizontal.put(6, 'g');
        horizontal.put(7, 'h');
    }

    static {
        vertical = new LinkedHashMap<Integer, Integer>();
        vertical.put(0, 8);
        vertical.put(1, 7);
        vertical.put(2, 6);
        vertical.put(3, 5);
        vertical.put(4, 4);
        vertical.put(5, 3);
        vertical.put(6, 2);
        vertical.put(7, 1);
    }

    public Field(int x, int y){
        if (x>=0 && x<Board.SIZE && y>=0 && y<Board.SIZE){
            this.x = x;
            this.y = y;
        } else {
            throw new IllegalArgumentException("x = " + x + " y = " + y);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Map<Integer, Character> getHorizontal() {
        return horizontal;
    }

    public static Map<Integer, Integer> getVertical() {
        return vertical;
    }

    public static Map<Character, Integer> getInvertedHorizontal() {
        return invertedHorizontal;
    }

    public static Map<Integer, Integer> getInvertedVertical() {
        return invertedVertical;
    }

    public boolean isTaken(){
        Board board = Board.getInstance();
        for(Observer figure : board.getFigures()){
            if(((Figure)figure).getField().getX() == this.getX() && ((Figure) figure).getField().getY() == this.getY()){
                return true;
            }
        }
        return false;
    }

    //TODO refactor this method. Should be placed in Board class.
    public boolean isUnderInfluence(Color color){
        Set  figures;
        if (color == Color.BLACK){
            figures = Board.getInstance().getBlackFigures();
        }else {
            figures = Board.getInstance().getWhiteFigures();
        }
        for (Object figure : figures){
            for (Object field : ((Figure)figure).getAttackedFields()){
                if (this.equals(field)){
                    return true;
                }
            }
        }
        return false;
    }

    //TODO Should be placed in Board class.
//    public Set fieldsAreUnderInfluence(Color color){
//        Set  figures = new LinkedHashSet();
//        if (color == Color.BLACK){
//            figures = Board.getBlackFigures();
//        }else {
//            figures = Board.getInstance().getWhiteFigures();
//        }
//        for (Object figure : figures){
//            for (Object field : ((Figure)figure).getAttackedFields()){
//                if (this.equals(field)){
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    //TODO refactor this method. Should be placed in Board class.
    public Figure getFigureByField(){
        for(Observer figure : Board.getInstance().getFigures()){
            if(((Figure)figure).getField().getX() == this.getX() && ((Figure) figure).getField().getY() == this.getY()){
                return (Figure) figure;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        if (x == field.x && y == field.y) return true;
        return false;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return horizontal.get(y) + "" + vertical.get(x);
    }
}
