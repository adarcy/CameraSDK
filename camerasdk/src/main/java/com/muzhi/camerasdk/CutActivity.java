package com.muzhi.camerasdk;

import com.muzhi.camerasdk.model.Constants;
import com.muzhi.camerasdk.utils.PhotoUtils;
import com.muzhi.camerasdk.view.CropImageView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;



public class CutActivity extends BaseActivity {
  
    private CropImageView mCropView;

    private Bitmap sourceMap;
    private ImageView iv_crop_rotate;
    private TextView tv_crop_complete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.camerasdk_activity_cut);
        showLeftIcon();
        setActionBarTitle("裁剪");
        findViews();
        
        sourceMap=Constants.bitmap;
        mCropView.setImageBitmap(sourceMap);		
        
    }

    private void findViews() {
        mCropView = (CropImageView) findViewById(R.id.cropImageView);
        iv_crop_rotate = (ImageView) findViewById(R.id.iv_crop_rotate);
        tv_crop_complete = (TextView) findViewById(R.id.tv_crop_complete);

        mCropView.setCropMode(CropImageView.CropMode.RATIO_1_1);

        initEvent();
    }
	
	private void initEvent() {
        iv_crop_rotate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sourceMap = PhotoUtils.rotateImage(sourceMap, -90);
                mCropView.setImageBitmap(sourceMap);
            }
        });

        tv_crop_complete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				done();
			}
		});
	}
    
    private void done(){
    	Constants.bitmap=mCropView.getCroppedBitmap();
    	setResult(Constants.RequestCode_Croper);
        finish();
    }
    
    
    
    
    
    
    
    
}
