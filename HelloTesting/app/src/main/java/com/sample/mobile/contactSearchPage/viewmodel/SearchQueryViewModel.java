package com.sample.mobile.contactSearchPage.viewmodel;


import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.sample.mobile.contacts.BR;

import io.reactivex.subjects.PublishSubject;

public class SearchQueryViewModel extends BaseObservable {

    private PublishSubject<String> searchQueryObservable;
    private String searchQuery;

    SearchQueryViewModel() {
        searchQuery= "";
        searchQueryObservable = PublishSubject.create();
    }

    @Bindable
    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
        searchQueryObservable.onNext(this.searchQuery);
        notifyPropertyChanged(BR.searchQuery);
    }

    PublishSubject<String> getSearchQueryObservable() {
        return searchQueryObservable;
    }
}
