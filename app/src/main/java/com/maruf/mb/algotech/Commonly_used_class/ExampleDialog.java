package com.maruf.mb.algotech.Commonly_used_class;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.maruf.mb.algotech.R;

public class ExampleDialog extends AppCompatDialogFragment {

    private TextInputLayout mEditTextSubject,mEditTextMessage;
    private EditText mEditTextTo;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_mailsend_dialog,null);
        builder.setView(view)
                .setTitle("Mail Send")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                })
                .setPositiveButton("send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendMail();

                    }
                });
        mEditTextTo = view.findViewById(R.id.edit_text_to);
        mEditTextSubject = view.findViewById(R.id.edit_text_subject);
        mEditTextMessage = view.findViewById(R.id.edit_text_message);

        return builder.create();
    }

    private void sendMail() {

        String recipientList = mEditTextTo.getText().toString();
        String[] recipients = recipientList.split(",");

        String subject = mEditTextSubject.getEditText().getText().toString();
        String message = mEditTextMessage.getEditText().getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT,message);


        intent.setType("message/rfc822");

        startActivity(Intent.createChooser(intent,"Choose an email claint"));
    }


}
