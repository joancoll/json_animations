package cat.dam.andy.json_animations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class WelcomePage extends AppCompatActivity {

    public static final int TIMER = 2000;

    TextView tv;
    LottieAnimationView btn_animation;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        btn_animation = findViewById(R.id.btn_animation);
        tv = findViewById(R.id.tv);
        relativeLayout = findViewById(R.id.relativeLayout);

        relativeLayout.setOnClickListener(v -> {
            btn_animation.setVisibility(View.VISIBLE);
            btn_animation.playAnimation();
            tv.setVisibility(View.GONE);

            new Handler().postDelayed(this::resetButton, TIMER);
        });

    }

    private void resetButton() {
        //btn_animation.setVisibility(View.GONE);

        btn_animation.pauseAnimation();
        //tv.setVisibility(View.VISIBLE);

        Intent i = new Intent(WelcomePage.this, LoginController.class);
        startActivity(i);

    }
}
