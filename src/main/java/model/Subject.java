package model;

/**
 * Created by phoenix on 21.12.16.
 */
public interface Subject {

    void notify(Observer figure);
    void register(Observer figure);
    void removeFigure(Observer figure);
}