package ua.bouquet.model.entity;

import java.awt.*;

public class Flower extends AbstractPlant {
    protected Color color;
    protected int countOfFlowers;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getCountOfFlowers() {
        return countOfFlowers;
    }

    public void setCountOfFlowers(int countOfFlowers) {
        this.countOfFlowers = countOfFlowers;
    }
}
