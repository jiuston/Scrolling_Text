package jesuitas.dam.scrollingtext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView articulo;
    TextView subtitulo;
    private ActionMode mActionmode;
    private ActionMode.Callback mActionModeCallBack= new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {

            MenuInflater inflater=getMenuInflater();
            inflater.inflate(R.menu.menu_contet_barra,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.edit_barra:
                    displayToast("Editar de la barra");
                    return true;
                case R.id.Share_barra:
                    displayToast("Share de la barra");
                    return true;
                //aqiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            displayToast(getString(R.string.saliendo));
            mActionmode=null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Asigno el menu contextual a esta View
        articulo=findViewById(R.id.article);
        registerForContextMenu(articulo);
        subtitulo=findViewById(R.id.article_subheading);
        subtitulo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mActionmode!=null) return false;

                mActionmode= MainActivity.this.startActionMode(mActionModeCallBack);
                v.setSelected(true);
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit:
                displayToast(getString(R.string.edit_text));
                return true;
            case R.id.Share:
                displayToast(getString(R.string.share_text));
                return true;
            case R.id.Delete:
                displayToast(getString(R.string.delete_text));
            default:
                return super.onContextItemSelected(item);
        }

    }

     public void displayToast(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
     }
}