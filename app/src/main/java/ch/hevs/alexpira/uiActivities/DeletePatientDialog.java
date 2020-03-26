package ch.hevs.alexpira.uiActivities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DeletePatientDialog extends AppCompatDialogFragment {

    //we want our Activity to be the listener of our Dialog
    private PatientDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //building the actual dialog
        builder.setTitle("Delete confirmation")
                .setMessage("Are you sure you want to delete this patient? All data will be removed from the database.")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //code to delete the data + go to somewhere else + display toast
                        listener.onYesClicked();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onNoClicked();

                    }
                });

        return builder.create();
    }

    //creating an interface to establish communication with the DisplayActivity
    public interface PatientDialogListener {
        void onYesClicked();
        void onNoClicked();
    }

    //Setting the listener to the activity
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (DeletePatientDialog.PatientDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + "must implement ExampleDialogListener");
        }
    }
}
