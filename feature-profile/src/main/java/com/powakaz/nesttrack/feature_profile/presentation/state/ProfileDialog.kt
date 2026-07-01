package com.powakaz.nesttrack.feature_profile.presentation.state

sealed class ProfileDialog {
    object None : ProfileDialog()
    object EditName : ProfileDialog()
    object EditBirth : ProfileDialog()
    object EditAvatar : ProfileDialog()
    data class DatePicker(val returnTo: ProfileDialog) : ProfileDialog()
}