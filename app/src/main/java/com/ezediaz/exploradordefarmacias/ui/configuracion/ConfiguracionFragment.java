package com.ezediaz.exploradordefarmacias.ui.configuracion;

import android.os.Bundle;
import android.content.res.Configuration;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;

import com.ezediaz.exploradordefarmacias.MainActivityViewModel;
import com.ezediaz.exploradordefarmacias.R;

import java.util.Locale;

public class ConfiguracionFragment extends PreferenceFragmentCompat {

    private MainActivityViewModel viewModel;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        viewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);

        // Maneja cambios de preferencia para el tipo de mapa
        ListPreference mapTypePreference = findPreference("map_type");
        if (mapTypePreference != null) {
            mapTypePreference.setOnPreferenceChangeListener((preference, newValue) -> {
                // Actualiza el tipo de mapa en MainActivityViewModel
                viewModel.setMapType(newValue.toString());

                // Navega a MapaFragment y elimina GalleryFragment del historial de navegación
                NavController navController = Navigation.findNavController(requireView());
                navController.navigate(R.id.action_nav_configuracion_to_nav_maps);
                navController.popBackStack(); // Elimina GalleryFragment del historial de navegación

                return true;
            });
        }

        // Maneja cambios de preferencia para el idioma
        ListPreference languagePreference = findPreference("language");
        if (languagePreference != null) {
            languagePreference.setOnPreferenceChangeListener((preference, newValue) -> {
                // Actualiza el idioma en MainActivityViewModel
                viewModel.setLanguage(newValue.toString());

                // Cambia el idioma de la aplicación
                Locale locale = new Locale(newValue.toString());
                Locale.setDefault(locale);

                // Actualiza la configuración de la aplicación
                Configuration config = new Configuration();
                config.setLocale(locale);
                getActivity().getResources().updateConfiguration(config, getActivity().getResources().getDisplayMetrics());

                // Reinicia la actividad para aplicar los cambios de idioma
                requireActivity().recreate();

                return true;
            });
        }
    }
}
