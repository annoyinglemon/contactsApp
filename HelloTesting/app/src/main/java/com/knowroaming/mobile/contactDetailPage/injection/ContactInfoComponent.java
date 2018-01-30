package com.knowroaming.mobile.contactDetailPage.injection;


import com.knowroaming.mobile.contactDetailPage.view.ContactInfoActivity;

import dagger.Subcomponent;

@ContactInfoScope
@Subcomponent()
public interface ContactInfoComponent {

    void inject(ContactInfoActivity activity);

}
