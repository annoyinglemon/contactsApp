package com.knowroaming.mobile.contactListPage.view;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.knowroaming.mobile.GlideRequests;
import com.knowroaming.mobile.contactListPage.viewmodel.ContactListItemViewModel;
import com.knowroaming.mobile.hellotesting.R;
import com.knowroaming.mobile.hellotesting.databinding.ListItemContactBinding;
import com.knowroaming.mobile.resultObjects.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    private final GlideRequests glideRequests;
    private List<Contact> contacts;

    public ContactsAdapter(GlideRequests glideRequests) {
        this.glideRequests = glideRequests;
        this.contacts = new ArrayList<>();
    }

    public void setContactList(List<Contact> contacts) {
        int previousCount = getItemCount();
        this.contacts.clear();
        this.contacts.addAll(contacts);
        notifyChanges(previousCount, getItemCount());
    }

    private void notifyChanges(int previousCount, int newCount) {
        if (previousCount == newCount) {
            notifyItemRangeChanged(0, newCount);
        } else if (previousCount > newCount) {
            notifyItemRangeChanged(0, newCount);
            notifyItemRangeRemoved(newCount, (previousCount - newCount));
        } else if (previousCount < newCount) {
            notifyItemRangeChanged(0, previousCount);
            notifyItemRangeInserted(previousCount, (newCount - previousCount));
        }
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemContactBinding listItemContactBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_contact, parent, false);
        return new ContactViewHolder(listItemContactBinding);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder viewHolder, int position) {
        Contact contact = contacts.get(position);
        viewHolder.bindViewModel(new ContactListItemViewModel(contact));
        if (contact.picture != null && contact.picture.largeUrl != null) {
            glideRequests
                    .load(contact.picture.largeUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(RequestOptions.circleCropTransform())
                    .into(viewHolder.getBinding().imageViewListItemContactImage);
        }
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
