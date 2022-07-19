package UI.Event;

public class EventListener {

    private Event.EventType eventType;
    private Runnable function;

    public EventListener(Event.EventType eventType, Runnable function) {
        this.eventType = eventType;
        this.function = function;
    }

    public Event.EventType getEventType() {
        return eventType;
    }

    public void setEventType(Event.EventType eventType) {
        this.eventType = eventType;
    }

    public Runnable getFunction() {
        return function;
    }

    public void setFunction(Runnable function) {
        this.function = function;
    }
}
