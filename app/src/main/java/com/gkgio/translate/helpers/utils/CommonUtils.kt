package com.gkgio.translate.helpers.utils

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.DialogInterface
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.gkgio.translate.R
import com.gkgio.translate.data.model.KeyValueItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import kotlin.collections.HashMap

fun Activity.snackBar(text: String) = Snackbar.make(this.findViewById(android.R.id.content), text, Snackbar.LENGTH_SHORT)

fun Fragment.snackBar(text: String) {
  val activity = this.activity
  if (activity != null) {
    Snackbar.make(activity.findViewById(android.R.id.content), text, Snackbar.LENGTH_SHORT)
  }
}

fun showErrorAlertDialog(
    activity: Activity?,
    message: String,
    positiveCallback: DialogInterface.OnClickListener) {
  if (activity != null) {
    android.support.v7.app.AlertDialog.Builder(activity)
        .setMessage(message)
        .setPositiveButton(R.string.yes, positiveCallback)
        .create()
        .show()
  } else {
    Log.e(TAG, "Can't show dialog without activity")
  }
}

fun isEmptyString(string: String?): Boolean {
  return string == null || string.isEmpty()
}

fun giveHashMapRandomElement(mapLanguages: HashMap<String, String>): KeyValueItem {
  val languagesList = mapLanguages.keys.toTypedArray()
  val key = languagesList[Random().nextInt(languagesList.size)]
  val value = mapLanguages[key]
  return KeyValueItem(key, value)
}

fun Activity?.hideKeyboard() {
  val view = this?.currentFocus
  if (view != null) {
    val imm = this?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
  }
}

fun Activity?.hideKeyboard(view: View?) {
  if (view != null) {
    val imm = this?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
  }
}

fun convertToJson(value: Any?): String {
  return Gson().toJson(value)
}

fun hashMapFromJson(value: String): HashMap<String, String> {
  val hashMapType = object : TypeToken<HashMap<String, String>>() {}.type
  return Gson().fromJson<HashMap<String, String>>(value, hashMapType)
}

fun keyValueItemsFromJson(value: String): List<KeyValueItem> {
  val keyValueListType = object : TypeToken<List<KeyValueItem>>() {}.type
  return Gson().fromJson<List<KeyValueItem>>(value, keyValueListType)
}
