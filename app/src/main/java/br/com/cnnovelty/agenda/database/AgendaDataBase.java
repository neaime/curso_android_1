package br.com.cnnovelty.agenda.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.cnnovelty.agenda.database.converter.ConversorCalendar;
import br.com.cnnovelty.agenda.model.Aluno;
import br.com.cnnovelty.agenda.database.dao.RoomAlunoDAO;

import static br.com.cnnovelty.agenda.database.AgendaMigrations.TODAS_MIGRATIONS;

@Database(entities = {Aluno.class}, version = 3, exportSchema = false)
@TypeConverters({ConversorCalendar.class})
public abstract class AgendaDataBase extends RoomDatabase {

    public static final String NOME_BANCO_DE_DADOS = "agenda.db";

    public abstract RoomAlunoDAO getRoomAlunoDAO();

    public static AgendaDataBase getInstance(Context context) {
        return Room.databaseBuilder(context, AgendaDataBase.class, NOME_BANCO_DE_DADOS)
                .allowMainThreadQueries()
                .addMigrations(TODAS_MIGRATIONS)
                .build();
    }
}
