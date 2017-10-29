package br.com.tairoroberto.testeeasynvest.handlers;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import br.com.tairoroberto.testeeasynvest.BuildConfig;
import br.com.tairoroberto.testeeasynvest.domain.Cell;
import br.com.tairoroberto.testeeasynvest.views.CustomTextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class TextViewHandlerTests {

    private ViewGroup parent;

    @Before
    public void before() {
        parent = new LinearLayout(RuntimeEnvironment.application);
    }

    @Test
    public void testLayoutIdCantBeZero() {
        Cell cell = mock(Cell.class);

        CustomTextView handler = new CustomTextView(parent, cell);

        assertTrue(handler.getLayoutId() > 0);
    }

    @Test
    public void testInitWithCellData() {
        Cell cell = new Cell();

        cell.setId(2582);

        cell.setMessage("Lorem ipsum dolor sit amet");

        cell.setTopSpacing(100);

        CustomTextView handler = new CustomTextView(parent, cell);

        TextView textView = handler.getView();

        assertEquals(cell.getId(), handler.getId());

        assertEquals(cell.getMessage(), textView.getText().toString());

        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) textView.getLayoutParams();

        assertEquals((int) cell.getTopSpacing(), params.topMargin);
    }

    @Test
    public void testCellHidden() {
        Cell cell = new Cell();

        cell.setHidden(true);

        CustomTextView handler = new CustomTextView(parent, cell);

        TextView textView = handler.getView();

        assertEquals(View.GONE, textView.getVisibility());
    }

}
