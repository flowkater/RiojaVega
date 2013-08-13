package multitasking;

import java.util.Collection;
import java.util.Iterator;

public class ConsoleLogger implements Logger{
	@Override
	synchronized public void writeEntry(Collection<String> entry) {
		for(Iterator<String> line = entry.iterator();line.hasNext();)
			System.out.println(line.next());
		System.out.println();
	}

	@Override
	synchronized public void writeEntry(String entry) {
		System.out.println(entry);
		System.out.println();
	}
}
