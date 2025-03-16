package de.eldecker.droid.romanhelden.namenGenerator;

import androidx.annotation.NonNull;


/**
 * Erzeugter Name für Romanfigur. Name kann auch nur aus Vorname bestehe
 *
 * @param vorname Vorname der Romanfigur
 *
 * @param nachname Nachname der Romanfigur
 */
public record NameRecord( String vorname,
                          String nachname ) {

    /**
     * Liefert ganzen Namen als String, mit Zeilenumbruch,
     * Groß-/Kleinschreibung normal.
     *
     * @return Vor- und Nachname mit Newline dazwischen
     */
    public String toStringNormal() {

        return vorname() + "\n" + nachname();
    }


    /**
     * Wie {@link #toStringNormal()}, aber nur Großbuchstaben.
     *
     * @return Vor- und Nachname ganz in Großbuchstaben, mit
     *         Newline dazwischen
     */
    public String toStringNurGrossbuchstaben() {

        return vorname().toUpperCase() + "\n" + nachname().toUpperCase();
    }


    /**
     * Liefert ganzen Namen als String, ohne Newline; z.B. um in
     * Zwischenablage zu kopieren.
     *
     * @return Vor- und Nachname als ein String ohne neue Zeile
     */
    @NonNull
    @Override
    public String toString() {

        return vorname() + " " + nachname();
    }

}
