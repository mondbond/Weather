package exp.weather.common;

import android.support.v4.app.Fragment;

/**
 * Created by User on 27.02.2017.
 */

public abstract class BaseFragment extends Fragment {
    @SuppressWarnings("unchecked")
    protected <T> T getComponent(Class<T> componentType) {
        return componentType.cast(((IHasComponent<T>)getActivity()).getComponent());
    }
}
