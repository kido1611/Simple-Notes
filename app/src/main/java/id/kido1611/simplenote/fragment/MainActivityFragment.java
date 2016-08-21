package id.kido1611.simplenote.fragment;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import id.kido1611.simplenote.BaseFragment;
import id.kido1611.simplenote.R;
import id.kido1611.simplenote.activity.EditActivity;
import id.kido1611.simplenote.adapter.NoteListAdapter;
import id.kido1611.simplenote.object.NoteItem;
import id.kido1611.simplenote.realm.RealmDB;
import id.kido1611.simplenote.utils.TimeUtils;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends BaseFragment
        implements NoteListAdapter.RecyclerItemClickListener{

    public MainActivityFragment() {
    }

    private RecyclerView mRecyclerView;
    private NoteListAdapter mNoteAdapter;
    private RealmResults<NoteItem> mItemLists;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void preInit() {

    }

    @Override
    public void init() {
        mNoteAdapter = new NoteListAdapter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.listNote);
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefreshlayout);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mNoteAdapter);

        loadData();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData(){
        mItemLists = getData();
        mNoteAdapter.setNoteList(mItemLists);
        if(mSwipeRefreshLayout.isRefreshing()) mSwipeRefreshLayout.setRefreshing(false);
    }

    public RealmResults<NoteItem> getData(){
        RealmResults<NoteItem> results = (RealmResults<NoteItem>) getRealmDB().getAllData(NoteItem.class).sort("date", Sort.DESCENDING);
        return results;
    }

    @Override
    public void onItemClick(int position, View view) {
        //showSnackBar("pos : "+position+" ,"+mItemLists.get(position).getTitle());
        Intent i = new Intent(getActivity(), EditActivity.class);
        i.putExtra(EditActivity.EXTRA_ID, mItemLists.get(position).getId());
        startActivity(i);
    }
}
