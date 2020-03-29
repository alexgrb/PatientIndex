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

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientHolder> {
    private List<PatientEntity> patients = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public PatientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_item, parent, false);
        return new PatientHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientHolder holder, int position) {
        if (patients != null) {
            PatientEntity currentPatient = patients.get(position);
            holder.textViewLastName.setText(currentPatient.getPatientLastName());
            holder.textViewFirstName.setText(currentPatient.getPatientFirstName());
        } else {
            holder.textViewLastName.setText("NomFamille");
            holder.textViewFirstName.setText("Pas trouvé");
        }
    }

    @Override
    public int getItemCount() {
        if (patients != null)
            return patients.size();
        else
            return 1;
    }

    public void setPatients(List<PatientEntity> patients) {
        if (patients != null) {
            this.patients = patients;
        }
        notifyDataSetChanged();
    }

    class PatientHolder extends RecyclerView.ViewHolder {
        private TextView textViewLastName;
        private TextView textViewFirstName;

        public PatientHolder(View itemView) {
            super(itemView);
            textViewLastName = itemView.findViewById(R.id.text_view_title);
            textViewFirstName = itemView.findViewById(R.id.text_view_description);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(patients.get(position));
                    }
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(PatientEntity patient);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}