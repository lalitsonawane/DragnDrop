package com.lalitsonawane.lalit.dragndrop;

import android.content.ClipData;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView option1, option2, option3, choice1, choice2, choice3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View to Drag
        option1 = (TextView) findViewById(R.id.option_1);
        option2 = (TextView) findViewById(R.id.option_2);
        option3 = (TextView) findViewById(R.id.option_3);

        //View to Drop
        choice1 = (TextView) findViewById(R.id.choice_1);
        choice2 = (TextView) findViewById(R.id.choice_2);
        choice3 = (TextView) findViewById(R.id.choice_3);

        //set Touch Listener
        option1.setOnTouchListener(new ChoiceTouchListener());
        option2.setOnTouchListener(new ChoiceTouchListener());
        option3.setOnTouchListener(new ChoiceTouchListener());

        //set Drag Listener
        choice1.setOnDragListener(new ChoiceDragListener());
        choice2.setOnDragListener(new ChoiceDragListener());
        choice3.setOnDragListener(new ChoiceDragListener());

    }


    private final class ChoiceTouchListener implements View.OnTouchListener {


        @Override
        public boolean onTouch(View v, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                //setup drag

                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);

               v.startDrag(data, shadowBuilder, v, 0);
                //v.startDragAndDrop(data, shadowBuilder, v, 0);
                v.setVisibility(View.INVISIBLE);

                return true;
            } else {
                return false;
            }

        }
    }

    private class ChoiceDragListener implements View.OnDragListener {


        @Override
        public boolean onDrag(View v, DragEvent event) {

            //Handle drag events

            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //no action
               //    return true;
                   break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //no action
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //no action
                    break;
                case DragEvent.ACTION_DROP:
                    //Handle the dragged view being dropped over a drop view.

                    View view = (View) event.getLocalState();
                    view.setVisibility(View.INVISIBLE);
                    //View dragged item is being  dropped on.
                    TextView dropTarget = (TextView) v;
                    //View being dragged and dropped.
                    TextView dropped = (TextView) view;
                    //Update the text in target view to reflect the data being dropped.
                    dropTarget.setText(dropped.getText());
                    //make it bold to highlight the fact that an item has been dropped.
                    dropTarget.setTypeface(Typeface.DEFAULT_BOLD);
                    Object tag = dropTarget.getTag();
                    //if there is already an item then set it back visible in its original place.
                    if (tag != null) {
                        //the tag is the view id already dropped here.
                        int existingId = (Integer) tag;
                        //set the original view visible again.
                        findViewById(existingId).setVisibility(View.VISIBLE);
                        //set the tag  in the target  view to the ID of the view being dropped.
                        dropTarget.setTag(dropped.getId());
                    }

                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //no action
                    break;
                default:
                    break;

            }
            return false;
        }
    }
}
