package de.eldecker.droid.romanhelden;

import static de.eldecker.droid.romanhelden.namenGenerator.NamenGenerator.erzeugeName;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import de.eldecker.droid.romanhelden.einstellungen.EinstellungenActivity;
import de.eldecker.droid.romanhelden.namenGenerator.LiteraturGenreEnum;
import de.eldecker.droid.romanhelden.namenGenerator.NameRecord;


/**
 * Haupt-Activity der App, zeigt die zufällig erzeugten Namen
 * von Romanfiguren an.
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG4LOGGING = "RomandHelden";

    /** UI-Element zur Anzeige des erzeugten Namens. */
    private TextView _nameTextView = null;

    /** Aktuell angezeigter Name. */
    private NameRecord _nameRecord = null;

    /** Objekt für Zugriff auf Einstellungen. */
    private SharedPreferences _sharedPreferences = null;


    /**
     * Lifecycle-Methode, wird beim Erzeugen der Activity für
     * eine bestimmte Display-Ausrichtung aufgerufen.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main );

        _nameTextView = findViewById( R.id.name_textview );

        _sharedPreferences = PreferenceManager.getDefaultSharedPreferences( this );

        neuerName();
    }


    /**
     * Methode lässt neuen Namen erzeugen und bringt ihn zur Anzeige
     */
    private void neuerName() {

        String genreTechWert = _sharedPreferences.getString( "literatur_genre", "KINDERBUCH" );

        LiteraturGenreEnum genreEnum = LiteraturGenreEnum.valueOf( genreTechWert );

        _nameRecord = erzeugeName( genreEnum );
        String name = _nameRecord.toString();
        _nameTextView.setText( name );

        namenZaehlerErhoehen();
    }


    /**
     * Zähler für Anzahl der erzeugten Namen in SharedPreferences um 1 erhöhen.
     */
    private void namenZaehlerErhoehen() {

        int zaehler = _sharedPreferences.getInt( "namen_zaehler", 0 );
        zaehler++;
        SharedPreferences.Editor editor = _sharedPreferences.edit();

        editor.putInt( "namen_zaehler", zaehler );
        editor.apply(); // Änderung committen

        Log.i( TAG4LOGGING, "Gesamtanzahl erzeugter Namen: " + zaehler );
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
    public boolean onOptionsItemSelected( MenuItem item ) {

        final int selectedMenuId = item.getItemId();

        if ( selectedMenuId == R.id.action_neuername ) {

            neuerName();
            return true;

        } else if ( selectedMenuId == R.id.action_einstellungen ) {

            Intent intent = new Intent( this, EinstellungenActivity.class );
            startActivity( intent );
            return true;

        } else if ( selectedMenuId == R.id.action_zwischenablage ) {

            kopiereInZwischenablage();
            return true;

        } else if ( selectedMenuId == R.id.action_ueber ) {

            aboutDialogAnzeigen();
            return true;

        } else {

            return super.onOptionsItemSelected( item );
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


    /**
     * Dialog "Über diese App" anzeigen, u.a. mit Anzahl der bereits erzeugten
     * Namen.
     */
    private void aboutDialogAnzeigen() {

        String versionName = BuildConfig.VERSION_NAME;
        int zaehler = _sharedPreferences.getInt( "namen_zaehler", 0 );

        String ueberText =
                getString( R.string.ueber_text,
                           zaehler,
                           versionName );

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder( this );
        dialogBuilder.setTitle( R.string.ueber_dialog_titel );
        dialogBuilder.setMessage( ueberText );

        dialogBuilder.setPositiveButton( R.string.ueber_dialog_button, null );

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

}