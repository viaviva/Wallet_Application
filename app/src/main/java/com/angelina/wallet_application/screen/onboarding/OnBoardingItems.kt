package com.angelina.wallet_application.screen.onboarding

import com.angelina.wallet_application.R

class OnBoardingItems(
    val image: Int,
    val title: Int,
    val desc: Int
) {
    companion object{
        fun getData(): List<OnBoardingItems>{
            return listOf(
                OnBoardingItems(R.drawable.oboarding, R.string.onbordingTitle1, R.string.onboardingText1),
                OnBoardingItems(R.drawable.oboarding, R.string.onbordingTitle1, R.string.onboardingText1),
                OnBoardingItems(R.drawable.oboarding, R.string.onbordingTitle1, R.string.onboardingText1)
            )
        }
    }
}