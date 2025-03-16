package de.eldecker.droid.romanhelden.namenGenerator;

import android.util.Log;

import java.util.Random;


public class NamenGenerator {

    private static final String TAG4LOGGING = "NamenGenerator";

    private static final String[] KINDERBUCH_VORNAMEN  = { "Bouncy", "Fluffy", "Funny", "Happy", "Jumpy", "Magic", "Snuggle", "Sunny", "Tiny", "Twinkle" };
    private static final String[] KINDERBUCH_NACHNAMEN = { "Bear", "Bunny", "Duck", "Elf", "Fairy", "Frog", "Kitty", "Panda", "Puppy", "Star" };

    private static final String[] WESTERN_VORNAMEN = { "Billy", "Bronco", "Buck", "Colt", "Dusty", "Johnny", "Lone", "Red", "Rusty", "Silver", "Wild" };
    private static final String[] WESTERN_NACHNAME = { "Canyon", "Deputy", "Hawk", "Outlaw", "Ranger", "Rider", "River", "Stallion", "Trail", "Wolf" };

    private static final String[] SUPERHELDEN_VORNAMEN  = { "Captain", "Crimson", "Dark", "Eternal", "Iron", "Mighty", "Phoenix", "Shadow", "Silver", "Thunder" };
    private static final String[] SUPERHELDEN_NACHNAMEN = { "Blaze", "Bolt", "Flare", "Force", "Justice", "Power", "Shield", "Strike", "Vision", "Wing" };

    private static final String[] HISTORISCH_VORNAMEN  = { "Arthur", "Baptiste", "Beatrice", "Catherine", "Eleanor", "Edmund", "Harriet", "Henry", "Matilda", "Theodore", "Walter" };
    private static final String[] HISTORISCH_NACHNAMEN = { "Barrow", "Fairchild", "Hastings", "Hawthorne", "Kingsley", "Lancaster", "Montague", "Pendleton", "Whitmore", "Winchester" };

    private static final String[] LIEBESROMAN_VORNAMEN = { "Amour", "Aurora", "Bella", "Celeste", "Eterna", "Felicity", "Luna", "Rose", "Serena", "Viola" };
    private static final String[] LIEBSROMAN_NACHNAMEN = { "Bliss", "Charm", "Desire", "Euphoria", "Glow", "Heart", "Heaven", "Kiss", "Love", "Passion" };

    private static final String[] SCIENCEFICTION_VORNAMEN  = { "Axel", "Juno", "Kai", "Kara", "Luna", "Lyra", "Nova", "Orion", "Vega", "Zane" };
    private static final String[] SCIENCEFICTION_NACHNAMEN = { "Astro", "Cosmos", "Eclipse", "Lightyear", "Nebula", "Photon", "Pulsar", "Quasar", "Stellar", "Vortex", "Zenith" };

    private static final String[] CYBERPUNK_VORNAMEN  = { "Cipher", "Dray", "Echo", "Kael", "Nova", "Nyx", "Raze", "Riko", "Sable", "Vex" };
    private static final String[] CYBERPUNK_NACHNAMEN = { "Flux", "Havoc", "Hex", "Onyx", "Ryker", "Sable", "Shard", "Striker", "Vortex", "Zero" };

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
        int idx    = sZufallsGenerator.nextInt( anzahl );

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

            case CYBERPUNK:
                    vornamenArray  = CYBERPUNK_VORNAMEN;
                    nachnamenArray = CYBERPUNK_NACHNAMEN;
                break;

            case HISTORISCH:
                    vornamenArray  = HISTORISCH_VORNAMEN;
                    nachnamenArray = HISTORISCH_NACHNAMEN;
                break;

            case KINDERBUCH :
                    vornamenArray  = KINDERBUCH_VORNAMEN;
                    nachnamenArray = KINDERBUCH_NACHNAMEN;
                break;

            case LIEBESROMAN:
                    vornamenArray  = LIEBESROMAN_VORNAMEN;
                    nachnamenArray = LIEBSROMAN_NACHNAMEN;
                break;

            case SCIENCEFICTION:
                    vornamenArray  = SCIENCEFICTION_VORNAMEN;
                    nachnamenArray = SCIENCEFICTION_NACHNAMEN;
                break;

            case SUPERHELDEN:
                    vornamenArray  = SUPERHELDEN_VORNAMEN;
                    nachnamenArray = SUPERHELDEN_NACHNAMEN;
                break;

            case WESTERN:
                    vornamenArray  = WESTERN_VORNAMEN;
                    nachnamenArray = WESTERN_NACHNAME;
                break;

            default:
                Log.e( TAG4LOGGING, "Unerwartetes Genre: " + genre );
                vornamenArray  = DUMMY_ARRAY;
                nachnamenArray = DUMMY_ARRAY;
        }

        String vorname  = holeRandomString( vornamenArray  );
        String nachname = holeRandomString( nachnamenArray );

        return new NameRecord( vorname, nachname );
    }

}
