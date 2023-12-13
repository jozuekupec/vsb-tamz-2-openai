package cz.lifecode.openaiclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.addLogAdapter(new AndroidLogAdapter());
        setContentView(R.layout.activity_main);

        ProgressBar progressBarLine = (ProgressBar) findViewById(R.id.progressBar);
        ProgressBar progressBarCircle = (ProgressBar) findViewById(R.id.progressBarCircle);

        ProgressBarThread progressBarLineThread = new ProgressBarThread(progressBarLine, 1500);
        progressBarLineThread.start();

        ProgressBarThread progressBarCircleThread = new ProgressBarThread(progressBarCircle, 1000);
        progressBarCircleThread.start();
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