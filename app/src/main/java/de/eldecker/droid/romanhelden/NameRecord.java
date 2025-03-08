package de.eldecker.droid.romanhelden;

import androidx.annotation.NonNull;

public record NameRecord(String vorname,
                         String nachname ) {

    @NonNull
    @Override
    public String toString() {

        return vorname() + "\n" + nachname();
    }
}
