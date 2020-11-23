package com.example.shaaditest.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.shaaditest.R;
import com.example.shaaditest.model.Person;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;

@Layout(R.layout.shadi_card_view)
public class Shadicard {


        @View(R.id.profileImageView)
        private ImageView profileImageView;

        @View(R.id.nameAgeTxt)
        private TextView nameAgeTxt;

        @View(R.id.locationNameTxt)
        private TextView locationNameTxt;

        @View(R.id.tv_ageheit)
        private  TextView tv_ageheit;



        private Person mProfile;
        private Context mContext;
        private SwipePlaceHolderView mSwipeView;

        public Shadicard(Context context, Person profile, SwipePlaceHolderView swipeView) {
            mContext = context;
            mProfile = profile;
            mSwipeView = swipeView;
        }

        @Resolve
        private void onResolved() {
           // Glide.with(mContext).load(mProfile.getPic_large()).circleCrop().into(profileImageView);

            Glide.with(mContext).load(mProfile.getPic_large()).asBitmap().centerCrop().into(new BitmapImageViewTarget(profileImageView) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    profileImageView.setImageDrawable(circularBitmapDrawable);
                }
            });
            nameAgeTxt.setText(mProfile.getFirst_nmae() + ", " + mProfile.getLast_name());
            tv_ageheit.setText(mProfile.getAge()+"yrs ,"+" "+mProfile.getGender());
            locationNameTxt.setText(mProfile.getCity()+", "+mProfile.getState()+", "+mProfile.getCountry());
        }

        @SwipeOut
        private void onSwipedOut() {
            Log.d("EVENT", "onSwipedOut");
            mSwipeView.addView(this);
        }

        @SwipeCancelState
        private void onSwipeCancelState() {
            Log.d("EVENT", "onSwipeCancelState");
        }

        @SwipeIn
        private void onSwipeIn() {
            Log.d("EVENT", "onSwipedIn");
        }

        @SwipeInState
        private void onSwipeInState() {
            Log.d("EVENT", "onSwipeInState");
        }

        @SwipeOutState
        private void onSwipeOutState() {
            Log.d("EVENT", "onSwipeOutState");
        }
    }
