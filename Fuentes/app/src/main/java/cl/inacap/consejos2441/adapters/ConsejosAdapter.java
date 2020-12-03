package cl.inacap.consejos2441.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

import cl.inacap.consejos2441.R;
import cl.inacap.consejos2441.dto.Consejo;

public class ConsejosAdapter extends ArrayAdapter<Consejo> {
    private List<Consejo> consejos;
    private Activity activity;
    public ConsejosAdapter(@NonNull Activity context, int resource, @NonNull List<Consejo> objects) {
        super(context, resource, objects);
        this.consejos = objects;
        this.activity = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.activity.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_consejo,null,true);
        TextView nombreTxt = rowView.findViewById(R.id.nombre_txt);
        TextView consejoTxt = rowView.findViewById(R.id.consejo_txt);
        ImageView imagenCo = rowView.findViewById(R.id.image_co);
        nombreTxt.setText(consejos.get(position).getCharacter());
        consejoTxt.setText(consejos.get(position).getQuote());
        Picasso.get().load(this.consejos.get(position).getImage())
                .resize(350,350)
                .centerCrop()
                .into(imagenCo);

        return rowView;

    }
}
