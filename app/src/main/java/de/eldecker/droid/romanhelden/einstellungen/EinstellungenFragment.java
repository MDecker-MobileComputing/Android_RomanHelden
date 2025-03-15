package de.eldecker.droid.romanhelden.einstellungen;

import android.os.Bundle;

import androidx.preference.ListPreference;

import androidx.preference.Preference;
import androidx.preference.Preference.SummaryProvider;
import androidx.preference.ListPreference.SimpleSummaryProvider;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SeekBarPreference;

import de.eldecker.droid.romanhelden.R;


/**
 * Vorlage für diese Klasse: {@code MySettingsFragment} von
 * <a href="https://developer.android.com/develop/ui/views/components/settings?hl=de">diese Seite</a>
 */
public class EinstellungenFragment extends PreferenceFragmentCompat {

    /**
     * Konfiguration für Preferences-Screen laden und SummaryProvider für Genre-Preference
     * setzen.
     */
    @Override
    public void onCreatePreferences( Bundle savedInstanceState, String rootKey ) {

        setPreferencesFromResource( R.xml.einstellungen, rootKey );

        ListPreference genrePreference = findPreference( "literatur_genre" );
        if ( genrePreference != null ) {

            SummaryProvider simpleSummaryProvider = SimpleSummaryProvider.getInstance();
            genrePreference.setSummaryProvider( simpleSummaryProvider );
        }


        SeekBarPreference seekBarPreference = findPreference("schriftgroesse_name");
        if ( seekBarPreference != null ) {

            seekBarPreference.setSummary ( seekBarPreference.getValue() + "" );

            seekBarPreference.setOnPreferenceChangeListener( new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange( Preference preference, Object neuerWert ) {

                    preference.setSummary( neuerWert + "" );
                    return true;
                }
            });
        }
    }

}
