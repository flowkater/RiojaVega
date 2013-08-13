package multitasking;

import java.util.Collection;

public interface Logger {
	public void writeEntry(Collection<String> entry);
	public void writeEntry(String entry);
}
