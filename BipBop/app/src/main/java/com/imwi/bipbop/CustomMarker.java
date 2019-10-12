package com.imwi.bipbop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;

import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class CustomMarker {

    public static final double DEFAULT_X = 0;
    public static final double DEFAULT_Y = 0;

    public MarkerOptions markerOptions;
    public LatLng latLng;
    public double x_pos;
    public double y_pos;
    public String title;
    public String snippet;
    public

    CustomMarker() {
        latLng = new LatLng(DEFAULT_X, DEFAULT_Y);
        x_pos = DEFAULT_X;
        y_pos = DEFAULT_Y;
        title = "marker";
        snippet = "snippet";
    }

    CustomMarker(double X, double Y, String title, String snippet) {
        latLng = new LatLng(X, Y);
        x_pos = X;
        y_pos = Y;
        this.title = title;
        this.snippet = snippet;
        markerOptions = new MarkerOptions()
                .position(latLng)
                .title(title)
                .snippet(snippet)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_name));
    }





}
