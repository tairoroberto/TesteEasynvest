package br.com.tairoroberto.testeeasynvest.handlers;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import br.com.tairoroberto.testeeasynvest.BuildConfig;
import br.com.tairoroberto.testeeasynvest.domain.Cell;
import br.com.tairoroberto.testeeasynvest.views.CustomButton;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class SendViewHandlerTests {

    private ViewGroup parent;

    @Before
    public void before() {
        parent = new LinearLayout(RuntimeEnvironment.application);
    }

    @Test
    public void testLayoutIdCantBeZero() {
        Cell cell = mock(Cell.class);

        CustomButton handler = new CustomButton(parent, cell);

        assertTrue(handler.getLayoutId() > 0);
    }

}
