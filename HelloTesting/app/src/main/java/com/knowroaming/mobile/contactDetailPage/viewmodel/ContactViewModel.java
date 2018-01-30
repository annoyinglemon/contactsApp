package com.knowroaming.mobile.contactDetailPage.viewmodel;


import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.knowroaming.mobile.hellotesting.BR;
import com.knowroaming.mobile.resultObjects.Contact;


public class ContactViewModel extends BaseObservable {

    private static final String EMAIL_ERROR_MESSAGE = "Enter a valid email address";
    private static final String FIELD_ERROR_MESSAGE = "This field is required.";

    public final Contact contact;
    private final boolean emailFieldEnabled;
    private final int editButtonVisibility;
    private final int saveButtonVisibility;
    private final int imageVisibility;
    private final int initialsVisibility;

    private String emailErrorMessage;
    private String titleErrorMessage;
    private String firstNameErrorMessage;
    private String lastNameErrorMessage;
    private String usernameErrorMessage;
    private String phoneErrorMessage;

    private boolean saveButtonEnabled;
    private boolean editButtonEnabled;

    ContactViewModel() {
        this.contact = new Contact();
        this.emailFieldEnabled = true;
        this.editButtonVisibility = View.GONE;
        this.saveButtonVisibility = View.VISIBLE;
        this.imageVisibility = View.GONE;
        this.initialsVisibility = View.GONE;
        this.saveButtonEnabled = false;
        this.editButtonEnabled = false;
        this.emailErrorMessage = EMAIL_ERROR_MESSAGE;
        this.titleErrorMessage = FIELD_ERROR_MESSAGE;
        this.firstNameErrorMessage = FIELD_ERROR_MESSAGE;
        this.lastNameErrorMessage = FIELD_ERROR_MESSAGE;
        this.usernameErrorMessage = FIELD_ERROR_MESSAGE;
        this.phoneErrorMessage = FIELD_ERROR_MESSAGE;
    }

    ContactViewModel(Contact contact) {
        this.contact = contact;
        this.emailFieldEnabled = false;
        this.editButtonVisibility = View.VISIBLE;
        this.saveButtonVisibility = View.GONE;
        if (this.contact.picture != null && this.contact.picture.largeUrl != null) {
            this.imageVisibility = View.VISIBLE;
            this.initialsVisibility = View.GONE;
        } else {
            this.imageVisibility = View.GONE;
            this.initialsVisibility = View.VISIBLE;
        }
        this.saveButtonEnabled = false;
        this.editButtonEnabled = false;
    }

    public void setEmail(String email) {
        contact.email = email;
        notifyPropertyChanged(BR.email);

        if (isValidEmail(contact.email)) {
            emailErrorMessage = null;
        } else {
            emailErrorMessage = EMAIL_ERROR_MESSAGE;
        }
        notifyPropertyChanged(BR.emailErrorMessage);
        validateFields();
    }

