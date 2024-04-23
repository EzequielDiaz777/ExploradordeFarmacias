package com.ezediaz.exploradordefarmacias.ui.configuracion;
import android.os.Bundle;
import android.content.res.Configuration;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;
import com.ezediaz.exploradordefarmacias.MainActivityViewModel;
import com.ezediaz.exploradordefarmacias.R;
import java.util.Locale;
public class ConfiguracionFragment extends PreferenceFragmentCompat {
    private MainActivityViewModel vm;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
        vm = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        ListPreference mapTypePreference = findPreference("tipoDeMapa");

        if (mapTypePreference != null) {
            mapTypePreference.setOnPreferenceChangeListener((preference, newValue) -> {
                vm.setTipoDeMapa(newValue.toString());
                return true;
            });
        }
        ListPreference languagePreference = findPreference("idioma");
        if (languagePreference != null) {
            languagePreference.setOnPreferenceChangeListener((preference, newValue) -> {
                vm.setMIdioma(newValue.toString());
                Locale locale = new Locale(newValue.toString());
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.setLocale(locale);
                getActivity().getResources().updateConfiguration(config, getActivity().getResources().getDisplayMetrics());
                requireActivity().recreate();
                return true;
            });
        }
    }
}
