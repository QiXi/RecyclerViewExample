package ru.qixi.example.recyclerview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	private static final String	MOCK_URL	= "http://lorempixel.com/800/400/nightlife/";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		RecyclerView recyclerView = (RecyclerView)findViewById(R.id.list);
		recyclerView.setHasFixedSize(false);
		final RecyclerAdapter adapter = new RecyclerAdapter(createMockList(), R.layout.item);
		recyclerView.setAdapter(adapter);
//		recyclerView.setLayoutManager(new GridLayoutManager(this));
		recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false));
		recyclerView.setItemAnimator(new DefaultItemAnimator());

		adapter.setOnItemClickListener(new OnRecyclerViewItemClickListener<ViewModel>() {

			@Override
			public void onItemClick(View view, ViewModel viewModel) {
				adapter.remove(viewModel);
			}
		});
	}


	private List<ViewModel> createMockList() {
		List<ViewModel> items = new ArrayList<ViewModel>();
		for (int i = 0; i < 20; i++) {
			items.add(new ViewModel(i, "Item " + (i + 1), MOCK_URL + (i % 10 + 1)));
		}
		return items;
	}

}
