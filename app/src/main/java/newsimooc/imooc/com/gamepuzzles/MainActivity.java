package newsimooc.imooc.com.gamepuzzles;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import newsimooc.imooc.com.view.GameLayout;

public class MainActivity extends AppCompatActivity {

    private GameLayout mGameLayout;
    private TextView mLevel;
    private TextView mTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLevel = (TextView) findViewById(R.id.id_level);
        //mTime = (TextView) findViewById(R.id.id_time);

        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Aturan Permainan Jigsaw")
                .setMessage("Game ini memiliki total 10 tingkat kesulitan，Dikatakan bahwa tidak ada yang melihat yang terakhir.\nJadi, ayolah, muda!\n")
                .setPositiveButton("Saya Tahu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Permainan Dimulai！", Toast.LENGTH_SHORT).show();
                    }
                }).show();


        mGameLayout = (GameLayout) findViewById(R.id.id_game);
        mGameLayout.setEnabled(true);
        mGameLayout.setOnGameListener(new GameLayout.GameListener() {
            @Override
            public void nextLevel(final int nextLevel) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Akhiri antarmuka")
                        .setMessage("Kesulitan upgrade！！！")
                        .setPositiveButton("Tingkat selanjutnya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mGameLayout.nextLevel();
                                //Log.d("MainActivity", "level = " + nextLevel);
                                mLevel.setText("" + nextLevel);
                            }
                        }).show();

            }

            @Override
            public void timechanged(int currentTime) {
                Log.d("MainActivity", "Perubahan waktu");
                mTime.setText("" + currentTime);
            }

            @Override
            public void gameover() {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Akhiri antarmuka")
                        .setMessage("Akhir waktu！Game Over！！！")
                        .setPositiveButton("Mulai ulang level ini", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mGameLayout.nextLevel();
                            }
                        })
                        .setNegativeButton("Hentikan permainan", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).show();
            }
        });
    }
}
