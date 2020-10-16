package com.coldmirrorapp;

public enum ListStatus {
    BUTTON(2),
    LIST(1);

    final int numColumns;

    ListStatus(int numColumns) {
        this.numColumns = numColumns;
    }
}
