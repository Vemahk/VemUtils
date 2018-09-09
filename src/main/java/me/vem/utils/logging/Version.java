package me.vem.utils.logging;

public final class Version {
	
	private final String title;
	private final int major, minor, rev;
	
	public Version(int major, int minor, int rev, String title) {
		this.major = major;
		this.minor = minor;
		this.rev = rev;
		this.title = title;
	}
	
	public String toString() {
		return String.format("%s[%d.%d.%d]", title, major, minor, rev);
	}
}
