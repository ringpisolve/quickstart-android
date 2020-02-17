package com.google.firebase.example.fireeats.java;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.example.fireeats.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class StudentInfoFormActivity extends AppCompatActivity {

    private static final String TAG = "StudentInfoFormActivity";
    private FirebaseFirestore db;
    ArrayList<Map<String, Object>> allStudents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info_form);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = FirebaseFirestore.getInstance();
        final EditText firstName = findViewById(R.id.firstName_edit_text);
        final EditText lastName = findViewById(R.id.lastName_edit_text);
        final EditText admissionNo = findViewById(R.id.admissionNo_edit_text);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                addStudent(db, String.valueOf(firstName.getText()), String.valueOf(lastName.getText()), Integer.parseInt(admissionNo.getText().toString()));

            }
        });
    }

    public void addStudent(FirebaseFirestore db, String firstName, String lastName, int admissionNo) {

        // Create a new user with a first and last name
        Map<String, Object> student = new HashMap<>();
        student.put("firstName", firstName);
        student.put("lastName", lastName);
        student.put("admissionNo", admissionNo);

        // Add a new document with a generated ID
        db.collection("students")
                .add(student)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });


    }

    private ArrayList<Map<String, Object>> getAllStudentsInfo(FirebaseFirestore db) {
        //allStudents.clear();
        db.collection("students")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d(TAG+" getStudInf", document.getId() + " => " + document.getData());
                                allStudents.add(document.getData());
                                Log.d(TAG, "getAllStudentsInfo"+ String.valueOf(allStudents.size()));
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
        return allStudents;
    }

}
