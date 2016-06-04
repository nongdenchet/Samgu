package apidez.com.samgu.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import apidez.com.samgu.R;
import apidez.com.samgu.databinding.SearchItemBinding;

/**
 * Created by nongdenchet on 6/4/16.
 */

public class SearchResultAdapter extends BaseRecyclerViewAdapter<String> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SearchItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.search_item, parent, false);
        return new SearchItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SearchItemViewHolder viewHolder = (SearchItemViewHolder) holder;
        viewHolder.bind(mItems.get(position));
    }

    public class SearchItemViewHolder extends RecyclerView.ViewHolder {
        SearchItemBinding binding;

        public SearchItemViewHolder(SearchItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(String value) {
            binding.setValue(value);
            binding.getRoot().setOnClickListener(v -> {
            });
            binding.executePendingBindings();
        }
    }
}
