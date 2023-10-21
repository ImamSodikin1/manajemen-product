package com.cowsoran.produkmanajemen.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cowsoran.produkmanajemen.MainActivity;
import com.cowsoran.produkmanajemen.R;
import com.cowsoran.produkmanajemen.helpers.DatabaseHelper;
import com.cowsoran.produkmanajemen.model.Product;

public class ProdukActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_PICK = 1;

    private ImageView productImageView;
    private EditText productIdEditText;
    private EditText productNameEditText;
    private EditText productCategoryEditText;
    private EditText productDescriptionEditText;
    private ImageView ivBack;

    private String selectedImagePath;

    private DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk);

        productImageView = findViewById(R.id.ivProdukImage);
        productIdEditText = findViewById(R.id.edtProdukId);
        productNameEditText = findViewById(R.id.edtProdukName);
        productCategoryEditText = findViewById(R.id.edtProdukKategori);
        productDescriptionEditText = findViewById(R.id.edtProdukDeskripsi);

        ivBack = findViewById(R.id.ivBackProduk);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProdukActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        Button selectImageButton = findViewById(R.id.btnAddProdukImage);
        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });

        Button addProductButton = findViewById(R.id.btnAddProduk);
        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });

        db = new DatabaseHelper(this);

    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }

    private void addProduct() {
        String productId = productIdEditText.getText().toString();
        String productName = productNameEditText.getText().toString();
        String productCategory = productCategoryEditText.getText().toString();
        String productDescription = productDescriptionEditText.getText().toString();

        // Validasi input pengguna di sini

        // Buat objek Product dengan input pengguna
        Product product = new Product(Integer.parseInt(productId), productName, productCategory, productDescription, selectedImagePath);

        // Simpan produk ke database
        db.addProduct(product);

        // Reset input pengguna
        productIdEditText.setText("");
        productNameEditText.setText("");
        productCategoryEditText.setText("");
        productDescriptionEditText.setText("");
        productImageView.setImageResource(R.drawable.ic_add_gambar);

        Toast.makeText(this, "Product added successfully!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                productImageView.setImageURI(selectedImageUri);
                selectedImagePath = selectedImageUri.toString();
            }
            Glide.with(this).load(selectedImageUri).into(productImageView);
        }
    }
}