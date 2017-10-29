package br.com.tairoroberto.testeeasynvest.modules

import br.com.tairoroberto.testeeasynvest.domain.CellType
import br.com.tairoroberto.testeeasynvest.domain.TypeField
import com.google.gson.*
import dagger.Module
import dagger.Provides
import java.lang.reflect.Type
import javax.inject.Singleton

@Module
class GsonModule {

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder()
                .registerTypeAdapter(CellType::class.java, CellTypeDeserializer())
                .registerTypeAdapter(TypeField::class.java, TypeFieldDeserializer())
                .create()
    }

    class CellTypeDeserializer : JsonDeserializer<CellType> {

        @Throws(JsonParseException::class)
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): CellType? {
            return if (json is JsonPrimitive) CellType.values()[json.getAsInt() - 1] else null
        }
    }

    class TypeFieldDeserializer : JsonDeserializer<TypeField> {

        @Throws(JsonParseException::class)
        override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): TypeField? {
            return if (json is JsonPrimitive) TypeField.values()[json.getAsInt() - 1] else null
        }
    }
}
