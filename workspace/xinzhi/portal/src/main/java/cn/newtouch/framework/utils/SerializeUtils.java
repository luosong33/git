package cn.newtouch.framework.utils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class SerializeUtils {
	
	private static Kryo kryo = new Kryo();
	
	public static byte[] serialize(Object t) {
		byte[] buffer = new byte[2048];
		Output output = new Output(buffer);
		kryo.writeClassAndObject(output, t);
		return output.toBytes();
	}

	public static <T> T deserialize(byte[] bytes) {
		Input input = new Input(bytes);
		@SuppressWarnings("unchecked")
		T t = (T) kryo.readClassAndObject(input);
		return t;
	}

}
