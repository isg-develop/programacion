package pandasystens.demoapp;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps extends FragmentActivity implements LocationProvider.LocationCallback {

    public static final String TAG = Maps.class.getSimpleName();

    private GoogleMap mMap;

    private LocationProvider mLocationProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        mLocationProvider = new LocationProvider(this, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        mLocationProvider.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLocationProvider.disconnect();
    }

   private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(19.206494, -98.391732)).title("Cine UTH"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(27.173321, -107.035103)).title("Cine Chihuahua"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(19.943019, -89.315349)).title("Cine Yucatan"));
    }

    public void handleNewLocation(Location location) {
        Log.d(TAG, location.toString());

        //Ubicacion usuario
        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();
        LatLng latLng = new LatLng(currentLatitude, currentLongitude);

        //Mostrar Coordenadas
        String coordenadas = "Latitud: " + currentLatitude + "/ Longitud" + currentLongitude;
        Toast.makeText(getApplicationContext(),coordenadas, Toast.LENGTH_LONG).show();

        MarkerOptions ubicacion = new MarkerOptions()
                .position(latLng)
                .title("Aqui estoy!");
        mMap.addMarker(ubicacion);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}
