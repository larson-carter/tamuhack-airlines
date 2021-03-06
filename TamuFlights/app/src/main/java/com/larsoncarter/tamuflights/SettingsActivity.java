package com.larsoncarter.tamuflights;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity {

    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings_activity);

        ImageButton goBackButton = findViewById(R.id.goBackButton);

        goBackButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (mAuth.getCurrentUser() != null) {

                    Intent intent = new Intent(SettingsActivity.this, HomeActivity.class);

                    startActivity(intent);

                } else {

                    Intent intent = new Intent(SettingsActivity.this, MainActivity.class);

                    startActivity(intent);

                }

            }

        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);

    }

}
