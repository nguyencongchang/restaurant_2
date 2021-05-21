package com.example.restaurant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant.DTO.CategoryDTO;

import java.util.List;

public class AddDishActivity extends AppCompatActivity implements View.OnClickListener {
    public static int REQUEST_CODE_CHANGE_IMG = 30001;
    public static int REQUEST_CODE_ADD_CAT = 30002;

    EditText editTextDishName, editTextPrice;
    ImageView img_dish, img_add_cat;
    Spinner spinCat;
    Button btn_ok, btn_cancel;
    String imgUrl;
    List<CategoryDTO> listCat;

    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_add_dish);
        editTextDishName = findViewById(R.id.ed_dish_name);
        editTextPrice = findViewById(R.id.ed_price);
        img_dish = findViewById(R.id.img_dish);
        img_add_cat = findViewById(R.id.img_add_cat);
        spinCat = findViewById(R.id.spin_cat);
        btn_ok = findViewById(R.id.btn_ok_add_dish);
        btn_cancel = findViewById(R.id.btn_cancel_add_dish);



        img_add_cat.setOnClickListener(this);
        img_dish.setOnClickListener(this);
        btn_ok.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_dish:
                Intent intentChangeIMG = new Intent();
                intentChangeIMG.setType("image/*");
                intentChangeIMG.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(intentChangeIMG,"Select photo"),REQUEST_CODE_CHANGE_IMG);
                break;
            case R.id.img_add_cat:
                Intent intent = new Intent(AddDishActivity.this, AddCategoryActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_CAT);
                break;
            case R.id.btn_ok_add_dish:
                int pos = spinCat.getSelectedItemPosition();
                String dishName = editTextDishName.getText().toString();
                String price = editTextPrice.getText().toString();


                Intent intent1 = new Intent();
                setResult(Activity.RESULT_OK,intent1);
                break;
            case R.id.btn_cancel_add_dish:
                finish();

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_ADD_CAT && resultCode== Activity.RESULT_OK){

        }
        if(requestCode == REQUEST_CODE_CHANGE_IMG && resultCode == Activity.RESULT_OK){
            imgUrl = data.getData().toString();
            img_dish.setImageURI(data.getData());
        }

    }

}
