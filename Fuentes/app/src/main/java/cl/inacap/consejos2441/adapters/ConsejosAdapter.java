package cl.inacap.consejos2441.adapters;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

import cl.inacap.consejos2441.dto.Consejo;

public class ConsejosAdapter extends ArrayAdapter<Consejo> {
    private List<Consejo> consejos;
    private Activity activity;
    public ConsejosAdapter(@NonNull Activity context, int resource, @NonNull List<Consejo> objects) {
        super(context, resource, objects);
        this.consejos = objects;
        this.activity = context;
    }


}
