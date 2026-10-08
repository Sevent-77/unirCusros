package com.example.unircursos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class CoursePage extends AppCompatActivity {

    TextView txtNome, txtCampus, txtGrau, txtTurno;
    TextView textDescription;
    ImageView imgAvatar;
    Button bttShare, bttAcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_course_name);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtNome = findViewById(R.id.textViewName);
        txtGrau = findViewById(R.id.textViewGrau);
        txtTurno = findViewById(R.id.textViewTurno);
        txtCampus = findViewById(R.id.textViewCampus);
        textDescription = findViewById(R.id.textViewDescricao);

        imgAvatar = findViewById(R.id.imageView2);

        bttAcess = findViewById(R.id.buttonAcess);
        bttShare = findViewById(R.id.buttonShare);

        String nome = getIntent().getStringExtra("Nome");
        String grau = getIntent().getStringExtra("Grau");
        String campus = getIntent().getStringExtra("Campus");
        String turno = getIntent().getStringExtra("Turno");
        String imagem = getIntent().getStringExtra("Imagem");
        String descricao = getIntent().getStringExtra("Descricao");
        String site = getIntent().getStringExtra("Site");

        txtNome.setText(nome);
        txtGrau.setText(grau);
        txtTurno.setText(turno);
        txtCampus.setText(campus);
        textDescription.setText(descricao);

        bttShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder str = new StringBuilder(nome);
                str.append("\n").append(campus).append("\nSaiba mais em: ").append(site);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, str.toString());
                intent.setType("text/plain");

                Intent intentShare = Intent.createChooser(intent, "Compartilhar via...");
                startActivity(intentShare);
            }
        });
        bttAcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAcess = new Intent(Intent.ACTION_VIEW, android.net.Uri.parse(site));
                startActivity(intentAcess);

            }
        });

        Glide.with(this).load(imagem).into(imgAvatar);
    }
}