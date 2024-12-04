package com.example.rickandmorty.fragments

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import com.example.rickandmorty.databinding.FragmentSettingsBinding
import com.example.rickandmorty.models.RickMorty
import com.example.rickandmorty.repository.RickMortyRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileWriter

private val Context.dataStore by preferencesDataStore(name = "app_settings")

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.IO).launch {
            updateFileStatus()
        }

        CoroutineScope(Dispatchers.Main).launch {
            val isDarkMode = loadThemeFromDataStore()
            binding.themeSwitch.isChecked = isDarkMode
            applyTheme(isDarkMode)
        }

        binding.themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            CoroutineScope(Dispatchers.IO).launch {
                saveThemeToDataStore(isChecked)
            }
            applyTheme(isChecked)
        }

        binding.deleteButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                deleteFile(requireContext())
                updateFileStatus()
            }
        }

        binding.restoreButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                restoreFile(requireContext())
                updateFileStatus()
            }
        }

        binding.saveCharactersButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val characters = loadCharactersFromRepository()
                saveCharactersToFile(requireContext(), characters)
                updateFileStatus()
            }
        }
    }

    private fun applyTheme(isDarkMode: Boolean) {
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        )
    }

    private suspend fun saveThemeToDataStore(isDarkMode: Boolean) {
        requireContext().dataStore.edit { preferences ->
            preferences[DARK_MODE_KEY] = isDarkMode
        }
    }

    private suspend fun loadThemeFromDataStore(): Boolean {
        val preferences = requireContext().dataStore.data.first()
        return preferences[DARK_MODE_KEY] ?: false
    }

    private suspend fun saveCharactersToFile(context: Context, characters: List<RickMorty>) {
        if (characters.isEmpty()) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Список персонажей пуст", Toast.LENGTH_SHORT).show()
            }
            return
        }
        val fileName = "Heroes_List_5.txt"
        val file = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
            fileName
        )
        try {
            FileWriter(file).use { writer ->
                characters.forEach { character ->
                    writer.write(
                        "ID: ${character.id}, Name: ${character.name}, " +
                                "Status: ${character.status}, Species: ${character.species}, " +
                                "Gender: ${character.gender}, Origin: ${character.origin.name}, " +
                                "Location: ${character.location.name}\n"
                    )
                }
            }
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Файл успешно сохранен: ${file.absolutePath}", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Ошибка сохранения файла: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private suspend fun deleteFile(context: Context) {
        val fileName = "Heroes_List_5.txt"
        val file = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
            fileName
        )
        if (file.exists() && file.delete()) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Файл удален: ${file.absolutePath}", Toast.LENGTH_SHORT).show()
            }
        } else {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Ошибка удаления файла", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private suspend fun restoreFile(context: Context) {
        val fileName = "Heroes_List_5.txt"
        val externalFile = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
            fileName
        )
        val internalFile = File(context.filesDir, fileName)

        if (internalFile.exists()) {
            withContext(Dispatchers.IO) {
                internalFile.copyTo(externalFile, overwrite = true)
            }
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Файл восстановлен во внешнем хранилище", Toast.LENGTH_SHORT).show()
            }
        } else {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Резервная копия не найдена", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun isFileExists(): Boolean {
        val fileName = "Heroes_List_5.txt"
        val file = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
            fileName
        )
        return file.exists()
    }

    private suspend fun updateFileStatus() {
        val fileExists = isFileExists()
        val statusMessage = if (fileExists) {
            "Файл существует"
        } else {
            "Файл отсутствует"
        }
        withContext(Dispatchers.Main) {
            binding.statusTextView.text = statusMessage
        }
    }

    private suspend fun loadCharactersFromRepository(): List<RickMorty> {
        val repository = RickMortyRepository()
        return try {
            val ids = (201..250).joinToString(",")
            repository.getCharactersByIds(ids)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
