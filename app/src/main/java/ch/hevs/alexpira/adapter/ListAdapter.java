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
import ch.hevs.alexpira.database.entity.PatientEntity;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.PatientHolder> {
    private List<PatientEntity> patients = new ArrayList<>();

    @NonNull
    @Override
    public PatientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_item, parent, false);
        return new PatientHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientHolder holder, int position) {
        PatientEntity currentPatient = patients.get(position);
        holder.textViewTitle.setText(currentPatient.getPatientLastName());
        holder.textViewDescription.setText(currentPatient.getPatientFirstName());

    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    public void setNotes(List<PatientEntity> patients) {
        this.patients = patients;
        notifyDataSetChanged();
    }

    class PatientHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;

        public PatientHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            //textViewPriority = itemView.findViewById(R.id.text_view_priority);
        }
    }
}