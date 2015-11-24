package com.android.ipm.mygymbuddy.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.ipm.mygymbuddy.R;
import com.tyczj.extendedcalendarview.Event;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {

    private List<Event> mEventsList;

    public EventAdapter(List<Event> e) {
        mEventsList = e;
    }

    @Override
    public EventHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.list_item_event, viewGroup, false);
        return new EventHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EventHolder holder, int position) {
        Event event = mEventsList.get(position);
        holder.title.setText(String.valueOf(event.getTitle()));
        holder.descr.setText(String.valueOf(event.getDescription()));
        holder.date.setText(String.valueOf(event.getStartDate("hh:mm") + " - " + event.getEndDate("hh:mm")));

    }

    @Override
    public int getItemCount() {
        return mEventsList.size();
    }

    public final static class EventHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title, descr, date;

        public EventHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.event_title);
            descr = (TextView) itemView.findViewById(R.id.event_descr);
            date = (TextView) itemView.findViewById(R.id.event_time);
        }

        @Override
        public void onClick(View v) {

        }
    }
}