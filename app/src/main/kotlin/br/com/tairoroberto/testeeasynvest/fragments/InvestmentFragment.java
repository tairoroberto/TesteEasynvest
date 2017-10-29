package br.com.tairoroberto.testeeasynvest.fragments;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.tairoroberto.testeeasynvest.AppComponent;
import br.com.tairoroberto.testeeasynvest.CustomApplication;
import br.com.tairoroberto.testeeasynvest.R;
import br.com.tairoroberto.testeeasynvest.domain.FundInfo;
import br.com.tairoroberto.testeeasynvest.domain.Graph;
import br.com.tairoroberto.testeeasynvest.domain.Info;
import br.com.tairoroberto.testeeasynvest.domain.Investment;
import br.com.tairoroberto.testeeasynvest.domain.MoreInfo;
import br.com.tairoroberto.testeeasynvest.presenters.InvestmentPresenter;
import br.com.tairoroberto.testeeasynvest.views.InvestmentView;

public class InvestmentFragment extends Fragment implements InvestmentView {

    private static final float LINE_WIDTH = 4;

    private static final float CIRCLE_HOLE_RADIUS = 6;

    private static final int MAX_RISK = 5;

    private static final long ANIMATION_DURATION = 2000;

    @Inject
    InvestmentPresenter presenter;

    LinearLayout content;

    TextView title;

    ImageView shareIcon;

    TextView fundName;

    TextView whatIs;

    TextView definition;

    LineChart lineChart;

    TextView riskTitle;

    ProgressBar risk;

    TextView fundInMonth;

    TextView cdiInMonth;

    TextView fundInYear;

    TextView cdiInYear;

    TextView fundIn12Months;

    TextView cdiIn12Months;

    LinearLayout riskContent;
    LinearLayout risk_1;
    LinearLayout risk_2;
    LinearLayout risk_3;
    LinearLayout risk_4;
    LinearLayout risk_5;


    public static InvestmentFragment newInstance() {
        return new InvestmentFragment();
    }

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
        View view = inflater.inflate(R.layout.fragment_investment, container, false);

        content = view.findViewById(R.id.content);
        title = view.findViewById(R.id.title);
        shareIcon = view.findViewById(R.id.share_icon);
        fundName = view.findViewById(R.id.fund_name);
        whatIs = view.findViewById(R.id.what_is);
        definition = view.findViewById(R.id.definition);
        lineChart = view.findViewById(R.id.line_chart);
        fundInMonth = view.findViewById(R.id.fund_in_month);
        cdiInMonth = view.findViewById(R.id.cdi_in_month);
        fundInYear = view.findViewById(R.id.fund_in_year);
        cdiInYear = view.findViewById(R.id.cdi_in_year);
        fundIn12Months = view.findViewById(R.id.fund_in_12_months);
        cdiIn12Months = view.findViewById(R.id.cdi_in_12_months);

        riskTitle = view.findViewById(R.id.risk_title);
        riskContent = view.findViewById(R.id.riskContent);
        risk_1 = view.findViewById(R.id.risk_1);
        risk_2 = view.findViewById(R.id.risk_2);
        risk_3 = view.findViewById(R.id.risk_3);
        risk_4 = view.findViewById(R.id.risk_4);
        risk_5 = view.findViewById(R.id.risk_5);

        Glide.with(this).load(R.drawable.share).into(shareIcon);

        presenter.getFund();

