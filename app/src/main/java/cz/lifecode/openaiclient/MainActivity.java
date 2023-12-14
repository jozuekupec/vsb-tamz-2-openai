package cz.lifecode.openaiclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.orhanobut.logger.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        ProgressBar progressBarLine = (ProgressBar) findViewById(R.id.progressBar);
//        ProgressBar progressBarCircle = (ProgressBar) findViewById(R.id.progressBarCircle);
//
//        ProgressBarThread progressBarLineThread = new ProgressBarThread(progressBarLine, 1500);
//        progressBarLineThread.start();
//
//        ProgressBarThread progressBarCircleThread = new ProgressBarThread(progressBarCircle, 1000);
//        progressBarCircleThread.start();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    public class ProgressBarThread extends Thread {
        private int progress = 0;
        private final int speed;
        private final ProgressBar progressBar;

        ProgressBarThread(ProgressBar progressBar, int speed) {
            this.progressBar = progressBar;
            this.speed = speed;
        }

        public void run() {
            Logger.i("ProgressBar thread STARTED - " + progressBar.getId());

            while (progress < 100) {
                progress += 2;
                progressBar.setProgress(progress);
                Logger.i("Progress: " + progress);

                try {
                    sleep(speed);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Logger.i("ProgressBar thread ENDED - " + progressBar.getId());
        }
    }
}