package eu.jgen.notes.dmw.lite.runtimes;

public class XArray {

	int subscript = 0;
	int max = -1;
	int last = -1;

	public XArray(int max) {
		super();
		this.max = max;
	}

	/*
	 * Sets current subscript to the specific value.
	 */
	public void setSubscript(int value) {
		if (value <= max) {
			this.last = value;
			this.subscript = value;
		}
	}

	/*
	 * Gets current value subscript.
	 */
	public int getSubscript() {
		return subscript;
	}

	/*
	 * Gets maximum value of the subscript.
	 */
	public int getMax() {
		return max;
	}

	/*
	 * Gets last value of the subscript.
	 */
	public int getLast() {
		return last;
	}

}
