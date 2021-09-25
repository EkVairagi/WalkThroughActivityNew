package com.example.walkthroughactivity.WalkThroughActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.walkthroughactivity.R;

import java.util.ArrayList;
import java.util.List;

public class WalkThroughActivity extends AppCompatActivity {
    private WalkThroughAdapter walkThroughAdapter;
    private LinearLayout layoutOnboardingIndicator;
    private Button buttonOnboardingAction;
    private static final int PERMISSION_REQUEST_CONTACT = 112;
    private TextView skip_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk_through2);

        layoutOnboardingIndicator = findViewById(R.id.layoutOnboardingIndicators);
        buttonOnboardingAction = findViewById(R.id.btnGetStarted);

        setOnboardingItem();
        skip_ = findViewById(R.id.skip_);



        ViewPager2 onboardingViewPager = findViewById(R.id.onboardingViewPager);
        onboardingViewPager.setAdapter(walkThroughAdapter);

        setOnboadingIndicator();
        setCurrentOnboardingIndicators(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicators(position);
            }
        });

        skip_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


        buttonOnboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (onboardingViewPager.getCurrentItem()==0)
                {
                    onboardingViewPager.setCurrentItem(1);
                }
                else if (onboardingViewPager.getCurrentItem()==1)
                {
                    onboardingViewPager.setCurrentItem(2);
                }
                else if (onboardingViewPager.getCurrentItem()==2)
                {
                    onboardingViewPager.setCurrentItem(3);
                }
                else if (onboardingViewPager.getCurrentItem()==3)
                {
                    startActivity(new Intent(WalkThroughActivity.this, MainActivity.class));
                }

            }
        });


    }


    private void setOnboadingIndicator()
    {
        ImageView[] indicators = new ImageView[walkThroughAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(), R.drawable.walk_through_inactive_dot
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnboardingIndicator.addView(indicators[i]);
        }
    }

    private void setCurrentOnboardingIndicators(int index)
    {
        int childCount = layoutOnboardingIndicator.getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            ImageView imageView = (ImageView) layoutOnboardingIndicator.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.walk_through_active_dot));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.walk_through_inactive_dot));
            }
        }
        if (index == 0)
        {

            buttonOnboardingAction.setText("Get Started");
        }
        else if (index==1)
        {

            buttonOnboardingAction.setText("Add Family Member");
        }
        else if (index==2)
        {

            buttonOnboardingAction.setText("Invite Contacts");
        }
        else if (index==3)
        {
            buttonOnboardingAction.setText("Choose Interest");
        }
    }
    List<WalkThroughModel> onBoardingItems = new ArrayList<>();
    private void setOnboardingItem() {


        WalkThroughModel itemGetStart = new WalkThroughModel();
        itemGetStart.setDescription("Hey.");
        itemGetStart.setImage(R.drawable.ic_get_start);

        WalkThroughModel itemInvite = new WalkThroughModel();
        itemInvite.setDescription("Invite!");
        itemInvite.setImage(R.drawable.ic_fam);

        WalkThroughModel itemInviteMates = new WalkThroughModel();
        itemInviteMates.setDescription("Let's get connected");
        itemInviteMates.setImage(R.drawable.ic_friends);

        WalkThroughModel itemEntertainment = new WalkThroughModel();
        itemEntertainment.setDescription("Choose interest");
        itemEntertainment.setImage(R.drawable.ic_friends);


        onBoardingItems.add(itemGetStart);
        onBoardingItems.add(itemInvite);
        onBoardingItems.add(itemInviteMates);
        onBoardingItems.add(itemEntertainment);

        walkThroughAdapter = new WalkThroughAdapter(onBoardingItems);
    }
}