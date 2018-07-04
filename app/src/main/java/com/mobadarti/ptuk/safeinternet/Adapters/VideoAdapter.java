package com.mobadarti.ptuk.safeinternet.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mobadarti.ptuk.safeinternet.Models.Video;
import com.mobadarti.ptuk.safeinternet.R;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.List;

public class VideoAdapter extends ArrayAdapter<Video> {
    private Context mContext;
    private List<Video> mVideos;
    private YouTubePlayer YPlayer;
    private FragmentActivity activity;

    public VideoAdapter(@NonNull Context context, @NonNull List<Video> objects, FragmentActivity activity) {
        super(context, R.layout.vid_list_layout, objects);
        mContext = context;
        mVideos = objects;
        this.activity = activity;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.vid_list_layout, null);

        /***get clicked view and play video url at this position**/
        final TextView textView = (TextView) convertView
                .findViewById(R.id.text_list_video);
        //final RelativeLayout relativeLayout = (RelativeLayout) convertView
          //      .findViewById(R.id.layout);
        final YouTubeThumbnailView youTubeThumbnailView = (YouTubeThumbnailView) convertView
                .findViewById(R.id.youtubethumbnail);
        final String title = mVideos.get(position).getTitle();
        final String videoUri = mVideos.get(position).getUrl();
        final YouTubeThumbnailLoader.OnThumbnailLoadedListener onThumbnailLoadedListener = new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
            @Override
            public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                youTubeThumbnailView.setVisibility(View.VISIBLE);
                //relativeLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

            }
        };
        youTubeThumbnailView.initialize(mContext.getResources().getString(R.string.youtube_api_key), new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setVideo(videoUri);
                textView.setText(title);
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(onThumbnailLoadedListener);
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
        youTubeThumbnailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent = YouTubeStandalonePlayer.createVideoIntent((Activity) mContext, mContext.getResources().getString(R.string.youtube_api_key), videoUri);
                //mContext.startActivity(intent);
            }
        });
        /*YouTubePlayerSupportFragment youtubefrag = YouTubePlayerSupportFragment.newInstance();
        youtubefrag.initialize(mContext.getResources().getString(R.string.youtube_api_key), new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                textView.setText(title);
                YPlayer = youTubePlayer;
                YPlayer.loadVideo(videoUri);
                YPlayer.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.videoView, youtubefrag).commit();
        */

        return convertView;
    }
}
