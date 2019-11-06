package com.huynhps09200.asm_mod201;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class CS2_Fragment extends Fragment implements OnMapReadyCallback {
GoogleMap map;

    public CS2_Fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_cs2_, container, false);
        SupportMapFragment mapFragment= (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map= googleMap;
        UiSettings uisetting = map.getUiSettings();

        uisetting.setCompassEnabled(true);
        uisetting.setZoomControlsEnabled(true);
        uisetting.setScrollGesturesEnabled(true);
        uisetting.setTiltGesturesEnabled(true);
        uisetting.isMyLocationButtonEnabled();
        uisetting.setMyLocationButtonEnabled(true);

        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        LatLng CS1= new LatLng(10.8119592,106.6797569);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(CS1,18));
        map.addMarker(new MarkerOptions().title("FPT POLYTECHNIC CS2").snippet("Nguyễn Kiệm").position(CS1));
    }
}
