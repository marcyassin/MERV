package cayenne.team.merv;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class TabFragment2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab_fragment2, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        final ListView listView2 = (ListView) getView().findViewById(R.id.listView2);

        super.onCreate(savedInstanceState);
        // Create a new Adapter
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1);

        // Assign adapter to ListView
        listView2.setAdapter(adapter);

        // Use Firebase to populate the list.
        Firebase.setAndroidContext(getActivity());

        new Firebase("https://crackling-fire-8381.firebaseio.com/jobFeed")
                .addChildEventListener(new ChildEventListener() {
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        adapter.add((String) dataSnapshot.child("text").getValue());
                    }

                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                        adapter.remove((String) dataSnapshot.child("text").getValue());
                    }

                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    }

                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                    }

                    public void onCancelled(FirebaseError firebaseError) {
                    }
                });
//        final EditText text = (EditText) getView().findViewById(R.id.jobText);
//        final Button button = (Button) getView().findViewById(R.id.addjob);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                new Firebase("https://crackling-fire-8381.firebaseio.com/jobFeed")
//                        .push()
//                        .child("text")
//                        .setValue(text.getText().toString());
//            }
//        });
//        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                new Firebase("https://crackling-fire-8381.firebaseio.com/jobFeed")
//                        .orderByChild("text")
//                        .equalTo((String) listView2.getItemAtPosition(position))
//                        .addListenerForSingleValueEvent(new ValueEventListener() {
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                if (dataSnapshot.hasChildren()) {
//                                    DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
//                                    firstChild.getRef().removeValue();
//                                }
//                            }
//
//                            public void onCancelled(FirebaseError firebaseError) {
//                            }
//                        });
//            }
//        });
    }

}