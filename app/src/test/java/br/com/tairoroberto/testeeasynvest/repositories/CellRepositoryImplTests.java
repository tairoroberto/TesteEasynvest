package br.com.tairoroberto.testeeasynvest.repositories;

import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import br.com.tairoroberto.testeeasynvest.domain.Cell;
import br.com.tairoroberto.testeeasynvest.domain.CellType;
import br.com.tairoroberto.testeeasynvest.domain.TypeField;
import br.com.tairoroberto.testeeasynvest.modules.GsonModule;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CellRepositoryImplTests {

    @Mock
    private AssetManager assetManager;

    private Gson gson;

    @Before
    public void before() {
        gson = new GsonBuilder()
                .registerTypeAdapter(CellType.class, new GsonModule.CellTypeDeserializer())
                .registerTypeAdapter(TypeField.class, new GsonModule.TypeFieldDeserializer())
                .create();
    }

    @Test
    public void testFormattedJson() throws IOException {
        InputStream data = new ByteArrayInputStream("{\"cells\":[{\"id\":1,\"type\":2,\"message\":\"Olá, primeiro se apresente com o seu nome:\",\"typefield\":null,\"hidden\":false,\"topSpacing\":60,\"show\":null,\"required\":false},{\"id\":2,\"type\":1,\"message\":\"Nome completo\",\"typefield\":1,\"hidden\":false,\"topSpacing\":35,\"show\":null,\"required\":true},{\"id\":4,\"type\":1,\"message\":\"Email\",\"typefield\":3,\"hidden\":true,\"topSpacing\":35,\"show\":null,\"required\":true},{\"id\":6,\"type\":1,\"message\":\"Telefone\",\"typefield\":2,\"hidden\":false,\"topSpacing\":10,\"show\":null,\"required\":true},{\"id\":5,\"type\":3,\"message\":\"http://kitten-picture\",\"typefield\":null,\"hidden\":false,\"topSpacing\":10,\"show\":null,\"required\":false},{\"id\":3,\"type\":4,\"message\":\"Gostaria de cadastrar meu email\",\"typefield\":null,\"hidden\":false,\"topSpacing\":35,\"show\":4,\"required\":false},{\"id\":7,\"type\":5,\"message\":\"Enviar\",\"typefield\":null,\"hidden\":false,\"topSpacing\":10,\"show\":null,\"required\":true}]}".getBytes("UTF-8"));

        when(assetManager.open("cells.json")).thenReturn(data);

        CellRepositoryImpl impl = new CellRepositoryImpl(assetManager, gson);

        Observable<List<Cell>> observable = impl.list();

        observable.subscribe((cells -> {
            assertNotNull(cells);

            assertNotNull(cells);

            assertEquals(7, cells.size());

            Cell first = cells.get(0);

            assertEquals(CellType.TEXT, first.getType());

            Cell second = cells.get(1);

            assertEquals(CellType.FIELD, second.getType());
        }));
    }

    @Test
    public void testReadError() throws IOException {
        InputStream data = new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException();
            }
        };

        when(assetManager.open("cells.json")).thenReturn(data);

        CellRepositoryImpl impl = new CellRepositoryImpl(assetManager, gson);

        TestObserver<List<Cell>> testObserver = new TestObserver<>();

        impl.list().subscribe(testObserver);

        testObserver.assertError(JsonSyntaxException.class);
    }

    @Test
    public void testErrorJson() throws IOException {
        InputStream data = new ByteArrayInputStream("{\"cells\":[{\"id\":1,\"type\":2,\"message\":\"Olá, primeiro se apresente com o seu nome:\",\"typefield\":null,\"hidden\":false,\"topSpacing\":60,\"show\":null,\"required\":false},{\"id\":2,\"type\":1,\"message\":\"Nome completo\",\"typefield\":1,\"hidden\":false,\"topSpacing\":35,\"show\":null,\"required\":true},{\"id\":4,\"type\":1,\"message\":\"Email\",\"typefield\":3,\"hidden\":true,\"topSpacing\":35,\"show\":null,\"required\":true},{\"id\":6,\"type\":1,\"message\":\"Telefone\",\"typefield\":2,\"hidden\":false,\"topSpacing\":10,\"show\":null,\"required\":true},{\"id\":5,\"type\":3,\"message\":\"http://kitten-picture\",\"typefield\":null\"hidden\":false,\"topSpacing\":10,\"show\":null,\"required\":false},{\"id\":3,\"type\":4,\"message\":\"Gostaria de cadastrar meu email\",\"typefield\":null,\"hidden\":false,\"topSpacing\":35,\"show\":4,\"required\":false},{\"id\":7,\"type\":5,\"message\":\"Enviar\",\"typefield\":null,\"hidden\":false,\"topSpacing\":10,\"show\":null,\"required\":true}}".getBytes("UTF-8"));

        when(assetManager.open("cells.json")).thenReturn(data);

        CellRepositoryImpl impl = new CellRepositoryImpl(assetManager, gson);

        TestObserver<List<Cell>> testObserver = new TestObserver<>();

        impl.list().subscribe(testObserver);

        testObserver.assertError(NumberFormatException.class);
    }


}
