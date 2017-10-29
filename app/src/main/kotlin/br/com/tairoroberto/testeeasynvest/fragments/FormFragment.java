package br.com.tairoroberto.testeeasynvest.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import br.com.tairoroberto.testeeasynvest.AppComponent;
import br.com.tairoroberto.testeeasynvest.CustomApplication;
import br.com.tairoroberto.testeeasynvest.R;
import br.com.tairoroberto.testeeasynvest.activities.MainActivity;
import br.com.tairoroberto.testeeasynvest.domain.Cell;
import br.com.tairoroberto.testeeasynvest.presenters.FormPresenter;
import br.com.tairoroberto.testeeasynvest.views.CustomButton;
import br.com.tairoroberto.testeeasynvest.views.CustomImageView;
import br.com.tairoroberto.testeeasynvest.views.FormView;
import br.com.tairoroberto.testeeasynvest.views.ViewHandler;
import br.com.tairoroberto.testeeasynvest.views.ViewsFactory;

public class FormFragment extends Fragment implements FormView {

    private static final int LOAD_PICTURE = 1;

    public static FormFragment newInstance() {
        return new FormFragment();
    }

    LinearLayout linearLayout;

    @Inject
    FormPresenter presenter;

    private ImageView imageView;

    private List<ViewHandler<?>> handlers;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CustomApplication application = (CustomApplication) getContext().getApplicationContext();

        AppComponent appComponent = application.getAppComponent();

        appComponent.inject(this);

        presenter.attachView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form, container, false);

        linearLayout = view.findViewById(R.id.linear_layout);
        presenter.getCells();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        presenter.detachView();
    }


    @Override
    public void displayError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LOAD_PICTURE && resultCode == Activity.RESULT_OK) {
            Glide.with(this).load(data.getData()).into(imageView);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    public void setCells(@NotNull List<Cell> cells) {
        ViewsFactory processor = new ViewsFactory(linearLayout);

        handlers = processor.getHandlers(cells);

        Stream.of(handlers).forEach(viewHandler -> {
            if (viewHandler instanceof CustomButton) {
                Button button = ((CustomButton) viewHandler).getView();

                button.setOnClickListener((v) -> {
                    Optional<Boolean> optional = Stream.of(handlers)
                            .filter(ViewHandler::required)
                            .filter(ViewHandler::isVisible)
                            .map(ViewHandler::validate)
                            .filter(b -> !b)
                            .findFirst();

                    optional.ifPresentOrElse(b -> {},
                            () -> {
                                MainActivity activity = (MainActivity) getActivity();

                                activity.success();
                            });
                });
            } else if (viewHandler instanceof CustomImageView) {
                imageView = ((CustomImageView) viewHandler).getView();

                imageView.setOnClickListener((v) -> {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(intent, LOAD_PICTURE);
                });
            }

            linearLayout.addView(viewHandler.getView());
        });
    }
}
