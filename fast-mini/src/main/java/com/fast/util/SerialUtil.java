package com.fast.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerialUtil {

	public static byte[] serialize(Object obj) {
		ObjectOutputStream stream = null;
		ByteArrayOutputStream bytes = null;
		try {
			bytes = new ByteArrayOutputStream();
			stream = new ObjectOutputStream(bytes);
			stream.writeObject(obj);
			return bytes.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object unSerialize(byte[] bytes) {
		ByteArrayInputStream input = null;
		try {
			input = new ByteArrayInputStream(bytes);
			ObjectInputStream objectInput = new ObjectInputStream(input);
			return objectInput.readObject();
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

}
