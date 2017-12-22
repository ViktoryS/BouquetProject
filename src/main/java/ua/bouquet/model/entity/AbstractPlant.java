package ua.bouquet.model.entity;

public abstract class AbstractPlant extends Item{
    protected long longOfStem;
    protected long price;
    protected Freshness freshness;
    protected Bouquet bouquet;

    public long getLongOfStem() {
        return longOfStem;
    }

    public void setLongOfStem(long longOfStem) {
        this.longOfStem = longOfStem;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Freshness getFreshness() {
        return freshness;
    }

    public void setFreshness(Freshness freshness) {
        this.freshness = freshness;
    }

    public Bouquet getBouquet() {
        return bouquet;
    }

    public void setBouquet(Bouquet bouquet) {
        this.bouquet = bouquet;
    }
}
