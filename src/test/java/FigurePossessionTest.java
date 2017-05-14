import model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by phoenix on 14.05.17.
 */
public class FigurePossessionTest {

    static Set<Observer> figures = new LinkedHashSet<Observer>();

    @Before
    public void setUpBoard(){

        //Putting white figures on the board
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
        //Adding to figures collection
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
        //Putting black figures on the board
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
        //Adding to figures collection
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
    }

    @After
    public void clearFiguresFromTheBoard(){
        figures.clear();
    }

    @Test
    public /*static*/ void checkInitialState(){
        Board board = Board.getInstance();
        System.out.println(Board.getFieldToFigure().size());
        assertEquals((board.getFieldToFigure().get(new Field(6, 0))).getPossibleFieldsToMove().size(), 1);
    }
}
