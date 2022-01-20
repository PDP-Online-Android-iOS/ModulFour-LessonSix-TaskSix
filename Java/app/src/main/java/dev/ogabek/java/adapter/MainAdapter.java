package dev.ogabek.java.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dev.ogabek.java.R;
import dev.ogabek.java.model.Member;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_SIMPLE_VIEW = 0;
    private static final int TYPE_RECYCLER_VIEW = 1;

    Context context;
    List<Member> members;

    public MainAdapter(Context context, List<Member> members) {
        this.context = context;
        this.members = members;
    }

    @Override
    public int getItemViewType(int position) {
        if (isAdsLayout(position)) {
            return TYPE_RECYCLER_VIEW;
        } else {
            return TYPE_SIMPLE_VIEW;
        }
    }

    private boolean isAdsLayout(int position) {
        return position % 13 == 0;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == TYPE_RECYCLER_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_layout, parent ,false);
            return new ItemRecyclerViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_layout, parent ,false);
            return new ItemSimpleViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemRecyclerViewHolder) {
            RecyclerView recyclerView = ((ItemRecyclerViewHolder) holder).recyclerView;

            refreshAdapter(recyclerView, prepareSubMemberList());

        }
    }

    private List<Member> prepareSubMemberList() {
        List<Member> members = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            members.add(new Member());
        }
        return members;
    }

    private void refreshAdapter(RecyclerView recyclerView, List<Member> members) {
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(context, members);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    private static class ItemRecyclerViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;

        public ItemRecyclerViewHolder(View view) {
            super(view);
            recyclerView = view.findViewById(R.id.rv_item_recycler_view);
            recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 1, GridLayoutManager.HORIZONTAL, false));
        }
    }

    private static class ItemSimpleViewHolder extends RecyclerView.ViewHolder {
        public ItemSimpleViewHolder(View view) {
            super(view);
        }
    }
}
