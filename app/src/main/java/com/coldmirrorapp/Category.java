package com.coldmirrorapp;

enum Category {

    harrypotter("", "#FF69B4"),
    avengers("", "#4286f4"),
    random("", "#F7B600"),
    japanoschlampen("", "#c141f4"),
    kunstwissenschaftlicheanalyse("", "#bab76a"),
    tatort("", "#0a1bb5"),
    nothing("", "");

    String name;
    String color;

    Category(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return color;
    }
}
