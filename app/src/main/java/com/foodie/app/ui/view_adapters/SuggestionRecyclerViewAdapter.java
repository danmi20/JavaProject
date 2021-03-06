package com.foodie.app.ui.view_adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.foodie.app.R;
import com.foodie.app.entities.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 1/12/2017.
 */

public class SuggestionRecyclerViewAdapter extends RecyclerView.Adapter<SuggestionRecyclerViewAdapter.SuggestionImageViewHolder> {

    private static final String TAG = "SuggestionRecyclerViewA";
    private List<Activity> activitiesList;
    private Context mContext;
    private String activityID;


    public SuggestionRecyclerViewAdapter(List<Activity> newActivitiesList, Context mContext, String id) {
        activityID = id;
        activitiesList = new ArrayList<Activity>();
        loadNewData(newActivitiesList);
        this.mContext = mContext;
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: called");
        return ((activitiesList != null) && (activitiesList.size() != 0) ? activitiesList.size() : 0);

    }

    @Override
    public void onBindViewHolder(final SuggestionRecyclerViewAdapter.SuggestionImageViewHolder holder, int position) {
        // Called by the layout manager when it wants new data in an existing row

        final Activity activityItem = activitiesList.get(position);
        Log.d(TAG, "onBindViewHolder: " + activityItem.getActivityName() + " --> " + position);
        if(activityItem.getActivityImage() !=null) {
            Bitmap bmp = BitmapFactory.decodeByteArray(activityItem.getActivityImage(), 0, activityItem.getActivityImage().length);
            holder.suggestionImage.setImageBitmap(bmp);
        }
        holder.activityName.setText(activityItem.getActivityName());
        holder.price.setText("$" + Double.toString(activityItem.getActivityCost()));
        holder.feature.setText(activityItem.getFeature());

    }

    @Override
    public SuggestionRecyclerViewAdapter.SuggestionImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Called by the layout manager when it needs a new view
        Log.d(TAG, "onCreateViewHolder: new view requested");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_suggestion, parent, false);
        return new SuggestionRecyclerViewAdapter.SuggestionImageViewHolder(view);
    }


    public void loadNewData(List<Activity> newActivities) {
        activitiesList.clear();
        boolean exist;
        for (Activity a : newActivities) {
            exist = false;
            if (!a.get_ID().equals("") && !a.get_ID().equals(activityID)) {
                for (Activity a2 : activitiesList) {
                    if (a.get_ID().equals(a2.get_ID())) {
                        exist = true;
                        break;
                    }
                }
                if (!exist){
                    activitiesList.add(a);
                }
            }
        }
//        this.activitiesList = newActivities;
        notifyDataSetChanged();
    }


    public Activity getActivity(int position) {
        return ((activitiesList != null) && (activitiesList.size() != 0) ? activitiesList.get(position) : null);
    }


    static class SuggestionImageViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "SuggestionImageViewHold";
        ImageView suggestionImage;
        TextView activityName;
        TextView price;
        TextView feature;

        public SuggestionImageViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "BusinessImageViewHolder: starts");
            this.suggestionImage = (ImageView) itemView.findViewById(R.id.suggestion_image);
            this.activityName = (TextView) itemView.findViewById(R.id.suggestion_name);
            this.price = (TextView) itemView.findViewById(R.id.suggestion_price);
            this.feature = (TextView) itemView.findViewById(R.id.suggestion_feature_text);
        }
    }
    public List<Activity> getActivitiesList() {
        return activitiesList;
    }


    public void setActivityID(String activityID) {
        this.activityID = activityID;
    }
}
