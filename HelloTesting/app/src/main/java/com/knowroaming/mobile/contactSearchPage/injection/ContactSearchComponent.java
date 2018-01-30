package com.knowroaming.mobile.contactSearchPage.injection;



import com.knowroaming.mobile.contactSearchPage.view.ContactSearchActivity;

import dagger.Subcomponent;

@ContactSearchScope
@Subcomponent()
public interface ContactSearchComponent {

    void inject(ContactSearchActivity activity);

}
