package br.com.cnnovelty.agenda.database.converter;

import androidx.room.TypeConverter;

import br.com.cnnovelty.agenda.model.TipoTelefone;

public class ConversorTipoRelefone {

    @TypeConverter
    public String toString(TipoTelefone tipo) {
        return tipo.name();
    }

    @TypeConverter
    public TipoTelefone toTipoTelefone(String valor) {
        if (valor != null) {
            return TipoTelefone.valueOf(valor);
        }

        return TipoTelefone.FIXO;
    }
}
