package com.example.parcialmoviles;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.parcialmoviles.adapter.ListUserAdapter;
import com.example.parcialmoviles.db.dbUsers;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.parcialmoviles.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView nombre;
    private TextView apellido;
    private TextView profesionText;
    private ToggleButton button;
    private EditText profesion;
    private EditText email;
    private Button publicar;


    private void validar(String nom, String apel, String prof, String em){
        if(!nom.equals("") && !apel.equals("") && !prof.equals("")  && !em.equals("")) {

            dbUsers dbUsuario = new dbUsers(MainActivity.this);
            long id = dbUsuario.addUsers(nom, apel,prof, em);

            if (id > 0) {
                Toast.makeText(MainActivity.this, "USUARIO GUARDADO", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "LLENAR CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        nombre = (TextView) findViewById(R.id.name);
        apellido = (TextView)findViewById(R.id.lastname);
        profesion = (EditText)findViewById(R.id.editTextProfession);
        profesionText = (TextView) findViewById(R.id.profession);
        email = (EditText) findViewById(R.id.editTextMail);
        button = (ToggleButton) findViewById(R.id.Tbutton);
        publicar = (Button) findViewById(R.id.button);


        profesion.setVisibility(View.GONE);
        profesionText.setVisibility(View.GONE);

        button.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            profesion.setVisibility(View.VISIBLE);
                            profesionText.setVisibility(View.VISIBLE);
                        } else {
                            profesion.setVisibility(View.GONE);
                            profesionText.setVisibility(View.GONE);
                        }
                    }
                });

        publicar.setAlpha(.5f);
        publicar.setEnabled(false);
        publicar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        validar(nombre.getText().toString(),
                                apellido.getText().toString(),
                                email.getText().toString(),
                                profesion.getText().toString()

                        );
                    }
                });

        adapter = new ListUserAdapter(dbUsers.showUsers());
    }


}