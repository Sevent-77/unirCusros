package com.example.unircursos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    private Button button;
    private RadioGroup radioGroup;
    private CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        spinner = findViewById(R.id.spinner);
        radioGroup = findViewById(R.id.radioGroup);
        checkBox = findViewById(R.id.checkBox);


        String option[] = {"Vilhena","Rolim de Moura", "Presidente Médici", "Ji-Paraná", "Guajará-Mirim","Cacoal", "Ariquemes", "Porto Velho", "Todos"};
        Collections.reverse(Arrays.asList(option));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, option);
        spinner.setAdapter(adapter);

        String campus = spinner.getSelectedItem().toString();
        int selected = radioGroup.getCheckedRadioButtonId();
        RadioButton rb = findViewById(selected);
        String grau = rb.getText().toString();
        boolean noite = checkBox.isChecked();


        button = findViewById(R.id.button);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ListCourse.class);
            intent.putExtra("campus", campus);
            intent.putExtra("grau", grau);
            intent.putExtra("noite", noite);
            startActivity(intent);
        });
    }
}