package tn.supcom.korrasti;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.IOException;

public class SelectItem extends AppCompatActivity {
    private GridView photoGrid;
    private int mPhotoSize, mPhotoSpacing;
    private ItemAdapter imageAdapter;
    private String[] arrImagesStrings;
    String Foldername,CategoryName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_item);

        mPhotoSize = getResources().getDimensionPixelSize(R.dimen.photo_size);
        mPhotoSpacing = getResources().getDimensionPixelSize(R.dimen.photo_spacing);
        photoGrid = (GridView) findViewById(R.id.albumGrid);

        Intent i=getIntent();
        Foldername=i.getStringExtra("Folder");
        CategoryName=i.getStringExtra("Category");
        arrImagesStrings = listAssetFiles(Foldername);
        getSupportActionBar().setTitle(CategoryName);

        imageAdapter=new ItemAdapter( getApplicationContext(), R.layout.picphotoitem ,arrImagesStrings,Foldername);
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
                Intent intent = new Intent(SelectItem.this,Coloriage.class);
                intent.putExtra(Coloriage.IMG, Foldername+"/"+arrImagesStrings[position]);
                //intent.setFlags(0x40000000);
                startActivity(intent);
            }
        });
    }

    private String [] listAssetFiles(String path)
    {
        String [] list;
        try
        {
            list = SelectItem.this.getAssets().list(path);
            if (list.length > 0)
            {
                System.out.println(list.length);
                return list;
            }
        }catch (IOException e)
        {
        }
        return null;
    }

}
