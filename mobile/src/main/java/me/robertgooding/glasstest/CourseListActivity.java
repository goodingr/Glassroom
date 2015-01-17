package me.robertgooding.glasstest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.dropbox.sync.android.*;


/**
 * An activity representing a list of Courses. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link CourseDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link CourseListFragment} and the item details
 * (if present) is a {@link CourseDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link CourseListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class CourseListActivity extends FragmentActivity
        implements CourseListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    private DbxAccountManager mDbxAcctMgr;
    static final int REQUEST_LINK_TO_DBX = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_course_list);
        try {
            mDbxAcctMgr = DbxAccountManager.getInstance(getApplicationContext(), "gsfsr29glmytywt", "c23fgx4tp3ugc06");
        }
        catch(DbxAccountManager.ConfigurationMismatchException e){

        }
        mDbxAcctMgr.startLink((Activity)this, REQUEST_LINK_TO_DBX);

        if (findViewById(R.id.course_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((CourseListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.course_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_LINK_TO_DBX) {
            if (resultCode == Activity.RESULT_OK) {
                // ... Start using Dropbox files.
            } else {
                // ... Link failed or was cancelled by the user.
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * Callback method from {@link CourseListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(CourseDetailFragment.ARG_ITEM_ID, id);
            CourseDetailFragment fragment = new CourseDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.course_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, CourseDetailActivity.class);
            detailIntent.putExtra(CourseDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
