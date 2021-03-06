package br.com.cnnovelty.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.cnnovelty.agenda.R;
import br.com.cnnovelty.agenda.asynctask.BuscaPrimeiroTelefoneDoAlunoTask;
import br.com.cnnovelty.agenda.dao.TelefoneDAO;
import br.com.cnnovelty.agenda.database.AgendaDataBase;
import br.com.cnnovelty.agenda.model.Aluno;

public class ListaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;
    private final TelefoneDAO dao;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
        dao = AgendaDataBase.getInstance(context).getTelefoneDAO();
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View viewCriada = criaView(viewGroup);
        Aluno alunoDevolvido = alunos.get(position);
        vincula(viewCriada, alunoDevolvido);
        return viewCriada;
    }

    private void vincula(View view, Aluno aluno) {
        TextView nome = view.findViewById(R.id.item_aluno_nome);
        nome.setText(aluno.getNomeCompleto() + " " + aluno.dataFormatada());
        TextView telefone = view.findViewById(R.id.item_aluno_telefone);
        new BuscaPrimeiroTelefoneDoAlunoTask(dao, aluno.getId(),  telefoneEncontrado ->
            telefone.setText(telefoneEncontrado.getNumero())).execute();
    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater.from(context)
                .inflate(R.layout.item_aluno, viewGroup, false);
    }

    public void atualiza(List<Aluno> alunos) {
        this.alunos.clear();
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        notifyDataSetChanged();
    }
}
