package com.sample.mobile.contactListPage.view;


import android.support.v7.widget.RecyclerView;

import com.sample.mobile.contactListPage.viewmodel.ContactListItemViewModel;
import com.sample.mobile.contacts.databinding.ListItemContactBinding;

public class ContactViewHolder extends RecyclerView.ViewHolder {

    private ListItemContactBinding binding;

    ContactViewHolder(ListItemContactBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
    }

    public ListItemContactBinding getBinding() {
        return binding;
    }

    void bindViewModel(ContactListItemViewModel contactListItemViewModel) {
        binding.setViewModel(contactListItemViewModel);
    }
}
