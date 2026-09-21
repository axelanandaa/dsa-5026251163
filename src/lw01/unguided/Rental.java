package lw01.unguided;

public abstract class Rental implements Chargeable {
    private final String id;
    private final int days;

    protected Rental(String id, int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("days must be positive");
        }
        this.id = id;
        this.days = days;
    }

    public String getId() {
        return id;
    }

    public int getDays() {
        return days;
    }

    @Override
    public abstract int calculateCharge();

    public int calculateCharge(int units) {
        if (units <= 0) {
            throw new IllegalArgumentException("units must be positive");
        }
        return units * calculateCharge();
    }

    public String label() {
        return "Rental";
    }

    public String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }
}
