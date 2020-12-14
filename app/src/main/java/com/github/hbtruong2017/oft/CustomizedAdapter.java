package com.github.hbtruong2017.oft;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomizedAdapter implements ListAdapter {

    private Context context;
    private ArrayList<Product> products;

    public CustomizedAdapter(Context context, ArrayList<Product> products){
        this.products=products;
        this.context=context;

    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Product productData = products.get(position);
        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.row_item, null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(productData.getLink()));
                    context.startActivity(intent);
                }
            });
            TextView name = convertView.findViewById(R.id.textViewName);
            TextView price = convertView.findViewById(R.id.textViewPrice);
            ImageView image = convertView.findViewById(R.id.imageInList);
            name.setText(productData.getName());
            price.setText(productData.getPrice());
            Picasso.with(context)
                    .load(productData.getImage())
                    .into(image);
        }
        return convertView;
    }

    public void notifyOnDataSetChanged(Context cont, ArrayList<Product> arrayL){
        new CustomizedAdapter(cont, arrayL);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return products.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
