package ch.hevs.alexpira.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ch.hevs.alexpira.R;
import ch.hevs.alexpira.database.entity.BedEntity;

public class BedAdapter extends RecyclerView.Adapter<BedAdapter.BedHolder> {
    private List<BedEntity> beds = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public BedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bed_item, parent, false);
        return new BedHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BedHolder holder, int position) {
        if (beds != null) {
            BedEntity currentBed = beds.get(position);
            holder.textViewBedID.setText(String.valueOf(currentBed.getPatientId()));
            holder.textViewBedNumber.setText(String.valueOf(currentBed.getBedNumber()));

        } else {
            holder.textViewBedID.setText("Numéro introuvable");
            holder.textViewBedNumber.setText("Pas trouvé");
        }
    }
    @Override
    public int getItemCount() {
        if (beds != null)
            return beds.size();
        else
            return 1;
    }

    public void setBeds(List<BedEntity> beds) {
        if (beds != null) {
            this.beds = beds;
        }
        notifyDataSetChanged();
    }

    public BedEntity getBedAt(int position){
        return beds.get(position);
    }

    class BedHolder extends RecyclerView.ViewHolder {
        private TextView textViewBedID;
        private TextView textViewBedNumber;
        private TextView textViewBedSize;

        public BedHolder(View itemView) {
            super(itemView);
            textViewBedID = itemView.findViewById(R.id.text_view_titlebed);
            textViewBedNumber = itemView.findViewById(R.id.edit_text_bedNumber);
            textViewBedSize = itemView.findViewById(R.id.edit_text_bedSize);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(beds.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(BedEntity bed);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
