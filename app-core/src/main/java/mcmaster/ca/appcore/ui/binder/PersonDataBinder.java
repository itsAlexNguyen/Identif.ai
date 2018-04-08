package mcmaster.ca.appcore.ui.binder;

import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import mcmaster.ca.appcore.R;
import mcmaster.ca.appcore.datastore.PersonResult;
import mcmaster.ca.appcore.ui.viewholder.PersonViewHolder;

public class PersonDataBinder extends AbstractDataBinder<PersonViewHolder> {
    private final PersonResult personResult;

    public PersonDataBinder(PersonResult personResult) {
        super();
        this.personResult = personResult;
    }

    @Override
    public PersonViewHolder createViewHolder(ViewGroup parent) {
        View view = getView(PersonViewHolder.getLayoutId(), parent);
        return new PersonViewHolder(view);
    }

    @Override
    public void bindViewHolder(PersonViewHolder holder) {
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
