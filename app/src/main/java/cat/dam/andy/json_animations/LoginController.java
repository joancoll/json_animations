package cat.dam.andy.json_animations;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class LoginController extends AppCompatActivity {

    Button btn_next;
    EditText et_input_password;
    LottieAnimationView loginResultAnimation;
    boolean loginOK = false;
    ImageView iv_logo, iv_blob;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_controller);

        initViews();
        initListeners();
        initAnimations();
    }

    private void initViews() {
        btn_next = findViewById(R.id.btn_next);
        et_input_password = findViewById(R.id.et_input_password);
        loginResultAnimation = findViewById(R.id.loginResultAnimation);
        iv_logo = findViewById(R.id.iv_logo);
        iv_blob = findViewById(R.id.iv_blob);
    }

    private void initListeners() {
        btn_next.setOnClickListener(v -> {
            closeKeyboard();

            if (et_input_password.getText().toString().equals("1")) {
                showAnimation(loginResultAnimation, R.raw.succes);
                loginOK = true;
            } else {
                showAnimation(loginResultAnimation, R.raw.failure);
                loginOK = false;
            }
        });
    }

    private void initAnimations() {
        Animation rotacio = AnimationUtils.loadAnimation(LoginController.this, R.anim.moviment);
        Animation rotacioInversa = AnimationUtils.loadAnimation(LoginController.this, R.anim.moviment_invers);
        new Handler().postDelayed(() -> {
            iv_logo.startAnimation(rotacio);
            iv_blob.startAnimation(rotacioInversa);
        }, 100);
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();

        if (view != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void showAnimation(LottieAnimationView animationContainer, int animationResource) {
        animationContainer.setAnimation(animationResource);
        animationContainer.setVisibility(View.VISIBLE);
        animationContainer.playAnimation();
        hideAnimationWhenFinished(animationContainer);
    }

    private void hideAnimationWhenFinished(LottieAnimationView animationView) {
        animationView.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                animationView.setVisibility(View.GONE);
                et_input_password.setText("");
                if (loginOK) startActivity(new Intent(LoginController.this, MainPage.class));
            }
        });
    }
}