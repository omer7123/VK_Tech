package com.example.vktech.util



fun Int.toDurationFormat(): String{
    val hours = this / 3600
    val minutes = (this % 3600) / 60
    val seconds = this % 60

    return if(hours>0){
        String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }else{
        String.format("%02d:%02d", minutes, seconds)
    }
}