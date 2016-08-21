package id.kido1611.simplenote.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import id.kido1611.simplenote.R;
import id.kido1611.simplenote.fragment.EditNoteFragment;

/**
 * Created by ahmad on 21/08/2016.
 */
public class EditActivity extends AppCompatActivity {

    public final static String EXTRA_ID = "note_extra_id";

    private CoordinatorLayout mCoordinatorLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        int note_id = 0;
        if(i!=null && i.getExtras()!=null) {
            note_id = i.getExtras().getInt(EXTRA_ID);
            if (note_id == 0) {
                setActTitle(getString(R.string.app_title_add));
            } else {
                setActTitle(getString(R.string.app_title_edit));
            }
        }else{
            close();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, EditNoteFragment.newInstace(note_id), "SimpleNotesEdit").commit();
    }

    public void close(){
        onBackPressed();
    }

    private void setActTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
