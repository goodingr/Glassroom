package me.robertgooding.glasstest;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.Arrays;
import java.util.List;

import me.robertgooding.glasstest.course.CourseContent;
import me.robertgooding.glasstest.course.CourseContent;

/**
 * A fragment representing a single Course detail screen.
 * This fragment is either contained in a {@link CourseListActivity}
 * in two-pane mode (on tablets) or a {@link CourseDetailActivity}
 * on handsets.
 */
public class CourseDetailFragment extends ListFragment  {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private CourseContent.CourseItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CourseDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = CourseContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_course_detail, container, false);
        String[] courseDetailString = {"Lectures", "Calendar", "Resources", "Course Info"};
        List<String> courseDetailArray = Arrays.asList(courseDetailString);
        ListView listView = (ListView) container.findViewById(R.id.course_detail_list);
        listView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1,
                               android.R.id.text1, courseDetailArray));
        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.course_detail)).setText(mItem.courseName);
        }

        return rootView;
    }
}
