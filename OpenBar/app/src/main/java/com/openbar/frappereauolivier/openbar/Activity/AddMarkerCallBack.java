package com.openbar.frappereauolivier.openbar.Activity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Frappereau Olivier on 30/12/2015.
 */
public interface AddMarkerCallBack {
    public void addMarker(LatLng ll, String name, GoogleMap googleMap);
}
