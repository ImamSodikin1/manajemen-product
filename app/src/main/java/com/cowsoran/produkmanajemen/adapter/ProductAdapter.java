package com.cowsoran.produkmanajemen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cowsoran.produkmanajemen.R;
import com.cowsoran.produkmanajemen.helpers.DatabaseHelper;
import com.cowsoran.produkmanajemen.model.Product;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {

    private Context context;
    private List<Product> productList;
    private ImageView ivDelete;

    private DatabaseHelper dbHelper;

    public ProductAdapter(Context context, List<Product> productList) {
        super(context, 0, productList);
        this.context = context;
        this.productList = productList;

        dbHelper = new DatabaseHelper(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_product, null);

        }

        Product product = productList.get(position);


        ivDelete = view.findViewById(R.id.ivDelete);
        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.deleteProduct(product.getId());
                productList.remove(position);
                notifyDataSetChanged();
            }
        });



        if (product != null) {
            ImageView productImageView = view.findViewById(R.id.ivResult);
            TextView idTextView = view.findViewById(R.id.tvResultId);
            TextView nameTextView = view.findViewById(R.id.tvResultName);
            TextView categoryTextView = view.findViewById(R.id.tvResultKategori);
            TextView descriptionTextView = view.findViewById(R.id.tvResultDeskripsi);

            Glide.with(context).load(product.getImagePath()).into(productImageView);
            idTextView.setText("ID: " + product.getId());
            nameTextView.setText("Nama: " + product.getName());
            categoryTextView.setText("Kategori: " + product.getCategory());
            descriptionTextView.setText("Deskripsi: " + product.getDescription());
        }

        return view;
    }
}