        Button button = new Button(getActivity());
        button.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.button_background));
        button.setText(R.string.investment);
        button.setTextColor(ContextCompat.getColor(getActivity(), R.color.textColorButton));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, (int) Math.ceil(20 * getActivity().getResources().getDisplayMetrics().density), 0, (int) Math.ceil(30 * getActivity().getResources().getDisplayMetrics().density));
        button.setLayoutParams(layoutParams);

        content.addView(button);

        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        presenter.detachView();
    }

    @Override
    public void setFund(@NonNull Investment investment) {
        title.setText(investment.getTitle());

        fundName.setText(investment.getFundName());

        whatIs.setText(investment.getWhatIs());

        definition.setText(investment.getDefinition());

        Graph graph = investment.getGraph();

        setupChart(graph);

        riskTitle.setText(investment.getRiskTitle());

        setInvestimentRisk(investment.getRisk());

        MoreInfo moreInfo = investment.getMoreInfo();

        FundInfo infoMonth = null;
        if (moreInfo != null) {
            infoMonth = moreInfo.getMonth();
        }

        if (infoMonth != null) {
            fundInMonth.setText(String.valueOf(infoMonth.getFund()));
        }

        if (infoMonth != null) {
            cdiInMonth.setText(String.valueOf(infoMonth.getCdi()));
        }

        FundInfo infoYear = null;
        if (moreInfo != null) {
            infoYear = moreInfo.getYear();
        }

        if (infoYear != null) {
            fundInYear.setText(String.valueOf(infoYear.getFund()));
        }

        if (infoYear != null) {
            cdiInYear.setText(String.valueOf(infoYear.getCdi()));
        }

        FundInfo info12Months = null;
        if (moreInfo != null) {
            info12Months = moreInfo.getTwelveMonths();
        }

        if (info12Months != null) {
            fundIn12Months.setText(String.valueOf(info12Months.getFund()));
        }

        if (info12Months != null) {
            cdiIn12Months.setText(String.valueOf(info12Months.getCdi()));
        }

        if (investment.getInfo() != null) {
            List<Info> infos = new ArrayList<>(investment.getInfo());

            if (investment.getDownInfo() != null) {
                infos.addAll(investment.getDownInfo());
            }

            setupInfos(infos);
        }
    }

    private void setupInfos(List<Info> infos) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        for (Info info : infos) {
            View child = inflater.inflate(R.layout.info, content, false);

            TextView name = (TextView) child.findViewById(R.id.name);

            TextView data = (TextView) child.findViewById(R.id.data);

            name.setText(info.getName());

            Object infoData = info.getData();

            if (infoData != null) {
                infoData = String.valueOf(infoData);
            }

            data.setText(infoData != null ? String.valueOf(infoData) : getString(R.string.download));

            data.setTextColor(ContextCompat.getColor(getContext(), infoData != null ? R.color.title : R.color.text_highlighted));

            ImageView icon = (ImageView) child.findViewById(R.id.icon);

            if (infoData == null) {
                Glide.with(this).load(R.drawable.download).into(icon);
            }

            content.addView(child);
        }
    }

    private void setupChart(Graph graph) {
        LineDataSet fundDataSet = createDataSet(graph.getFund(), getString(R.string.fund));

        int fundDataSetColor = ContextCompat.getColor(getContext(), R.color.fund_chart_line);

        fundDataSet.setColor(fundDataSetColor);

        fundDataSet.setValueTextColor(fundDataSetColor);

        fundDataSet.setLineWidth(LINE_WIDTH);

        fundDataSet.setCircleColor(fundDataSetColor);

        fundDataSet.setCircleHoleRadius(CIRCLE_HOLE_RADIUS);

        LineDataSet cdiDataSet = createDataSet(graph.getCdi(), getString(R.string.cdi));

        int cdiDataSetColor = ContextCompat.getColor(getContext(), R.color.cdi_chart_line);

        cdiDataSet.setColor(cdiDataSetColor);

        cdiDataSet.setValueTextColor(cdiDataSetColor);

        cdiDataSet.setLineWidth(LINE_WIDTH);

        cdiDataSet.setCircleColor(cdiDataSetColor);

        cdiDataSet.setCircleHoleRadius(CIRCLE_HOLE_RADIUS);

        LineData lineData = new LineData();

        lineData.addDataSet(fundDataSet);

        lineData.addDataSet(cdiDataSet);

        lineChart.setData(lineData);

        lineChart.invalidate();
    }

    private LineDataSet createDataSet(float[] points, String label) {
        List<Entry> entries = new ArrayList<>();

        for (int i = 0; i < points.length; i++) {
            entries.add(new Entry(i, points[i]));
        }

        return new LineDataSet(entries, label);
    }

    public void setInvestimentRisk(int risk) {
        switch (risk) {
            case 1:
                setLayoutRisk(risk_1);
                break;
            case 2:
                setLayoutRisk(risk_2);
                break;
            case 3:
                setLayoutRisk(risk_3);
                break;
            case 4:
                setLayoutRisk(risk_4);
                break;
            case 5:
                setLayoutRisk(risk_5);
                break;
        }
    }

    public void setLayoutRisk(LinearLayout contentRisk) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, (int) Math.ceil(10 * getActivity().getResources().getDisplayMetrics().density));
        layoutParams.weight = 1;
        contentRisk.setLayoutParams(layoutParams);
    }
}
