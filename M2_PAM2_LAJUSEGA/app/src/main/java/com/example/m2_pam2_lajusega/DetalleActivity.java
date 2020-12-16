package com.example.m2_pam2_lajusega;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.m2_pam2_lajusega.adapters.NotaAdapter;
import com.example.m2_pam2_lajusega.models.NotaModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DetalleActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TextView reciboid, reciboname,recibofrase,recibodescripcion;
    private Button btn_update;

    private ArrayList<NotaModel> list;

    private String TAG = "LFNOT";
    final private String collection = "santos";
    private NotaModel model;
    private DocumentReference documentReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle2);

        reciboid = findViewById(R.id.tvReciboid);
        reciboname = findViewById(R.id.tvReciboname);
        recibofrase = findViewById(R.id.tvRecibofrase);
        recibodescripcion = findViewById(R.id.tvRecibodescripcion);
        btn_update = findViewById(R.id.btn_update);


        String id = getIntent().getStringExtra("id");
        if (id != null) {
            reciboid.setText(id);
            updateUI(id);
        } else {
            reciboid.setText("No se recibe nada");
        }


    }

    private void goToMain(){
        Intent listar = new Intent(DetalleActivity.this, MainActivity.class);
        startActivity(listar);
    }

    private void updateUI(String id){
        documentReference = db.collection(collection)
                .document(id);

        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){

                            DocumentSnapshot document = task.getResult();
                            model = document.toObject(NotaModel.class);
                            model.setFbId(document.getId());

                            if (model != null){
                                reciboid.setText(model.getFbId());
                                reciboname.setText(model.getTitulo());
                                recibofrase.setText(model.getFrase());
                                recibodescripcion.setText(model.getContenido());
                            }
                        }
                    }
                });

       /* db.collection(collection)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                model = document.toObject(NotaModel.class);
                                model.setFbId(document.getId());

                            }

                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }

                });*/

       btn_update.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (model.getFbId() != null && !model.getFbId().equals("")) {
                   Intent intent = new Intent(DetalleActivity.this, EditarActivity.class);
                   intent.putExtra("id", model.getFbId());
                   startActivity(intent);
               }

           }
       });



    }



}

