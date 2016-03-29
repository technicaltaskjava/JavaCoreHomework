package t03.model.entity;

import t03.model.Tuple;

public class Pair<A, B> implements Tuple {
	private final A left;
	private final B right;

	private Pair(final A left, final B right) {
		this.left = left;
		this.right = right;
	}

	public static <A, B> Pair create(final A left, final B right) {
		return new Pair<>(left, right);
	}

	public A getLeft() {
		return left;
	}

	public B getRight() {
		return right;
	}
	
	@Override
	public int hashCode() {
		int result = left != null ? left.hashCode() : 0;
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

		Pair<?, ?> pair = (Pair<?, ?>) o;

		if (left != null ? !left.equals(pair.left) : pair.left != null) {
			return false;
		}
		return right != null ? right.equals(pair.right) : pair.right == null;

	}
	
	@Override
	public String toString() {
		return "Pair{" +
				"left=" + left +
				", right=" + right +
				'}';
	}
}
