package com.lovejoy.core;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.lovejoy.views.activity.R;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lovejoy.views.adapter.commentAdapter;
import com.lovejoy.model.actmembers;

import java.util.LinkedList;
import java.util.List;


public class CustomDialogforpeople extends Dialog {

    public CustomDialogforpeople(Context context) {
        super(context);
    }

    public CustomDialogforpeople(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;
        private String title;
        //private String message;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;
        private List<actmembers> mData = null;
        private commentAdapter mAdapter = null;
        private ListView list_members;
        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(String message) {
            //this.message = message;
            return this;
        }


        public Builder setMessage(int message) {
           // this.message = (String) context.getText(message);
            return this;
        }

        /**
         * Set the Dialog title from resource
         *
         * @param title
         * @return
         */
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        /**
         * Set the Dialog title from String
         *
         * @param title
         * @return
         */

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         *
         * @param positiveButtonText
         * @return
         */
        public Builder setPositiveButton(int positiveButtonText,
                                         OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText,
                                         OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText,
                                         OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText,
                                         OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public CustomDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final CustomDialog dialog = new CustomDialog(context,R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog_people_comment, null);
            dialog.addContentView(layout, new LayoutParams(
                    LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            // set the dialog title
            ((TextView) layout.findViewById(R.id.title)).setText(title);

            ((TextView) layout.findViewById(R.id.title)).setTypeface( MDFontsUtils.getTypeface(context,"fonts/Roboto-Black.ttf"));
            // set the confirm button
            if (positiveButtonText != null) {
                ((Button) layout.findViewById(R.id.positiveButton))
                        .setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.positiveButton))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    positiveButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_POSITIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.positiveButton).setVisibility(
                        View.GONE);
            }
            // set the cancel button
            if (negativeButtonText != null) {
                ((Button) layout.findViewById(R.id.negativeButton))
                        .setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    ((Button) layout.findViewById(R.id.negativeButton))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    negativeButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_NEGATIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.negativeButton).setVisibility(
                        View.GONE);
            }
            // set the content message

            list_members = (ListView)layout.findViewById(R.id.list2);
            mData = new LinkedList<actmembers>();
            mData.add(new actmembers("王辉", 2,1));
            mData.add(new actmembers("于航",2, 1));
            mData.add(new actmembers("天然", 2, 1));
            mData.add(new actmembers("田杨", 2, 1));
            mData.add(new actmembers("超哲", 2, 1));
            mData.add(new actmembers("王辉", 2,1));
            mData.add(new actmembers("于航wewew",2, 1));
            mData.add(new actmembers("天然weeeeeee", 2, 1));
            mData.add(new actmembers("田杨wewe", 2, 1));
            mData.add(new actmembers("超哲eeee", 2, 1));
            mData.add(new actmembers("王辉eewew", 2,1));
         mData.add(new actmembers("于航",2, 1));
         mData.add(new actmembers("天然", 2, 1));
         mData.add(new actmembers("田杨", 2, 1));
          mData.add(new actmembers("超哲", 2, 1));
            mAdapter = new commentAdapter((LinkedList<actmembers>) mData, context);
            list_members.setAdapter(mAdapter);
            dialog.setContentView(layout);
            return dialog;
        }

    }
}
