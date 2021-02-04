package br.com.cnnovelty.agenda.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.cnnovelty.agenda.dao.TelefoneDAO;
import br.com.cnnovelty.agenda.model.Aluno;
import br.com.cnnovelty.agenda.model.Telefone;

public class BuscaTodosTelefonesAlunoTask extends AsyncTask<Void, Void, List<Telefone>> {

    private final TelefoneDAO telefoneDAO;
    private final Aluno aluno;
    private final TelefonesDoAlunoEncontradoListener listener;

    public BuscaTodosTelefonesAlunoTask(TelefoneDAO telefoneDAO, Aluno aluno, TelefonesDoAlunoEncontradoListener listener) {
        this.telefoneDAO = telefoneDAO;
        this.aluno = aluno;
        this.listener = listener;
    }

    @Override
    protected List<Telefone> doInBackground(Void... voids) {
        return telefoneDAO.buscaTodosTelefonesAluno(aluno.getId());
    }


    @Override
    protected void onPostExecute(List<Telefone> telefones) {
        super.onPostExecute(telefones);
        listener.quandoEncontrados(telefones);
    }

    public interface TelefonesDoAlunoEncontradoListener {
        void quandoEncontrados(List<Telefone> telefones);
    }
}
