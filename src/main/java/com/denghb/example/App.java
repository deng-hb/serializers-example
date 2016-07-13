package com.denghb.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.alibaba.fastjson.JSON;

import io.protostuff.GraphIOUtil;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 * 
 * @author denghb
 *
 */
public class App {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		System.out.println("Hello World!");

		Schema<User> schema = RuntimeSchema.getSchema(User.class);

		User user = new User();
		user.setEmail("asdaa1fff232asdaa1fff23ff232q@qq.com");
		user.setName("asdaa1fff232asdaa1fff232wasdaa1fff232");
		user.setMobile("132432asdaa1fff232asdaa1fff23234234");

		// JVM 自带
		long jvmStart = System.currentTimeMillis();
		ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream("result.obj"));
		oout.writeObject(user);
		oout.close();

		File f = new File("result.obj");
		System.out.println(f.length());

		ObjectInputStream oin = new ObjectInputStream(new FileInputStream("result.obj"));
		Object newUser = oin.readObject();
		oin.close();
		System.out.println(newUser);
		System.out.println(System.currentTimeMillis() - jvmStart);

		// protostuff
		long protoStart = System.currentTimeMillis();
		byte[] data1 = GraphIOUtil.toByteArray(user, schema, LinkedBuffer.allocate());

		System.out.println(data1.length);

		User user1 = schema.newMessage();
		GraphIOUtil.mergeFrom(data1, user1, schema);
		System.out.println(user1);
		System.out.println(System.currentTimeMillis() - protoStart);

		byte[] data2 = ProtobufIOUtil.toByteArray(user, schema, LinkedBuffer.allocate());

		System.out.println(data2.length);

		User user2 = schema.newMessage();
		ProtobufIOUtil.mergeFrom(data2, user2, schema);

		System.out.println(user2);

		byte[] data3 = ProtostuffIOUtil.toByteArray(user, schema, LinkedBuffer.allocate());

		System.out.println(data3.length);

		User user3 = schema.newMessage();
		ProtobufIOUtil.mergeFrom(data3, user3, schema);

		System.out.println(user3);

		// JSON
		long jsonStart = System.currentTimeMillis();
		String json = JSON.toJSONString(user);
		System.out.println(json.getBytes().length);

		User user4 = JSON.parseObject(json, User.class);
		System.out.println(user4);
		System.out.println(System.currentTimeMillis() - jsonStart);
	}
}
