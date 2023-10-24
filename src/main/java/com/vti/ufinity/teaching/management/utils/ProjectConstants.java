package com.vti.ufinity.teaching.management.utils;

import java.util.Locale;

/**
 * Created on Duc.NguyenViet, 2023
 *
 * @author Duc.NguyenViet
 */
public final class ProjectConstants {

	// FIXME : Customize project constants for your application.

	public static final String DEFAULT_ENCODING = "UTF-8";

	public static final Locale TURKISH_LOCALE = new Locale.Builder().setLanguage("en").setRegion("US").build();

	private ProjectConstants() {

		throw new UnsupportedOperationException();
	}

}
