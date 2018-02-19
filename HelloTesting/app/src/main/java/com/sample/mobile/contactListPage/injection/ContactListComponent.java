package com.sample.mobile.contactListPage.injection;


import com.sample.mobile.contactListPage.view.ContactListActivity;

import dagger.Subcomponent;

@ContactListScope
@Subcomponent()
public interface ContactListComponent {

    void inject(ContactListActivity activity);

}
