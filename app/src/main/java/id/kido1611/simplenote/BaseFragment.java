package id.kido1611.simplenote;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

import id.kido1611.simplenote.activity.MainActivity;
import id.kido1611.simplenote.realm.RealmDB;

/**
 * Created by ahmad on 21/08/2016.
 */
public abstract class BaseFragment extends Fragment{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        preInit();
        super.onCreate(savedInstanceState);
        init();
    }

    public abstract void preInit();
    public abstract void init();

    public RealmDB getRealmDB(){
        return ((MainApplication)getActivity().getApplication()).getRealmDB();
    }

    public void showSnackBar(String message){
        showSnackBar(message, Snackbar.LENGTH_SHORT, null, null, null);
    }
    public void showSnackBar(String message, int length){
        showSnackBar(message, length, null, null, null);
    }
    public void showSnackBar(String message, int length, Snackbar.Callback callback, String actionTitle, View.OnClickListener clickListener){
        ((MainActivity)getActivity()).showSnackBar(message, length, callback, actionTitle, clickListener);
    }
}
