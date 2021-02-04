package br.com.cnnovelty.agenda.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.cnnovelty.agenda.dao.TelefoneDAO;
import br.com.cnnovelty.agenda.database.converter.ConversorCalendar;
import br.com.cnnovelty.agenda.database.converter.ConversorTipoRelefone;
import br.com.cnnovelty.agenda.database.dao.AlunoDAO;
import br.com.cnnovelty.agenda.model.Aluno;
import br.com.cnnovelty.agenda.model.Telefone;

import static br.com.cnnovelty.agenda.database.AgendaMigrations.TODAS_MIGRATIONS;

@Database(entities = {Aluno.class, Telefone.class}, version = 5, exportSchema = false)
@TypeConverters({ConversorCalendar.class, ConversorTipoRelefone.class})
public abstract class AgendaDataBase extends RoomDatabase {

    public static final String NOME_BANCO_DE_DADOS = "agenda.db";
    public abstract AlunoDAO getRoomAlunoDAO();
    public abstract TelefoneDAO getTelefoneDAO();

    public static AgendaDataBase getInstance(Context context) {
        return Room.databaseBuilder(context, AgendaDataBase.class, NOME_BANCO_DE_DADOS)
                .addMigrations(TODAS_MIGRATIONS)
                .build();
    }
}
