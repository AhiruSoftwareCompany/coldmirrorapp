package com.coldmirrorapp;

enum Category {

    harrypotter("Harry Potter", "#FF69B4"),
    avengers("Avengers Synchro", "#4286f4"),
    random("Random", "#F7B600"),
    japanoschlampen("Japanoschlampen", "#c141f4"),
    kunstwissenschaftlicheanalyse("Kunstwissenschaftliche Analyse", "#bab76a"),
    tatort("Tatort Synchro", "#0a1bb5"),
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
