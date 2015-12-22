package com.cirrius.bomb;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.gesture.Gesture;
import android.gesture.GestureOverlayView;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.io.File;

/**
 * Created by admin on 12/19/15.
 */
public class Configure extends Activity {
    final int FILE_SELECT_CODE = 23456;
    TableLayout fixed_column, scrollable_part;
    TableRow.LayoutParams wrapWrapTableRowParams, matchWrapTableRowParams, matchWrapWithWeightTableRowParams;

    final String EDITTEXT = "0";
    final String LOV = "1";
    final String IMAGEPICKERE = "2";
    final String RADIO = "3";
    final String SIGN = "4";
    final String FILEPICKER = "5";
    final String TOGGLE = "6";
    final String CHKBOX = "7";
    final String TEXTVIEW = "8";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configure);
        fixed_column = (TableLayout) findViewById(R.id.fixed_column);
        scrollable_part = (TableLayout) findViewById(R.id.scrollable_part);

        wrapWrapTableRowParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        matchWrapTableRowParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        matchWrapWithWeightTableRowParams = new TableRow.LayoutParams(200, TableRow.LayoutParams.WRAP_CONTENT);


        String[][] str =
                {{"Name", "QTY", "TYPE", "IMG", "RADIO", "SIGN", "FILE", "TOGGLE", "CHECKBOX"},


                {"C-FIX", "0", "1", "2", "3", "4", "6", "5", "7"},
                {"MEZZO", "0", "1", "2", "3", "4", "6", "5", "7"},
                {"MEZZO", "0", "1", "2", "3", "4", "6", "5", "7"},
                {"MEZZO", "0", "1", "2", "3", "4", "6", "5", "7"},
                {"MEZZO", "0", "1", "2", "3", "4", "6", "5", "7"},
                {"MEZZO", "0", "1", "2", "3", "4", "6", "5", "7"},
                {"MEZZO", "0", "1", "2", "3", "4", "6", "5", "7"},
                {"MEZZO", "0", "1", "2", "3", "4", "6", "5", "7"},
                {"MEZZO", "0", "1", "2", "3", "4", "6", "5", "7"}


        };

        for (int j = 0; j < str.length; j++) {
            EditText nameHeader = new EditText(this);
            nameHeader.setText(str[j][0]);
            nameHeader.setTextColor(Color.WHITE);
            nameHeader.setEnabled(false);
            nameHeader.setGravity(Gravity.CENTER_HORIZONTAL);
            nameHeader.setBackgroundColor(Color.parseColor("#5c6bc0"));
            nameHeader.setLayoutParams(matchWrapTableRowParams);
            fixed_column.addView(nameHeader);

            TableRow row = new TableRow(this);
            row.setLayoutParams(matchWrapTableRowParams);
            row.setGravity(Gravity.CENTER);
            row.setBackgroundColor(Color.parseColor("#9fa8da"));
            for (int i = 1; i < str[j].length; i++) {
                if (str[j][i].equals(EDITTEXT)) {
                    row.addView(getEditText(this));
                } else if (str[j][i].equals(LOV)) {
                    row.addView(getSpinner(this));
                } else if (str[j][i].equals(IMAGEPICKERE)) {
                    ImageView imageView = new ImageView(this);
                    imageView.setImageResource(android.R.drawable.ic_menu_camera);
                    row.addView(imageView);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            selectImage(Configure.this);
                        }
                    });
                } else if (str[j][i].equals(RADIO)) {
                    ImageView imageView = new ImageView(this);
                    imageView.setImageResource(android.R.drawable.ic_menu_save);
                    row.addView(imageView);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getRadioDialog(Configure.this);
                        }
                    });
                } else if (str[j][i].equals(SIGN)) {
                    ImageView imageView = new ImageView(this);
                    imageView.setImageResource(android.R.drawable.ic_menu_send);
                    row.addView(imageView);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getSignDialog(Configure.this);
                        }
                    });
                } else if (str[j][i].equals(FILEPICKER)) {
                    ImageView imageView = new ImageView(this);
                    imageView.setImageResource(android.R.drawable.ic_menu_share);
                    row.addView(imageView);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showFileChooser();
                        }
                    });
                } else if (str[j][i].equals(TOGGLE)) {
                    Switch tb = new Switch(this);
                    tb.setTextColor(Color.WHITE);

                    tb.setLayoutParams(matchWrapWithWeightTableRowParams);
                    row.addView(tb);

                } else if (str[j][i].equals(CHKBOX)) {

                    CheckBox cb = new CheckBox(getApplicationContext());
                    cb.setText("Agree");

                    cb.setTextColor(Color.WHITE);
                    cb.setLayoutParams(matchWrapWithWeightTableRowParams);
                    row.addView(cb);
                } else {
                    EditText columnHeader = new EditText(this);
                    columnHeader.setText(str[j][i]);

                    columnHeader.setGravity(Gravity.CENTER_HORIZONTAL);
                    columnHeader.setTextColor(Color.WHITE);
                    columnHeader.setEnabled(false);
                    columnHeader.setBackgroundColor(Color.parseColor("#283593"));
                    columnHeader.setLayoutParams(matchWrapWithWeightTableRowParams);
                    row.addView(columnHeader);
                }
            }
            scrollable_part.addView(row);
        }


    }

    public EditText getEditText(Context context) {
        EditText editText = new EditText(context);

        editText.setTextColor(Color.BLACK);
        //   editText.setPadding(1, 1, 1, 1);
        editText.setHint("Type Here");
        editText.setTextColor(Color.WHITE);
        editText.setLayoutParams(matchWrapTableRowParams);
        return editText;
    }

    public Spinner getSpinner(Context context) {

        String[] spinnerArray = {"Yes", "No"};
        Spinner spinner = new Spinner(context);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setLayoutParams(matchWrapWithWeightTableRowParams);
        return spinner;
    }

    private void selectImage(Context context) {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void getRadioDialog(Context context) {
        Dialog dialog = new Dialog(context);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        RadioGroup rg = new RadioGroup(context);
        rg.setLayoutParams(new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.MATCH_PARENT));
        RadioButton rb1 = new RadioButton(context);
        rb1.setText("Yes");
        RadioButton rb2 = new RadioButton(context);
        rb2.setText("No");
        rg.addView(rb1);
        rg.addView(rb2);
        dialog.setContentView(rg);
        dialog.getWindow().setLayout(250, 250);
        dialog.show();
    }

    public void getSignDialog(Context context) {
        final Dialog dialog_box = new Dialog(context);
        dialog_box.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_box.setContentView(R.layout.dialoglayout);
        Window window = dialog_box.getWindow();
//        window.setBackgroundDrawable(new ColorDrawable(
//                Color.DKGRAY));
        //dialog_box.getWindow().setLayout(600, 450);
        dialog_box.getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog_box.show();
        final GestureOverlayView gesturesView = (GestureOverlayView) dialog_box.findViewById(R.id.gestures2);
        gesturesView.addOnGesturePerformedListener(new GestureOverlayView.OnGesturePerformedListener() {
            @Override
            public void onGesturePerformed(GestureOverlayView gestureOverlayView, Gesture gesture) {

            }
        });
        Button reset = (Button) dialog_box.findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                gesturesView.clearAnimation();
                gesturesView.clear(true);
            }
        });
        Button done = (Button) dialog_box.findViewById(R.id.save);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog_box.dismiss();
            }
        });
    }

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a Image"), FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {

//                    Uri uri = data.getData();
//                    String path = getRealPathFromURI(uri);
//                    File imgFile = new File(path);
//                    if (imgFile.exists()) {
////                        myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
////                        img.setImageBitmap(myBitmap);
//                    }

                }
                break;
        }
    }

    /**
     * Returns path of the file specified by content Uri
     */
    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(column_index);
        cursor.close();
        return path;
    }
}
