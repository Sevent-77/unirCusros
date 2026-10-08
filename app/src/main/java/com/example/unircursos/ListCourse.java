package com.example.unircursos;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListCourse extends AppCompatActivity {


    private RecyclerView recyclerView;
    private CourseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_course);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        String campus = getIntent().getStringExtra("campus");
        String grau = getIntent().getStringExtra("grau");
        boolean noite = getIntent().getBooleanExtra("noite", false);




        ArrayList<Curso> todosCursos = CursosData.getCursos();
        ArrayList<Curso> lista = new ArrayList<>();

        for (Curso curso : todosCursos) {

            boolean campusValido = campus == null
                    || campus.equalsIgnoreCase("todos")
                    || campus.trim().equalsIgnoreCase(curso.getCampus().trim());

            boolean grauValido = grau == null
                    || grau.equalsIgnoreCase("todos")
                    || grau.trim().equalsIgnoreCase(curso.getGrau().trim());

            boolean turnoValido = !noite
                    || curso.getTurno().trim().equalsIgnoreCase("noturno");

            if (campusValido && grauValido && turnoValido) {
                lista.add(curso);
            }
        }

        adapter = new CourseAdapter(lista);
        recyclerView = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        findViewById(R.id.emptyMessage).setVisibility(lista.isEmpty()
                ? android.view.View.VISIBLE : android.view.View.GONE);
    }
}