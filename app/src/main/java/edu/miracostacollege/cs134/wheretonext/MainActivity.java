package edu.miracostacollege.cs134.wheretonext;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;

import java.io.IOException;
import java.util.List;
import java.util.zip.Inflater;

import edu.miracostacollege.cs134.wheretonext.model.College;
import edu.miracostacollege.cs134.wheretonext.model.JSONLoader;

public class MainActivity extends AppCompatActivity {

    private List<College> collegesList;
    private CollegeListAdapter collegesListAdapter;


    private ListView collegesListView;
    List<College> collegeList;
    EditText nameEditText;
    EditText populationEditText;
    EditText tuitionEditText;
    RatingBar collegeRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        collegesListView.setAdapter(collegesListAdapter);
        collegesListView = findViewById(R.id.collegeListView);
        nameEditText = findViewById(R.id.nameEditText);
        populationEditText = findViewById(R.id.populationEditText);
         tuitionEditText = findViewById(R.id.tuitionEditText);
         collegeRatingBar = findViewById(R.id.collegeRatingBar);


        // Done:  Fill the collegesList with all Colleges from the database


        try{
            collegeList = JSONLoader.loadJSONFromAsset(this);
        }
        catch(IOException e){
            Log.e("Where2Next", e.getMessage());
        }

        // TODO:  Connect the list adapter with the list
        // TODO:  Set the list view to use the list adapter
        collegesListAdapter = new CollegeListAdapter(this, R.layout.activity_college_list_item, collegeList);

        ArrayAdapter<> adapter = new MyListAdapter();
        ListView list =  (ListView)getActivity().findViewById(R.id.client_listView);
        list.setAdapter(adapter);



    }

    public void viewCollegeDetails(View view) {

        // TODO: Implement the view college details using an Intent
        //extract postion from tag
        int pos = (int) view.getTag();
        College selectedCollege = collegeList.get(pos);

        Intent intent = new Intent(this, CollegeDetailsActivity.class);
        intent.putExtra("name", selectedCollege.getName());
        intent.putExtra("population", selectedCollege.getPopulation());
        intent.putExtra("tuition", selectedCollege.getTuition());
        intent.putExtra("imageName", selectedCollege.getImageName());

        startActivity(intent);
    }

    public void addCollege(View view) {

        // TODO: Implement the code for when the user clicks on the addCollegeButton

    }

}
