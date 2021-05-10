@file:Suppress("DEPRECATION")

package com.example.delivery_food.utilites

import android.app.Activity
import android.content.Intent
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.delivery_food.R
import com.squareup.picasso.Picasso

fun showToast(message: String) {
    Toast.makeText(APP_ACTIVITY, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.replaceActivity(activity: Activity) {
    val intent = Intent(this, activity::class.java)
    startActivity(intent)
    this.finish()
}

fun Fragment.replaceActivity(activity: Activity) {
    val intent = Intent(this.context, activity::class.java)
    startActivity(intent)
}

fun AppCompatActivity.replaceFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .addToBackStack(null)
        .replace(R.id.main_container, fragment)
        .commit()
}

fun Fragment.replaceFragment(fragment: Fragment) {
    fragmentManager?.beginTransaction()
        ?.addToBackStack(null)
        ?.replace(R.id.main_container, fragment)
        ?.commit()
}

fun AppCompatActivity.replaceFragmentAuth(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .addToBackStack(null)
        .replace(R.id.auth_container, fragment)
        .commit()
}

fun Fragment.replaceFragmentAuth(fragment: Fragment) {
    fragmentManager?.beginTransaction()
        ?.addToBackStack(null)
        ?.replace(R.id.auth_container, fragment)
        ?.commit()
}

//Image view function
fun ImageView.downloadAndSetImage(url: String) {
    Picasso.get()
        .load(url)
        .fit()
        .placeholder(R.mipmap.ic_launcher)
        .into(this)
}
