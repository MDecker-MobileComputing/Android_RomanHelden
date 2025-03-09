package de.eldecker.droid.romanhelden;

import static de.eldecker.droid.romanhelden.namenGenerator.NamenGenerator.erzeugeName;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import de.eldecker.droid.romanhelden.einstellungen.EinstellungenActivity;
import de.eldecker.droid.romanhelden.namenGenerator.NameRecord;


/**
 * Haupt-Activity der App, zeigt die zufällig erzeugten Namen
 * von Romanfiguren an.
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG4LOGGING = "RomandHelden";

    /** UI-Element zur Anzeige des erzeugten Namens. */
    private TextView _nameTextView = null;

    /** Menü-Eintrag in ActionBar für Erzeugung neuer Name. */
    private MenuItem _neuNameMenuItem = null;

    /** Menü-Eintrag in ActionBar für Anzeige Einstellungen. */
    private MenuItem _einstellungenMenuItem = null;

    /** Menü-Eintrag in ActionBar für Kopieren Name in Zwischenablage. */
    private MenuItem _zwischenablageMenuItem = null;

    /** Aktuell angezeigter Name. */
    private NameRecord _nameRecord = null;


    /**
     * Lifecycle-Methode, wird beim Erzeugen der Activity für
     * eine bestimmte Display-Ausrichtung aufgerufen.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main );

        _nameTextView = findViewById( R.id.name_textview );
    }


    /**
     * Lifecycle, die bei Neu-Anzeige der Activity aufgerufen
     * wird. Holt einen neuen Namen und zeigt diesen an.
     */
    @Override
    protected void onStart() {

        super.onStart();

        neuerName();
    }


    /**
     * Methode lässt neuen Namen erzeugen und bringt ihn zur Anzeige
     */
    private void neuerName() {

        _nameRecord = erzeugeName();
        String name = _nameRecord.toString();
        _nameTextView.setText( name );
    }


    /**
     * Menü für ActionBar einrichten (Icon und Overflow-Menü)
     *
     * @param menu Menü-Objekt, zu dem Einträge hinzugefügt werden sollen.
     *
     * @return Konfiguriertes Menü
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.actionbar_menu, menu );

        _neuNameMenuItem        = menu.findItem( R.id.action_neuername      );
        _einstellungenMenuItem  = menu.findItem( R.id.action_einstellungen  );
        _zwischenablageMenuItem = menu.findItem( R.id.action_zwischenablage );

        return super.onCreateOptionsMenu( menu );
    }


    /**
     * Event-Handler für Menu-Items in der ActionBar.
     *
     * @param item  Menu-Item, welches gerade ein Event ausgelöst hat.
     *
     * @return Es wird {@code true} zurückgegeben, wenn wir in dieser
     *          Methode das Ereignis verarbeiten konnten, ansonsten
     *          der Wert der Super-Methode.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        final int selectedMenuId = item.getItemId();

        if ( selectedMenuId == _neuNameMenuItem.getItemId() ) {

            neuerName();
            return true;

        } else if ( selectedMenuId == _einstellungenMenuItem.getItemId() ) {

            Intent intent = new Intent(this, EinstellungenActivity.class);
            startActivity(intent);
            return true;

        } else if ( selectedMenuId == _zwischenablageMenuItem.getItemId() ) {

            kopiereInZwischenablage();
            return true;

        } else {

            return super.onOptionsItemSelected(item);
        }
    }


    /**
     * Aktuellen Namen in Zwischenablage kopieren.
     */
    private void kopiereInZwischenablage() {

        ClipboardManager clipboard = (ClipboardManager)
                                getSystemService( Context.CLIPBOARD_SERVICE );

        if ( clipboard == null ) {

            Toast.makeText( this,
                            "Kann nicht auf Zwischenablage zugreifen.",
                            Toast.LENGTH_LONG
                          ).show();
            return;
        }

        String name = _nameRecord.fuerZwischenablage();

        ClipData clip = ClipData.newPlainText("Name", name );

        clipboard.setPrimaryClip( clip );
    }

}