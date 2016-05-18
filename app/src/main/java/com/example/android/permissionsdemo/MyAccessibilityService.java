package com.example.android.permissionsdemo;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by kumnehal on 05/14/16.
 */

public class MyAccessibilityService extends AccessibilityService {
    AccessibilityServiceInfo info;
    Boolean buttonAdded = false;
    ImageButton imageButton;
    Context displayContext;
    Display display;
    WindowManager wm;
    Toast toast;
    Boolean productPage = true;
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.i("Method","OnAccessibility Event");
        CharSequence content = event.getContentDescription();
      //  Log.i("Type",String.valueOf(Type));
        AccessibilityNodeInfo source = event.getSource();

        if (source == null) {
            return;
        }
        if(content != null){
     //       Log.i("Content",content.toString());
        }
//
//            Log.i("Event Class",event.getClassName().toString());
//            Log.i("Package",event.getPackageName().toString());
//            Log.i("Event Type", String.valueOf(event.getEventType()));
            List<CharSequence> text = event.getText();
//            if(source.getText() != null)
//               Log.i("Source",source.getText().toString());
//            if(source.getClassName() != null)
//               Log.i("SourceClass",source.getClassName().toString());
//        for(CharSequence values : text){
//          Log.i("Values", values.toString());
//        }
        imageButton = new ImageButton(this);
        imageButton.setBackground(null);
        WindowManager.LayoutParams p = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);


        p.gravity = Gravity.CENTER_VERTICAL | Gravity.RIGHT;
        p.x = 0;
        p.y = 100;


        imageButton.setImageResource(R.drawable.logogit);
        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        if(event.getPackageName().toString().equalsIgnoreCase("in.swiggy.android") || event.getPackageName().toString().equalsIgnoreCase("in.amazon.mShop.android.shopping")){
            if(event.getPackageName().toString().equalsIgnoreCase("in.amazon.mShop.android.shopping")){
                //Toast amazon = Toast.makeText(getApplicationContext(), "Amazon", Toast.LENGTH_LONG);
                //amazon.show();
            }
            if(event.getEventType() == 1 && event.getPackageName().toString().equalsIgnoreCase("in.swiggy.android")) {
                if (source.getClassName() != null && source.getClassName().toString().equalsIgnoreCase("android.widget.LinearLayout") && text.size() > 1) {
                    Log.i("Name", text.get(0).toString());
                    toast = Toast.makeText(getApplicationContext(), text.get(0).toString(), Toast.LENGTH_LONG);
                    toast.show();
                    wm.addView(imageButton,p);
                }
            }
            else if(event.getClassName().toString().equalsIgnoreCase("in.swiggy.android.activities.ProfileActivity")){
                Log.i("Remove button", "Remove");
                try{
                    wm.removeViewImmediate(imageButton);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }



       // imageView.setBackgroundResource(R.drawable.logogit);

//----amazon
//        if((event.getClassName().toString().equalsIgnoreCase("com.amazon.mShop.details.web.WebProductDetailsActivity")) && event.getEventType() == 32 ) {
//            Log.i("If","Inside if");
//            wm = (WindowManager) getSystemService(WINDOW_SERVICE);
//            if(source.getParent().getClassName()!= null)
//            Log.i("Parent",source.getParent().getClassName().toString());
//            if(source.getClassName()!=null && source.getClassName().equals("android.view.View")){
//                toast = Toast.makeText(getApplicationContext(),"Products Page",Toast.LENGTH_LONG);
//                toast.show();
//            }
//            imageButton.setImageResource(R.drawable.logogit);
//            //((WindowManager)getApplicationContext().getSystemService(WINDOW_SERVICE)).addView(imageButton, p);
//            buttonAdded =  true;
//           display =  wm.getDefaultDisplay();
//           displayContext = createDisplayContext(display);
//            //Log.i("Context", String.valueOf(displayContext));
//
//            wm.addView(imageButton, p);
//
//            //Log.i("Window Mgr", String.valueOf(wm));
//        }
//        else if(event.getEventType() == 32 && !event.getClassName().toString().equalsIgnoreCase("com.amazon.mShop.details.web.WebProductDetailsActivity")){
//            if(source.getClassName() != null){
//
//            }
//            productPage = true;
//           // Log.i("Method", "Remove from other apps");
//           // imageButton.setImageResource(R.drawable.blank);
//            if(buttonAdded){
//                //Log.i("Context", String.valueOf(displayContext));
//                try{
//                    //wm = ((WindowManager)displayContext.getSystemService(WINDOW_SERVICE));
//                    //Log.i("Window Mgr", String.valueOf(wm));
//                   // wm.getDefaultDisplay();
////                    stopService(new Intent(this, MyAccessibilityService.class));
////                    startService(new Intent(this, MyAccessibilityService.class));
//                   // wm.removeView(imageButton);
//
//
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//                buttonAdded = false;
//            }
//        } ---- amazon

        imageButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
               Log.i("Event","Button Touched.....");
                return false;
            }
        });
    }
    @Override
    public void onInterrupt() {
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i("Method","On Destroy");
    }

    @Override
    public void onCreate() {
        Log.i("Method","On Create");
    }

    @Override
    protected void onServiceConnected() {
        Log.i("Connected","Permission");
        info = new AccessibilityServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPE_VIEW_CLICKED |
                AccessibilityEvent.TYPE_VIEW_FOCUSED|AccessibilityEvent.TYPE_VIEW_HOVER_EXIT|AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED|AccessibilityEvent.TYPE_VIEW_SELECTED|AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED;
        info.packageNames = new String[] {"in.swiggy.android","in.amazon.mShop.android.shopping","com.amazon.anow","com.flipkart.android"};
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        info.flags = AccessibilityServiceInfo.DEFAULT;
        info.flags = AccessibilityServiceInfo.FLAG_INCLUDE_NOT_IMPORTANT_VIEWS; info.flags = AccessibilityServiceInfo.FLAG_REPORT_VIEW_IDS;
        info.flags = AccessibilityServiceInfo.FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY; info.flags = AccessibilityServiceInfo.FLAG_RETRIEVE_INTERACTIVE_WINDOWS;
        info.notificationTimeout = 100;
        this.setServiceInfo(info);
    }
}
