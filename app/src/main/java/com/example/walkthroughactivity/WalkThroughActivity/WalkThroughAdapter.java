package com.example.walkthroughactivity.WalkThroughActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walkthroughactivity.R;

import java.util.ArrayList;
import java.util.List;

public class WalkThroughAdapter extends RecyclerView.Adapter<WalkThroughAdapter.OnboardingViewHolder>{


    private List<WalkThroughModel> onBoardingItems;

    public WalkThroughAdapter(List<WalkThroughModel> onBoardingItems) {
        this.onBoardingItems = onBoardingItems;
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_boarding_one, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        holder.setOnBoardingData(onBoardingItems.get(position));
    }

    @Override
    public int getItemCount() {
        return onBoardingItems.size();
    }

    class OnboardingViewHolder extends RecyclerView.ViewHolder {

        private TextView textDescription;
        private ImageView imageOnboarding;


        private TextView ivWelcome;

        OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            textDescription = itemView.findViewById(R.id.textDescription);
            imageOnboarding = itemView.findViewById(R.id.imageOnboarding);

            ivWelcome = itemView.findViewById(R.id.ivWelcome);

        }

        void setOnBoardingData(WalkThroughModel onBoardingItem)
        {
            textDescription.setText(onBoardingItem.getDescription());
            imageOnboarding.setImageResource(onBoardingItem.getImage());

        }
    }
}