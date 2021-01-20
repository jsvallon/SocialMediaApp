package com.jsv.socialmediaapp.view.binding.login

import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import javax.inject.Inject

class FragmentBindingLogin @Inject constructor(val fragment: Fragment) {

    @BindingAdapter("setRequestInfo")
    fun bindEmail(editText: EditText, txt: String?) {
        editText.setText(txt)
    }
}