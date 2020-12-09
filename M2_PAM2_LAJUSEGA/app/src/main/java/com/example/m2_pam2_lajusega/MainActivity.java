package com.example.m2_pam2_lajusega;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.m2_pam2_lajusega.adapters.NotaAdapter;
import com.example.m2_pam2_lajusega.models.NotaModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String TAG = "LFNOT";
    final private String collection = "santos";
    private ListView lv_main_santos;
    private Button btn_main_nuevo;
    private ArrayList<NotaModel> list;
    private NotaAdapter adapter;
    private NotaModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_main_santos = findViewById(R.id.lv_main_santos);
        btn_main_nuevo = findViewById(R.id.btn_main_nuevo);


        btn_main_nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToRegister();
            }
        });


        list = new ArrayList<>();

        db.collection(collection)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                model = document.toObject(NotaModel.class);
                                model.setFbId(document.getId());
                                list.add(model);

                            }

                            adapter = new NotaAdapter(getApplicationContext(), list);
                            lv_main_santos.setAdapter(adapter);

                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }

                });



        lv_main_santos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                goToDetail(list.get(i).getFbId());
            }
        });

    }

    private void goToRegister(){
        Intent nuevo = new Intent(MainActivity.this, RegistroActivity.class);
        startActivity(nuevo);
    }

    private void goToDetail(String id){
        Intent intent = new Intent(MainActivity.this, DetalleActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }


    }




