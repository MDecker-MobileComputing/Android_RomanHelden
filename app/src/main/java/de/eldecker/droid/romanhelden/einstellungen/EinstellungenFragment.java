package de.eldecker.droid.romanhelden.einstellungen;

import static android.widget.Toast.LENGTH_LONG;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
public class EinstellungenFragment extends PreferenceFragmentCompat
                                   implements Preference.OnPreferenceClickListener  {

    private SharedPreferences _sharedPreferences = null;

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


        SeekBarPreference seekBarPreference = findPreference( "schriftgroesse_name" );
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

        Preference zaehlerResetPreference = findPreference( "reset_zaehler" );
        zaehlerResetPreference.setOnPreferenceClickListener( this );

        _sharedPreferences = getPreferenceManager().getSharedPreferences();
    }


    /**
     * Event-Handler-Methode für Preference zum Zurücksetzen des Zählers.
     *
     * @param preference The preference that was clicked
     *
     * @return Immer {@code true} als Zeichen, dass das Event "verbraucht" wurde
     */
    @Override
    public boolean onPreferenceClick(@NonNull Preference preference) {

        Context context = requireContext();

        OnClickListener jaHandler = new OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which ) {

                SharedPreferences.Editor editor = _sharedPreferences.edit();
                editor.putInt( "namen_zaehler", 0 );
                editor.apply(); // Änderung committen

                Toast.makeText( context, R.string.toast_zaehler_reset_erfolg, LENGTH_LONG ).show();
            }
        };

        new AlertDialog.Builder( context )
                       .setTitle(R.string.zaehler_reset_confirm_titel)
                       .setMessage(R.string.zaehler_reset_confirm_text)
                       .setPositiveButton( R.string.button_ja, jaHandler )
                       .setNegativeButton(R.string.button_nein, null )
                       .show();

        return true;
    }
}
