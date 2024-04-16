package com.example.walkthrough.activites.Tailor.ModelClass;

import android.net.Uri;

public class DataModel {
    private Uri imageUri;

    public DataModel(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }
}