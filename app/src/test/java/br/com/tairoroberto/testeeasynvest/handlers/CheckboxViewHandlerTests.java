package br.com.tairoroberto.testeeasynvest.handlers;

import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import br.com.tairoroberto.testeeasynvest.BuildConfig;
import br.com.tairoroberto.testeeasynvest.domain.Cell;
import br.com.tairoroberto.testeeasynvest.views.CustomCheckbox;
import br.com.tairoroberto.testeeasynvest.views.ViewHandler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class CheckboxViewHandlerTests {

    private ViewGroup parent;

    @Before
    public void before() {
        parent = new LinearLayout(RuntimeEnvironment.application);
    }

    @Test
    public void testLayoutIdCantBeZero() {
        Cell cell = mock(Cell.class);

        CustomCheckbox handler = new CustomCheckbox(parent, cell);

        assertTrue(handler.getLayoutId() > 0);
    }

    @Test
    public void testCheckboxAction() {
        Cell cell = new Cell();

        cell.setMessage("Checkbox");

        cell.setShow(2);

        CustomCheckbox handler = new CustomCheckbox(parent, cell);

        CheckBox checkBox = handler.getView();

        assertEquals(cell.getMessage(), checkBox.getText());

        ViewHandler<?> sibling = mock(ViewHandler.class);

        handler.configureShow(sibling);

        checkBox.setChecked(true);

        verify(sibling).setVisible(true);
    }

    @Test
    public void testCheckboxValidation() {
        Cell cell = new Cell();

        cell.setRequired(true);

        CustomCheckbox handler = new CustomCheckbox(parent, cell);

        assertFalse(handler.validate());
    }

}
