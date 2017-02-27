package exp.weather.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import javax.inject.Inject;
import exp.weather.R;
import exp.weather.common.BaseFragment;
import exp.weather.interfaces.ISearchView;
import exp.weather.interfaces.MainScreenComponent;
import exp.weather.interfaces.MainScreenContract;
import exp.weather.presenters.MainScreenPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragment implements ISearchView {

    @Inject
    MainScreenPresenter presenter;

    private EditText cityNameEt;
    private ImageButton searchBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        cityNameEt = (EditText) v.findViewById(R.id.cityNameEt);
        searchBtn = (ImageButton) v.findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!cityNameEt.getText().toString().equals("")) {
                    presenter.makeWeatherRequest(cityNameEt.getText().toString());
                }
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getComponent(MainScreenComponent.class).inject(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public String getCityName()
    {
        return cityNameEt.getText().toString();
    }
}
