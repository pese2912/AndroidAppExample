package com.example.tacademy.sampledialog;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

/**
 * Created by dongja94 on 2016-04-26.
 */
public class MySingleDialogFragment extends DialogFragment {

    String[] items = {"item0", "item1", "item2", "item3"};

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setTitle("single choice");
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "item choice : " + items[which], Toast.LENGTH_SHORT).show();
                MyMessageDialog f = MyMessageDialog.newInstance(items[which]);
                FragmentTransaction ft = getFragmentManager().beginTransaction();

                Fragment old = getFragmentManager().findFragmentByTag("dialog");
                if (old != null) {
                    ft.remove(old);
                }
                ft.addToBackStack(null);
                f.show(ft, "messagedialog");
            }
        });
        return builder.create();
    }
}