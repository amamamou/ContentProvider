package com.example.app1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EvenementDatabaseHandler db;
    Evenement e1,e2;
    Button B;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inter_app1);
        db = new EvenementDatabaseHandler(this);
        B=(Button)findViewById(R.id.btn_view_db_app1);
        /*B.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                startActivity(intent);
            }
        });*/

        e2= new Evenement();
        e2.setTitre("Réunion de projet");
        e2.setLieu("Salle 101");
        e2.setDate("2024-11-23");
        e2.setHeure_debut("14:00");
        e2.setHeure_fin("15:30");



        e1 = new Evenement();
        e1.setTitre("Réunion de projet");
        e1.setLieu("Salle 101");
        e1.setDate("2024-11-23");
        e1.setHeure_debut("14:00");
        e1.setHeure_fin("15:30");

        db.insertEvenement(e1);
        db.insertEvenement(e2);




    }
}