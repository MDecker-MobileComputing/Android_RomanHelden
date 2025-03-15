package de.eldecker.droid.romanhelden.einstellungen;

import de.eldecker.droid.romanhelden.R;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
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

        ActionBar actionBar = getSupportActionBar();
        if ( actionBar != null ) {

            actionBar.setDisplayHomeAsUpEnabled( true );
            actionBar.setHomeAsUpIndicator( R.drawable.baseline_arrow_back_24 ); // Optional: set a custom back icon
        }

        EinstellungenFragment einstellFragment = new EinstellungenFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace( R.id.einstellungen_container, einstellFragment )
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {

        if ( item.getItemId() == android.R.id.home ) {
            
            getOnBackPressedDispatcher().onBackPressed(); 
            return true;
        }
        return super.onOptionsItemSelected( item );
    }
    
}
