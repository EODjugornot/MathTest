package madbomberlabs.com.mathtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SplashActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    public void goStart(View v)
    {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
    }
}
