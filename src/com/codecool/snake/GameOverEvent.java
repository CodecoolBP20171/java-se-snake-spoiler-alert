package com.codecool.snake;

import javafx.event.Event;
import javafx.event.EventType;

public class GameOverEvent extends Event {

    public GameOverEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }
}
