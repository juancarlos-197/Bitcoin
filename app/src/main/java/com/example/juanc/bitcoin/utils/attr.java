package com.example.juanc.bitcoin.utils;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.juanc.bitcoin.R;
import com.squareup.picasso.Picasso;

/**
 * Created by RicardoAndrés on 07/08/2017.
 */

public class attr {

    @BindingAdapter("app:cityFromId")
    public static void loadCity(TextView txt, long id){
        for (com.example.juanc.bitcoin.models.City city : L.cityStaticList){
            if(id == city.getId()){
                txt.setText(city.getName());
            }
        }
    }

    @BindingAdapter("app:imageByte")
    public static void loadImageFromByte(ImageView img, byte[] resource){
        if(resource != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(resource, 0, resource.length);
            img.setImageBitmap(bitmap);
        }else {
            Picasso.with(img.getContext())
                    .load(R.drawable.ic_empresa)
                    .into(img);
        }
    }

}
