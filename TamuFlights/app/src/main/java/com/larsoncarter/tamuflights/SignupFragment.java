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

public class SignupFragment extends Fragment {

    private static final String TAG = "EmailPassword";

    private FirebaseAuth mAuth;

    private View RootView;

    private Button signupButton;

    private String email;
    private String password;
    private String passwordVerify;

    private EditText emailField;
    private EditText passwordField;
    private EditText passwordVerifyField;

    public SignupFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        // Inflate the layout for this fragment
        RootView = inflater.inflate(R.layout.fragment_signup, container, false);

        emailField = RootView.findViewById(R.id.emailTextField);
        passwordField = RootView.findViewById(R.id.passwordTextField);
        passwordVerifyField = RootView.findViewById(R.id.confirmPasswordTextField);

        signupButton = (Button) RootView.findViewById(R.id.signupButton);

        email = emailField.getText().toString();
        password = passwordField.getText().toString();

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (passwordField.getText().toString().equals(passwordVerifyField.getText().toString())) {

                    createAccount(emailField.getText().toString(), passwordField.getText().toString());

                } else {

                    Log.d(TAG, "Passwords do not match!");

                    Toast.makeText(getActivity(), "Passwords do not match!",
                            Toast.LENGTH_SHORT).show();

                }

            }
        });

        return RootView;

    }

    private void createAccount(String email, String password) {
            Log.d(TAG, "createAccount:" + email);

            // [START create_user_with_email]
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(getActivity(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

    }

}
