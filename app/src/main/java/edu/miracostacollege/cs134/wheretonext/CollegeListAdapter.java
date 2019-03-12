package edu.miracostacollege.cs134.wheretonext;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import edu.miracostacollege.cs134.wheretonext.R;
import edu.miracostacollege.cs134.wheretonext.model.College;

/**
 * Helper class to provide custom adapter for the <code>College</code> list.
 */
public class CollegeListAdapter extends ArrayAdapter<College> {

    private Context mContext;
    private List<College> mCollegesList = new ArrayList<>();
    private int mResourceId;



    /**
     * Creates a new <code>CollegeListAdapter</code> given a mContext, resource id and list of colleges.
     *
     * @param c The mContext for which the adapter is being used (typically an activity)
     * @param rId The resource id (typically the layout file name)
     * @param colleges The list of colleges to display
     */
    public CollegeListAdapter(Context c, int rId, List<College> colleges) {
        super(c, rId, colleges);
        mContext = c;
        mResourceId = rId;
        mCollegesList = colleges;
    }

    /**
     * Gets the view associated with the layout.
     * @param pos The position of the College selected in the list.
     * @param convertView The converted view.
     * @param parent The parent - ArrayAdapter
     * @return The new view with all content set.
     */
    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {

        College focusedEvent = mCollegesList.get(pos);
        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        //store the position in each views tag
        view.setTag(pos);

        // Done:  Write the code to correctly inflate the view (college_list_item) with
        // Done:  all widgets filled with the appropriate College information.

        ImageView collegeListImageView = view.findViewById(R.id.collegeListImageView);
        TextView collegeListtextView = view.findViewById(R.id.collegeListTextView);
        RatingBar collegeListRatingBar = view.findViewById(R.id.collegeListRatingBar);


        collegeListtextView.setText(focusedEvent.getName());
        collegeListRatingBar.setRating((float) focusedEvent.getRating());

        AssetManager am = mContext.getAssets();

        try
        {
            InputStream stream = am.open(focusedEvent.getImageName());
            Drawable image = Drawable.createFromStream(stream, "This is an image of a college");

            collegeListImageView.setImageDrawable(image);

        }

        catch(IOException e)
        {
            Log.e("College", e.getMessage());
        }

        return view;
    }
}
