package com.revature.eval.java.core;

import java.awt.image.ReplicateScaleFilter;
import java.time.temporal.*;
import java.util.*;

public class EvaluationService {
	
	private static final String ALPHABETIC = "[A-z]";
	private static final float ALPHABET_MIDDLE_UPPERCASE = ((float)('A' + 'Z') / 2.0f);
	static final float ALPHABET_MIDDLE_LOWERCASE = ((float)('a' + 'z') / 2.0f);
	private static final String ALPHANUMERIC = "\\w";
	private static final String CONSONANTS = "[bcdfghjklmnpqrstvwxyz]";
	private static final String DIGIT = "\\d";
	private static final int FIRST_PRIME = 2;
	private static final String NONALPHABETIC = "[^A-z]";
	private static final String NONALPHANUMERIC = "\\W";
	private static final String NONDIGIT = "\\D";
	private static final String NONWHITESPACE = "\\S";
	private static final int NUM_LETTERS_IN_ALPHABET = 26;
	private static final int ISBN_LENGTH = 10;
	private static final int PHONE_NUMBER_LENGTH = 10;
	private static final String PIG_LATIN_SOUND = "ay";
	private static final char US_CODE = '1';
	private static final String WHITESPACE = "\\s";

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		char[] reversed = new char[string.length()];
		for (int i = reversed.length - 1, j=0; i >= 0; i--, j++) {
			reversed[j] = string.charAt(i);
		}
		return new String(reversed);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		// TODO Write an implementation for this method declaration
		String acronym = "";
		boolean lookingForInitial = true;
		int phraseLength = phrase.length();
		for (int i = 0; i < phraseLength; ++i) {
			char c = phrase.charAt(i);
			boolean letter = Character.isLetter(c);
			if (lookingForInitial && letter) {
				lookingForInitial = false;
				c = Character.toUpperCase(c);
				acronym += c;
			} else if (!lookingForInitial && !letter) {
				lookingForInitial = true;
			}
		}
		return acronym;
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			// TODO Write an implementation for this method declaration
			return sidesOneAndTwoEqual() && sidesOneAndThreeEqual();
		}

		public boolean isIsosceles() {
			// TODO Write an implementation for this method declaration
			return sidesOneAndTwoEqual() || sidesOneAndThreeEqual() || sidesTwoAndThreeEqual();
		}

		public boolean isScalene() {
			// TODO Write an implementation for this method declaration
			return !isIsosceles();
		}
		
		private boolean sidesOneAndTwoEqual() {
			return getSideOne() == getSideTwo();
		}
		
		private boolean sidesOneAndThreeEqual() {
			return getSideOne() == getSideThree();
		}
		
		private boolean sidesTwoAndThreeEqual() {
			return getSideTwo() == getSideThree();
		}
	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		// TODO Write an implementation for this method declaration
		int score = 0;
		int stringLength = string.length();
		for (int i = 0; i < stringLength; ++i) {
			char c = string.charAt(i);
			c = Character.toLowerCase(c);
			score += getScrabbleLetterValue(c);
		}
		return score;
	}
	
	private HashMap<Character, Integer> scrabbleLetterValueMap = null;
	
	private int getScrabbleLetterValue(char letter) {
		return getScrabbleLetterValueMap().get(letter);
	}
	
	private Map<Character, Integer> getScrabbleLetterValueMap() {
		if (scrabbleLetterValueMap == null) {
			
			scrabbleLetterValueMap = new HashMap<>(NUM_LETTERS_IN_ALPHABET);
			scrabbleLetterValueMap.put('a', 1);
			scrabbleLetterValueMap.put('b', 3);
			scrabbleLetterValueMap.put('c', 3);
			scrabbleLetterValueMap.put('d', 2);
			scrabbleLetterValueMap.put('e', 1);
			scrabbleLetterValueMap.put('f', 4);
			scrabbleLetterValueMap.put('g', 2);
			scrabbleLetterValueMap.put('h', 4);
			scrabbleLetterValueMap.put('i', 1);
			scrabbleLetterValueMap.put('j', 8);
			scrabbleLetterValueMap.put('k', 5);
			scrabbleLetterValueMap.put('l', 1);
			scrabbleLetterValueMap.put('m', 3);
			scrabbleLetterValueMap.put('n', 1);
			scrabbleLetterValueMap.put('o', 1);
			scrabbleLetterValueMap.put('p', 3);
			scrabbleLetterValueMap.put('q', 10);
			scrabbleLetterValueMap.put('r', 1);
			scrabbleLetterValueMap.put('s', 1);
			scrabbleLetterValueMap.put('t', 1);
			scrabbleLetterValueMap.put('u', 1);
			scrabbleLetterValueMap.put('v', 4);
			scrabbleLetterValueMap.put('w', 4);
			scrabbleLetterValueMap.put('x', 8);
			scrabbleLetterValueMap.put('y', 4);
			scrabbleLetterValueMap.put('z', 10);
		}
		return scrabbleLetterValueMap;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		// TODO Write an implementation for this method declaration
		String cleanedNumber = removeNonNumbers(string);
		cleanedNumber = removeCountryCode(cleanedNumber);
		checkPhoneNumberValidity(cleanedNumber);
		return cleanedNumber;
	}
	
	private String removeCountryCode(String phoneNumber) {
		if (phoneNumber.charAt(0) == US_CODE) {
			return phoneNumber.substring(1);
		}
		return phoneNumber;
	}
	
	private void checkPhoneNumberValidity(String phoneNumber) {
		int pnLength = phoneNumber.length();
		if (pnLength != PHONE_NUMBER_LENGTH) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		// TODO Write an implementation for this method declaration
		String[] words = string.split(NONALPHABETIC);
		HashMap<String, Integer> wordCount = new HashMap<>();
		for (String word : words) {
			String cleanedWord = word.replaceAll(NONALPHABETIC, "");
			int wordLength = cleanedWord.length();
			if (wordLength > 0) {
				addWordToCount(cleanedWord, wordCount);
			}
		}
		return wordCount;
	}
	
	private void addWordToCount(String word, Map<String, Integer> count) {
		Integer val = count.get(word);
		if (val == null ) {
			val = 0;
		}
		++val;
		count.put(word, val);
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T extends Comparable<T>> {
		private List<T> sortedList;

		public int indexOf(T t) {
			int start = 0;
			int end = getSortedList().size() - 1;
			int index = indexOf(t, start, end);
			return index;
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}
		
		private int indexOf(T t, int start, int end) {
			List<T> sl = getSortedList();
			// If element not found.
			if (start > end) {
				return -1;
			}
			int middle = (start + end) / 2;
			T middleVal = sl.get(middle);
			int comparison = t.compareTo(middleVal);
			// If t is middle.
			if (comparison == 0) {
				return middle;
			}
			int newStart;
			int newEnd;
			// If t is less than middle.
			if (comparison < 0) {
				newStart = start;
				newEnd = middle - 1;
			}
			// If t is greater than middle.
			else {
				newStart = middle + 1;
				newEnd = end;
			}
			int index = indexOf(t, newStart, newEnd);
			return index;
		}
	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	public String toPigLatin(String string) {
		// TODO Write an implementation for this method declaration
		String newString = "";
		String[] splitString = string.split(WHITESPACE);
		for (String s : splitString) {
			s = moveFirstConsonant(s);
			s = addAy(s);
			newString += (s + " ");
		}
		newString = newString.stripTrailing();
		return newString;
	}
	
	private String moveFirstConsonant(String word) {
		String firstConsonant = getFirstConsonant(word);
		int startIndex = firstConsonant.length();
		int endIndex = word.length();
		String newWord = word.substring(startIndex, endIndex);
		newWord += firstConsonant;
		return newWord;
	}
	
	private String getFirstConsonant(String word) {
		String firstConsonant = "";
		int wordLength = word.length();
		for (int i = 0; i < wordLength; ++i) {
			char c = word.charAt(i);
			boolean isConsonantLetter = (CONSONANTS.indexOf(c) >= 0);
			if (isConsonantLetter) {
				firstConsonant += c;
				if (c == 'q') {
					firstConsonant += 'u';
					++i;
				}
			} else {
				break;
			}
		}
		return firstConsonant;
	}
	
	private String addAy(String word) {
		return word + PIG_LATIN_SOUND;
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		// TODO Write an implementation for this method declaration
		int[] digits = getDigits(input);
		int sum = getArmstrongSum(digits);
		boolean armstrongNumber = (input == sum);
		return armstrongNumber;
	}
	
	private int[] getDigits(int number) {
		int remainder = number;
		Stack<Integer> stack = new Stack<>();
		while (remainder > 0) {
			int nextDigit = remainder % 10;
			stack.push(nextDigit);
			remainder /= 10;
		}
		int numDigits = stack.size();
		int[] digits = new int[numDigits];
		for (int i = 0; i < numDigits; ++i) {
			int nextDigit = stack.pop();
			digits[i] = nextDigit;
		}
		return digits;
	}
	
	private int getArmstrongSum(int[] digits) {
		int numDigits = digits.length;
		int sum = 0;
		for (int i : digits) {
			sum += ((int) Math.pow(i, numDigits));
		}
		return sum;
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		// TODO Write an implementation for this method declaration
		List<Long> primeFactors = new ArrayList<>();
		long end = (long) Math.floor(Math.sqrt(l));
		long prod = l;
		for (long fact = FIRST_PRIME; fact <= end; ++fact) {
			while ((prod % fact) == 0) {
				primeFactors.add(fact);
				prod /= fact;
			}
		}
		int numPrimeFactors = primeFactors.size();
		boolean lIsPrime = (numPrimeFactors <= 0);
		if (lIsPrime) {
			primeFactors.add(l);
		}
		return primeFactors;
	}
	

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			// TODO Write an implementation for this method declaration
			int stringLength = string.length();
			char[] ciphertext = new char[stringLength];
			for (int i = 0; i < stringLength; ++i) {
				char c= string.charAt(i);
				char shiftedChar = c;
				boolean uppercase = Character.isUpperCase(c);
				boolean lowercase = Character.isLowerCase(c);
				if (uppercase || lowercase) {
					shiftedChar += key;
					if (uppercase) {
						shiftedChar -= 'A';
						shiftedChar %= NUM_LETTERS_IN_ALPHABET;
						shiftedChar += 'A';
					} else if (lowercase) {
						shiftedChar -= 'a';
						shiftedChar %= NUM_LETTERS_IN_ALPHABET;
						shiftedChar += 'a';
					}
				}
				ciphertext[i] = shiftedChar;
			}
			String newString = new String(ciphertext);
			return newString;
		}

	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	public int calculateNthPrime(int i) {
		// TODO Write an implementation for this method declaration
		if (i <= 0) {
			throw new IllegalArgumentException();
		}
		ArrayList<Integer> primes = new ArrayList<>(Arrays.asList(FIRST_PRIME));
		for (int j = (FIRST_PRIME + 1); primes.size() < i; ++j) {
			boolean divisible = false;
			for (int thisPrime : primes) {
				// If j is divisible.
				if ((j % thisPrime) == 0) {
					divisible = true;
					break;
				}
			}
			if (!divisible) {
				primes.add(j);
			}
		}
		int last = primes.size() - 1;
		int nthPrime = primes.get(last);
		return nthPrime;
	}
	
	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			// TODO Write an implementation for this method declaration
			String code = code(string);
			code = spaceText(code);
			return code;
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			// TODO Write an implementation for this method declaration
			return code(string);
		}
		
		private static String code(String string) {
			String newString = string.replaceAll(NONALPHANUMERIC, "");
			newString = substitute(newString);
			return newString;
		}
		
		private static String substitute(String string) {
			String newString = "";
			int stringLength = string.length();
			for (int i = 0; i < stringLength; ++i) {
				char c = string.charAt(i);
				boolean uppercase = Character.isUpperCase(c);
				boolean lowercase = Character.isLowerCase(c);
				if (uppercase || lowercase) {
					if (uppercase) {
						c -= 'A';
						c += 'a';
					}
					float offset = ((float)c - ALPHABET_MIDDLE_LOWERCASE);
					offset *= -1.0f;
					offset += ALPHABET_MIDDLE_LOWERCASE;
					c = (char)offset;
				}
				newString += c;
			}
			return newString;
		}
		
		private static String spaceText(String string) {
			StringBuilder sb = new StringBuilder(string);
			int counter = 5;
			for (int i = 0; i < sb.length(); ++i) {
				if (counter <= 0) {
					sb.insert(i, ' ');
					counter = 5;
				} else {
					--counter;
				}
			}
			String newString = sb.toString();
			return newString;
		}
	}
	
	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		// TODO Write an implementation for this method declaration
		String isbn = string.replaceAll("-", "");
		int sum = 0;
		for (int i = 0; i < ISBN_LENGTH; ++i) {
			char nextChar = isbn.charAt(i);
			int nextInt;
			boolean lastDigitX = ((i == (ISBN_LENGTH - 1)) && (nextChar == 'X'));
			if (lastDigitX) {
				nextInt = 10;
			} else {
				nextInt = charToInt(nextChar);
				boolean zeroThroughNine = ((nextInt >= 0) && (nextInt <= 9));
				if (!zeroThroughNine) {
					return false;
				}
			}
			sum += (nextInt * (ISBN_LENGTH - i));
		}
		sum %= (ISBN_LENGTH +1);
		boolean valid = (sum == 0);
		return valid;
	}
	
	private int charToInt(char c) {
		return Character.getNumericValue(c);
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		// TODO Write an implementation for this method declaration
		String lowercaseString = string.toLowerCase();
		Map<Character, Boolean> lettersUsed = createLettersUsedMap();
		char[] lowercaseStringCA = lowercaseString.toCharArray();
		// Iterating through string.
		for (char c : lowercaseStringCA) {
			lettersUsed.replace(c, true);
		}
		Collection<Boolean> mapValues = lettersUsed.values();
		for (Boolean used : mapValues) {
			if (used == false) {
				return false;
			}
		}
		return true;
	}
	
	private Map<Character, Boolean> createLettersUsedMap() {
		TreeMap<Character, Boolean> lettersUsed = new TreeMap<>();
		for (Character c = 'a'; c <= 'z'; ++c) {
			lettersUsed.put(c, false);
		}
		return lettersUsed;
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		// TODO Write an implementation for this method declaration
		Temporal gsDate = given.plus(GIGASECOND, ChronoUnit.SECONDS);
		return gsDate;
	}
	
	private static final long GIGASECOND = 1000000000;

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	public int getSumOfMultiples(int i, int[] set) {
		// TODO Write an implementation for this method declaration
		int sum = 0;
		Set<Integer> multiples = multiplesOfSet(i, set);
		for (int m : multiples) {
			sum += m;
		}
		return sum;
	}
	
	private Set<Integer> multiplesOfSet(int i, int[] set) {
		HashSet<Integer> multiples = new HashSet<>();
		for (int j = 0; j < i; ++j) {
			if (isDivisiblebySet(j, set)) {
				multiples.add(j);
			}
		}
		return multiples;
	}
	
	private boolean isDivisiblebySet(int dividend, int[] divisorSet) {
		for (int divisor : divisorSet) {
			if ((dividend % divisor) == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isLuhnValid(String string) {
		// TODO Write an implementation for this method declaration
		int luhnSum = luhnSum(string);
		boolean divisibleByTen = ((luhnSum % 10) == 0);
		return divisibleByTen;
	}
	
	private int doubleLuhnDigit(int digit) {
		int doubledDigit = digit * 2;
		doubledDigit %= 10;
		return doubledDigit;
	}
	
	private int luhnSum(String string) {
		ArrayList<Integer> adds = new ArrayList<>();
		String numericString = removeNonNumbers(string);
		for (int i = 0; i < numericString.length(); ++i) {
			int digit = charToInt(numericString.charAt(i));
			boolean evenIndex = ((i % 2) != 0);
			if (evenIndex) {
				digit = doubleLuhnDigit(digit);
			}
			adds.add(digit);
		}
		// Get sum.
		int sum = 0;
		for (int i : adds) {
			sum += i;
		}
		return sum;
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		// TODO Write an implementation for this method declaration
		Scanner scnr = new Scanner(string);
		int firstOperand = scnr.nextInt();
		int secondOperand = scnr.nextInt();
		scnr.close();
		if (string.contains("plus")) {
			return firstOperand + secondOperand;
		} else if (string.contains("minus")) {
			return firstOperand - secondOperand;
		} else if (string.contains("multiplied")) {
			return firstOperand * secondOperand;
		} else if (string.contains("divided")) {
			return firstOperand / secondOperand;
		}
		return 0;
	}

	private String removeNonNumbers(String string) {
		return string.replaceAll(NONDIGIT, "");
	}
}
