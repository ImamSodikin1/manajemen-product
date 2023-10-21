package com.cowsoran.produkmanajemen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cowsoran.produkmanajemen.R;
import com.cowsoran.produkmanajemen.helpers.DatabaseHelper;
import com.cowsoran.produkmanajemen.model.Product;

import java.util.List;

public class StockAdapter extends ArrayAdapter<Product> {

    private DatabaseHelper dbHelper;

    public StockAdapter(Context context, List<Product> productList) {
        super(context, 0, productList);
        dbHelper = new DatabaseHelper(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.stock_produk, parent, false);
        }

        Product product = getItem(position);

        TextView namaProdukTextView = convertView.findViewById(R.id.tvStokProdukName);
        TextView stokProdukTextView = convertView.findViewById(R.id.tvStok);

        namaProdukTextView.setText("Nama Produk :" + product.getName());
        stokProdukTextView.setText("Stok        : " + dbHelper.getProductCount(product.getName()));

        return convertView;
    }
}

