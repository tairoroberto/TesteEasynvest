package br.com.tairoroberto.testeeasynvest.handlers;

import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import br.com.tairoroberto.testeeasynvest.BuildConfig;
import br.com.tairoroberto.testeeasynvest.domain.Cell;
import br.com.tairoroberto.testeeasynvest.domain.TypeField;
import br.com.tairoroberto.testeeasynvest.views.CustomFieldView;
import br.com.tairoroberto.testeeasynvest.views.ViewHandler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class CustomFieldViewTests {

    private ViewGroup parent;

    @Before
    public void before() {
        parent = new LinearLayout(RuntimeEnvironment.application);
    }

    @Test
    public void testLayoutIdCantBeZero() {
        Cell cell = mock(Cell.class);

        CustomFieldView handler = new CustomFieldView(parent, cell);

        assertTrue(handler.getLayoutId() > 0);
    }

    @Test
    public void testHint() {
        Cell cell = new Cell();

        cell.setMessage("Hint");

        CustomFieldView handler = new CustomFieldView(parent, cell);

        EditText editText = handler.getView().getEditText();

        if (editText != null) {
            assertEquals(cell.getMessage(), editText.getHint());
        }
    }


    @Test
    public void testValidTelNumber8Digits() {
        Cell cell = new Cell();

        cell.setTypeField(TypeField.TEL_NUMBER);

        CustomFieldView handler = new CustomFieldView(parent, cell);

        EditText editText = handler.getView().getEditText();

        if (editText != null) {
            editText.setText("(11) 11111-111");
        }

        assertTrue(handler.validate());
    }

    @Test
    public void testInvalidTelNumber() {
        Cell cell = new Cell();

        cell.setTypeField(TypeField.TEL_NUMBER);

        CustomFieldView handler = new CustomFieldView(parent, cell);

        EditText editText = handler.getView().getEditText();

        if (editText != null) {
            editText.setText("(11) 1111-111");
        }

        assertFalse(handler.validate());
    }

    @Test
    public void testValidEmail() {
        Cell cell = new Cell();

        cell.setTypeField(TypeField.EMAIL);

        CustomFieldView handler = new CustomFieldView(parent, cell);

        EditText editText = handler.getView().getEditText();

        if (editText != null) {
            editText.setText("tairorobertog@hotmail.com");
        }

        assertTrue(handler.validate());
    }

    @Test
    public void testInvalidEmail() {
        Cell cell = new Cell();

        cell.setTypeField(TypeField.EMAIL);

        CustomFieldView handler = new CustomFieldView(parent, cell);

        EditText editText = handler.getView().getEditText();

        if (editText != null) {
            editText.setText("poifhdpaafh.com");
        }

        assertFalse(handler.validate());
    }

    @Test
    public void testShow() {
        Cell cell = new Cell();

        CustomFieldView handler = new CustomFieldView(parent, cell);

        ViewHandler<?> viewHandler = mock(ViewHandler.class);

        handler.configureShow(viewHandler);

        EditText editText = handler.getView().getEditText();

        if (editText != null) {
            editText.callOnClick();
        }

        verify(viewHandler).setVisible(true);
    }

    @Test
    public void testValidTelNumber() {
        Cell cell = new Cell();

        cell.setTypeField(TypeField.TEL_NUMBER);

        CustomFieldView handler = new CustomFieldView(parent, cell);

        EditText editText = handler.getView().getEditText();

        if (editText != null) {
            editText.setText("(11) 11111-1111");
        }

        assertTrue(handler.validate());
    }

}
