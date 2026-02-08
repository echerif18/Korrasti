package tn.supcom.korrasti;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class StudentAccountActivity extends AppCompatActivity {

    private ImageButton bact1;
    private ImageButton bact2;
    private ImageButton bact3;
    private ImageButton bact4;
    private ImageButton bact5;
    private ImageButton bact6;
    //private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_account);

        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        bact1= (ImageButton)findViewById(R.id.imageButton1);
        bact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(StudentAccountActivity.this,PronciationAct.class);
                startActivity(i);
            }
        });

        bact2= (ImageButton)findViewById(R.id.imageButton2);
        bact2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(StudentAccountActivity.this,SelectCat.class);
                startActivity(i);
            }
        });

        bact3= (ImageButton)findViewById(R.id.imageButton3);
        bact3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(StudentAccountActivity.this,ChansonsContes.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.barreaction, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Inflate the menu; this adds items to the action bar if it is present.
        switch (item.getItemId()) {
            case R.id.Ardoise_action:
                startActivity(new Intent(StudentAccountActivity.this,Ardoise.class));
                break;
            case R.id.Notebook_action:
                startActivity(new Intent(StudentAccountActivity.this,Notebook.class));
                break;

            default:
           /*case android.R.id.home:
                onBackPressed();
                return true;*/
                break;
        }
        return true;
    }
}
