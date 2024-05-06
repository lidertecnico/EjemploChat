package aplicacionesmoviles.avanzado.todosalau.ejemplochat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import aplicacionesmoviles.avanzado.todosalau.ejemplochat.R;
import aplicacionesmoviles.avanzado.todosalau.ejemplochat.model.MessageModel;

public class MessageAdapter extends ArrayAdapter<MessageModel> {

    private Context mContext;
    private List<MessageModel> mMessages;

    // Constructor de la clase MessageAdapter
    public MessageAdapter(Context context, List<MessageModel> messages) {
        super(context, 0, messages);
        mContext = context;
        mMessages = messages;
    }

    // Método llamado por ListView para obtener la vista de cada elemento en la lista
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            // Si no hay una vista reutilizable, inflarla desde el layout XML
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item_message, parent, false);
        }

        // Obtener el mensaje actual en la posición especificada
        MessageModel currentMessage = mMessages.get(position);

        // Obtener referencias a los TextView en el layout del mensaje
        TextView senderNameTextView = listItem.findViewById(R.id.senderNameTextView);
        TextView messageTextView = listItem.findViewById(R.id.messageTextView);
        TextView timestampTextView = listItem.findViewById(R.id.timestampTextView);

        // Establecer los valores de los TextView con los datos del mensaje actual
        senderNameTextView.setText(currentMessage.getSenderEmail());
        messageTextView.setText(currentMessage.getMessageText());

        // Formatear y establecer el timestamp en el TextView correspondiente
        String formattedTimestamp = formatTimestamp(currentMessage.getTimestamp());
        timestampTextView.setText(formattedTimestamp);

        // Devolver la vista completa para mostrarla en ListView
        return listItem;
    }

    // Método para formatear el timestamp según un formato específico
    private String formatTimestamp(Date timestamp) {
        // Utilizar SimpleDateFormat para formatear el timestamp a una cadena legible
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        return dateFormat.format(timestamp);
    }
}