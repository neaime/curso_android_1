package br.com.cnnovelty.agenda;

import android.app.Application;

import br.com.cnnovelty.agenda.dao.AlunoDAO;
import br.com.cnnovelty.agenda.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunoTeste();
    }

    private void criaAlunoTeste() {
        AlunoDAO dao = new AlunoDAO();
        dao.salva(new Aluno("Felipe Neaime", "123456789", "fneaime@gmail.com"));
        dao.salva(new Aluno("Marina Pavani", "987654321", "marina@gmail.com"));
    }
}
