package com.demo.movieapi;

import android.content.res.Resources;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public class Utils {
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static MediatorLiveData<Boolean> combineLiveData(LiveData liveData1, LiveData liveData2) {
        final Object[] object1 = {null};
        final Object[] object2 = {null};
        MediatorLiveData<Boolean> mediatorLiveData = new MediatorLiveData<>();
        mediatorLiveData.addSource(liveData1, new Observer() {
            @Override
            public void onChanged(Object o) {
                object1[0] = o;
                boolean completed = object1[0] != null && object2[0] != null;
                mediatorLiveData.setValue(completed);
            }
        });
        mediatorLiveData.addSource(liveData2, new Observer() {
            @Override
            public void onChanged(Object o) {
                object2[0] = o;
                boolean completed = object1[0] != null && object2[0] != null;
                mediatorLiveData.setValue(completed);
            }
        });
        return mediatorLiveData;
    }
}
