package UI.Event;

import java.awt.*;

public final class Event {

    private static Long eventCounter = 0L;

    public enum EventType {
        click, hover
    }

    private final Long id;
    private final EventType type;
    private Point point;


    public Event(EventType type) {
        Event.eventCounter++;
        this.id = Event.eventCounter;
        this.type = type;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }
    public EventType getEventType() {
        return this.type;
    }

    public Long getId() {
        return this.id;
    }
}
