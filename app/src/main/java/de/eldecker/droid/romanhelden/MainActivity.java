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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuCompat;
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

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        _nameTextView = findViewById( R.id.name_textview );

        _sharedPreferences = PreferenceManager.getDefaultSharedPreferences( this );

        neuerName();
    }


    /**
     * Lifecycle-Methode; wird aufgerufen, wenn Activity neu zur Anzeige gebracht wird.
     * Aktualisiert bei Bedarf den Untertitel und setzt die Schriftgröße.
     */
    @Override
    protected void onStart() {

        super.onStart();

        ActionBar actionBar = getSupportActionBar();
        if ( actionBar != null ) {

            String genreString = getGenreAnzeigeName();

            String untertitel = getString( R.string.actionbar_subtitel_genre, genreString );
            actionBar.setSubtitle( untertitel );
        }

        int schriftgroesse = _sharedPreferences.getInt("schriftgroesse_name", 40 );
        _nameTextView.setTextSize( schriftgroesse );
    }


    /**
     * Gibt Anzeigename für aktuell gewähltes Genre laut Preferences zurück.
     *
     * @return Anzeigename Literaturgenre, z.B. "Western"
     */
    private String getGenreAnzeigeName() {

        LiteraturGenreEnum genreEnum = getGenreAusPreferences();

        String[] techwerteArray =
                getResources().getStringArray( R.array.literatur_genres_techwerte );

        int index = -1;
        for ( int i = 0; i < techwerteArray.length; i++ ) {

            if ( techwerteArray[i].equals( genreEnum.toString() ) ) {

                index = i;
                break;
            }
        }

        if ( index == -1 ) {

            return "???";
        }

        String[] anzeigeArray =
                getResources().getStringArray( R.array.literatur_genres_anzeigenamen );
        return anzeigeArray[ index ];
    }


    /**
     * Aktuelles Genre aus Preferences auslesen.
     *
     * @return Aktuelles Literaturgenre, für das Namen erzeugt werden sollen.
     */
    private LiteraturGenreEnum getGenreAusPreferences() {

        String genreTechWert =
                _sharedPreferences.getString( "literatur_genre", "KINDERBUCH" );

        return LiteraturGenreEnum.valueOf( genreTechWert );
    }


    /**
     * Methode lässt neuen Namen erzeugen und bringt ihn zur Anzeige
     */
    private void neuerName() {

        boolean allesGrossbuchstaben =
                _sharedPreferences.getBoolean( "alles_grossbuchstaben", false );

        LiteraturGenreEnum genreEnum = getGenreAusPreferences();

        _nameRecord = erzeugeName( genreEnum );

        String name = "";
        if ( allesGrossbuchstaben ) {

            name = _nameRecord.toStringNurGrossbuchstaben();

        } else {

            name = _nameRecord.toStringNormal();
        }

        _nameTextView.setText( name );
        starteAnimation();

        namenZaehlerErhoehen();
    }


    /**
     * Tween-Animation auf (neu erzeugten) Namen anwenden.
     */
    private void starteAnimation() {

        boolean animationAktiv =
                _sharedPreferences.getBoolean( "animation_aktiv", false );

        if ( animationAktiv == false ) {

            return;
        }

        Animation tweenAnimation =
                AnimationUtils.loadAnimation( this,
                                              R.anim.tween_animation );

        _nameTextView.startAnimation( tweenAnimation );
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

        MenuCompat.setGroupDividerEnabled( menu, true );

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
     * Aktuell angezeigten Namen in Zwischenablage kopieren.
     */
    private void kopiereInZwischenablage() {

        ClipboardManager clipboard = (ClipboardManager)
                                getSystemService( Context.CLIPBOARD_SERVICE );

        if ( clipboard == null ) {

            Toast.makeText( this,
                            R.string.toast_zwischenablage_fehler,
                            Toast.LENGTH_LONG
                          ).show();
            return;
        }

        String name = _nameRecord.toString();

        String clipboardLabel = getString( R.string.clipboard_label );
        ClipData clip = ClipData.newPlainText( clipboardLabel, name );

        clipboard.setPrimaryClip( clip );
    }


    /**
     * Dialog "Über diese App" anzeigen, u.a. mit Anzahl der bereits erzeugten
     * Namen und der Version.
     */
    private void aboutDialogAnzeigen() {

        String versionName = BuildConfig.VERSION_NAME;
        int zaehler = _sharedPreferences.getInt( "namen_zaehler", 0 );

        String ueberText = getString( R.string.ueber_text,
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
