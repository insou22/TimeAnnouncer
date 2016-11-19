package co.insou.timeannouncer;

public class Announcer {

    private final long tick;
    private final String message;

    public Announcer(long tick, String message) {
        this.tick = tick;
        this.message = message;
    }

    public long getTick() {
        return tick;
    }

    public String getMessage() {
        return message;
    }

}
