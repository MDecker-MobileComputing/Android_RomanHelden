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
     * Liefert ganzen Namen als String, mit Zeilenumbrüche.
     *
     * @return Vor- und Nachname mit einer neuen Zeile dazwischen
     */
    @NonNull
    @Override
    public String toString() {

        return vorname() + "\n" + nachname();
    }

    /**
     * Liefert ganzen Namen als String, ohne Zeilenumbrüche.
     *
     * @return Vor- und Nachname als ein String ohne neue Zeile
     */
    public String fuerZwischenablage() {

        return vorname() + " " + nachname();
    }
}
