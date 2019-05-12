package com.peepeep.transport.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.peepeep.transport.R;
import com.peepeep.transport.interfaces.OnActionItemClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by poojitha.konduru on 3/29/2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    Context mContext;
    OnActionItemClickListener onActionItemClickListener;
    LayoutInflater inflater;
    RecyclerViewHolder viewHolder;
    ArrayList name_id;
    int screen_type;

    public RecyclerViewAdapter(Context context, ArrayList<?> data,int type) {
        this.mContext = context;
        inflater = LayoutInflater.from(context);

        screen_type=type;

    }

   /* public RecyclerViewAdapter(Context context, ArrayList<NameIdDataset> approvebuttons) {
    }*/

    @Override
    public RecyclerViewAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.trip_item, parent, false);
        viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {


    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tr_type)
        AppCompatTextView mTr_Type;
        @BindView(R.id.tr_date)
        AppCompatTextView mTr_Date;
        @BindView(R.id.tr_st_time)
        AppCompatTextView mTr_st_time;
        @BindView(R.id.tr_current_location)
        AppCompatTextView mTr_pick_location;
        @BindView(R.id.tr_end_time)
        AppCompatTextView mTr_end_time;
        @BindView(R.id.tr_drop_location)
        AppCompatTextView mTr_drop_location;
        @BindView(R.id.footer_submit_button)
        Button mSubmit;
        @BindView(R.id.footer_cancel_button)
        Button mCancel;
        @BindView(R.id.foot_linearlayout)
        LinearLayout mFooterLayout;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mSubmit.setOnClickListener(this);
            mCancel.setOnClickListener(this);
            itemView.setOnClickListener(this);
            if(screen_type==2)
                mFooterLayout.setVisibility(View.GONE);

        }

        @Override
        public void onClick(View v) {

            try {
                if (onActionItemClickListener != null)
                    onActionItemClickListener.onActionItemClick(getPosition(),"booking_id");
                else {
                    onActionItemClickListener = (OnActionItemClickListener) ((Activity) mContext);
                    onActionItemClickListener.onActionItemClick(getPosition(), "bokking_id");
                }

            } catch (Exception e) {
                e.printStackTrace();

            }
        }

    }


    @Override
    public int getItemCount() {


            return 2;
    }

    public void setOnActionItemClickListener(OnActionItemClickListener onActionItemClickListener) {

        this.onActionItemClickListener = onActionItemClickListener;
    }


}
