package com.knowroaming.mobile.contactListPage.injection;


import com.knowroaming.mobile.contactListPage.view.ContactListActivity;

import dagger.Subcomponent;

@ContactListScope
@Subcomponent()
public interface ContactListComponent {

    void inject(ContactListActivity activity);

}
