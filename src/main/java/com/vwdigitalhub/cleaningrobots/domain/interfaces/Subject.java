package com.vwdigitalhub.cleaningrobots.domain.interfaces;

import com.vwdigitalhub.cleaningrobots.domain.valueobject.Coordinate;

public interface Subject {
    void addObserver(Observer observer);
    void movementTo(Coordinate coordinate);
}