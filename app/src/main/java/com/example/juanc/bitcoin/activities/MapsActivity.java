package com.example.juanc.bitcoin.Modelo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.juanc.bitcoin.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    /*Creamos un objeto Marker para el marcador y se declaran dos variables para la
    * latitud y longitud, de nuestra posicion actual  una variable location_request_code*/

    private static final int LOCATION_REQUEST_CODE = 1;

    private GoogleMap mMap;
    private Marker marcador;
    double lat = 0.0;
    double lng = 0.0;
    Button btnPais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtenga el SupportMapFragment y se notificará cuando el mapa esté listo para ser utilizado.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btnPais = (Button) findViewById(R.id.btnPais);
        btnPais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Acepto Bitcoin");
        toolbar.setLogo(R.drawable.iconoradarbit
        );

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
///////////////////////////////
        // Designar los Controles UI
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Mostrar diálogo explicativo
            } else {
                // Solicitar permiso
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        LOCATION_REQUEST_CODE);
            }
        }

        mMap.getUiSettings().setZoomControlsEnabled(true);
        // Marcadores
        miUbicacion();

// Coordenadas Parque caldas
        LatLng latLng = new LatLng(2.4419159701623965, -76.60637855529785);

        MarkerOptions markerOptions =
                new MarkerOptions()
                        .position(latLng)
                        .title("Parque Caldas")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.iconoradarbit))
                        .snippet("Tienda de Ropa");
        marcador = googleMap.addMarker(markerOptions);





    }



    /*Se crea un metodo que servira para agregar un marcador en el mapa, crearemos
    * un objeto LatLng, en el cual incluirmos la latitud y longitud, luego utilizamos el elemento CameraUpdate,
    * centraremos la camara a la posicion de nuestra marca.*/

    private void agregarUbicacion(double lag, double lng) {
        LatLng cordenadas = new LatLng(lag, lng);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(cordenadas, 10);
        /*Si el marcado es diferente de null se debera de remover, seguidamente
        * agregamos algunas propiedades a nuestro marker como titulo y un icono
        * y por ultimo agregamos el metodo animateCamera para mover la cámara
        * desde una posición a otra con animación*/
        if (marcador != null) marcador.remove();
        marcador = mMap.addMarker(new MarkerOptions()
                .position(cordenadas).title("Mi ubicación")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.iconoubicacion)));
        mMap.animateCamera(miUbicacion);
    }


    /*Crearemos un metodo para octener la latitud y longitud de nuestra posición actual, utilizando para ello
    * métodos proporcionados por la clase Location,la cual utilizaremos como parametro de nuestro método.
    */
    private void actualizarUbicación(Location location) {

        /*Comprobamos si la localización resivida es diferente de null, antes de asignar el valor a las variables,
        * para asi evitar que la app se nos vaya a cerrar al ejecutarla.*/
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            agregarUbicacion(lat, lng);
        }
    }

    /*Se implementa un objeto de tipo LocationListener, el cual tiene la funcion de estar siempre antento a cualquier
    * cambio de localidad recibido por el GPS del dispositivo, al instanciar el objeto, automaticamente se crea una serie
    * de metodos asociados a distintos eventos que podemos recibir de nuestro proveedor de localización.*/
    LocationListener loclistener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            /*Este metodo onLocationChanged() se lanza cada vez que recibe una actualizacion de la posicion,
            * dentro de este metodo se llama el metodo actualizarUbicacion(), para refrescar la
            * posición actual del mapa */
            actualizarUbicación(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    /*Creamos un método, en el que hacemos referencia a la clase LocationManager, la cual es utilizada para obtener
     * servicios de geo posicicionamiento en el dispositivo. Mediante el metodo getLastKnownLocation
      * obtenes la última posición conocida, luego llamamos a nuestro método actualizarUbicación() mediante el método
      * requestLocationUpdates(), solicitamos al GPS actualizaciones de posición cada 15 segundos */
    private void miUbicacion() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarUbicación(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 15000, 0, loclistener);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == LOCATION_REQUEST_CODE) {
            // ¿Permisos asignados?
            if (permissions.length > 0 &&
                    permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION) &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                mMap.setMyLocationEnabled(true);
            } else {
                Toast.makeText(this, "Error de permisos", Toast.LENGTH_LONG).show();
            }

        }
    }


    public final Marker addMarker(MarkerOptions options) {
        return null;
    }


}
