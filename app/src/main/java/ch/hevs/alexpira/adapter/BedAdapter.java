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
import ch.hevs.alexpira.database.entity.PatientEntity;

public class BedAdapter extends RecyclerView.Adapter<BedAdapter.BedHolder> {
    private List<BedEntity> beds = new ArrayList<>();

    @NonNull
    @Override
    public BedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bed_item, parent, false);
        return new BedHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BedHolder holder, int position) {
        BedEntity currentpatient = beds.get(position);
        if (beds != null) {
            BedEntity currentBed = beds.get(position);
            holder.textViewTitle.setText(currentpatient.getId());
            holder.textViewDescription.setText(currentpatient.getBedNumber());

        } else {
            holder.textViewTitle.setText("Numéro introuvable");
            holder.textViewDescription.setText("Pas trouvé");
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

    class BedHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;

        public BedHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_titlebed);
            textViewDescription = itemView.findViewById(R.id.text_view_descriptionbed);
            textViewPriority = itemView.findViewById(R.id.text_view_prioritybed);
        }
    }
}
