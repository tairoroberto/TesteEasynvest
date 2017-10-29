package br.com.tairoroberto.testeeasynvest.presenters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import br.com.tairoroberto.testeeasynvest.domain.Cell;
import br.com.tairoroberto.testeeasynvest.repositories.CellRepository;
import br.com.tairoroberto.testeeasynvest.views.FormView;
import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FormPresenterTests {

    @Mock
    private CellRepository repository;

    @Mock
    private FormView view;

    private FormPresenter presenter;

    @Before
    public void setup() {
        presenter = new FormPresenter(repository);

        presenter.attachView(view);
    }

    @Test
    public void testGetCellsWithDetachedView() {
        presenter.detachView();

        presenter.getCells();

        verify(repository, never()).list();

        verify(view, never()).setCells(any());

        verify(view, never()).displayError(any());
    }

    @Test
    public void testGetCells() {
        Cell cell = new Cell();

        cell.setId(10);

        List<Cell> source = Collections.singletonList(cell);

        when(repository.list()).thenReturn(Observable.just(source));

        presenter.getCells();

        verify(repository).list();

        verify(view).setCells(any());

        verify(view, never()).displayError(any());
    }

    @Test
    public void testGetCellsError() {
        when(repository.list()).thenReturn(Observable.error(new Exception()));

        presenter.getCells();

        verify(repository).list();

        verify(view).displayError(any());

        verify(view, never()).setCells(any());
    }

}
