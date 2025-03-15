package de.eldecker.droid.romanhelden.namenGenerator;

import android.util.Log;

import java.util.Random;


public class NamenGenerator {

    private static final String TAG4LOGGING = "NamenGenerator";

    private static final String[] KINDERBUCH_VORNAMEN  = { "Fluffy", "Tiny", "Happy", "Jumpy", "Sunny", "Snuggle", "Bouncy", "Magic", "Funny", "Twinkle" };
    private static final String[] KINDERBUCH_NACHNAMEN = { "Bunny", "Bear", "Kitty", "Panda", "Frog", "Fairy", "Elf", "Star", "Puppy", "Duck" };

    private static final String[] WESTERN_VORNAMEN = { "Dusty", "Buck", "Red", "Johnny", "Lone", "Silver", "Rusty", "Wild", "Bronco", "Colt" };
    private static final String[] WESTERN_NACHNAME = { "Rider", "Canyon", "Trail", "River", "Stallion", "Wolf", "Hawk", "Deputy", "Ranger", "Outlaw" };

    private static final String[] SUPERHELDEN_VORNAMEN  = { "Captain", "Mighty", "Shadow", "Iron", "Thunder", "Crimson", "Silver", "Dark", "Eternal", "Phoenix" };
    private static final String[] SUPERHELDEN_NACHNAMEN = { "Justice", "Power", "Strike", "Wing", "Force", "Blaze", "Shield", "Flare", "Bolt", "Vision" };

    private static final String[] HISTORISCH_VORNAMEN  = { "Baptiste", "Eleanor", "Theodore", "Harriet", "Walter", "Beatrice", "Arthur", "Catherine", "Edmund", "Matilda", "Henry" };
    private static final String[] HISTORISCH_NACHNAMEN = { "Pendleton", "Montague", "Hastings", "Whitmore", "Fairchild", "Kingsley", "Barrow", "Lancaster", "Hawthorne", "Winchester" };

    private static final String[] LIEBESROMAN_VORNAMEN = { "Amour", "Bella", "Luna", "Eterna", "Rose", "Felicity", "Serena", "Aurora", "Celeste", "Viola" };
    private static final String[] LIEBSROMAN_NACHNAMEN = { "Heart", "Love", "Passion", "Bliss", "Euphoria", "Kiss", "Charm", "Desire", "Glow", "Heaven" };

    private static final String[] SCIENCEFICTION_VORNAMEN = { "Nova", "Zane", "Orion", "Lyra", "Kai", "Vega", "Axel", "Luna", "Juno", "Kara" };    
    private static final String[] SCIENCEFICTION_NACHNAMEN = { "Stellar", "Quasar", "Lightyear", "Nebula", "Cosmos", "Eclipse", "Photon", "Astro", "Pulsar", "Vortex", "Zenith" };


    /** Array für Fehlerfall (wenn Genre nicht erkannt wird). */
    private static final String[] DUMMY_ARRAY = { "???" };

    /** Zufallsgenerator für Auswahl von Strings aus Array. */
    private static final Random sZufallsGenerator = new Random();


    /**
     * Zufälligen String aus Array mit Vor- oder Nachnamen auswählen.
     *
     * @param namenArray Array mit Vor- oder Nachnamen
     *
     * @return Ausgwählter String aus {@cde namenArray}
     */
    private static String holeRandomString( String[] namenArray ) {

        int anzahl = namenArray.length;
        int idx = sZufallsGenerator.nextInt( anzahl );

        return namenArray[ idx ];
    }

    
    /**
     * Neuen Namen für Romanfigur erzeugen.
     *
     * @param genre Genre, für das der Name erzeugt werden soll
     *
     * @return Objekt mit erzeugtem Namen
     */
    public static NameRecord erzeugeName( LiteraturGenreEnum genre ) {

        String[] vornamenArray = null;
        String[] nachnamenArray = null;

        switch ( genre ) {

            case KINDERBUCH :
                    vornamenArray  = KINDERBUCH_VORNAMEN;
                    nachnamenArray = KINDERBUCH_NACHNAMEN;
                break;

            case WESTERN:
                    vornamenArray  = WESTERN_VORNAMEN;
                    nachnamenArray = WESTERN_NACHNAME;
                break;

            case SUPERHELDEN:
                    vornamenArray  = SUPERHELDEN_VORNAMEN;
                    nachnamenArray = SUPERHELDEN_NACHNAMEN;
                break;

            case HISTORISCH:
                    vornamenArray  = HISTORISCH_VORNAMEN;
                    nachnamenArray = HISTORISCH_NACHNAMEN;
                break;

            case LIEBESROMAN:
                    vornamenArray  = LIEBESROMAN_VORNAMEN;
                    nachnamenArray = LIEBSROMAN_NACHNAMEN;
                break;

            case SCIENCEFICTION:
                    vornamenArray  = SCIENCEFICTION_VORNAMEN;
                    nachnamenArray = SCIENCEFICTION_NACHNAMEN;
                break;

            default:
                Log.e( TAG4LOGGING, "Unerwartetes Genre: " + genre );
                vornamenArray  = DUMMY_ARRAY;
                nachnamenArray = DUMMY_ARRAY;
        }

        String vorname  = holeRandomString( vornamenArray );
        String nachname = holeRandomString( nachnamenArray );

        return new NameRecord( vorname, nachname );
    }

}
