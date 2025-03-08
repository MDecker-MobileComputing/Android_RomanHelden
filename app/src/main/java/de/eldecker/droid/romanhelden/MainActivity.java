package de.eldecker.droid.romanhelden;

import static de.eldecker.droid.romanhelden.NamenGenerator.erzeugeName;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private TextView _nameTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _nameTextView = findViewById( R.id.name_textview );
    }

    @Override
    protected void onStart() {

        super.onStart();

        NameRecord nameRecord = erzeugeName();
        _nameTextView.setText( nameRecord.toString() );
    }

}