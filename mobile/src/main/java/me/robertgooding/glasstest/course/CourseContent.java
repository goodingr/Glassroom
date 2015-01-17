package me.robertgooding.glasstest.course;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class CourseContent {
    Firebase ref = new Firebase("https://glassroom.firebaseio.com/");
    /**
     * An array of sample (dummy) items.
     */
    public static List<CourseItem> ITEMS = new ArrayList<CourseItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, CourseItem> ITEM_MAP = new HashMap<String, CourseItem>();

    public CourseContent() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println(dataSnapshot.getValue());
                addItem(new CourseItem(dataSnapshot.getValue()));
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    static {


    }

    private static void addItem(CourseItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.courseName, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class CourseItem {
        public String courseName;
        public String courseDays;
        public String courseTime;

        public CourseItem(Object courseData) {
            courseName = courseData.toString();
        }

        @Override
        public String toString() {
            return courseName;
        }
    }
}
