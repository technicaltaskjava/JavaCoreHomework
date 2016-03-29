package t03.model.entity;

import t03.model.Tuple;

public class Triple<A, B, C> implements Tuple {
	private final A left;
	private final B middle;
	private final C right;

	private Triple(final A left, final B middle, final C right) {
		this.left = left;
		this.middle = middle;
		this.right = right;
	}

	public static <A, B, C> Triple create(final A left, final B middle, final C right) {
		return new Triple<>(left, middle, right);
	}

	public A getLeft() {
		return left;
	}

	public B getMiddle() {
		return middle;
	}

	public C getRight() {
		return right;
	}

	@Override
	public int hashCode() {
		int result = left != null ? left.hashCode() : 0;
		result = 31 * result + (middle != null ? middle.hashCode() : 0);
		result = 31 * result + (right != null ? right.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Triple<?, ?, ?> triple = (Triple<?, ?, ?>) o;

		if (left != null ? !left.equals(triple.left) : triple.left != null) {
			return false;
		}
		if (middle != null ? !middle.equals(triple.middle) : triple.middle != null) {
			return false;
		}
		return right != null ? right.equals(triple.right) : triple.right == null;

	}

	@Override
	public String toString() {
		return "Triple{" +
				"left=" + left +
				", middle=" + middle +
				", right=" + right +
				'}';
	}
}
