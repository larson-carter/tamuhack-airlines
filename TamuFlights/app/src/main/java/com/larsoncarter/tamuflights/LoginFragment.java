package com.larsoncarter.tamuflights;

import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment {

    private static final String TAG = "EmailPassword";

    private View RootView;

    private FirebaseAuth mAuth;

    private EditText emailField;
    private EditText passwordField;

    private Button loginButton;

    private String email;
    private String password;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {

        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RootView = inflater.inflate(R.layout.fragment_login, container, false);

        emailField = RootView.findViewById(R.id.emailTextField);
        passwordField = RootView.findViewById(R.id.emailTextField);

        loginButton = RootView.findViewById(R.id.signupButton);

        email = emailField.getText().toString();
        password = passwordField.getText().toString();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(emailField.getText().toString(), passwordField.getText().toString());
            }
        });

        return RootView;

    }

    private void signIn(String email, String password) {

        Log.d(TAG, "signIn:" + email);

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }

        });

    }

}




