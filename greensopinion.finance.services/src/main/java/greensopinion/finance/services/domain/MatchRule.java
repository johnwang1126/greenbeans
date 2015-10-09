package greensopinion.finance.services.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.regex.Pattern;

import com.google.common.base.MoreObjects;

public class MatchRule {

	private final String pattern;
	private transient Pattern $pattern;

	public static MatchRule withPattern(String pattern) {
		return new MatchRule(pattern);
	}

	String getPattern() {
		return pattern;
	}

	private MatchRule(String regex) {
		this.pattern = checkNotNull(regex);
		this.$pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	}

	public boolean matches(Transaction transaction) {
		checkNotNull(transaction);
		return $pattern.matcher(transaction.getDescription()).find();
	}

	@Override
	public int hashCode() {
		return pattern.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		MatchRule other = (MatchRule) obj;
		return pattern.equals(other.pattern);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(MatchRule.class).add("pattern", pattern).toString();
	}
}