package com.vwdigitalhub.cleaningrobots.domain.interfaces;

import com.vwdigitalhub.cleaningrobots.domain.valueobject.Coordinate;

public interface Observer {
    void isConflictWithCoordinate(Coordinate coordinate);
}

