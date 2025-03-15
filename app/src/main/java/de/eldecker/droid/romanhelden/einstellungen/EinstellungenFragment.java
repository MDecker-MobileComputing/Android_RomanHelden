package de.eldecker.droid.romanhelden.einstellungen;

import  de.eldecker.droid.romanhelden.R;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.Preference;
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

        SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();

        // Retrieve the current counter value
        int zaehler = sharedPreferences.getInt( "namen_zaehler", 0 );

        // Find the preference and update its summary
        Preference zaehlerPreference = findPreference( "namen_zaehler" );
        if ( zaehlerPreference != null ) {

            zaehlerPreference.setSummary( zaehler + "" );
        }
    }

}
