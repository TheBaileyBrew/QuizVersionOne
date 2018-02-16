package com.thebaileybrew.firefly.quizversionone;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.VideoView;

/**
 * Created by grumnb on 2/8/2018.
 */

public class FragmentFour extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        return (LinearLayout)inflater.inflate(R.layout.fragment_question_four, container, false);


    }



}

