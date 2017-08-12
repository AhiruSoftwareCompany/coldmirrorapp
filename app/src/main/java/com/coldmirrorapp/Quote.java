package com.coldmirrorapp;

import java.util.Arrays;

class Quote {
    private String id;
    private String name;
    private Category category;

    private static Quote[] quoteArray = {
            new Quote(Category.harrypotter, "blitzaufderstirn", "Blitzchen auf der Stirn"),
            new Quote(Category.harrypotter, "geilekarten", "Geile Karten"),
            new Quote(Category.harrypotter, "hellemal", "Das helle Mal"),
            new Quote(Category.harrypotter, "irischeiren", "Irische Iren"),
            new Quote(Category.harrypotter, "istderhaesslich", "Ist der hässlich"),
            new Quote(Category.harrypotter, "jetztsindsiealletot", "Jetzt sind sie alle tot."),
            new Quote(Category.harrypotter, "joghurt", "Joghurt"),
            new Quote(Category.harrypotter, "journalist1", "Ich als Journalist"),
            new Quote(Category.harrypotter, "journalist2", "Ich als Journalist 2"),
            new Quote(Category.harrypotter, "keinetraenen", "Keine Tränen"),
            new Quote(Category.harrypotter, "lutschen", "Lutschen!"),
            new Quote(Category.harrypotter, "nicerdumbledore", "Netter Dumbledore"),
            new Quote(Category.harrypotter, "normalersatzbau", "Normaler lateinischer Satzbau"),
            new Quote(Category.harrypotter, "purezauberei", "Pure Zauberei"),
            new Quote(Category.harrypotter, "schlechterfilm", "Schlechter Film"),
            new Quote(Category.harrypotter, "schwulbullshit", "Schwul, Bullshit"),
            new Quote(Category.harrypotter, "sogebildet", "So Gebildet"),
            new Quote(Category.harrypotter, "tannenzapfen", "Tannenzapfen."),
            new Quote(Category.harrypotter, "toastrack", "Toast Rack"),
            new Quote(Category.harrypotter, "topbesetzung", "Top Besetzung"),
            new Quote(Category.harrypotter, "volldumm", "Ah, voll dumm"),
            new Quote(Category.harrypotter, "yaytot", "Yay, Tot :)"),
            new Quote(Category.avengers, "bratwurstmitsenf", "Bratwurst mit Senf"),
            new Quote(Category.avengers, "einbausparvertrag", "Sweet, ein Bausparvertrag"),
            new Quote(Category.avengers, "ichhabnstander", "Ich hab n Ständer"),
            new Quote(Category.avengers, "nebanane", "Ne Banane"),
            new Quote(Category.avengers, "nice", "Nice"),
            new Quote(Category.avengers, "wasistdeinlieblingstrinken", "Was ist dein Lieblingsgetränk"),
            new Quote(Category.random, "aequitasbitch", "Was macht\naequits hier?"),
            new Quote(Category.random, "ahahahalustig", "Ahahaha Lustig!"),
            new Quote(Category.random, "boahistdaslustig", "Boah ist das lustig"),
            new Quote(Category.random, "brutalekillerspiele", "Grund: Brutale Killerspiele"),
            new Quote(Category.random, "coldmirrormachtpornos", "Coldmirror macht Pornos"),
            new Quote(Category.random, "fickmichblick", "\'Fick mich\' Blick"),
            new Quote(Category.random, "hi1", "Hiii 1"),
            new Quote(Category.random, "hi2", "Hiii 2"),
            new Quote(Category.random, "hohotitten", "Höhö, Titten."),
            new Quote(Category.random, "ichhabnenschaden", "Ich hab nen\nSchaden"),
            new Quote(Category.random, "ichwillsex", "Ich will\nSex."),
            new Quote(Category.random, "istdasnichtgenial", "Ist das nicht genial?"),
            new Quote(Category.random, "jadaswarseigentlich", "Ja das wars\beigentlich"),
            new Quote(Category.random, "keingeld", "Kein Geld"),
            new Quote(Category.random, "kindheitgeloescht", "Kindheitserinnerung gelöscht"),
            new Quote(Category.random, "krankenwagen", "Krankenwagen"),
            new Quote(Category.random, "lache", "Lache"),
            new Quote(Category.random, "lache1", "Lache 1"),
            new Quote(Category.random, "langerbalken", "Langer\nBalken"),
            new Quote(Category.random, "richtiglame", "Richtig\nl a m e"),
            new Quote(Category.random, "ruelps", "Rülps"),
            new Quote(Category.random, "schuldigung", "Schuldigung"),
            new Quote(Category.random, "sodummkanndiedochnichtsein", "So dumm kann die doch nicht sein"),
            new Quote(Category.random, "sotalentiert", "So talentiert"),
            new Quote(Category.random, "totalgetriggert", "Total\ngetriggertt"),
            new Quote(Category.random, "unzufrieden", "Unzufriedenes Volk"),
            new Quote(Category.random, "witz", "Witz"),
            new Quote(Category.random, "wow", "Wow."),
            new Quote(Category.random, "zwanzigprozentwenigerfett", "20% weniger\nFett!")};


    Quote(Category category, String id, String name) {
        this.category = category;
        this.id = id;
        this.name = name;
    }

    String getColor() {
        return category.toString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Quote[] getAll() {
        return quoteArray;
    }

}
