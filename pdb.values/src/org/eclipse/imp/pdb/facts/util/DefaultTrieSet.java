package org.eclipse.imp.pdb.facts.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DefaultTrieSet {

	private static Class<?> target = TrieSet_5Bits.class;

	private static Method persistentSetOfEmpty;
	private static Method persistentSetOfKeyValuePairs;

	private static Method transientSetOfEmpty;
	private static Method transientSetOfKeyValuePairs;

	static {
		try {
			persistentSetOfEmpty = target.getMethod("of");
			persistentSetOfKeyValuePairs = target.getMethod("of", Object[].class);

			transientSetOfEmpty = target.getMethod("transientOf");
			transientSetOfKeyValuePairs = target.getMethod("transientOf", Object[].class);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static final <K> ImmutableSet<K> of() {
		try {
			return (ImmutableSet<K>) persistentSetOfEmpty.invoke(null);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static final <K> ImmutableSet<K> of(K... keys) {
		try {
			return (ImmutableSet<K>) persistentSetOfKeyValuePairs.invoke(null, (Object) keys);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static final <K> TransientSet<K> transientOf() {
		try {
			return (TransientSet<K>) transientSetOfEmpty.invoke(null);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static final <K> TransientSet<K> transientOf(K... keys) {
		try {
			return (TransientSet<K>) transientSetOfKeyValuePairs.invoke(null, (Object) keys);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

}
