package de.eldecker.droid.romanhelden.namenGenerator;

import androidx.annotation.NonNull;


public record NameRecord( String vorname,
                          String nachname ) {
    @NonNull
    @Override
    public String toString() {

        return vorname() + "\n" + nachname();
    }

    public String fuerZwischenablage() {

        return vorname() + " " + nachname();
    }
}
