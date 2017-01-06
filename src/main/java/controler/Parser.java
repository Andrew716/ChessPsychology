package controler;

import model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by phoenix on 21.12.16.
 */
public class Parser {

    private static List<Observer> candidates = new ArrayList<Observer>();
    private static String mainTurn;
    private static Field field;


    public static void parseTurn(String turn, boolean isWhite){
        candidates.clear();
        mainTurn = turn;
        field = parseTargetField(turn);
        if (isWhite){
            findingAndUpdating(turn, isWhite);
        }else {
            findingAndUpdating(turn, isWhite);
        }
    }

    private static void findingAndUpdating(String turn, boolean isWhite){
        if (turn.equalsIgnoreCase("O-O")){
            if (isWhite){
                Board.getInstance().setCoordinates(new Field(7, 6), new Field(7, 4).getFigureByField());
                Board.getInstance().setCoordinates(new Field(7, 5), new Field(7, 7).getFigureByField());
            }else {
                Board.getInstance().setCoordinates(new Field(0, 6), new Field(0, 4).getFigureByField());
                Board.getInstance().setCoordinates(new Field(0, 5), new Field(0, 7).getFigureByField());
            }
            return;
        }
        if (turn.equalsIgnoreCase("O-O-O")){
            if (isWhite){
                Board.getInstance().setCoordinates(new Field(7, 2), new Field(7, 4).getFigureByField());
                Board.getInstance().setCoordinates(new Field(7, 3), new Field(7, 0).getFigureByField());
            }else {
                Board.getInstance().setCoordinates(new Field(0, 2), new Field(0, 4).getFigureByField());
                Board.getInstance().setCoordinates(new Field(0, 3), new Field(0, 0).getFigureByField());
            }
            return;
        }
        if (turn.contains("R")){
            if (turn.contains("x")){
                Board.getInstance().setCoordinates(field, fetchFigure(Rock.class, isWhite, true));
            }else {
                Board.getInstance().setCoordinates(field, fetchFigure(Rock.class, isWhite, false));
            }
            return;
        }
        if (turn.contains("N")){
            if (turn.contains("x")){
                Board.getInstance().setCoordinates(field, fetchFigure(Knight.class, isWhite, true));
            }else {
                Board.getInstance().setCoordinates(field, fetchFigure(Knight.class, isWhite, false));
            }
            return;
        }
        if (turn.contains("B")){
            if (turn.contains("x")){
                Board.getInstance().setCoordinates(field, fetchFigure(Bishop.class, isWhite, true));
            }else {
                Board.getInstance().setCoordinates(field, fetchFigure(Bishop.class, isWhite, false));
            }
            return;
        }
        if (turn.contains("Q")){
            if (turn.contains("x")){
                Board.getInstance().setCoordinates(field, fetchFigure(Queen.class, isWhite, true));
            }else {
                Board.getInstance().setCoordinates(field, fetchFigure(Queen.class, isWhite, false));
            }
            return;
        }
        if (turn.contains("K")){
            if (turn.contains("x")){
                Board.getInstance().setCoordinates(field, fetchFigure(King.class, isWhite, true));
            }else {
                Board.getInstance().setCoordinates(field, fetchFigure(King.class, isWhite, false));
            }
            return;
        }else {
            if (turn.contains("x")){
                Board.getInstance().setCoordinates(field, fetchFigure(Pawn.class, isWhite, true));
            }else {
                Board.getInstance().setCoordinates(field, fetchFigure(Pawn.class, isWhite, false));
            }
        }
    }

    private static Figure fetchFigure(Class clazz, boolean isWhite, boolean isKilling){
        Field fieldUnderAttack = null;
        boolean flag = false;
        Iterator<Observer> iterator;
        if (isWhite){
            iterator = Board.getWhiteFigures().iterator();
        }else {
            iterator = Board.getBlackFigures().iterator();
        }
        while (iterator.hasNext()){
            Observer currentFigure = iterator.next();
            if (currentFigure.getClass() == clazz){
                if (isKilling){
                    Iterator<Figure> couldBeKilled = ((Figure)currentFigure).getWhoCouldBeKilled().iterator();
                    while (couldBeKilled.hasNext()){
                        Figure figureUnderAttack = couldBeKilled.next();
                        Field couldBeUnderAttack = figureUnderAttack.getField();
                        if (couldBeUnderAttack.equals(field)){
                            flag = true;
                            fieldUnderAttack = couldBeUnderAttack;
                            candidates.add(currentFigure);
                        }
                    }
                }else {
                    Iterator<Field> possibleTurns = ((Figure)currentFigure).getPossibleFieldsToMove().iterator();
                    while (possibleTurns.hasNext()){
                        Field currentField = possibleTurns.next();
                        if (currentField.equals(field)){
                            candidates.add(currentFigure);
                        }
                    }
                }
            }
        }
        if (flag){
            field.removeFigureByField(isWhite);
        }
        if (candidates.size() > 1){
            return choseProperFigure();
        }else {
            return (Figure) candidates.get(0);
        }
    }

    private static Figure choseProperFigure(){
        char secondPosition = mainTurn.charAt(1);
        int integer = Character.getNumericValue(secondPosition);
        if (integer > Board.SIZE){
            for (Observer figure : candidates){
                if (((Figure) figure).getField().getY() == Field.getInvertedHorizontal().get(secondPosition)){
                    return (Figure) figure;
                }
            }
        }else {
            for (Observer figure : candidates){
                if (((Figure) figure).getField().getX() == Field.getInvertedVertical().get(integer)){
                    return (Figure) figure;
                }
            }
        }
        return null;
    }

    private static Field parseTargetField(String turn){
        int x;
        int y;
        if (!turn.equalsIgnoreCase("O-O") && !turn.equalsIgnoreCase("O-O-O")){
            if (turn.contains("+")){
                x = Field.getInvertedVertical().get(Character.getNumericValue(turn.charAt(turn.length()-2)));
                y = Field.getInvertedHorizontal().get(turn.charAt(turn.length()-3));
            }else {
                x = Field.getInvertedVertical().get(Character.getNumericValue(turn.charAt(turn.length()-1)));
                y = Field.getInvertedHorizontal().get(turn.charAt(turn.length()-2));
            }
            return new Field(x,y);
        }else {
            return null;
        }

    }
}
