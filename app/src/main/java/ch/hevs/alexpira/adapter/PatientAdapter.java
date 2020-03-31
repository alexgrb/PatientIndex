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
import ch.hevs.alexpira.database.pojo.PatientWithBed;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientHolder> {
    private List<PatientWithBed> patients = new ArrayList<>();
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
        PatientWithBed currentPatient = patients.get(position);
        holder.textViewLastName.setText(currentPatient.patientEntity.getPatientLastName());
        holder.textViewFirstName.setText(currentPatient.patientEntity.getPatientFirstName());
        holder.textViewPatientBedNumber.setText(String.valueOf(currentPatient.patientEntity.getBedId()));
    }

    @Override
    public int getItemCount() {
        if (patients != null)
            return patients.size();
        else
            return 1;
    }

    //this method is used to get a patient at a certain position
    public PatientEntity getPatientAt(int position) {
        return patients.get(position).patientEntity;
    }

    public void setPatients(List<PatientWithBed> patients) {
        if (patients != null) {
            this.patients = patients;
        }
        notifyDataSetChanged();
    }

    class PatientHolder extends RecyclerView.ViewHolder {
        private TextView textViewLastName;
        private TextView textViewFirstName;
        private TextView textViewPatientBedNumber;

        public PatientHolder(View itemView) {
            super(itemView);
            textViewLastName = itemView.findViewById(R.id.text_view_title);
            textViewFirstName = itemView.findViewById(R.id.text_view_description);
            textViewPatientBedNumber = itemView.findViewById(R.id.text_view_patientBedNumber);

            //we click on our itemView
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //get the position
                    int position = getAdapterPosition();
                    //verify if the position is not -1 (or no-position)
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(patients.get(position));
                    }
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(PatientWithBed patient);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}