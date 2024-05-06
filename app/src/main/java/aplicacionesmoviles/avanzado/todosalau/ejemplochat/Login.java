package aplicacionesmoviles.avanzado.todosalau.ejemplochat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import aplicacionesmoviles.avanzado.todosalau.ejemplochat.Presenter.LoginPresenter;
import aplicacionesmoviles.avanzado.todosalau.ejemplochat.model.LoginModel;
import aplicacionesmoviles.avanzado.todosalau.ejemplochat.view.LoginContract;

/**
 * Esta clase representa la actividad de inicio de sesión de la aplicación.
 */
public class Login extends AppCompatActivity implements LoginContract.View {

        // Componentes de la interfaz de usuario
        private EditText editTextEmail;
        private EditText editTextPassword;
        private Button btnLogin;
        private TextView registerText;

        // Presentador
        private LoginPresenter presenter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_login);

                // Inicialización de componentes de la interfaz de usuario
                editTextEmail = findViewById(R.id.emailEditTextLogin);
                editTextPassword = findViewById(R.id.passwordEditTextLogin);
                btnLogin = findViewById(R.id.loginButton);
                registerText = findViewById(R.id.registerText);

                // Creación del presentador
                presenter = new LoginPresenter(this, new LoginModel());

                // Configuración del listener del botón de inicio de sesión
                btnLogin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                presenter.loginUser();
                        }
                });

                // Configuración del listener para el texto de registro
                registerText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                navigateToRegistration();
                        }
                });
        }

        @Override
        public void showToast(String message) {
                // Mostrar un mensaje de tostada
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void navigateToHome() {
                // Navegar a la actividad principal después de iniciar sesión
                Intent intent = new Intent(this, Chat.class);
                startActivity(intent);
                finish();
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

        /**
         * Método para navegar a la actividad de registro.
         */
        private void navigateToRegistration() {
                Intent intent = new Intent(Login.this, Registro.class);
                startActivity(intent);
                finish();
        }
}