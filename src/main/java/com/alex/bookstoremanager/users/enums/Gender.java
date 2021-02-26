package com.alex.bookstoremanager.users.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {

    MALE("Male"),
    FEMALE("Female");

    Gender(String string) {
		// TODO Auto-generated constructor stub
	}

	private String description;
}
