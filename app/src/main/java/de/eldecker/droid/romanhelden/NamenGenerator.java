package de.eldecker.droid.romanhelden;

import java.util.Random;


public class NamenGenerator {

    private static final String[] KINDERBUCH_PREFIXES = {"Fluffy", "Tiny", "Happy", "Jumpy", "Sunny", "Snuggle", "Bouncy", "Magic", "Funny", "Twinkle"};
    private static final String[] KINDERBUCH_SUFFIXES = {"Bunny", "Bear", "Kitty", "Panda", "Frog", "Fairy", "Elf", "Star", "Puppy", "Duck"};

    private static final Random sZufallsGenerator = new Random();

    private static String holeRandomString( String[] namenArray ) {

        int anzahl = namenArray.length;
        int idx = sZufallsGenerator.nextInt( anzahl );

        return namenArray[ idx ];
    }

    public static NameRecord erzeugeName() {

        String vorname  = holeRandomString( KINDERBUCH_PREFIXES );
        String nachname = holeRandomString( KINDERBUCH_SUFFIXES );

        return new NameRecord( vorname, nachname );
    }

}
