package br.com.tairoroberto.testeeasynvest.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonPrimitive;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Type;

import br.com.tairoroberto.testeeasynvest.domain.TypeField;
import br.com.tairoroberto.testeeasynvest.modules.GsonModule;

import static junit.framework.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TypeFieldDeserializerTests {

    @Mock
    private Type typeOf;

    @Mock
    private JsonDeserializationContext context;

    private GsonModule.TypeFieldDeserializer deserializer = new GsonModule.TypeFieldDeserializer();

    @Test
    public void testTypeNonZeroBased() {
        TypeField source = TypeField.TEL_NUMBER;

        TypeField result = deserializer.deserialize(new JsonPrimitive(source.ordinal() + 1), typeOf, context);

        assertEquals(source, result);
    }

}
