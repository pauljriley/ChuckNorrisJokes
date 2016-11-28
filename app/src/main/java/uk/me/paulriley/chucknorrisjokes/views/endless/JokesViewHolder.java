package uk.me.paulriley.chucknorrisjokes.views.endless;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.devspark.robototextview.widget.RobotoTextView;

import uk.me.paulriley.chucknorrisjokes.R;

public class JokesViewHolder extends RecyclerView.ViewHolder {
    public RobotoTextView joke;

    public JokesViewHolder(View itemView) {
        super(itemView);
        joke = (RobotoTextView) itemView.findViewById(R.id.joke);
    }
}
