package com.coldmirrorapp;

enum Category {

    harrypotter("#FF69B4"), avengers("#4286f4"), random("#F7B600"), japanoschlampen("#c141f4"), kunstwissenschaftlicheanalyse("#bab76a"), tatort("#0a1bb5"), nothing("");

    String color;

    Category(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color;
    }
}
