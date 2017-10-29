package br.com.tairoroberto.testeeasynvest.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonPrimitive;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Type;

import br.com.tairoroberto.testeeasynvest.domain.CellType;
import br.com.tairoroberto.testeeasynvest.modules.GsonModule;

import static junit.framework.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class CellTypeDeserializerTests {

    @Mock
    private Type typeOf;

    @Mock
    private JsonDeserializationContext context;

    private GsonModule.CellTypeDeserializer deserializer = new GsonModule.CellTypeDeserializer();

    @Test
    public void testTypeNonZeroBased() {
        CellType source = CellType.IMAGE;

        CellType result = deserializer.deserialize(new JsonPrimitive(source.ordinal() + 1), typeOf, context);

        assertEquals(source, result);
    }

}
