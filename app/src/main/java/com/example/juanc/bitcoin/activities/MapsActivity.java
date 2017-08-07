package com.example.juanc.bitcoin.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.alfredayibonte.questionnaireviewlib.adapters.CheckListAdapter;
import com.alfredayibonte.questionnaireviewlib.models.Answer;
import com.alfredayibonte.questionnaireviewlib.utils.AnswerType;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.juanc.bitcoin.R;
import com.example.juanc.bitcoin.databinding.ActivityMapsBinding;
import com.example.juanc.bitcoin.databinding.TemplatePlaceViewBinding;
import com.example.juanc.bitcoin.models.Category;
import com.example.juanc.bitcoin.models.City;
import com.example.juanc.bitcoin.models.Country;
import com.example.juanc.bitcoin.models.Event;
import com.example.juanc.bitcoin.models.Place;
import com.example.juanc.bitcoin.utils.L;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener,
        AdapterView.OnItemSelectedListener, CheckListAdapter.OnCheckItemClickListener,
        GoogleMap.OnInfoWindowClickListener {

    public static final String MAP_TYPE = "MapType";
    public static final int MAP_TYPE_LOCATION = 1;
    public static final int MAP_TYPE_EVENT = 2;

    ActivityMapsBinding binding;
    private GoogleMap mMap;
    int mapType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_maps);

        Bundle bundle = getIntent().getExtras();
        mapType = bundle.getInt(MAP_TYPE);

        L.categorySelectStaticList = new ArrayList<>();
        L.categorySelectStaticList.addAll(L.categoryStaticList);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        binding.mapSearchBtn.setOnClickListener(this);
        binding.buscarBtn.setOnClickListener(this);
        binding.mapCountrySpn.setOnItemSelectedListener(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        if(mapType == MAP_TYPE_LOCATION){
            loadPlaces(null);
        }else if(mapType == MAP_TYPE_EVENT){
            loadEvents();
        }

        googleMap.setOnInfoWindowClickListener(this);
    }

    private void setLocationSearch(){
        List<Address> addressList = null;
        Geocoder geocoder = new Geocoder(this);

        String query;
        float zoom;

        if(binding.mapCitySpn.getSelectedItem()!=null){
            query = binding.mapCitySpn.getSelectedItem().toString();
            zoom = 13;
            if(mapType == MAP_TYPE_LOCATION){
                loadPlaces(selectedCity(binding.mapCitySpn.getSelectedItem().toString()));
            }else{
                //loadEvents con filtro
            }

        }else{
            query = binding.mapCountrySpn.getSelectedItem().toString();
            zoom = 5;
        }
        try {
            addressList = geocoder.getFromLocationName(query, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Address address = addressList.get(0);
        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

    }

    private City selectedCity(String name){
        City city = null;
        for (City c : L.cityStaticList) {
            if(c.getName().equals(name)){
                city = c;
            }
        }
        return city;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.map_search_btn:
                loadCountries();
                binding.formCard.setVisibility(View.VISIBLE);
                binding.mapSearchBtn.setVisibility(View.GONE);
                if(mapType == MAP_TYPE_LOCATION){
                    binding.mapCategoryList.setVisibility(View.VISIBLE);
                }else{
                    binding.mapCategoryList.setVisibility(View.GONE);
                }
                break;
            case R.id.buscar_btn:
                setLocationSearch();
                binding.formCard.setVisibility(View.GONE);
                binding.mapSearchBtn.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void loadCountries(){
        ArrayList<String> countries = new ArrayList<>();
        for (Country country : L.countryStaticList) {
            countries.add(country.getName());
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                countries);
        binding.mapCountrySpn.setAdapter(adapter);

        ArrayList<String> categories = new ArrayList<>();

        for (Category category : L.categoryStaticList){
            categories.add(category.getName());
        }

        binding.mapCategoryList.setQuestion("Seleccione las categorias: ");
        binding.mapCategoryList.setViewType(AnswerType.CHECKLIST);
        binding.mapCategoryList.setAnswers(categories);
        binding.mapCategoryList.addCheckItemListener(this);

    }

    private void loadPlaces(City city){

        mMap.clear();

        if(city == null){
            for (Place place : L.placeStaticList) {
                for (Category category : L.categorySelectStaticList){
                    if(category.getId() == place.getCategory_id()){
                        mMap.addMarker(addPlaceMarker(place));
                    }
                }
            }
        }else{
            for (Place place : L.placeStaticList) {
                if(place.getCity_id() == city.getId()){
                    for (Category category : L.categorySelectStaticList){
                        if(category.getId() == place.getCategory_id()){
                            mMap.addMarker(addPlaceMarker(place));
                        }
                    }
                }

            }
        }


    }

    private void loadEvents(){
        Calendar actual = Calendar.getInstance();


        for (Event event : L.eventStaticList) {
            Calendar eDate = Calendar.getInstance();
            eDate.setTime(event.getDate());
            if(actual.before(eDate)){
                mMap.addMarker(addEventMarker(event));
            }

        }
    }

    private MarkerOptions addEventMarker(Event event) {
        LatLng latLng = new LatLng(event.getLatitude(), event.getLongitude());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(event.getDate());

        int y = calendar.get(Calendar.YEAR);
        String m = (calendar.get(Calendar.MONTH)+1)<10 ? "0"+(calendar.get(Calendar.MONTH)+1) : ""+(calendar.get(Calendar.MONTH)+1);
        String d = calendar.get(Calendar.DAY_OF_MONTH)<10 ? "0"+calendar.get(Calendar.DAY_OF_MONTH) : ""+calendar.get(Calendar.DAY_OF_MONTH);
        String h = calendar.get(Calendar.HOUR_OF_DAY)<10 ? "0"+calendar.get(Calendar.HOUR_OF_DAY) : ""+calendar.get(Calendar.HOUR_OF_DAY);
        String mm = calendar.get(Calendar.MINUTE)<10 ? "0"+calendar.get(Calendar.MINUTE) : ""+calendar.get(Calendar.MINUTE);

        String snippet = y + "-" + m + "-" + d + "   " + h + ":" + mm;

        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .title(event.getName())
                .snippet(snippet)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));

        return markerOptions;
    }

    private MarkerOptions addPlaceMarker(Place place){
        LatLng latLng = new LatLng(place.getLatitude(), place.getLongitude());
        Category category = L.categoryStaticList.get(0);
        for (Category c : L.categoryStaticList) {
            if(c.getId() == place.getCategory_id()){
                category = c;
            }
        }

        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .title(place.getName())
                .snippet("Categoria: " + category.getName())
                .icon(BitmapDescriptorFactory.defaultMarker(category.getColor()));

        return markerOptions;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Country country = L.countryStaticList.get(i);

        ArrayList<String> cities = new ArrayList<>();
        for (City city : L.cityStaticList) {
            if(city.getCountry_id() == country.getId()){
                cities.add(city.getName());
            }
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                cities);
        binding.mapCitySpn.setAdapter(adapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onCheckItemClick(List<Answer> list) {

        L.categorySelectStaticList.clear();
        for (Answer answer :list) {
            if(answer.isChecked()){
                for (Category category :
                        L.categoryStaticList) {
                    if (answer.getAnswer().equals(category.getName())){
                        L.categorySelectStaticList.add(category);
                    }
                }
            }
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

        if(mapType == MAP_TYPE_LOCATION){
            Place place = new Place();

            for(Place p : L.placeStaticList){
                if(p.getName().equals(marker.getTitle())){
                    place = p;
                }
            }

            binding.mapPlaceView.setPlace(place);
            binding.placeViewContainer.setVisibility(View.VISIBLE);
            binding.mapPlaceView.placeViewOkBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binding.placeViewContainer.setVisibility(View.GONE);
                }
            });
        }else{
            //Hace lo de Evento
        }
    }
}
