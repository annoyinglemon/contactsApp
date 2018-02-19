package com.sample.mobile.contactSearchPage.injection;



import com.sample.mobile.contactSearchPage.view.ContactSearchActivity;

import dagger.Subcomponent;

@ContactSearchScope
@Subcomponent()
public interface ContactSearchComponent {

    void inject(ContactSearchActivity activity);

}
