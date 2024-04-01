package model;

// a helper class for EventLog to use single point of change
public class EventLogHelper {

    public static void logEvent(String description) {
        EventLog.getInstance().logEvent(new Event(description));
    }

    public static void printLogs() {
        EventLog.getInstance().iterator().forEachRemaining(event -> System.out.println(event.getDescription()));
    }
}
