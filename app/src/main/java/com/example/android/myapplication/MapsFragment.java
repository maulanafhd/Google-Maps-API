package com.example.android.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapsFragment extends Fragment implements OnMapReadyCallback {

    static final CameraPosition NEWYORK = CameraPosition.builder().target(new LatLng(40.784, -73.9857))
            .zoom(21)
            .bearing(0)
            .tilt(45)
            .build();
    static final CameraPosition SEATLLE = CameraPosition.builder().target(new LatLng(47.6204, -122.3491))
            .zoom(17)
            .bearing(0)
            .tilt(45)
            .build();
    static final CameraPosition TOKYO = CameraPosition.builder().target(new LatLng(35.6895, 139.6917))
            .zoom(17)
            .bearing(90)
            .tilt(45)
            .build();
    static final CameraPosition MALANG = CameraPosition.builder().target(new LatLng(-7.9775, 112.6344))
            .zoom(17)
            .bearing(90)
            .tilt(45)
            .build();

    GoogleMap m_map;
    boolean mapReady = false;
    MarkerOptions HotelTugu, BalaiKota, Stasiun, renton, kirkland, kent, Bunka, Shinjuku, Keio, TransitMuseum, NYU;

    LatLng llRenton = new LatLng(47.489805, -122.120502);
    LatLng llKirkland = new LatLng(47.73019, -122.1768858);
    LatLng llEverett = new LatLng(47.978748, -122.202001);
    LatLng llLynnwood = new LatLng(47.819533, -122.32288);
    LatLng llMontlake = new LatLng(47.7973733, -122.3281771);
    LatLng llKent = new LatLng(47.385938, -122.2582);
    LatLng llShoware = new LatLng(47.38702, -122.23986);

    public MapsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        HotelTugu = new MarkerOptions().position(new LatLng(-7.9773, 112.6329)).title("Hotel Tugu");
        BalaiKota = new MarkerOptions().position(new LatLng(-7.978, 112.6338)).title("Balai Kota Malang");
        Stasiun = new MarkerOptions().position(new LatLng(-7.9775, 112.637)).title("Stasiun Kereta Malang");
        renton = new MarkerOptions().position(new LatLng(47.489805, -122.120502)).title("Renton");
        kirkland = new MarkerOptions().position(new LatLng(47.73019, -122.1768858)).title("Kirkland");
        kent = new MarkerOptions().position(new LatLng(47.385938, -122.2582)).title("Kent Valley");
        Bunka = new MarkerOptions().position(new LatLng(35.6852, 139.6943)).title("Bunka Gakuen University");
        Shinjuku = new MarkerOptions().position(new LatLng(35.6912, 139.6926)).title("Shinjuku Sumitomo Building");
        Keio = new MarkerOptions().position(new LatLng(35.6895, 139.6944)).title("Keio Plaza");
        TransitMuseum = new MarkerOptions().position(new LatLng(40.6905, -73.9898)).title("New York Transit Museum");
        NYU = new MarkerOptions().position(new LatLng(40.6942, -73.9865)).title("NYU Tandon School of Engineering");
        Button btnMap = (Button) view.findViewById(R.id.btnMap);
        Button btnSatellite = (Button) view.findViewById(R.id.btnSatellite);
        Button btnHybrid = (Button) view.findViewById(R.id.btnHybrid);
        Button btnNewYork = (Button) view.findViewById(R.id.btnNewYork);
        Button btnSeattle = (Button) view.findViewById(R.id.btnSeattle);
        Button btnTokyo = (Button) view.findViewById(R.id.btnTokyo);
        Button btnMalang = (Button) view.findViewById(R.id.btnMalang);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady)
                    m_map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });
        btnSatellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady)
                    m_map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });
        btnHybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady)
                    m_map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });

        btnNewYork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady)
                    flyTo(NEWYORK);
            }
        });
        btnSeattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady)
                    flyTo(SEATLLE);
            }
        });
        btnTokyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady)
                    flyTo(TOKYO);
            }
        });
        btnMalang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapReady)
                    flyTo(MALANG);
            }
        });
        MapFragment mapFragment = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return view;
    }
    

    private void flyTo(CameraPosition target) {
        m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target), 10000, null);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapReady = true;
        m_map = googleMap;
        m_map.addMarker(HotelTugu);
        m_map.addMarker(BalaiKota);
        m_map.addMarker(Stasiun);
        m_map.addMarker(renton);
        m_map.addMarker(kirkland);
        m_map.addMarker(kent);
        m_map.addMarker(Bunka);
        m_map.addMarker(Shinjuku);
        m_map.addMarker(Keio);
        m_map.addMarker(TransitMuseum);
        m_map.addMarker(NYU);
        LatLng newYork = new LatLng(40.7484, -73.9857);
        CameraPosition target = CameraPosition.builder().target(newYork).zoom(14).build();
        m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));
        m_map.addPolyline(new PolylineOptions().geodesic(true)
                .add(llRenton)
                .add(llKirkland)
                .add(llEverett)
                .add(llLynnwood)
                .add(llMontlake)
                .add(llKent)
                .add(llShoware)
                .add(llRenton));
        m_map.addCircle(new CircleOptions().center(llRenton).radius(5000).strokeColor(Color.GREEN)
                .fillColor(Color.argb(64, 0, 255, 0)));
    }
}