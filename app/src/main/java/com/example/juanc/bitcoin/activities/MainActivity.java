package com.example.juanc.bitcoin.activities;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.juanc.bitcoin.R;
import com.example.juanc.bitcoin.databinding.ActivityMainBinding;
import com.example.juanc.bitcoin.models.Place;
import com.example.juanc.bitcoin.utils.L;
import com.example.juanc.bitcoin.utils.Test;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

public class MainActivity extends AppCompatActivity implements OnBMClickListener{

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        buildMenu();
        Test.dataTest();
        L.placeStaticList.get(0).setImage(Place.bitmap2bytes(BitmapFactory.decodeResource(getResources(), R.drawable.arturocalle)));
    }

    private void buildMenu(){
        binding.mainMenuBtn.setButtonEnum(ButtonEnum.Ham);
        binding.mainMenuBtn.setPiecePlaceEnum(PiecePlaceEnum.HAM_4);
        binding.mainMenuBtn.setButtonPlaceEnum(ButtonPlaceEnum.HAM_4);

        int p = 20;

        //Option empresas
        HamButton.Builder empresas = new HamButton.Builder()
                .normalImageRes(R.drawable.ic_empresa)
                .imagePadding(new Rect(p,p,p,p))
                .normalTextRes(R.string.empresas)
                .normalColorRes(R.color.tealDark)
                .highlightedColorRes(R.color.tealLight)
                .listener(this);
        binding.mainMenuBtn.addBuilder(empresas);
        //Option eventos
        HamButton.Builder eventos = new HamButton.Builder()
                .normalImageRes(R.drawable.ic_empresa)
                .imagePadding(new Rect(p,p,p,p))
                .normalText("Eventos")
                .normalColorRes(R.color.orangeDark)
                .highlightedColorRes(R.color.orangeLight)
                .listener(this);
        binding.mainMenuBtn.addBuilder(eventos);
        //Option venta
        HamButton.Builder venta = new HamButton.Builder()
                .normalImageRes(R.drawable.ic_empresa)
                .imagePadding(new Rect(p,p,p,p))
                .normalText("Ventas")
                .normalColorRes(R.color.purpleDark)
                .highlightedColorRes(R.color.purpleLight)
                .listener(this);
        binding.mainMenuBtn.addBuilder(venta);
        //Option Compra
        HamButton.Builder compras = new HamButton.Builder()
                .normalImageRes(R.drawable.ic_empresa)
                .imagePadding(new Rect(p,p,p,p))
                .normalText("Compras")
                .normalColorRes(R.color.redDark)
                .highlightedColorRes(R.color.redLight)
                .listener(this);
        binding.mainMenuBtn.addBuilder(compras);
    }

    @Override
    public void onBoomButtonClick(int index) {
        Class newActivity = null;
        int map = -1;
        int person = -1;
        switch (index){
            case 0:
                newActivity = MapsActivity.class;
                map = MapsActivity.MAP_TYPE_LOCATION;
                break;
            case 1:
                newActivity = MapsActivity.class;
                map = MapsActivity.MAP_TYPE_EVENT;
                break;
            case 2:
                newActivity = PersonActivity.class;
                person = 1;
                break;
            case 3:
                newActivity = PersonActivity.class;
                person = 2;
                break;
        }
        if(newActivity!=null){
            Intent intent = new Intent(MainActivity.this, newActivity);
            intent.putExtra(MapsActivity.MAP_TYPE, map);
            intent.putExtra(PersonActivity.PERSON_TYPE, person);
            startActivity(intent);
        }
    }
}
