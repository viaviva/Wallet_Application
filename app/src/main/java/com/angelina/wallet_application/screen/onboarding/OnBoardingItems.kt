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
                OnBoardingItems(R.drawable.oboarding, R.string.onboardingTitle1, R.string.onboardingText1),
                OnBoardingItems(R.drawable.onboarding2, R.string.onboardingTitle2, R.string.onboardingText2),
                OnBoardingItems(R.drawable.onboarding3, R.string.onboardingTitle3, R.string.onboardingText3)
            )
        }
    }
}