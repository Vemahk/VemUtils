package me.vem.utils.io;

import java.nio.ByteBuffer;

public interface Compressable {
	
	/**
	 * @return The number of bytes the object is expected to compress into. 
	 */
	int writeSize();
	
	/**
	 * Writes the compressed form of the object into the ByteBuffer.
	 * @param buf A ByteBuffer
	 * @return The given buffer.
	 */
	ByteBuffer writeTo(ByteBuffer buf);
	
	/**
	 * Calls writeTo(byte[] buf). The byte[] it gives it is a new one with a length of writeSize().
	 * @return The constructed byte array.
	 */
	default byte[] compress() {
		byte[] out = new byte[writeSize()];
		writeTo(out);
		return out;
	}
	
	
	/**
	 * Calls writeTo(byte[] buf, 0). Writes the compressed form of this object to the beginning of the given byte array.
	 * @param buf
	 */
	default void writeTo(byte[] buf) {
		writeTo(buf, 0);
	}
	
	/**
	 * Writes the compressed form of this object to the given byte array, starting at index 'offset.
	 * @param buf The byte array.
	 * @param offset The index of the first byte written to this array.
	 */
	default void writeTo(byte[] buf, int offset) {
		writeTo(ByteBuffer.wrap(buf, offset, buf.length - offset));
	}
}
