package com.capgemini.wsb.fitnesstracker.training.internal;

import lombok.Getter;

/**
 *  Enumerates all possible types of activities.
 */
@Getter
public enum ActivityType {

    RUNNING("Running"),
    CYCLING("Cycling"),
    WALKING("Walking"),
    SWIMMING("Swimming"),
    TENNIS("Tenis");

    private final String displayName;

    /**
     *  Constructor
     * @param displayName display name
     */
    ActivityType(String displayName) {
        this.displayName = displayName;
    }

}