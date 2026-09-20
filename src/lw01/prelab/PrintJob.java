package lw01.prelab;

public abstract class PrintJob implements Chargeable {

    private String id;
    private int pages;

    public PrintJob(String id, int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.pages = pages;
    }

    public String getId() {
        return id;
    }

    public int getPages() {
        return pages;
    }

    public abstract String label();

    public int calculateCharge(int copies) {
        if (copies <= 0) {
            throw new IllegalArgumentException();
        }

        return copies * calculateCharge();
    }

    public String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }
}
