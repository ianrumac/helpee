package ee.help.helpee.fragments;

import com.pkmmte.view.CircularImageView;
import com.rengwuxian.materialedittext.MaterialEditText;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ee.help.helpee.R;
import ee.help.helpee.mvp.presenters.RegisterPresenter;
import retrofit.mime.TypedFile;

/**
 * Created by ian on 25/04/15.
 */
public class RegisterFragment extends BaseFragment {

    static final int REQUEST_CAMERA = 1;

    static final int SELECT_FILE = 3;

    @InjectView(R.id.register_username)
    MaterialEditText registerUsername;

    @InjectView(R.id.register_email)
    MaterialEditText registerEmail;

    @InjectView(R.id.register_password)
    MaterialEditText registerPassword;

    @InjectView(R.id.profile_picture)
    CircularImageView profilePicture;


    @Inject
    RegisterPresenter registerPresenter;

    TypedFile typedFile;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.inject(this, contentView);
        return contentView;
    }

    @OnClick(R.id.profile_picture)
    void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    @OnClick(R.id.create_account_button)
    void createUserAccount() {
        if (registerUsername.getText().length() < 3) {
            showError(getActivity().getString(R.string.check_username));
        } else if (registerEmail.getText().length() < 5 && !registerEmail.getText().toString().contains("@")) {
            showError(getActivity().getString(R.string.check_email));
        } else if (registerUsername.getText().length() < 3) {
            showError(getActivity().getString(R.string.check_password));
        } else if (typedFile == null) {
            showError(getActivity().getString(R.string.check_picture));

        } else {
            registerPresenter.registerUser(
                    registerUsername.getText().toString(),
                    registerEmail.getText().toString(),
                    registerPassword.getText().toString(),
                    typedFile);

        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {

                /*User requested to get take a pic, so we save it and use the thumbnail as profile*/
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

                File destination = new File(Environment.getExternalStorageDirectory(),
                        System.currentTimeMillis() + ".jpg");

                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                profilePicture.setImageBitmap(thumbnail);
                typedFile = new TypedFile("multipart/form-data", destination);

            } else if (requestCode == SELECT_FILE) {
                                /*User requested to select a picture, so we load it from URI, create a thumbnail*/

                Uri selectedImageUri = data.getData();
                typedFile = new TypedFile("multipart/form-data", new File(selectedImageUri.getPath()));

                String[] projection = {MediaStore.MediaColumns.DATA};
                String selectedImagePath = "";
                Cursor cursor = getActivity().getContentResolver().query(selectedImageUri, projection, null, null, null);
                if (cursor.moveToFirst()) {

                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    selectedImagePath = cursor.getString(column_index);
                }
                cursor.close();

                Bitmap bm;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(selectedImagePath, options);
                final int REQUIRED_SIZE = 200;
                int scale = 1;
                while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                        && options.outHeight / scale / 2 >= REQUIRED_SIZE) {
                    scale *= 2;
                }
                options.inSampleSize = scale;
                options.inJustDecodeBounds = false;
                bm = BitmapFactory.decodeFile(selectedImagePath, options);

                profilePicture.setImageBitmap(bm);
            }


        }
    }

}
