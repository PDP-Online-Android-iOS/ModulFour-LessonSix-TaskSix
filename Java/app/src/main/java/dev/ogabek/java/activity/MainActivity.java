package dev.ogabek.java.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import dev.ogabek.java.R;
import dev.ogabek.java.adapter.MainAdapter;
import dev.ogabek.java.model.Member;

public class MainActivity extends AppCompatActivity {

     Context context;
     RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        refreshAdapter(prepareMemberList());

    }

    private void initViews() {
        context = this;
        recyclerView = findViewById(R.id.rv_main_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 1));
    }

    private List<Member> prepareMemberList() {
        List<Member> members = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            members.add(new Member());
        }
        return members;
    }

    private void refreshAdapter(List<Member> members) {
        MainAdapter adapter = new MainAdapter(context, members);
        recyclerView.setAdapter(adapter);
    }

}