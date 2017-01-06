package model;

import java.util.*;

/**
 * Created by phoenix on 21.12.16.
 */
public class Board implements Subject{

    public final static byte SIZE = 8;
    private static Set<Observer> figures = new LinkedHashSet();
    private static Set<Observer> whiteFigures = new LinkedHashSet<Observer>();
    private static Set<Observer> blackFigures = new LinkedHashSet<Observer>();
    public final static Set<Field> listOfFields = new LinkedHashSet();
    private Field field;
    private Field previousTurn;
    private volatile static Board uniqueInstance;


    private Board(){

        Figure whitePawnA = new Pawn(new Field(6,0), Color.WHITE);
        Figure whitePawnB = new Pawn(new Field(6,1), Color.WHITE);
        Figure whitePawnC = new Pawn(new Field(6,2), Color.WHITE);
        Figure whitePawnD = new Pawn(new Field(6,3), Color.WHITE);
        Figure whitePawnE = new Pawn(new Field(6,4), Color.WHITE);
        Figure whitePawnF = new Pawn(new Field(6,5), Color.WHITE);
        Figure whitePawnG = new Pawn(new Field(6,6), Color.WHITE);
        Figure whitePawnH = new Pawn(new Field(6,7), Color.WHITE);
        Figure whiteRockA = new Rock(new Field(7,0), Color.WHITE);
        Figure whiteRockH = new Rock(new Field(7,7), Color.WHITE);
        Figure whiteKnightB = new Knight(new Field(7,1), Color.WHITE);
        Figure whiteKnightG = new Knight(new Field(7,6), Color.WHITE);
        Figure whiteBishopC = new Bishop(new Field(7,2), Color.WHITE);
        Figure whiteBishopF = new Bishop(new Field(7,5), Color.WHITE);
        Figure whiteQueen = new Queen(new Field(7,3), Color.WHITE);
        Figure whiteKing = new King(new Field(7,4), Color.WHITE);
        //Adding to figures
        figures.add(whitePawnA);
        figures.add(whitePawnB);
        figures.add(whitePawnC);
        figures.add(whitePawnD);
        figures.add(whitePawnE);
        figures.add(whitePawnF);
        figures.add(whitePawnG);
        figures.add(whitePawnH);
        figures.add(whiteRockA);
        figures.add(whiteRockH);
        figures.add(whiteKnightB);
        figures.add(whiteKnightG);
        figures.add(whiteBishopC);
        figures.add(whiteBishopF);
        figures.add(whiteQueen);
        figures.add(whiteKing);
        //Filling black figures on the board
        Figure blackPawnA = new Pawn(new Field(1,0), Color.BLACK);
        Figure blackPawnB = new Pawn(new Field(1,1), Color.BLACK);
        Figure blackPawnC = new Pawn(new Field(1,2), Color.BLACK);
        Figure blackPawnD = new Pawn(new Field(1,3), Color.BLACK);
        Figure blackPawnE = new Pawn(new Field(1,4), Color.BLACK);
        Figure blackPawnF = new Pawn(new Field(1,5), Color.BLACK);
        Figure blackPawnG = new Pawn(new Field(1,6), Color.BLACK);
        Figure blackPawnH = new Pawn(new Field(1,7), Color.BLACK);
        Figure blackRockA = new Rock(new Field(0,0), Color.BLACK);
        Figure blackRockH = new Rock(new Field(0,7), Color.BLACK);
        Figure blackKnightB = new Knight(new Field(0,1), Color.BLACK);
        Figure blackKnightG = new Knight(new Field(0,6), Color.BLACK);
        Figure blackBishopC = new Bishop(new Field(0,2), Color.BLACK);
        Figure blackBishopF = new Bishop(new Field(0,5), Color.BLACK);
        Figure blackQueen = new Queen(new Field(0,3), Color.BLACK);
        Figure blackKing = new King(new Field(0,4), Color.BLACK);
        //Adding to figures
        figures.add(blackPawnA);
        figures.add(blackPawnB);
        figures.add(blackPawnC);
        figures.add(blackPawnD);
        figures.add(blackPawnE);
        figures.add(blackPawnF);
        figures.add(blackPawnG);
        figures.add(blackPawnH);
        figures.add(blackRockA);
        figures.add(blackRockH);
        figures.add(blackKnightB);
        figures.add(blackKnightG);
        figures.add(blackBishopC);
        figures.add(blackBishopF);
        figures.add(blackQueen);
        figures.add(blackKing);
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                listOfFields.add(new Field(i,j));
            }
        }
        Iterator<Figure> iterator = Board.getFigures().iterator();
        while (iterator.hasNext()){
            Figure currentFigure = iterator.next();
            if (currentFigure.getColor() == Color.WHITE){
                whiteFigures.add(currentFigure);
            }else {
                blackFigures.add(currentFigure);
            }
            currentFigure.possibleTurns();
        }
    }

    public static Board getInstance(){
        if (uniqueInstance == null){
            uniqueInstance = new Board();
        }
        return uniqueInstance;
    }

    public static List<Figure> getFiguresByClass(Class clazz){
        List<Figure> returnedFigures = new ArrayList<Figure>();
        for (Object figure : figures){
            if (figure.getClass() == clazz){
                returnedFigures.add((Figure) figure);
            }
        }
        return returnedFigures;
    }

    public Field getPreviousTurn() {
        return previousTurn;
    }

    public void setPreviousTurn(Field previousTurn) {
        this.previousTurn = previousTurn;
    }

    public static Set<Observer> getWhiteFigures() {
        return whiteFigures;
    }

    public static Set<Observer> getBlackFigures() {
        return blackFigures;
    }

    public static Set getFigures() {
        return figures;
    }

    public void notify(Observer figure) {
        figure.update(field);
    }

    public void register(Observer figure) {
        figures.add(figure);
    }

    public void remove(Observer figure) {
        figures.remove(figure);
    }

    public void setCoordinates(Field field, Observer figure){
        if (figures.contains(figure)){
            this.field = field;
            notify(figure);
        }
        for (Observer figure1 : figures){
            ((Figure)figure1).possibleTurns();
            ((Figure)figure1).attackedFields();
        }
    }
}