package com.example.lesson21.ui.home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.lesson21.model.NoteModel;
import com.example.lesson21.R;
import com.example.lesson21.adapter.NoteAdapter;
import com.example.lesson21.databinding.FragmentHomeBinding;
import com.example.lesson21.utils.App;
import com.example.lesson21.utils.Constants;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private NoteAdapter adapter;
    private FragmentHomeBinding binding;
    List<NoteModel> list = new ArrayList<>();
    private boolean isDashboard = false;
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    LinearLayoutManager linearLayoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.recyclerView.setAdapter(adapter);
        return binding.getRoot();
    }

    private void swipeDelete() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(R.string.alertMess)
                        .setPositiveButton(R.string.alertYes, (dialog, which) -> {
                            App.getDatabase().getDao().delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));
                            adapter.notifyDataSetChanged();
                        });
                builder.setNegativeButton(R.string.alertNo, (dialog, which) ->
                        adapter.notifyDataSetChanged()).show();
            }
        }).attachToRecyclerView(binding.recyclerView);

    }

    private void getDataFromDB() {
        App.getDatabase().getDao().getAll().observe(getViewLifecycleOwner(), noteModels -> {
            adapter.setList(noteModels);
            list = noteModels;
        });

    }


    private void getData() {
        getParentFragmentManager().setFragmentResultListener(Constants.REQUEST_KEY, getViewLifecycleOwner(), (requestKey, result) -> {
            NoteModel model = (NoteModel) result.getSerializable(Constants.BUNDLE_KEY);
            adapter.addTask(model);
            if (isDashboard) {
                binding.recyclerView.setLayoutManager(staggeredGridLayoutManager);
            } else {
                binding.recyclerView.setLayoutManager(linearLayoutManager);
            }
        });
    }

    private void unitView() {
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        adapter = new NoteAdapter();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayoutManager = new LinearLayoutManager(requireContext());
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        unitView();
        getData();
        getDataFromDB();
        search();
        swipeDelete();
    }

    private void search() {
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void filter(String text) {
        ArrayList<NoteModel> filteredList = new ArrayList<>();

        for (NoteModel item : list) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        if (item.getItemId() == R.id.dashboard) {
            isDashboard = !isDashboard;
            if (isDashboard) {
                item.setIcon(R.drawable.ic_list);
            } else {
                item.setIcon(R.drawable.ic_dashboard);
            }
            binding.recyclerView.setLayoutManager(isDashboard ? staggeredGridLayoutManager : linearLayoutManager);
            binding.recyclerView.setAdapter(adapter);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}