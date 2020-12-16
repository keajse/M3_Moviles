package com.example.m2_pam2_lajusega;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.m2_pam2_lajusega.models.NotaModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditarActivity extends AppCompatActivity {

    private EditText editarname, editardescripcion, editarfrase;
    private TextView reciboid;
    private Button btn_editar;
    private NotaModel model;
    private String TAG = "LFNOT";
    final private String collection = "santos";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference documentReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);


        reciboid = findViewById(R.id.reciboid);
        editardescripcion = findViewById(R.id.editardescripcion);
        editarname = findViewById(R.id.editarname);
        editarfrase = findViewById(R.id.editarfrase);
        btn_editar = findViewById(R.id.btn_editar);

        model = new NotaModel();

        String id = getIntent().getStringExtra("id");

        if (id != null && !id.equals("")){
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
                                    editarname.setText(model.getTitulo());
                                    editarfrase.setText(model.getFrase());
                                    editardescripcion.setText(model.getContenido());
                                }
                            }
                        }
                    });
        }



        btn_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contenido, titulo, frase;
                titulo = editarname.getText().toString();
                contenido = editarfrase.getText().toString();
                frase = editarfrase.getText().toString();

                String id = model.getFbId();

                if(id != null && !id.equals("")) {

                    model.setTitulo(titulo);
                    model.setContenido(contenido);
                    model.setFrase(frase);

// Add a new document with a generated ID
                    db.collection(collection)
                            .add(model)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {


                                    Intent intent = new Intent(EditarActivity.this,DetalleActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error adding document", e);
                                    Toast.makeText(getApplicationContext(), "No se Actualizó correctamente" + e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                }
            }


        });



    }



}
