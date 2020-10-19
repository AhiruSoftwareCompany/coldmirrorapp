package com.coldmirrorapp;

import androidx.annotation.NonNull;

class Quote {
    private static final Quote[] quoteArray = {
            new Quote(Category.harrypotter, "abgeblitzt", "Abgeblitzt!", "5 Minuten Harry Podcast #9"),
            new Quote(Category.harrypotter, "absichtlicheschaedigung", "Absichtliche Schädigung"),
            new Quote(Category.harrypotter, "achdumeinescheisse", "Ach du meine Scheiße!", "Harry Potter und schon wieder irgendwas"),
            new Quote(Category.harrypotter, "auchdualtelady", "Auch du, alte Lady", "5 Minuten Harry Podcast #5"),
            new Quote(Category.harrypotter, "besseralsnichts", "Besser als nichts", "5 Minuten Harry Podcast #11"),
            new Quote(Category.harrypotter, "blitzaufderstirn", "Blitzchen auf der Stirn"),
            new Quote(Category.harrypotter, "casting", "Holzcasting", "5 Minuten Harry Podcast #13"),
            new Quote(Category.harrypotter, "chefgehtrueckwaerts", "Chef geht rückwärts", "Harry Potter und schon wieder irgendwas"),
            new Quote(Category.harrypotter, "dasheissteristgeradeindeutschland", "Das heißt, er ist gerade in Deutschland?", "Harry Potter und schon wieder irgendwas"),
            new Quote(Category.harrypotter, "dasistdeinproblemmeinfreund", "Das ist dein Problem mein Freund", "Harry Potter und schon wieder irgendwas"),
            new Quote(Category.harrypotter, "dasistmiregal", "Das ist mir egal.", "Harry Potter und schon wieder irgendwas"),
            new Quote(Category.harrypotter, "dasistunnoetigundzulang", "Das ist unnötig und zu lang", "5 Minuten Harry Podcast #9"),
            new Quote(Category.harrypotter, "dayum", "Dayum!", "5 Minuten Harry Podcast #9"),
            new Quote(Category.harrypotter, "dieantwortlautetja", "Die Antwortet lautet ja", "5 Minuten Harry Podcast #11"),
            new Quote(Category.harrypotter, "dubistgemeldetundblockiert", "Du bist gemeldet und blockiert!"),
            new Quote(Category.harrypotter, "dubistsodumm", "Du bist so dumm!", "Harry Potter und schon wieder irgendwas"),
            new Quote(Category.harrypotter, "duoberpflaume", "Du Oberpflaume", "5 Minuten Harry Podcast #4"),
            new Quote(Category.harrypotter, "einaufmuepfigerarbeiter", "Ein aufmüpfiger Arbeiter!", "Harry Potter und schon wieder irgendwas"),
            new Quote(Category.harrypotter, "einelampe", "Eine Lampe", "5 Minuten Harry Podcast #9"),
            new Quote(Category.harrypotter, "excuseme", "Excuse Me?!", "5 Minuten Harry Podcast #12"),
            new Quote(Category.harrypotter, "frittenbudenjingle", "McD Jingle", "Harry Potter und schon wieder irgendwas"),
            new Quote(Category.harrypotter, "geilekarten", "Geile Karten"),
            new Quote(Category.harrypotter, "genuggefreut", "Genug gefreut"),
            new Quote(Category.harrypotter, "getuschel", "Getuschel über Snape", "Harry Potter und schon wieder irgendwas"),
            new Quote(Category.harrypotter, "hagridwitz", "Hagrid's Witz", "5 Minuten Harry Podcast #5"),
            new Quote(Category.harrypotter, "harrybeimfrauenarzt", "Harry beim Frauenarzt", "Harry Potter und schon wieder irgendwas"),
            new Quote(Category.harrypotter, "hashtaggoennung", "#Gönnung", "5 Minuten Harry Podcast #9"),
            new Quote(Category.harrypotter, "hashtagrelatable", "#relatable", "5 Minuten Harry Podcast #11"),
            new Quote(Category.harrypotter, "hellemal", "Das helle Mal"),
            new Quote(Category.harrypotter, "herminekauftbuecher", "Hermine kauft Bücher", "Harry Potter und schon wieder irgendwas"),
            new Quote(Category.harrypotter, "hitler", "Hitler?", "Harry Potter und schon wieder irgendwas"),
            new Quote(Category.harrypotter, "howtobildung", "How to: Bildung", "5 Minuten Harry Podcast #11"),
            new Quote(Category.harrypotter, "huhwerbistdudenn", "Hö? Wer bist du denn?", "Harry Potter und schon wieder irgendwas"),
            new Quote(Category.harrypotter, "ichalseditor", "Ich, als Editor", "5 Minuten Harry Podcast #9"),
            new Quote(Category.harrypotter, "ichalsjournalist1", "Ich, als Journalist 1"),
            new Quote(Category.harrypotter, "ichalsjournalist2", "Ich, als Journalist 2"),
            new Quote(Category.harrypotter, "ichalskostuemdesigner", "Ich, als Kostümdesigner", "5 Minuten Harry Podcast #9"),
            new Quote(Category.harrypotter, "ichalssynchronsprecher", "Ich, als Synchronsprecher", "5 Minuten Harry Podcast #4"),
            new Quote(Category.harrypotter, "ichalsuebersetzer", "Ich, als Übersetzer", "5 Minuten Harry Podcast #9"),
            new Quote(Category.harrypotter, "irischeiren", "Irische Iren"),
            new Quote(Category.harrypotter, "istderhaesslich", "Ist der hässlich"),
            new Quote(Category.harrypotter, "jetztsindsiealletot", "Jetzt sind sie alle tot."),
            new Quote(Category.harrypotter, "joghurt", "Joghurt"),
            new Quote(Category.harrypotter, "keinetraenen", "Keine Tränen"),
            new Quote(Category.harrypotter, "laesteronispeziale", "Lästeroni Speziale"),
            new Quote(Category.harrypotter, "lutschen", "Lutschen!"),
            new Quote(Category.harrypotter, "manchmalmussichuebermichselberlachen", "Manchmal muss ich über mich selber lachen.", "Harry Potter und schon wieder irgendwas"),
            new Quote(Category.harrypotter, "meinebuecher", "MEINE Bücher.", "Harry Potter und schon wieder irgendwas"),
            new Quote(Category.harrypotter, "neunzehnhundertzwoelfundachzig", "Neunzehnhundertzwölfundachzig", "Harry Potter und schon wieder irgendwas"),
            new Quote(Category.harrypotter, "newstime", "Newstime!"),
            new Quote(Category.harrypotter, "nicerdumbledore", "Netter Dumbledore"),
            new Quote(Category.harrypotter, "normalersatzbau", "Normaler lateinischer Satzbau"),
            new Quote(Category.harrypotter, "penisamputiertenmistkaefer", "Ihr penisamputierten Mistkäfer", "Harry Potter und schon wieder irgendwas"),
            new Quote(Category.harrypotter, "purezauberei", "Pure Zauberei"),
            new Quote(Category.harrypotter, "radclifflachen", "Radcliff's Lachen", "5 Minuten Harry Podcast #5"),
            new Quote(Category.harrypotter, "rechenichsoviel", "Rechne nich so viel, Ron.", "Harry Potter und schon wieder irgendwas"),
            new Quote(Category.harrypotter, "scheissaufdiedrittewelt", "Scheiß auf die dritte Welt!", "Harry Potter und schon wieder irgendwas"),
            new Quote(Category.harrypotter, "schlechterfilm", "Schlechter Film"),
            new Quote(Category.harrypotter, "schwulbullshit", "Schwul, Bullshit"),
            new Quote(Category.harrypotter, "sogebildet", "So Gebildet"),
            new Quote(Category.harrypotter, "soupsoupsoup", "Soup Soup Soup", "5 Minuten Harry Podcast #5"),
            new Quote(Category.harrypotter, "sovielaufwand", "Ey, aber das ist so viel Aufwand", "5 Minuten Harry Podcast #11"),
            new Quote(Category.harrypotter, "suppenauswahl", "Suppenauswahl", "5 Minuten Harry Podcast #5"),
            new Quote(Category.harrypotter, "tannenzapfen", "Tannenzapfen."),
            new Quote(Category.harrypotter, "toastrack", "Toast Rack"),
            new Quote(Category.harrypotter, "topbesetzung", "Top Besetzung"),
            new Quote(Category.harrypotter, "toplehrer", "Top Lehrer!", "5 Minuten Harry Podcast #12"),
            new Quote(Category.harrypotter, "unrealistischerfilm", "Unrealistischer Film", "5 Minuten Harry Podcast #13"),
            new Quote(Category.harrypotter, "uuuhdaskaufenwirnicht", "Uhh, das kaufen wir nicht", "5 Minuten Harry Podcast #2"),
            new Quote(Category.harrypotter, "volldumm", "Ah, voll dumm"),
            new Quote(Category.harrypotter, "wasdauertdenndasolange", "Was dauert denn das so lange", "5 Minuten Harry Podcast #9"),
            new Quote(Category.harrypotter, "waszumgeiersteffi", "Was zum Geier Steffi??", "5 Minuten Harry Podcast #11"),
            new Quote(Category.harrypotter, "weristflori", "Wer ist Flori?", "Harry Potter und schon wieder irgendwas"),
            new Quote(Category.harrypotter, "wow", "Wow."),
            new Quote(Category.harrypotter, "wow2", "Wow.", "5 Minuten Harry Podcast #11"),
            new Quote(Category.harrypotter, "yaytot", "Yay, Tot :)"),
            new Quote(Category.avengers, "bratwurstmitsenf", "Bratwurst mit Senf", "Behinderte, kleine Avengers Synchro"),
            new Quote(Category.avengers, "einbausparvertrag", "Sweet, ein Bausparvertrag", "Behinderte, kleine Avengers Synchro"),
            new Quote(Category.avengers, "ichhabnstander", "Ich hab 'n Ständer", "Behinderte, kleine Avengers Synchro"),
            new Quote(Category.avengers, "moechtestduetwasspeckspecki", "Möchtest du etwas Speck Specki?", "Behinderte, kleine Avengers Synchro"),
            new Quote(Category.avengers, "nebanane", "Ne Banane", "Behinderte, kleine Avengers Synchro"),
            new Quote(Category.avengers, "nice", "Nice", "Behinderte, kleine Avengers Synchro"),
            new Quote(Category.avengers, "wasistdeinlieblingstrinken", "Was ist dein Lieblingsgetränk", "Behinderte, kleine Avengers Synchro"),
            new Quote(Category.japanoschlampen, "eigenartigerklaus", "Eigenartiger Klaus", "Japanoschlampen #35 - Alte und neue Rivalen"),
            new Quote(Category.japanoschlampen, "abachtzehn", "Ab 18!", "Japanoschlampen #8 - Operation Shopping"),
            new Quote(Category.random, "aequitasbitch", "Was macht aequitas hier?"),
            new Quote(Category.random, "ahahahalustig", "Ahahaha Lustig!"),
            new Quote(Category.random, "bambus", "Bambus!", "Hilfe, mein Haus sieht scheiße aus, bau mir'n neues!"),
            new Quote(Category.random, "bambusbambusbambus", "Bambus! Bambus! BAMBUS!", "Hilfe, mein Haus sieht scheiße aus, bau mir'n neues!"),
            new Quote(Category.random, "boahistdaslustig", "Boah ist das lustig"),
            new Quote(Category.random, "brutalekillerspiele", "Grund: Brutale Killerspiele"),
            new Quote(Category.random, "coldmirrormachtpornos", "Coldmirror macht Pornos"),
            new Quote(Category.random, "daswaerbescheuert", "Das wär bescheuert", "How to Video Video #2 - Drehbuch (der Film im Kopf)"),
            new Quote(Category.random, "dumm", "Dumm!"),
            new Quote(Category.random, "durchnefetteexplosion", "Durch ne fette Explosion", "StarStarSpace #21"),
            new Quote(Category.random, "fickmichblick", "'Fick mich' Blick"),
            new Quote(Category.random, "fuerbesondereleute", "Für besondere Leute", "hr Videowettbewerb \"Du führst Regie\""),
            new Quote(Category.random, "hahagay", "Haha, das ist gay."),
            new Quote(Category.random, "hi1", "Hiii 1"),
            new Quote(Category.random, "hi2", "Hiii 2"),
            new Quote(Category.random, "hohotitten", "Höhö, Titten."),
            new Quote(Category.random, "ichhabnenschaden", "Ich hab nen Schaden"),
            new Quote(Category.random, "ichwillsex", "Ich will Sex."),
            new Quote(Category.random, "istdasnichtgenial", "Ist das nicht genial?"),
            new Quote(Category.random, "jadaswarseigentlich", "Ja das wars eigentlich"),
            new Quote(Category.random, "keingeld", "Kein Geld"),
            new Quote(Category.random, "kindheitgeloescht", "Kindheitserinnerung gelöscht"),
            new Quote(Category.random, "krankenwagen", "Krankenwagen"),
            new Quote(Category.random, "lache", "Lache 1"),
            new Quote(Category.random, "lache1", "Lache 2"),
            new Quote(Category.random, "langerbalken", "Langer, breiter Balken"),
            new Quote(Category.random, "musikwunsch", "Musikwunsch"),
            new Quote(Category.random, "richtiglame", "Richtig lame"),
            new Quote(Category.random, "ruelps", "Rülps"),
            new Quote(Category.random, "saudumm", "Saudumm"),
            new Quote(Category.random, "schrei1", "Schrei 1"),
            new Quote(Category.random, "schrei2", "Schrei 2"),
            new Quote(Category.random, "schuldigung", "'Schuldigung"),
            new Quote(Category.random, "sexismus", "Sexismus"),
            new Quote(Category.random, "sodummkanndiedochnichtsein", "So dumm kann die doch nicht sein"),
            new Quote(Category.random, "sotalentiert", "So talentiert"),
            new Quote(Category.random, "totalgetriggert", "Total getriggert"),
            new Quote(Category.random, "unzufrieden", "Unzufriedenes Volk"),
            new Quote(Category.random, "verpruegeln", "Verprügeln"),
            new Quote(Category.random, "witz", "Witz"),
            new Quote(Category.random, "zwanzigprozentwenigerfett", "20% weniger Fett!"),
            new Quote(Category.tatort, "erstaunlich", "Erstaunlich"),
            new Quote(Category.tatort, "ichwolltenichtkommen", "Ich wollte nicht kommen"),
            new Quote(Category.tatort, "kuckmalderweltraum", "Kuck mal, der Weltraum"),
            new Quote(Category.tatort, "spassundaction", "Spaß und Action wird groß geschrieben"),
            new Quote(Category.tatort, "tachdusackfalte", "Tach, du Sackfalte")};

    private final String id;
    private final String name;
    private String source;
    private final Category category;

    public Quote(Category category, String id, String name) {
        this.category = category;
        this.id = id;
        this.name = name;
    }

    public Quote(Category category, String id, String name, String source) {
        this.category = category;
        this.id = id;
        this.name = name;
        this.source = source;
    }

    public static Quote[] getAll() {
        return quoteArray;
    }

    public static Quote getRandom() {
        return quoteArray[(int) (Math.random() * ((quoteArray.length)))];
    }

    public String getColor() {
        return category.toString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    public String getSource() {
        if (source != null) {
            return source;
        }
        return "";
    }

    public String getCategoryName() {
        if (category != null) {
            return category.getName();
        }
        return "";
    }
}
