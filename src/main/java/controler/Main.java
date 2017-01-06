package controler;

import model.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by phoenix on 21.12.16.
 */
public class Main {

    private final static String REG_EX_TURN = "^\\d+\\.\\s*(\\S+)\\s*(\\S+)*$";
    private final static String REG_EX_SURNAMES = "";
    private final static String PATH_TO_FILE = "src/main/resources/2";
    private static List<String> whiteTurns = new ArrayList<String>();
    private static List<String> blackTurns = new ArrayList<String>();

    public static void main(String[] args){
//        Board board = Board.getInstance();
//        printFigures();
//        printFile();
//        System.out.println("White figures");
//        Iterator figureIterator = Board.getFigures().iterator();
//        while (figureIterator.hasNext()){
//            Figure currentFigure = (Figure) figureIterator.next();
//            if (currentFigure.getColor() == Color.WHITE){
//                System.out.println(currentFigure.toString() + currentFigure.getPossibleFieldsToMove());
//            }
//        }
//        System.out.println("Black figures");
//        Iterator iterator1 = Board.getFigures().iterator();
//        while (iterator1.hasNext()){
//            Figure currentFigure = (Figure) iterator1.next();
//            if (currentFigure.getColor() == Color.BLACK){
//                System.out.println(currentFigure.toString() + currentFigure.getPossibleFieldsToMove());
//            }
//        }
    }

    public static void printFile(){
        Pattern pattern = Pattern.compile(REG_EX_TURN);
        try{
            File text = new File(PATH_TO_FILE);
            Scanner scnr = new Scanner(text);
            String sCurrentLine;
            while (scnr.hasNextLine()) {
                sCurrentLine = scnr.nextLine();
                Matcher matcher = pattern.matcher(sCurrentLine);
                if (matcher.matches()){
                    whiteTurns.add(matcher.group(1));
                    Parser.parseTurn(matcher.group(1), true);
                    printFigures();

                    System.out.println("White figures");
                    Iterator figureIterator = Board.getFigures().iterator();
                    while (figureIterator.hasNext()){
                        Figure currentFigure = (Figure) figureIterator.next();
                        if (currentFigure.getColor() == Color.WHITE){
                            System.out.println(currentFigure.toString() + currentFigure.getPossibleFieldsToMove() + currentFigure.getAttackedFields() + currentFigure.getWhoCouldBeKilled());
                        }
                    }
                    System.out.println("Black figures");
                    Iterator iterator1 = Board.getFigures().iterator();
                    while (iterator1.hasNext()){
                        Figure currentFigure = (Figure) iterator1.next();
                        if (currentFigure.getColor() == Color.BLACK){
                            System.out.println(currentFigure.toString() + currentFigure.getPossibleFieldsToMove() + currentFigure.getAttackedFields()+ currentFigure.getWhoCouldBeKilled());
                        }
                    }
                    System.out.println(sCurrentLine + " ==== " +  matcher.group(1) + "   " + matcher.group(2));

                    blackTurns.add(matcher.group(2));
                    Parser.parseTurn(matcher.group(2), false);
                    printFigures();
                    System.out.println("White figures");
                    Iterator iterator = Board.getFigures().iterator();
                    while (iterator.hasNext()){
                        Figure currentFigure = (Figure) iterator.next();
                        if (currentFigure.getColor() == Color.WHITE){
                            System.out.println(currentFigure.toString() + currentFigure.getPossibleFieldsToMove() + currentFigure.getAttackedFields() + currentFigure.getWhoCouldBeKilled());
                        }
                    }
                    System.out.println("Black figures");
                    Iterator iterator2 = Board.getFigures().iterator();
                    while (iterator2.hasNext()){
                        Figure currentFigure = (Figure) iterator2.next();
                        if (currentFigure.getColor() == Color.BLACK){
                            System.out.println(currentFigure.toString() + currentFigure.getPossibleFieldsToMove() + currentFigure.getAttackedFields() + currentFigure.getWhoCouldBeKilled());
                        }
                    }
                    System.out.println(sCurrentLine + " ==== " +  matcher.group(1) + "   " + matcher.group(2));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printFigures(){
        System.out.println();
        int counter = 1;
        for (int i = 0; i < Board.SIZE; i++){
            System.out.print(Field.getVertical().get(i) + "  ");
            for (int j = 0; j < Board.SIZE; j++){
                Field currentPoint = new Field(i, j);
                if (currentPoint.isTaken()){
                    System.out.print(" " + print(currentPoint.getFigureByField()) + " ");
                }else {
                    System.out.print("   ");
                }
            }
            if (counter == Board.SIZE){
                System.out.println();
                System.out.println();
                System.out.print("    ");
                for (int k = 0; k < Board.SIZE; k++){
                    System.out.print(Field.getHorizontal().get(k) + "  ");
                }
            }
            counter++;
            System.out.println();
        }
    }

    public static String print(Figure figure){
        if (figure.getClass() == King.class){
            if (figure.getColor() == Color.WHITE){
                return "K";
            }else {
                return "k";
            }
        }
        if (figure.getClass() == Queen.class){
            if (figure.getColor() == Color.WHITE){
                return "Q";
            }else {
                return "q";
            }
        }
        if (figure.getClass() == Rock.class){
            if (figure.getColor() == Color.WHITE){
                return "R";
            }else {
                return "r";
            }
        }
        if (figure.getClass() == Knight.class){
            if (figure.getColor() == Color.WHITE){
                return "N";
            }else {
                return "n";
            }
        }
        if (figure.getClass() == Bishop.class){
            if (figure.getColor() == Color.WHITE){
                return "B";
            }else {
                return "b";
            }
        }
        if (figure.getClass() == Pawn.class){
            if (figure.getColor() == Color.WHITE){
                return "P";
            }else {
                return "p";
            }
        }
        return null;
    }
}
