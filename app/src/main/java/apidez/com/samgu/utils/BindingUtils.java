package apidez.com.samgu.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import apidez.com.samgu.adapter.BaseRecyclerViewAdapter;

/**
 * Created by nongdenchet on 6/4/16.
 */
public class BindingUtils {

    @BindingAdapter("items")
    public static <T> void setItems(RecyclerView recyclerView, List<T> items) {
        BaseRecyclerViewAdapter<T> adapter = (BaseRecyclerViewAdapter<T>) recyclerView.getAdapter();
        if (adapter != null) adapter.setItems(items);
    }
}
