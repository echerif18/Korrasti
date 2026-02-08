package tn.supcom.korrasti;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.GridView;

public class SelectCat extends AppCompatActivity {
    private GridView photoGrid;
    private int mPhotoSize, mPhotoSpacing;
    private Picadapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_cat);
        photoGrid = (GridView) findViewById(R.id.albumGrid);
        Model.LoadModel();
        String[] ids = new String[Model.Items.size()];
        for (int i= 0; i < ids.length; i++){
            ids[i] = Integer.toString(i+1);
        }
        mPhotoSize = getResources().getDimensionPixelSize(R.dimen.photo_size);
        mPhotoSpacing = getResources().getDimensionPixelSize(R.dimen.photo_spacing);
        imageAdapter=new Picadapter(getApplicationContext(),R.layout.photoitem,ids,"CATIMAGE");
        photoGrid.setAdapter(imageAdapter);


        // get the view tree observer of the grid and set the height and numcols dynamically
        photoGrid.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (imageAdapter.getNumColumns() == 0) {
                    final int numColumns = (int) Math.floor(photoGrid.getWidth() / (mPhotoSize + mPhotoSpacing));
                    if (numColumns > 0) {
                        final int columnWidth = (photoGrid.getWidth() / numColumns) - mPhotoSpacing;
                        imageAdapter.setNumColumns(numColumns);
                        imageAdapter.setItemHeight(columnWidth);

                    }
                }
            }
        });

        photoGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Log.e("FolderName", Model.GetbyId(position+1).FolderName);

                String FolderName=Model.GetbyId(position+1).FolderName;
                String CategoryName=Model.GetbyId(position+1).Name;
                Intent i=new Intent(SelectCat.this,SelectItem.class);
                i.putExtra("Folder", FolderName);
                i.putExtra("Category", CategoryName);
                startActivity(i);

            }
        });

    }
}
