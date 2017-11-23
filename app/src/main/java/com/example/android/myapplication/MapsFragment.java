package com.example.android.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;


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
    static final CameraPosition MALANG = CameraPosition.builder().target(new LatLng(-7.9666, 112.6326))
            .zoom(17)
            .bearing(90)
            .tilt(45)
            .build();

    GoogleMap m_map;
    boolean mapReady = false;
    double latitude, longitude;

    public MapsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_setting) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void flyTo(CameraPosition target) {
        m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target), 10000, null);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapReady = true;
        m_map = googleMap;
        LatLng newYork = new LatLng(40.7484, -73.9857);
        CameraPosition target = CameraPosition.builder().target(newYork).zoom(14).build();
        m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));
    }
}