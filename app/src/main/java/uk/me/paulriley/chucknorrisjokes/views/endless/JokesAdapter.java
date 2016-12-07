package uk.me.paulriley.chucknorrisjokes.views.endless;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import uk.me.paulriley.chucknorrisjokes.R;

import static uk.me.paulriley.chucknorrisjokes.utility.StringFormating.fromHtml;

public class JokesAdapter extends RecyclerView.Adapter<JokesViewHolder> {

    private List<String> jokes = new ArrayList<>();

    public void setData(List<String> jokes) {
        this.jokes.addAll(jokes);
    }

    @Override
    public JokesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.joke_card, parent, false);
        return new JokesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JokesViewHolder holder, int position) {
        String joke = jokes.get(position);
        holder.joke.setText(fromHtml(joke));
    }

    @Override
    public int getItemCount() {
        return jokes.size();
    }
}
