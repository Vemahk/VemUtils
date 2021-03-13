package me.vem.utils.io;

public interface Compressable {
	
	/**
	 * Writes the compressed form of the object into the RollingDataSaver.
	 * @param buf A ByteBuffer
	 * @return The given buffer.
	 */
	RollingDataSaver writeTo(RollingDataSaver saver);
}
