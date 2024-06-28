package com.hrap.app.fbase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
Context context=this;
FirebaseFirestore database;
TextView tvResult;
String id="";
ToDo toDo=null;
Button btn;
Button xoa;
Button up;
Button show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database=FirebaseFirestore.getInstance();
        btn=findViewById(R.id.btn_A);
        tvResult=findViewById(R.id.tvResult);
        xoa=findViewById(R.id.button3);
        up=findViewById(R.id.button);
        show=findViewById(R.id.button2);
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteFireBase();
            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateFireBase();
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectData();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertDatabase();
            }
});
    }
    public void InsertDatabase(){
        id= UUID.randomUUID().toString();
        toDo=new ToDo(id,"title 1","content 1");
        HashMap<String,Object> maptodo=toDo.convertoHasMap();
        database.collection("TODO")
                .document(id)
                .set(maptodo)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        tvResult.setText("insert thnah coong");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        tvResult.setText(e.getMessage());
                    }
                });
    }

    public void UpdateFireBase(){
        id="7cdc9033-d435-4203-8e32-5cbfc8593bbc";
        toDo=new ToDo(id,"title up lan 1","content up lan 1");
        database.collection("TODO")
                .document(toDo.getId())
                .update(toDo.convertoHasMap())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        tvResult.setText("update thanh cong");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        tvResult.setText(e.getMessage());
                    }
                });
    }
    public void deleteFireBase(){
        id="17d3ada9-8cd1-4ce9-8586-0d11ddaae3fd";
        database.collection("TODO")
                .document(id)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        tvResult.setText("xoa thanh cong");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        tvResult.setText(e.getMessage());
                    }
                });
    }
    String strKQ="";
    public ArrayList<ToDo>SelectData(){
        ArrayList<ToDo> list=new ArrayList<>();
        database.collection("TODO")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                      if (task.isSuccessful()){
                          for (QueryDocumentSnapshot d:task.getResult()){
                              ToDo toDo1=d.toObject(ToDo.class);
                              strKQ+="ID: "+toDo1.getId()+"\n";
                              list.add(toDo1);
                          }
                          tvResult.setText(strKQ);
                      }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        tvResult.setText(e.getMessage());
                    }
                });

        return  list;
    }

}