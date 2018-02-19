package com.sample.mobile.contactDetailPage.injection;


import com.sample.mobile.contactDetailPage.view.ContactInfoActivity;

import dagger.Subcomponent;

@ContactInfoScope
@Subcomponent()
public interface ContactInfoComponent {

    void inject(ContactInfoActivity activity);

}
