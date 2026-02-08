package tn.supcom.korrasti ;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Eya on 29/11/2017.
 */

public class Picadapter extends ArrayAdapter<String> {


private final Context context;
private final String[] Ids;
private final int rowResourceId;
private LayoutInflater mInflater;
private RelativeLayout.LayoutParams mImageViewLayoutParams;
private int mItemHeight = 0;
private int mNumColumns = 0;
private final String Foldername;

public Picadapter(Context context, int textViewResourceId, String[] objects,String foldername) {
        super(context, textViewResourceId, objects);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.Ids=objects;
        this.rowResourceId=textViewResourceId;
        this.Foldername=foldername;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mImageViewLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT);
        }

public View getView(int position, View convertView, ViewGroup parent)
        {
        if (convertView == null)
        convertView = mInflater.inflate(rowResourceId, null);

        ImageView cover = (ImageView) convertView.findViewById(R.id.cover);
        TextView title = (TextView) convertView.findViewById(R.id.title);

        cover.setLayoutParams(mImageViewLayoutParams);

        // Check the height matches our calculated column width
        if (cover.getLayoutParams().height != mItemHeight) {
        cover.setLayoutParams(mImageViewLayoutParams);
        }

        int id = Integer.parseInt(Ids[position]);
        String imageFile = Model.GetbyId(id).IconFile;
        title.setText(Model.GetbyId(id).Name);

        Picasso.with(context).load(Uri.parse("file:///android_asset/"+Foldername+"/"+imageFile)).into(cover);
        return convertView;
        }

// set numcols
public void setNumColumns(int numColumns) {
        mNumColumns = numColumns;
        }

public int getNumColumns() {
        return mNumColumns;
        }

// set photo item height
public void setItemHeight(int height) {
        if (height == mItemHeight) {
        return;
        }
        mItemHeight = height;
        mImageViewLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mItemHeight);
        notifyDataSetChanged();
        }


        }