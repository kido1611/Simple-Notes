package id.kido1611.simplenote.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import id.kido1611.simplenote.BaseFragment;
import id.kido1611.simplenote.R;
import id.kido1611.simplenote.activity.EditActivity;
import id.kido1611.simplenote.object.NoteItem;
import id.kido1611.simplenote.realm.RealmDB;
import id.kido1611.simplenote.utils.TimeUtils;

/**
 * Created by ahmad on 21/08/2016.
 */
public class EditNoteFragment extends BaseFragment {

    public static EditNoteFragment newInstace(int id){

        EditNoteFragment fragment = new EditNoteFragment();
        Bundle args = new Bundle();
        args.putInt(EditActivity.EXTRA_ID, id);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void preInit() {

    }

    int note_id = 0;
    private NoteItem mNoteItem = null;

    private TextInputEditText mEditTextTitle, mEditTextNote;
    private Button mButtonEdit;

    @Override
    public void init() {
        note_id = getArguments().getInt(EditActivity.EXTRA_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_note, container, false);

        mEditTextTitle = (TextInputEditText) rootView.findViewById(R.id.text_input_edit_title);
        mEditTextNote = (TextInputEditText) rootView.findViewById(R.id.text_input_edit_note);
        mButtonEdit = (Button) rootView.findViewById(R.id.button_edit);

        mButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mEditTextNote.getText().toString().isEmpty() && !mEditTextTitle.getText().toString().isEmpty())
                    doEditNote();
            }
        });

        if(note_id!=0) {
            mNoteItem = getRealmDB().getById(NoteItem.class, note_id);
            mEditTextTitle.setText(mNoteItem.getTitle());
            mEditTextNote.setText(mNoteItem.getNote());
            mButtonEdit.setText(R.string.button_edit);
        }

        return rootView;
    }

    private void doEditNote(){
        NoteItem item =new NoteItem();
        if(note_id==0){
            item.setId((int) (System.currentTimeMillis())/1000);
            item.setArchieve(false);
        }else{
            item.setId(mNoteItem.getId());
            item.setArchieve(mNoteItem.isArchieve());
        }

        item.setTitle(mEditTextTitle.getText().toString());
        item.setNote(mEditTextNote.getText().toString());
        item.setDate(TimeUtils.getUnix());

        if(note_id==0)
            getRealmDB().add(item);
        else
            getRealmDB().update(item);
        ((EditActivity)getActivity()).close();
    }
}
