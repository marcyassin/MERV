package cayenne.team.merv.Main.Tabs.Tasks.Tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import cayenne.team.merv.R;


public class SummaryFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_summary, container, false);

        SupportMapFragment mSupportMapFragment;

        mSupportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapwhere);
        if (mSupportMapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mSupportMapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.mapwhere, mSupportMapFragment).commit();
        }

        if (mSupportMapFragment != null) {
            mSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    if (googleMap != null) {

                        googleMap.getUiSettings().setAllGesturesEnabled(true);

                        double latitude = 42.408569;
                        double longitude = -71.116354;

//                        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(15.0f).build();
//                        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
//                        googleMap.moveCamera(cameraUpdate);

                        // For dropping a marker at a point on the Map
                        googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("My Home").snippet("Home Address"));
                        // For zooming automatically to the Dropped PIN Location
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,
                                longitude), 12.0f));

                    }

                }
            });
        }
        return view;
    }




    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        final TextView textview = (TextView) getView().findViewById(R.id.summaryTextView);

        super.onCreate(savedInstanceState);


        // Use Firebase to populate the list.
        Firebase.setAndroidContext(getActivity());

        new Firebase("https://crackling-fire-8381.firebaseio.com/summary")
                .addChildEventListener(new ChildEventListener() {
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                        textview.setText((String) dataSnapshot.child("text").getValue());
                        textview.setText((String) dataSnapshot.child("text").getValue());
                    }

                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    }

                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                    }

                    public void onCancelled(FirebaseError firebaseError) {
                    }
                });
    }


}