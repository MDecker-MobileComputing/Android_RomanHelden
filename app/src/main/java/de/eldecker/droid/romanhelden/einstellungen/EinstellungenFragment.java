package de.eldecker.droid.romanhelden.einstellungen;

import  de.eldecker.droid.romanhelden.R;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;


/**
 * Vorlage für diese Klasse: {@code MySettingsFragment} von
 * <a href="https://developer.android.com/develop/ui/views/components/settings?hl=de">diese Seite</a>
 */
public class EinstellungenFragment extends PreferenceFragmentCompat {


    /**
     * Konfiguration für Preferences-Screen laden.
     */
    @Override
    public void onCreatePreferences( Bundle savedInstanceState, String rootKey ) {

        setPreferencesFromResource( R.xml.einstellungen, rootKey );
    }

}
