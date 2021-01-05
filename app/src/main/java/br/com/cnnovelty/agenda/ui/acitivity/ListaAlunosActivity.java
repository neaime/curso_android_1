package br.com.cnnovelty.agenda.ui.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.cnnovelty.agenda.R;
import br.com.cnnovelty.agenda.model.Aluno;
import br.com.cnnovelty.agenda.ui.ListaAlunosView;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Alunos";
    private final ListaAlunosView listaAlunosView = new ListaAlunosView(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        configuraBtnAdicionar();
        configuraLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_aluno_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_alunos_menu_remover) {
            listaAlunosView.confirmaRemocao(item);
        }
        return super.onContextItemSelected(item);
    }

    private void configuraBtnAdicionar() {
        FloatingActionButton btnNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        btnNovoAluno.setOnClickListener(v -> abreFormularioModoInsereAluno());
    }

    private void abreFormularioModoInsereAluno() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaAlunosView.atualizaAlunos();
    }


    private void configuraLista() {
        ListView listaAlunos = findViewById(R.id.activity_lista_alunos_list_view);
        listaAlunosView.configuraAdapter(listaAlunos);
        configuraListenerDeClickPorItem(listaAlunos);
        registerForContextMenu(listaAlunos);
    }

    private void configuraListenerDeClickPorItem(ListView listaAlunos) {
        listaAlunos.setOnItemClickListener((parent, view, position, id) -> {
            Aluno alunoEscolhido = (Aluno) parent.getItemAtPosition(position);
            abreFormularioModoEditAluno(alunoEscolhido);
        });
    }

    private void abreFormularioModoEditAluno(Aluno aluno) {
        Intent vaiParaFormulario = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        vaiParaFormulario.putExtra(ConstantesActivities.CHAVE_ALUNO, aluno);
        startActivity(vaiParaFormulario);
    }
}
