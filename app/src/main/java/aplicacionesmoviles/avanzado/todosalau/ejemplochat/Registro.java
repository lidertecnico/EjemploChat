package aplicacionesmoviles.avanzado.todosalau.ejemplochat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;

import aplicacionesmoviles.avanzado.todosalau.ejemplochat.Presenter.RegistroPresenter;
import aplicacionesmoviles.avanzado.todosalau.ejemplochat.model.RegistroModel;
import aplicacionesmoviles.avanzado.todosalau.ejemplochat.view.RegistroContract;
/**
 * Esta clase representa la actividad de registro de la aplicación.
 */
public class Registro extends AppCompatActivity implements RegistroContract.View {

    // Componentes de la interfaz de usuario
    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button btnRegister;
    private TextView textViewLogin;

    // Presentador
    private RegistroPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Inicialización de componentes de la interfaz de usuario
        editTextName = findViewById(R.id.usernameEditText);
        editTextEmail = findViewById(R.id.emailEditText);
        editTextPassword = findViewById(R.id.passwordEditText);
        btnRegister = findViewById(R.id.registerButton);
        textViewLogin = findViewById(R.id.loginText);

        // Creación del presentador
        presenter = new RegistroPresenter(this, new RegistroModel());

        // Configuración de los listeners
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.registerUser();
            }
        });

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLogin();
            }
        });
    }

    @Override
    public void showToast(String message) {
        // Mostrar un mensaje de tostada
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearInputFields() {
        // Limpiar los campos de entrada
        editTextName.setText("");
        editTextEmail.setText("");
        editTextPassword.setText("");
    }

    @Override
    public void navigateToLogin() {
        // Navegar a la actividad de inicio de sesión
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }

    @Override
    public String getName() {
        // Obtener el nombre ingresado por el usuario
        return editTextName.getText().toString().trim();
    }

    @Override
    public String getEmail() {
        // Obtener el correo electrónico ingresado por el usuario
        return editTextEmail.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        // Obtener la contraseña ingresada por el usuario
        return editTextPassword.getText().toString().trim();
    }
}