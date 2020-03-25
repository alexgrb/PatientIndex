package ch.hevs.alexpira;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DeleteBedDialog extends AppCompatDialogFragment  {

    //we want our Activity to be the listener of our Dialog
    private BedDialogListener listener;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //building the actual dialog
        builder.setTitle("Delete confirmation")
                .setMessage("Are you sure you want to delete this bed? All data will be removed from the database.")
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
    public interface BedDialogListener {
        void onYesClicked();
        void onNoClicked();
    }

    //Setting the listener to the activity
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (BedDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + "must implement ExampleDialogListener");
        }
    }

}