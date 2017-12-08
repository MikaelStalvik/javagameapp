package com.imploded.javagameapp.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.imploded.javagameapp.R;
import com.imploded.javagameapp.models.FilterItem;

import java.util.List;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.FilterHolder> {

    private List<FilterItem> filterItems;
    private Context context;

    public FilterAdapter(Context context, List<FilterItem> filterItems) {
        this.filterItems = filterItems;
        this.context = context;
    }

    @Override
    public FilterAdapter.FilterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_filter, parent, false);
        FilterAdapter.FilterHolder holder = new FilterAdapter.FilterHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(FilterAdapter.FilterHolder holder, int position) {
        final FilterItem item = filterItems.get(position);
        holder.platformCheckBox.setChecked(item.checked);
        holder.platformCheckBox.setText(item.name);
        holder.countTextView.setText(String.valueOf(item.count));
    }

    @Override
    public int getItemCount() {
        return filterItems.size();
    }

    public class FilterHolder extends RecyclerView.ViewHolder {

        CheckBox platformCheckBox;
        TextView countTextView;
        View layout;

        public FilterHolder(View v) {
            super(v);
            layout = v;
            //v.setOnClickListener(this);
            platformCheckBox = v.findViewById(R.id.platformCheckBox);
            countTextView = v.findViewById(R.id.countTextView);
        }

    }
}
