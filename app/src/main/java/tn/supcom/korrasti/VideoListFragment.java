package tn.supcom.korrasti;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

/**
 * Created by Scott on 19/04/15.
 */
public class VideoListFragment extends ListFragment {

    /**
     * Empty constructor
     */
    public VideoListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new VideoListAdapter(getActivity()));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        final Context context = getActivity();
        final String DEVELOPER_KEY = getString(R.string.DEVELOPER_KEY);
        final tn.supcom.korrasti.content.YouTubeContent.YouTubeVideo video = tn.supcom.korrasti.content.YouTubeContent.ITEMS.get(position);

        switch (position) {
            case 0:
                //Opens in the the custom Lightbox activity
                final Intent intent = new Intent(getActivity(), CustomLightboxActivity.class);
                intent.putExtra(CustomLightboxActivity.KEY_VIDEO_ID, video.id);
                startActivity(intent);
                break;
            case 1:
                //Opens in the the custom Lightbox activity
                final Intent intent1 = new Intent(getActivity(), CustomLightboxActivity.class);
                intent1.putExtra(CustomLightboxActivity.KEY_VIDEO_ID, video.id);
                startActivity(intent1);
                break;
            case 2:
                //Opens in the the custom Lightbox activity
                final Intent intent2 = new Intent(getActivity(), CustomLightboxActivity.class);
                intent2.putExtra(CustomLightboxActivity.KEY_VIDEO_ID, video.id);
                startActivity(intent2);
                break;
            case 3:
                //Opens in the the custom Lightbox activity
                final Intent intent3 = new Intent(getActivity(), CustomLightboxActivity.class);
                intent3.putExtra(CustomLightboxActivity.KEY_VIDEO_ID, video.id);
                startActivity(intent3);
                break;
            case 4:
                //Opens in the the custom Lightbox activity
                final Intent intent4 = new Intent(getActivity(), CustomLightboxActivity.class);
                intent4.putExtra(CustomLightboxActivity.KEY_VIDEO_ID, video.id);
                startActivity(intent4);
                break;
            case 5:
                //Opens in the the custom Lightbox activity
                final Intent intent5 = new Intent(getActivity(), CustomLightboxActivity.class);
                intent5.putExtra(CustomLightboxActivity.KEY_VIDEO_ID, video.id);
                startActivity(intent5);
                break;
            case 6:
                //Opens in the the custom Lightbox activity
                final Intent intent9 = new Intent(getActivity(), CustomLightboxActivity.class);
                intent9.putExtra(CustomLightboxActivity.KEY_VIDEO_ID, video.id);
                startActivity(intent9);
                break;
            case 7:
                //Opens in the the custom Lightbox activity
                final Intent intent6 = new Intent(getActivity(), CustomLightboxActivity.class);
                intent6.putExtra(CustomLightboxActivity.KEY_VIDEO_ID, video.id);
                startActivity(intent6);
                break;
            case 8:
                //Opens in the the custom Lightbox activity
                final Intent intent7 = new Intent(getActivity(), CustomLightboxActivity.class);
                intent7.putExtra(CustomLightboxActivity.KEY_VIDEO_ID, video.id);
                startActivity(intent7);
                break;


        }
    }

}
