package cat.dam.andy.json_animations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;

public class MainPage extends AppCompatActivity {

    LottieAnimationView lav_like1, lav_like2, lav_like3;
    boolean liked_1 = false;
    boolean liked_2 = false;
    boolean liked_3 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_page);
        initViews();
        initListeners();
    }

    private void initViews() {
        lav_like1 = findViewById(R.id.lav_like1);
        lav_like2 = findViewById(R.id.lav_like2);
        lav_like3 = findViewById(R.id.lav_like3);
    }

    private void initListeners() {
        lav_like1.setOnClickListener(v -> liked_1 = likeAnimation(lav_like1, R.raw.black_joy, liked_1));
        lav_like2.setOnClickListener(v -> liked_2 = likeAnimation(
                lav_like2, R.raw.apple_event, liked_2));
        lav_like3.setOnClickListener(v -> liked_3 = likeAnimation(lav_like3, R.raw.hmm, liked_3));
    }

    private boolean likeAnimation(LottieAnimationView imageView, int animation, boolean liked) {
        if (liked) {
            imageView.setImageResource(R.drawable.twitter_like);
        } else {
            imageView.setAnimation(animation);
            imageView.playAnimation();
        }
        return !liked;
    }
}