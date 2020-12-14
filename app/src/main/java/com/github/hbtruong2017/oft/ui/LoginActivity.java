package com.github.hbtruong2017.oft.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.github.hbtruong2017.oft.MainActivity;
import com.github.hbtruong2017.oft.R;
import com.github.hbtruong2017.oft.api.ServiceCallback;
import com.github.hbtruong2017.oft.api.ServiceProvider;
import com.github.hbtruong2017.oft.api.UserService;
import com.github.hbtruong2017.oft.api.model.User;
import com.github.hbtruong2017.oft.ui.appointment.DateActivity;
import com.github.hbtruong2017.oft.ui.appointment.MapActivity;
import com.github.hbtruong2017.oft.ui.login.OnboardingFragment;
import com.github.hbtruong2017.oft.ui.login.SignInFragment;
import com.github.hbtruong2017.oft.ui.login.SignUpFragment;

public class LoginActivity extends AppCompatActivity {

    private final UserService userService;

    public LoginActivity() {
        this.userService = ServiceProvider.getService(UserService.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, OnboardingFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    public void onBackPressed() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, OnboardingFragment.newInstance())
                .commitNow();
    }

    public void onOnboardingSignIn(View view) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, SignInFragment.newInstance())
                .commitNow();
    }

    public void onOnboardingSignUp(View view) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, SignUpFragment.newInstance())
                .commitNow();
    }

    private User getUser() {
        EditText emailEditText = findViewById(R.id.emailEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);

        User user = new User();
        user.setEmail(emailEditText.getText().toString());
        user.setPassword(passwordEditText.getText().toString());
        return user;
    }

    public void onSignIn(View view) {
        userService.auth(getUser()).enqueue(ServiceCallback.of(this, (res, body) -> {
            onSuccess(body);
        }));
    }

    public void onSignUp(View view) {
        userService.post(getUser()).enqueue(ServiceCallback.of(this, (res, body) -> {
            onSuccess(body);
        }));
    }

    private void onSuccess(User user) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("user.id", user.getId());
        editor.putString("user.name", user.getName());
        editor.putString("user.email", user.getEmail());
        editor.apply();

        Log.i(LoginActivity.class.getSimpleName(), "Logged In: " + user.getId());
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
