package com.coldmirrorapp;

enum Category {

    harrypotter("Harry Potter", "#d81b60"),
    avengers("Avengers Synchro", "#3949ab"),
    random("Random", "#fdd835"),
    japanoschlampen("Japanoschlampen", "#8e24aa"),
    kunstwissenschaftlicheanalyse("Kunstwissenschaftliche Analyse", "#c0ca33"),
    tatort("Tatort Synchro", "#039be5"),
    nothing("", "#757575");

    final String name;
    final String color;

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
