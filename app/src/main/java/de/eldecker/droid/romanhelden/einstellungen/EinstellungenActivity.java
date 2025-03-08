package de.eldecker.droid.romanhelden.einstellungen;

import  de.eldecker.droid.romanhelden.R;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


/**
 * Vorlage für diese Klasse: {@code MySettingsActivity} von
 * <a href="https://developer.android.com/develop/ui/views/components/settings?hl=de">diese Seite</a>
 */
public class EinstellungenActivity extends AppCompatActivity {

    /**
     * Lifecycle-Methode; zeigt {@link EinstellungenFragment} an.
     */
    protected void onCreate( Bundle savedInstanceState ) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_einstellungen );

        EinstellungenFragment einstellFragment = new EinstellungenFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace( R.id.einstellungen_container, einstellFragment )
                .commit();
    }
    
}
