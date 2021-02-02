package br.com.cnnovelty.agenda.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.cnnovelty.agenda.model.Aluno;

@Dao
public interface RoomAlunoDAO {
    @Insert
    void salva(Aluno aluno);

    @Query("SELECT * FROM Aluno")
    List<Aluno> getAlunos();

    @Delete
    void remove(Aluno aluno);

    @Update
    void edita(Aluno aluno);
}
