package de.eldecker.droid.romanhelden;

import static de.eldecker.droid.romanhelden.NamenGenerator.erzeugeName;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    /** UI-Element zur Anzeige des erzeugten Namens. */
    private TextView _nameTextView = null;

    /** Menu-Eintrag in ActionBar für Erzeugung neuer Name. */
    private MenuItem _neuNameMenuItem = null;


    /**
     * Lifecycle-Methode
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        NameRecord nameRecord = erzeugeName();
        _nameTextView.setText( nameRecord.toString() );
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

        _neuNameMenuItem = menu.findItem( R.id.action_neuername );

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

        if ( selectedMenuId == item.getItemId() ) {

            neuerName();
            return true;

        } else {

            return super.onOptionsItemSelected(item);
        }
    }

}