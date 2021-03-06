package com.kygo.common.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CollectionUtils {

	public static boolean isEmpty(@SuppressWarnings("rawtypes") Collection collection) {
		return (collection == null) || (collection.isEmpty());
	}

	public static <T> List<T> asList(@SuppressWarnings("unchecked") T... args) {
		if ((args == null) || (args.length == 0)) {
			return Collections.emptyList();
		}
		return Arrays.asList(args);
	}

}
