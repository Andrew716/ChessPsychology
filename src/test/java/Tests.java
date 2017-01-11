import model.*;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by phoenix on 10.01.17.
 */
public class Tests  {

    static Figure knight;

    @Before
    public  void setFigure(){
        knight  = new Knight(new Field(7, 1), Color.WHITE);
    }


    @Test
    public void possibleTurns(){
        int k  = 2;
        int r = 3;
        assertEquals (5, k+r);
    }

    @Test
    public void removeFigure() {
        Board.getInstance().getFigures().remove(knight);
        if ((knight).getColor() == Color.BLACK){
            Board.getInstance().getBlackFigures().remove(knight);
        }else {
            Board.getInstance().getWhiteFigures().remove(knight);
        }
        assertEquals(31, Board.getFigures().size());
        assertEquals(15, Board.getInstance().getWhiteFigures().size());
    }
}
