package mcmaster.ca.appcore.ui.binder;

import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import mcmaster.ca.appcore.R;
import mcmaster.ca.appcore.common.InputListener;
import mcmaster.ca.appcore.datastore.PersonResult;
import mcmaster.ca.appcore.ui.viewholder.PersonViewHolder;

public class PersonDataBinder extends AbstractDataBinder<PersonViewHolder> {
    private final PersonResult personResult;
    private final InputListener<PersonResult> listener;

    public PersonDataBinder(PersonResult personResult, InputListener<PersonResult> listener) {
        super();
        this.personResult = personResult;
        this.listener = listener;
    }

    @Override
    public PersonViewHolder createViewHolder(ViewGroup parent) {
        View view = getView(PersonViewHolder.getLayoutId(), parent);
        return new PersonViewHolder(view);
    }

    @Override
    public void bindViewHolder(PersonViewHolder holder) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onInputReceived(personResult);
            }
        });
        holder.nameText.setText(personResult.name);
        holder.scoreText.setText(String.valueOf(personResult.score));
        if (personResult.profileUrl != null) {
            Picasso.get().load(personResult.profileUrl)
                .resize(200, 200)
                .placeholder(R.drawable.profile_placeholder)
                .into(holder.profileIcon);
        }
    }
}
