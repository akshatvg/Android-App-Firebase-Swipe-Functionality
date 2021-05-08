package com.akshatvg.assessment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> list = new ArrayList<>();
    RecyclerAdapter recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        getData();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerAdapter(list, this);
        recyclerView.setAdapter(recyclerAdapter);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition();
            switch (direction){
                case ItemTouchHelper.LEFT:
                    list.remove(position);
                    recyclerAdapter.notifyItemRemoved(position);
                    break;
            }
        }
    };

    void getData(){
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://android-assessment-1-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference ref = database.getReference();
        Log.d("Here", database.toString());
        ref.child("Strings").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("Here", "Called");
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String s = ds.getValue(String.class);
                    Log.d("Here", s);
                    list.add(s);
                    recyclerAdapter.notifyItemInserted(list.size());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Here", error.getCode()+" Failed");
                throw error.toException();
            }
        });
    }
}