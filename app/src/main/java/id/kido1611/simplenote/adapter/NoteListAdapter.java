package id.kido1611.simplenote.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.kido1611.simplenote.R;
import id.kido1611.simplenote.object.NoteItem;
import id.kido1611.simplenote.utils.TimeUtils;
import io.realm.RealmResults;

/**
 * Created by ahmad on 21/08/2016.
 */
public class NoteListAdapter  extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>{

    private RealmResults<NoteItem> noteList;
    private RecyclerItemClickListener mRecyclerItemClickListener;

    public NoteListAdapter(RecyclerItemClickListener recyclerItemClickListener){
        this.mRecyclerItemClickListener = recyclerItemClickListener;
    }

    public void setNoteList(RealmResults<NoteItem> noteList){
        this.noteList = noteList;
        notifyDataSetChanged();
    }

    public NoteItem getItem(int position){
        return noteList.get(position);
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_note_item, parent, false);
        final NoteViewHolder holder = new NoteViewHolder(rootView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPos = holder.getAdapterPosition();
                if(adapterPos != RecyclerView.NO_POSITION){
                    if(mRecyclerItemClickListener!=null) mRecyclerItemClickListener.onItemClick(adapterPos, holder.itemView);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        NoteItem item = getItem(position);

        holder.mTextTitle.setText(item.getTitle());
        holder.mTextNote.setText(item.getNote());
        //holder.mTextDate.setText(TimeUtils.unixToTimeAgo(item.getDate()));
        holder.mTextDate.setText(TimeUtils.longToTime(item.getDate()));
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public interface RecyclerItemClickListener{
        void onItemClick(int position, View view);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{

        TextView mTextTitle;
        TextView mTextNote;
        TextView mTextDate;

        public NoteViewHolder(View itemView) {
            super(itemView);

            mTextTitle = (TextView) itemView.findViewById(R.id.text_note_title);
            mTextNote = (TextView) itemView.findViewById(R.id.text_note_message);
            mTextDate = (TextView) itemView.findViewById(R.id.text_note_date);
        }
    }
}