    private boolean isValidEmail(CharSequence target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    @Bindable
    public String getEmailErrorMessage() {
        return emailErrorMessage;
    }

    @Bindable
    public String getEmail() {
        return contact.email;
    }

    public void setTitle(String title) {
        contact.name.title = title;
        notifyPropertyChanged(BR.title);

        if (contact.name.title.isEmpty()) {
            titleErrorMessage = FIELD_ERROR_MESSAGE;
        } else {
            titleErrorMessage = null;
        }
        notifyPropertyChanged(BR.titleErrorMessage);
        validateFields();
    }

    @Bindable
    public String getTitleErrorMessage() {
        return titleErrorMessage;
    }

    @Bindable
    public String getTitle() {
        return contact.name.title;
    }

    public void setFirstName(String firstName) {
        contact.name.firstName = firstName;
        notifyPropertyChanged(BR.firstName);

        if (contact.name.firstName.isEmpty()) {
            firstNameErrorMessage = FIELD_ERROR_MESSAGE;
        } else {
            firstNameErrorMessage = null;
        }
        notifyPropertyChanged(BR.firstNameErrorMessage);
        validateFields();
    }

    @Bindable
    public String getFirstNameErrorMessage() {
        return firstNameErrorMessage;
    }

    @Bindable
    public String getFirstName() {
        return contact.name.firstName;
    }

    public void setLastName(String lastName) {
        contact.name.lastName = lastName;
        notifyPropertyChanged(BR.lastName);

        if (contact.name.lastName.isEmpty()) {
            lastNameErrorMessage = FIELD_ERROR_MESSAGE;
        } else {
            lastNameErrorMessage = null;
        }
        notifyPropertyChanged(BR.lastNameErrorMessage);
        validateFields();
    }

    @Bindable
    public String getLastNameErrorMessage() {
        return lastNameErrorMessage;
    }

    @Bindable
    public String getLastName() {
        return contact.name.lastName;
    }

    public String getNameInitials() {
        if (contact.name.firstName == null || contact.name.lastName == null ||
                contact.name.lastName.isEmpty() || contact.name.firstName.isEmpty()) {
            return "";
        }
        return contact.name.firstName.substring(0, 1).toUpperCase().concat(contact.name.lastName.substring(0, 1).toUpperCase());
    }

    public void setStreet(String street) {
        contact.location.street = street;
        notifyPropertyChanged(BR.street);
    }

    @Bindable
    public String getStreet() {
        return contact.location.street;
    }

    public void setCity(String city) {
        contact.location.city = city;
        notifyPropertyChanged(BR.city);
    }

    @Bindable
    public String getCity() {
        return contact.location.city;
    }

    public void setState(String state) {
        contact.location.state = state;
        notifyPropertyChanged(BR.state);
    }

    @Bindable
    public String getState() {
        return contact.location.state;
    }

    public void setPostalCode(String postalCode) {
        try {
            contact.location.postalCode = Long.parseLong(postalCode);
        } catch (NumberFormatException e) {
            contact.location.postalCode = 0;
        }
        notifyPropertyChanged(BR.postalCode);
    }

    @Bindable
    public String getPostalCode() {
        return Long.toString(contact.location.postalCode);
    }

    public void setUsername(String username) {
        contact.login.username = username;
        notifyPropertyChanged(BR.username);

        if (contact.login.username.isEmpty()) {
            usernameErrorMessage = FIELD_ERROR_MESSAGE;
        } else {
            usernameErrorMessage = null;
        }
        notifyPropertyChanged(BR.usernameErrorMessage);
        validateFields();
    }

    @Bindable
    public String getUsernameErrorMessage() {
        return usernameErrorMessage;
    }

    @Bindable
    public String getUsername() {
        return contact.login.username;
    }

    public void setBirthDate(String birthDate) {
        contact.birthDate = birthDate;
        notifyPropertyChanged(BR.birthDate);
    }

    @Bindable
    public String getBirthDate() {
        return contact.birthDate;
    }

    public void setPhoneNumber(String phoneNumber) {
        contact.phoneNumber = phoneNumber;
        notifyPropertyChanged(BR.phoneNumber);

        if (contact.phoneNumber.isEmpty()) {
            phoneErrorMessage = FIELD_ERROR_MESSAGE;
        } else {
            phoneErrorMessage = null;
        }
        notifyPropertyChanged(BR.phoneErrorMessage);
        validateFields();
    }

    @Bindable
    public String getPhoneErrorMessage() {
        return phoneErrorMessage;
    }

    @Bindable
    public String getPhoneNumber() {
        return contact.phoneNumber;
    }

    public void setCellNumber(String cellNumber) {
        contact.cellNumber = cellNumber;
        notifyPropertyChanged(BR.cellNumber);
    }

    @Bindable
    public String getCellNumber() {
        return contact.cellNumber;
    }

    public boolean isEmailFieldEnabled() {
        return emailFieldEnabled;
    }

    public int getImageVisibility() {
        return imageVisibility;
    }

    public int getInitialsVisibility() {
        return initialsVisibility;
    }

    private void validateFields() {
        if (emailErrorMessage != null || titleErrorMessage != null || firstNameErrorMessage != null
                || lastNameErrorMessage != null || usernameErrorMessage != null || phoneErrorMessage != null) {
            if (saveButtonVisibility == View.VISIBLE) {
                saveButtonEnabled = false;
                notifyPropertyChanged(BR.saveButtonEnabled);
            } else if (editButtonVisibility == View.VISIBLE) {
                editButtonEnabled = false;
                notifyPropertyChanged(BR.editButtonEnabled);
            }
        } else {
            if (saveButtonVisibility == View.VISIBLE) {
                saveButtonEnabled = true;
                notifyPropertyChanged(BR.saveButtonEnabled);
            } else if (editButtonVisibility == View.VISIBLE) {
                editButtonEnabled = true;
                notifyPropertyChanged(BR.editButtonEnabled);
            }
        }
    }

    @Bindable
    public boolean isSaveButtonEnabled() {
        return saveButtonEnabled;
    }

    @Bindable
    public boolean isEditButtonEnabled() {
        return editButtonEnabled;
    }

    public int getEditButtonVisibility() {
        return editButtonVisibility;
    }

    public int getSaveButtonVisibility() {
        return saveButtonVisibility;
    }
}
