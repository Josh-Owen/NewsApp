package com.joshowen.newsapp.ui.settings

import android.content.Intent
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.joshowen.newsapp.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.fragment_settings, rootKey)

        val themeMode: SwitchPreference? =
            findPreference(resources.getString(R.string.pref_dark_mode_key))
        themeMode?.setOnPreferenceChangeListener { _, _ ->
            MaterialAlertDialogBuilder(requireContext()).setMessage(
                R.string.settings_switch_theme_warning_body
            ).setPositiveButton(
                R.string.settings_switch_theme_warning_positive
            ) { _, _: Int ->
                val intent: Intent? =
                    requireContext().packageManager.getLaunchIntentForPackage(requireContext().applicationContext.packageName)
                intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }.setNegativeButton(
                R.string.settings_switch_theme_warning_negative
            ) { _, _: Int ->
                themeMode.isChecked = !themeMode.isChecked
            }.setOnCancelListener { themeMode.isChecked = !themeMode.isChecked }
                .setTitle(R.string.settings_switch_theme_warning_title).show()
            true
        }
    }

}