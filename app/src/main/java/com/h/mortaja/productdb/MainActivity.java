package com.h.mortaja.productdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private  EditText editTextProductName;
    private  EditText editTextProductPrice;
    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    private Button btnSave;
    private Button btnGet;
     FirebaseFirestore db  =FirebaseFirestore.getInstance();
     ArrayList<User>arrayList =new ArrayList<User>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextProductName =findViewById(R.id.edName);
        editTextProductPrice =findViewById(R.id.edPrice);
        recyclerView =findViewById(R.id.rcdata);
        btnSave =findViewById(R.id.btnSave);
        btnGet =findViewById(R.id.btnGet);
        homeAdapter =new HomeAdapter();
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        btnSave.setOnClickListener(v -> {
            saveToFirebase();
        });
        btnGet.setOnClickListener(v -> {
            loadFromFirebase();
        });


    }
   public void saveToFirebase(){
String Name =editTextProductName.getText().toString();
String Phone =editTextProductPrice.getText().toString();

      User user =new User(Name,Phone);
      db.collection("User").add(user).addOnSuccessListener(command -> {
               Toast.makeText(MainActivity.this,"onSuccess",Toast.LENGTH_LONG).show();


       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
               Toast.makeText(MainActivity.this,"onFailure",Toast.LENGTH_LONG).show();

           }
       });

   }
   public void loadFromFirebase() {
       db.collection("User").addSnapshotListener((value, error) -> {
           if(error==null){
               arrayList.clear();
               value.forEach(
                       queryDocumentSnapshot -> {
                  User user = queryDocumentSnapshot.toObject(User.class);
                  arrayList.add(user);

               });
               homeAdapter.arrayList=arrayList;
               homeAdapter.notifyDataSetChanged();
           }
       });
   }

}