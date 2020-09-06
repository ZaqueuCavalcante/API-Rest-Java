package br.com.zaqueucavalcante.apirestjava.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class EncodeURI {

	public static String decode(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
