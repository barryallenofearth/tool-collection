package de.michaelevelt.toolcollection.similarity;

import java.util.List;

public abstract class SimilarNameFinder<T> {

	public T findSimilarObject(List<T> comparisons, String searchTerm) {

		double similarityIndex;
		double similarityIndexMax = 0;

		searchTerm = searchTerm.toLowerCase();
		T mostSimilarObject = null;

		String comparisonString;

		//		compare searchTerm to all possible Objects
		for (T comparison : comparisons) {
			for (String string : toStrings(comparison)) {
				comparisonString = string.trim().toLowerCase();

				similarityIndex = detemine_SimilarityIndex(searchTerm, comparisonString);
				if (similarityIndex > similarityIndexMax) {
					similarityIndexMax = similarityIndex;
					mostSimilarObject = comparison;
				}
			}
		}

		return mostSimilarObject;
	}

	/**
	 * Multiple Strings might be describing the object equally well
	 * @param object object for which similartiy shall be found
	 * @return array of string describing the object
	 */
	protected abstract String[] toStrings(T object);

	private double detemine_SimilarityIndex(String teamName, String comparisonTeam) {
		String longerName = teamName, shorterName = comparisonTeam;
		if (teamName.length() < comparisonTeam.length()) { // longerName should always have greater length
			longerName = comparisonTeam;
			shorterName = teamName;
		}
		int longerNameLength = longerName.length();
		if (longerNameLength == 0) {
			return 1.0; /* both strings are zero length */
		}
		/* // If you have Apache Commons Text, you can use it to calculate the edit distance:
	    LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
	    return (longerNameLength - levenshteinDistance.apply(longerName, shorterName)) / (double) longerNameLength; */
		return (longerNameLength - editDistance(longerName, shorterName)) / (double) longerNameLength;

	}

	// Example implementation of the Levenshtein Edit Distance
	// See http://rosettacode.org/wiki/Levenshtein_distance#Java
	//	or http://www.cis.uni-muenchen.de/~micha/praesentationen/rechtschreibkorrektur/Levenshtein.html

	private int editDistance(String longerName, String shorterName) {
		longerName = longerName.toLowerCase();
		shorterName = shorterName.toLowerCase();

		int[] costs = new int[shorterName.length() + 1];
		for (int i = 0; i <= longerName.length(); i++) {
			int previousValue = i;
			for (int j = 0; j <= shorterName.length(); j++) {
				if (i == 0) {
					costs[j] = j;
				} else {
					if (j > 0) {
						int newValue = costs[j - 1];
						if (longerName.charAt(i - 1) != shorterName.charAt(j - 1)) {
							newValue = Math.min(Math.min(newValue, previousValue),
									costs[j]) + 1;
						}
						costs[j - 1] = previousValue;
						previousValue = newValue;
					}
				}
			}
			if (i > 0) {
				costs[shorterName.length()] = previousValue;
			}
		}
		return costs[shorterName.length()];
	}

}
