package com.example.hanish.health_on_the_go;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class camera extends Activity
{
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    static int curr_pos;
    String pname;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        this.imageView = (ImageView)this.findViewById(R.id.imageView1);
        Button photoButton = (Button) this.findViewById(R.id.button1);
        photoButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }
    private void saveImage(Bitmap finalBitmap, String image_name) {

     String root = Environment.getExternalStorageDirectory().toString();
    File myDir = new File(root);
        Toast.makeText(this, ""+myDir, Toast.LENGTH_SHORT).show();

    myDir.mkdirs();
    String fname = "Image-" + image_name+ ".jpg";
    File file = new File(myDir, fname);
    if (file.exists()) file.delete();
    Log.i("LOAD", root + fname);
    try {
        FileOutputStream out = new FileOutputStream(file);
        finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
        out.flush();
        out.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    private String getName()
    {
        int i=curr_pos;
        while(true)


        {
            pname = "Disease" +i;
            i++;
            curr_pos=i;
            break;
        }
        return pname;
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
            String pname = getName();
            saveImage(photo,pname);
        }
    }
}
