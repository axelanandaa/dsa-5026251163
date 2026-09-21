package lw01.unguided;

public class ProjectorRental extends Rental {
    private static final int FIRST_DAYS = 3;
    private static final int RATE_FIRST = 60000;
    private static final int RATE_EXTRA = 45000;
    private static final int SETUP_FEE = 20000;

    public ProjectorRental(String id, int days) {
        super(id, days);
    }

    @Override
    public int calculateCharge() {
        int days = getDays();
        int first = Math.min(days, FIRST_DAYS);
        int extra = days - first;
        return first * RATE_FIRST + extra * RATE_EXTRA + SETUP_FEE;
    }

    @Override
    public String label() {
        return "Projector";
    }
}