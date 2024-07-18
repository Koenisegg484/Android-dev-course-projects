package com.example.simplynotetaker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private static List<Note> notes = new ArrayList<>();
    private static OnItemClickListener listener;

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);

        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {

        Note CurrentNote = notes.get(position);
        holder.title.setText(CurrentNote.getTitle());
        holder.content.setText(CurrentNote.getDescription());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes){
        this.notes = notes;
        notifyDataSetChanged();
    }

    public Note getNotes(int position){
        return notes.get(position);
    }

    static class NoteHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView content;

        public NoteHolder(@NonNull View itemView){
            super(itemView);

            title = itemView.findViewById(R.id.notetitle);
            content = itemView.findViewById(R.id.noteContent);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(listener != null && pos != RecyclerView.NO_POSITION){
                        listener.onItemClick(notes.get(pos));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Note note);

    }

    public void setOnItemClickListener(OnItemClickListener listener){

        this.listener = listener;
    }
}
