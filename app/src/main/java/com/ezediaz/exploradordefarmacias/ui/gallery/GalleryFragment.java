package com.ezediaz.exploradordefarmacias.ui.gallery;
import androidx.annotation.Nullable;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.preference.ListPreference;
import com.ezediaz.exploradordefarmacias.R;
import androidx.preference.PreferenceFragmentCompat;

import java.util.Locale;


public class GalleryFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        // Manejar cambios de preferencia, por ejemplo, el tipo de mapa
        ListPreference mapTypePreference = findPreference("map_type");
        mapTypePreference.setOnPreferenceChangeListener((preference, newValue) -> {
            // Actualizar el tipo de mapa según el nuevo valor
            actualizarTipoDeMapa(newValue.toString());
            return true;
        });

        // Manejar cambios de preferencia, por ejemplo, el idioma
        ListPreference languagePreference = findPreference("language");
        languagePreference.setOnPreferenceChangeListener((preference, newValue) -> {
            // Actualizar el idioma de la aplicación según el nuevo valor
            actualizarIdioma(newValue.toString());
            return true;
        });
    }

    private void actualizarTipoDeMapa(String mapType) {
        // Implementar la lógica para cambiar el tipo de mapa en la aplicación
    }

    private void actualizarIdioma(String language) {
        // Crear un objeto Locale para el nuevo idioma
        Locale locale = new Locale(language);
        // Establecer el nuevo Locale como el predeterminado
        Locale.setDefault(locale);

        // Actualizar la configuración de la aplicación
        Configuration config = new Configuration();
        config.locale = locale;
        getActivity().getResources().updateConfiguration(config, getActivity().getResources().getDisplayMetrics());

        // Opcional: puedes realizar acciones adicionales después de cambiar el idioma
        // por ejemplo, reiniciar la actividad para aplicar los cambios inmediatamente.
        getActivity().recreate(); // Esto reiniciará la actividad
    }

}